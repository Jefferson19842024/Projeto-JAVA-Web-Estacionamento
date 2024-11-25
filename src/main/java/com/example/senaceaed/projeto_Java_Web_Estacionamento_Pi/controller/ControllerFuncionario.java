package com.example.senaceaed.projeto_Java_Web_Estacionamento_Pi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControllerFuncionario {
    
  

    @GetMapping("/tela-inicial")
    public String mostraIndex() {
        return "index";
    }
    
     @GetMapping("/cadastrarFuncionario")
    public String mostraCadastroFuncionario() {
        return "cadastrarFuncionario";
    }

    
     @GetMapping("/listagemFuncionario")
    public String mostraListagemFuncionario() {
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
  
    @PostMapping("/guardar-Filme")
    public String processarFormulario(Model model, @ModelAttribute Filme filme, RedirectAttributes redirectAttributes) {
        if (filme.getId() != null) {
            filmeService.atualizar(filme.getId(), filme);
        } else {
            filmeService.criarFilme(filme);
        }
        redirectAttributes.addFlashAttribute("Filmes", filmeService.listarTodos());
        return "redirect:/listagemFilmes";
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
