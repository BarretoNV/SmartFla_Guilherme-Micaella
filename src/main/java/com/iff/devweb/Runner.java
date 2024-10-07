package com.iff.devweb;

import com.iff.devweb.Service.TreinoService;
import com.iff.devweb.entity.Aluno;
import com.iff.devweb.entity.CdNivelDificuldadeEnum;
import com.iff.devweb.entity.CdTipoTreinoEnum;
import com.iff.devweb.entity.Treino;
import com.iff.devweb.exception.TreinoDurationExceededException;
import com.iff.devweb.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    TreinoService treinoService;

    @Autowired
    AlunoRepository alunoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Criando Treino 1 - com validação de duração
        Treino treino1 = new Treino(
                null,
                "Treino de Força",
                "Treino intenso de força para musculação",
                "08:30", // Duracao que extrapola o limite de 8 horas
                LocalDate.now(),
                LocalDate.now(),
                CdTipoTreinoEnum.Forca,
                CdNivelDificuldadeEnum.Avancado,
                "Treino com alta carga",
                "Excelente desempenho",
                "Desafiador, mas recompensador!",
                null
        );

        // Tenta salvar o treino1 e captura a exceção
        try {
            treinoService.validarDuracaoTreino(treino1);
            treinoService.salvarTreino(treino1);
            System.out.println("Treino salvo: " + treino1.getNomeTreino());
        } catch (TreinoDurationExceededException e) {
            System.out.println("Erro ao salvar treino1: " + e.getMessage());
        }

        // Criando Treino 2 - Duração válida
        Treino treino2 = new Treino(
                null,
                "Treino de Resistência",
                "Treino voltado para resistência cardiovascular",
                "04:25", // Duração dentro do limite
                LocalDate.now(),
                LocalDate.now(),
                CdTipoTreinoEnum.Resistencia,
                CdNivelDificuldadeEnum.Intermediario,
                "Treino com foco em resistência",
                "Bom desenvolvimento",
                "Treino adequado ao nível.",
                null
        );

        // Salva treino2
        try {
            treinoService.validarDuracaoTreino(treino2);
            treinoService.salvarTreino(treino2);
            System.out.println("Treino salvo: " + treino2.getNomeTreino());
        } catch (TreinoDurationExceededException e) {
            System.out.println("Erro ao salvar treino2: " + e.getMessage());
        }

        // Criando Alunos e associando ao treino
        Aluno aluno1 = new Aluno(2L, "Luiz Araujo", "luiz@gmail.com", LocalDate.parse("1996-06-09"),
                "(22) 99999-9999", "Gavea, RJ", LocalDateTime.now(), LocalDate.now(), null, treino2);

        Aluno aluno2 = new Aluno(3L, "Maria Silva", "maria@gmail.com", LocalDate.parse("1995-04-15"),
                "(22) 99999-9999", "Centro, RJ", LocalDateTime.now(), LocalDate.now(), null, treino2);

        Aluno aluno3 = new Aluno(4L, "João Souza", "joao@gmail.com", LocalDate.parse("1998-01-20"),
                "(22) 98888-8888", "Copacabana, RJ", LocalDateTime.now(), LocalDate.now(), null, treino2);

        // Salvando alunos
        alunoRepository.save(aluno1);
        alunoRepository.save(aluno2);
        alunoRepository.save(aluno3);

        System.out.println("Aluno salvo: " + aluno1.getNome());
        System.out.println("Aluno salvo: " + aluno2.getNome());
        System.out.println("Aluno salvo: " + aluno3.getNome());
    }
}
