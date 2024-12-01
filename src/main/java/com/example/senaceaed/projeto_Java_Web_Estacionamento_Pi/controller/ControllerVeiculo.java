package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Veiculo;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service.VeiculoService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ControllerVeiculo {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/cadastrarVeiculo")
    public String mostraCadastroVeiculo(Model model) {
        model.addAttribute("veiculo", new Veiculo());
        return "cadastrarVeiculo";
    }

    @PostMapping("/guardarVeiculo")
    public String processarFormularioCadastroVeiculo(@ModelAttribute Veiculo veiculo, Model model) {
        LocalDate hoje = LocalDate.now();
        veiculo.setDataRegistro(hoje);
        veiculoService.salvarVeiculo(veiculo);
        model.addAttribute("success", "Veículo cadastrado com sucesso!");
        return "redirect:/listagemVeiculo";
    }

    @GetMapping("/listagemVeiculo")
    public String listarVeiculos(Model model) {
        model.addAttribute("veiculos", veiculoService.listarVeiculos());
        return "listagemVeiculo";
    }

    @PostMapping("/registrarSaidaVeiculo")
    public String processarSaida(@RequestParam("id") Integer id,
    @RequestParam("dataSaida") String dataSaida,
    @RequestParam("valor") String valor,
    @RequestParam("tipoPagamento") String tipoPagamento,
    Model model) {
        Veiculo veiculo = veiculoService.buscarPorId(id);

        if (veiculo == null) {
            model.addAttribute("error", "Veículo não encontrado");
            return "listagemVeiculo";
        }

        // Verificar se já existe uma saída registrada
        if (veiculo.getDataSaida() != null) {
            model.addAttribute("error", "Saída já registrada para este veículo");
            return "listagemVeiculo";
        }

        veiculo.setDataSaida(LocalDate.parse(dataSaida)); // Atribuindo data de saída
        veiculo.setValor(new BigDecimal(valor)); // Atribuindo valor
        veiculo.setTipoPagamento(tipoPagamento); // Atribuindo tipo de pagamento

        veiculoService.atualizarVeiculo(veiculo); // Atualizando o veículo no serviço

        model.addAttribute("success", "Saída registrada com sucesso!");
        return "redirect:/listagemVeiculo"; // Redireciona de volta para a listagem de veículos
    }

}
