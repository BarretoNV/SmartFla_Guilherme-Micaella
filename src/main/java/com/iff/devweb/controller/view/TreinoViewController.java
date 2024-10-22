package com.iff.devweb.controller.view;

import com.iff.devweb.entity.Treino;
import com.iff.devweb.Service.TreinoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view/treinos")
public class TreinoViewController {

    @Autowired
    private TreinoService treinoService;

    // Método para listar todos os treinos
    @GetMapping
    public String showTreinos(Model model) {
        model.addAttribute("treinos", treinoService.listarTodosTreinos());
        return "listarTreinos";
    }

    // Método para exibir o formulário de novo treino
    @GetMapping("/novo")
    public String showNewTreinoForm(Model model) {
        model.addAttribute("treino", new Treino());
        return "novoTreino";
    }

    // Método para salvar um novo treino
    @PostMapping
    public String saveTreino(@ModelAttribute Treino treino) {
        treinoService.salvarTreino(treino);
        return "redirect:/view/treinos";
    }

    // Método para exibir o formulário de edição de treino
    @GetMapping("/editar/{id}")
    public String showEditTreinoForm(@PathVariable Long id, Model model) {
        Treino treino = treinoService.buscarPorId(id).orElseThrow(() -> new IllegalArgumentException("ID inválido:" + id));
        model.addAttribute("treino", treino);
        return "editarTreino";
    }

    // Método para atualizar um treino
    @PostMapping("/editar/{id}")
    public String updateTreino(@PathVariable Long id, @ModelAttribute Treino treino) {
        treino.setId(id);
        treinoService.salvarTreino(treino);
        return "redirect:/view/treinos";
    }

    // Método para deletar um treino
    @GetMapping("/deletar/{id}")
    public String deleteTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return "redirect:/view/treinos";
    }
}
