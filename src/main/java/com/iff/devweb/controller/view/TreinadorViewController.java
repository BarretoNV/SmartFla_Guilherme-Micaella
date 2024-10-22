package com.iff.devweb.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.iff.devweb.Service.TreinadorService;
import com.iff.devweb.entity.Treinador;

import java.util.Optional;

@Controller
public class TreinadorViewController {

    @Autowired
    private TreinadorService treinadorService;

    @GetMapping("/view/treinadores")
    public String listarTreinadores(Model model) {
        model.addAttribute("treinadores", treinadorService.listarTodosTreinadores());
        return "listarTreinadores"; // Nome do arquivo HTML para a listagem de treinadores
    }

    @GetMapping("/view/treinadores/novo")
    public String mostrarFormNovoTreinador() {
        return "adicionarTreinador"; // Nome do arquivo HTML para adicionar um novo treinador
    }

    @GetMapping("/view/treinadores/editar/{id}")
    public String mostrarFormEditarTreinador(@PathVariable Long id, Model model) {
        Optional<Treinador> treinador = treinadorService.buscarPorId(id);
        if (treinador.isPresent()) {
            model.addAttribute("treinador", treinador.get());
            return "editarTreinador"; // Nome do arquivo HTML para editar um treinador
        }
        return "redirect:/view/treinadores"; // Redireciona para a listagem se não encontrar o treinador
    }

    // Você pode adicionar métodos adicionais para deletar ou tratar outras requisições se necessário.
}
