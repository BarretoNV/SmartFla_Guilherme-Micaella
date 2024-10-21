package com.iff.devweb.controller.view;

import com.iff.devweb.Service.AlunoService;
import com.iff.devweb.entity.Aluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/view/alunos")
public class AlunosViewController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public String listarAlunos(Model model) {
        List<Aluno> alunos = alunoService.listarTodosAlunos();
        model.addAttribute("alunos", alunos);
        return "listarAlunos";
    }

    @GetMapping("/novo")
    public String novoAluno(Model model) {
        model.addAttribute("aluno", new Aluno());
        return "novoAluno";
    }

    @PostMapping("/salvar")
    public String salvarAluno(Aluno aluno) {
        alunoService.salvarAluno(aluno);
        return "redirect:/view/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model) {
        Aluno aluno = alunoService.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno inválido com ID: " + id));
        model.addAttribute("aluno", aluno);
        return "editarAluno";
    }

    @GetMapping("/deletar/{id}")
    public String deletarAluno(@PathVariable Long id) {
        alunoService.deletarAluno(id);
        return "redirect:/view/alunos";
    }
}
