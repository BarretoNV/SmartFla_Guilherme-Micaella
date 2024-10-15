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

    // Método para mostrar todos os treinos
    @GetMapping
    public String showAllTreinos(Model model) {
        model.addAttribute("treinos", treinoService.listarTodosTreinos());
        return "treinosList";  // Nome do arquivo HTML para listar os treinos
    }

    // Método para mostrar o formulário de adição de novo treino
    @GetMapping("/new")
    public String showNewTreinoForm(Model model) {
        model.addAttribute("treino", new Treino());
        return "newTreino";  // Nome do arquivo HTML do formulário para novos treinos
    }

    // Método para salvar um novo treino
    @PostMapping
    public String saveTreino(@ModelAttribute Treino treino) {
        treinoService.salvarTreino(treino);
        return "redirect:/view/treinos";  // Redireciona para a lista de treinos após salvar
    }

    // Método para mostrar o formulário de edição de treino existente
    @GetMapping("/edit/{id}")
    public String showEditTreinoForm(@PathVariable Long id, Model model) {
        Treino treino = treinoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de treino inválido: " + id));
        model.addAttribute("treino", treino);
        return "editTreino";
    }

    // Método para atualizar um treino existente
    @PostMapping("/update/{id}")
    public String updateTreino(@PathVariable Long id, @ModelAttribute Treino treino) {
        treino.setId(id);
        treinoService.salvarTreino(treino);
        return "redirect:/view/treinos";
    }

    // Método para deletar um treino
    @GetMapping("/delete/{id}")
    public String deleteTreino(@PathVariable Long id) {
        treinoService.deletarTreino(id);
        return "redirect:/view/treinos";
    }
}
