package com.iff.devweb;

import com.iff.devweb.entity.Aluno;
import com.iff.devweb.entity.Treino;
import com.iff.devweb.repository.AlunoRepository;
import com.iff.devweb.repository.TreinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    TreinoRepository treinoRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public void run(String... args) throws Exception {

        Treino treino1 = new Treino(
                null,
                "Treino de Força",               // nomeTreino
                "Treino intenso de força para musculação", // descricao
                90,                              // duracao
                LocalDate.now(),                 // data
                LocalDate.now(),                 // horario
                "Força",                         // tipoTreino
                "Avançado",                      // nivelDificuldade
                "Treino com alta carga",         // observacoes
                "Excelente desempenho",          // feedbackTreinador
                "Desafiador, mas recompensador!",// feedbackAluno
                null                             // alunos (pode ser null ou uma lista vazia)
        );

        treinoRepository.save(treino1);

        Treino treino2 = new Treino(
                null,
                "Treino de Resistência",          // nomeTreino
                "Treino voltado para resistência cardiovascular", // descricao
                60,                              // duracao
                LocalDate.now(),                 // data
                LocalDate.now(),                 // horario
                "Resistência",                   // tipoTreino
                "Intermediário",                 // nivelDificuldade
                "Treino com foco em resistência",// observacoes
                "Bom desenvolvimento",           // feedbackTreinador
                "Treino adequado ao nível.",     // feedbackAluno
                null                             // alunos (pode ser null ou uma lista vazia)
        );

        treinoRepository.save(treino2);

        Aluno aluno1 = new Aluno(2L, "Luiz Araujo", "luiz@gmail.com", LocalDate.parse("1996-06-09"),
                "2299999999", "Gavea, RJ", LocalDateTime.now(), LocalDate.now(), null, treino1);

        Aluno aluno2 = new Aluno(3L, "Maria Silva", "maria@gmail.com", LocalDate.parse("1995-04-15"),
                "2199999999", "Centro, RJ", LocalDateTime.now(), LocalDate.now(), null, treino1);

        Aluno aluno3 = new Aluno(4L, "João Souza", "joao@gmail.com", LocalDate.parse("1998-01-20"),
                "2298888888", "Copacabana, RJ", LocalDateTime.now(), LocalDate.now(), null, treino2);

        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);
        alunoRepository.save(aluno3);

        System.out.println("Treino salvo: " + treino1.getNomeTreino() + " - Tipo: " + treino1.getTipoTreino());
        System.out.println("Aluno salvo: " + aluno1.getNome());
        System.out.println("Aluno salvo: " + aluno2.getNome());

        System.out.println("Treino salvo: " + treino2.getNomeTreino() + " - Tipo: " + treino2.getTipoTreino());
        System.out.println("Aluno salvo: " + aluno3.getNome());
    }
}
