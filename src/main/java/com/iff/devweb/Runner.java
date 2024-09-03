package com.iff.devweb;

import com.iff.devweb.entity.Aluno;
import com.iff.devweb.entity.Treinador;
import com.iff.devweb.entity.Usuario;
import com.iff.devweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Aluno aluno1 = new Aluno(1l, "Luiz Araujo", "la@gmail.com", LocalDate.parse("1996-06-09"), "2299999999", "Gavea, RJ", LocalDateTime.parse("2024-06-09T12:10"), LocalDate.parse("2024-06-09"), null);
        userRepository.save(aluno1);

        System.out.println("id: " + aluno1.getId() + " nome: " + aluno1.getNome() + " email: " + aluno1.getEmail() + " dataNasc: " + aluno1.getDataNasc() + " tel: " + aluno1.getTel() + " endereco: " + aluno1.getEndereco() + " dataCadastro: " + aluno1.getDataCadastro());

        Treinador treinador1 = new Treinador(1l, "Luiz Araujo", "la@gmail.com", LocalDate.parse("1996-06-09"), "2299999999", "Gavea, RJ", LocalDateTime.parse("2024-06-09T12:10"), "Cardio", "Profissional", LocalDate.parse("2024-12-12"), null);
        userRepository.save(treinador1);

        System.out.println("id: " + treinador1.getId() + " nome: " + treinador1.getNome() + " email: " + treinador1.getEmail() + " dataNasc: " + treinador1.getDataNasc() + " tel: " + treinador1.getTel() + " endereco: " + treinador1.getEndereco() + " dataCadastro: " + treinador1.getDataCadastro());

    }
}
