package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.model.Funcionario;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerFuncionario {

    private List<Funcionario> listaFuncionario = new ArrayList();

    @GetMapping("/tela-inicial")
    public String mostraIndex() {
        return "index";
    }

    @GetMapping("/cadastrarFuncionario")
    public String mostraCadastroFuncionario(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        return "cadastrarFuncionario";
    }

    @PostMapping("/guardarFuncionario")
    public String processarFormulario(Model model, @ModelAttribute Funcionario funcionario) {
        funcionario.setId(listaFuncionario.size() + 1);
        listaFuncionario.add(funcionario);
        return "redirect:/listagemFuncionario";
    }

    @GetMapping("/listagemFuncionario")
    public String mostraListagemFuncionario(Model model) {
        model.addAttribute("funcionarios", listaFuncionario);
        return "listagemFuncionario";
    }

    
    
    /* @PostMapping("/preferencias")
    public String gravaPreferencias(@ModelAttribute Preferencia pref, HttpServletResponse response) {
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setHttpOnly(true);
        cookiePrefEstilo.setMaxAge(86400);
        response.addCookie(cookiePrefEstilo);
        return "redirect:/tela-inicial";
    }
  
   

   
    @GetMapping("/listagemFilmes")
    public String mostraListagemFilmes(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model) {
        List<Filme> filmes = filmeService.listarTodos();
        Map<Integer, List<Analise>> analisesMap = new HashMap<>();
        for (Filme filme : filmes) {
            List<Analise> analises = analiseService.buscarPorIdFilmeId(filme.getId());
            analisesMap.put(filme.getId(), analises);
        }
        model.addAttribute("Filmes", filmes);
        model.addAttribute("analisesMap", analisesMap);
        model.addAttribute("css", tema);
        return "listagemFilmes";
    }

   
    @GetMapping("/exibir")
    public String mostraDetalhes(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, Model model, @RequestParam String id) {
        Integer idFilme = Integer.parseInt(id);
        Filme filmeEncontrado = filmeService.buscarPorId(idFilme);
        List<Analise> analiseEncontrada = analiseService.buscarPorIdFilmeId(idFilme);
        model.addAttribute("filme", filmeEncontrado);
        model.addAttribute("analise", analiseEncontrada);
        model.addAttribute("analise", new Analise());
        model.addAttribute("css", tema);
        return "detalhes";
    }

  
    @GetMapping("/alterar-filme")
    public String exibirFormularioAlteracao(@CookieValue(name = "pref-estilo", defaultValue = "claro") String tema, @RequestParam("id") Integer id, Model model) {
        Filme filme = filmeService.buscarPorId(id);
        model.addAttribute("filme", filme);
        model.addAttribute("css", tema);
        return "cadastrarFilme";
    }
    
    @PostMapping("/excluir-filme")
    public String excluirFilme(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        List<Analise> analises = analiseService.buscarPorIdFilmeId(id);
        if (!analises.isEmpty()) {
            redirectAttributes.addFlashAttribute("erroMensagem", "Não é possível excluir o filme. Primeiro, exclua as análises associadas.");
            return "redirect:/listagemFilmes";
        }
        filmeService.excluir(id);
        return "redirect:/listagemFilmes";
    }
} */
}
