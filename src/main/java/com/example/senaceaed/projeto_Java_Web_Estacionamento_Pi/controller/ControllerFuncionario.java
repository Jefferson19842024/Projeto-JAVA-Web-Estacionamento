package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ControllerFuncionario {

    @Autowired
    FuncionarioService funcionarioService;

    @GetMapping("/tela-inicial")
    public String mostraIndex(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("css", tema);
        return "index";
    }

    @GetMapping("/cadastrarFuncionario")
    public String mostrarFormulario(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("css", tema);
        return "cadastrarFuncionario";
    }

    @PostMapping("/guardarFuncionario")
    public String guardarFuncionario(@ModelAttribute("funcionario") Funcionario funcionario) {
        if (funcionario.getId() != null) {
            funcionarioService.atualizar(funcionario.getId(), funcionario);
        } else {
            funcionarioService.criarFuncionario(funcionario);
        }
        return "redirect:/listagemFuncionario";
    }

    @GetMapping("/listagemFuncionario")
    public String mostraListagemFuncionario(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        List<Funcionario> funcionarios = funcionarioService.listarTodos();
        model.addAttribute("funcionarios", funcionarios);
        model.addAttribute("css", tema);
        return "listagemFuncionario";
    }

    @GetMapping("/exibir")
    public String mostraDetalhes(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model, @RequestParam Long id) {
        Funcionario funcionarioEncontrado = funcionarioService.buscarPorId(id);
        model.addAttribute("funcionario", funcionarioEncontrado);
        model.addAttribute("css", tema);
        return "detalhes";
    }


    @GetMapping("/alterarFuncionario")
    public String exibirFormularioAlteracao(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, @RequestParam("id") Long id, Model model) {
        Funcionario funcionario = funcionarioService.buscarPorId(id);
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("css", tema);
        return "cadastrarFuncionario";
    }

 
    @PostMapping("/excluir-funcionario")
    public String excluirFuncionario(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        funcionarioService.excluir(id);
        redirectAttributes.addFlashAttribute("success", "Funcionário excluído com sucesso!");
        return "redirect:/listagemFuncionario";
    }
}
