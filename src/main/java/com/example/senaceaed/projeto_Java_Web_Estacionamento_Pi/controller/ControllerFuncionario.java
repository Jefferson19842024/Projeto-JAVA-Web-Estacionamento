package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.service.FuncionarioService; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerFuncionario {

    @Autowired
    private FuncionarioService funcionarioService;  
    
    @GetMapping("/tela-inicial")
    public String mostraIndex(Model model) {
               return "index";     }

    @PostMapping("/loginFuncionario")
    public String processarLoginFuncionario(@ModelAttribute Funcionario funcionario, Model model) {
        Funcionario f = funcionarioService.buscarPorId(funcionario.getId());  
        if (f != null && f.getNome().equals(funcionario.getNome())) { 
            model.addAttribute("nomeFuncionario", f.getNome());
            model.addAttribute("cargoFuncionario", f.getCargo());
            model.addAttribute("success", "Login realizado com sucesso!"); 
            return "index";  
        }
        model.addAttribute("error", "Funcionário não encontrado ou dados incorretos!");
        return "index";
    }

    @GetMapping("/cadastrarFuncionario")
    public String mostraCadastroFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cadastrarFuncionario";
    }

    @PostMapping("/guardarFuncionario")
    public String processarFormulario(Model model, @ModelAttribute Funcionario funcionario) {
        funcionarioService.salvarFuncionario(funcionario); 
        return "redirect:/listagemFuncionario";
    }
    
    @GetMapping("/listagemFuncionario")
    public String mostraListagemFuncionario(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "listagemFuncionario";
    }
}