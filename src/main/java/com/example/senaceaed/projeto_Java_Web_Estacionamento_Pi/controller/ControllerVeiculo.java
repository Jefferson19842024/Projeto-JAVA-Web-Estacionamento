package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Preferencia;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Veiculo;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service.FuncionarioService;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service.VeiculoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
public class ControllerVeiculo {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/cadastrarVeiculo")
    public String mostraCadastroVeiculo(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("css", tema);
        model.addAttribute("veiculo", new Veiculo());
        return "cadastrarVeiculo";
    }
    
      @PostMapping("/preferencias")
    public String gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(86400);
        response.addCookie(cookiePrefEstilo);
        return "redirect:/tela-inicial";
    }

   @PostMapping("/guardarVeiculo")
public String guardarVeiculo(@ModelAttribute("veiculo") Veiculo veiculo, RedirectAttributes redirectAttributes) {
    try {

        if (veiculo.getDataRegistro() == null) {
            veiculo.setDataRegistro(LocalDate.now()); 
        }

        if (veiculo.getId() != null) {
            veiculoService.atualizarVeiculo(veiculo.getId(), veiculo);
            redirectAttributes.addFlashAttribute("success", "Veículo atualizado com sucesso!");
        } else {
            veiculoService.salvarVeiculo(veiculo);
            redirectAttributes.addFlashAttribute("success", "Veículo cadastrado com sucesso!");
        }
    } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/listagemVeiculo";
}


    @GetMapping("/listagemVeiculo")
    public String listarVeiculos(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        List<Veiculo> veiculos = veiculoService.listarVeiculos();
        model.addAttribute("veiculos", veiculos);
        model.addAttribute("css", tema);
        return "listagemVeiculo";
    }

    @GetMapping("/exibirVeiculo")
    public String mostraDetalhesVeiculo(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model, @RequestParam Long id) {
        Veiculo veiculoEncontrado = veiculoService.buscarPorId(id);
        if (veiculoEncontrado == null) {
            model.addAttribute("error", "Veículo não encontrado");
            return "listagemVeiculo";
        }
        model.addAttribute("veiculo", veiculoEncontrado);
        model.addAttribute("css", tema);
        return "detalhes";
    }

    @PostMapping("/registrarSaidaVeiculo")
    public String processarSaida(@RequestParam("id") Long id,
                                 @RequestParam("dataSaida") String dataSaida,
                                 @RequestParam("valor") String valor,
                                 @RequestParam("tipoPagamento") String tipoPagamento,
                                 Model model, RedirectAttributes redirectAttributes) {
        try {
            Veiculo veiculo = veiculoService.buscarPorId(id);
            if (veiculo == null) {
                redirectAttributes.addFlashAttribute("error", "Veículo não encontrado");
                return "redirect:/listagemVeiculo";
            }

            if (veiculo.getDataSaida() != null) {
                redirectAttributes.addFlashAttribute("error", "Saída já registrada para este veículo");
                return "redirect:/listagemVeiculo";
            }

            veiculo.setDataSaida(LocalDate.parse(dataSaida));
            veiculo.setValor(new BigDecimal(valor));
            veiculo.setTipoPagamento(tipoPagamento);
            veiculoService.atualizarVeiculo(id, veiculo);

            redirectAttributes.addFlashAttribute("success", "Saída registrada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao registrar saída: " + e.getMessage());
        }

        return "redirect:/listagemVeiculo";
    }

    @GetMapping("/alterarVeiculo")
    public String exibirFormularioAlteracao(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema,
                                            @RequestParam("id") Long id, Model model) {
        Veiculo veiculo = veiculoService.buscarPorId(id);
        if (veiculo == null) {
            model.addAttribute("error", "Veículo não encontrado");
            return "redirect:/listagemVeiculo";
        }
        model.addAttribute("veiculo", veiculo);
        model.addAttribute("css", tema);
        return "cadastrarVeiculo";
    }

    @PostMapping("/excluir-veiculo")
    public String excluirVeiculo(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        try {
            veiculoService.excluirVeiculo(id);
            redirectAttributes.addFlashAttribute("success", "Veículo excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao excluir veículo: " + e.getMessage());
        }
        return "redirect:/listagemVeiculo";
    }
}
