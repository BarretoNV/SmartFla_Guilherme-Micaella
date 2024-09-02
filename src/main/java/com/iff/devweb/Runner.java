package com.iff.devweb;

import com.iff.devweb.entity.Usuario;
import com.iff.devweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario1 = new Usuario(1l, "Luiz Araujo", "la@gmail.com", LocalDate.parse("1996-06-09"), "2299999999", "Gavea, RJ", LocalDate.parse("2024-06-09"));
        userRepository.save(usuario1);

        System.out.println("id: " + usuario1.getId() + " nome: " + usuario1.getNome() + " email: " + usuario1.getEmail() + " dataNasc: " + usuario1.getDataNasc() + " tel: " + usuario1.getTel() + " endereco: " + usuario1.getEndereco() + " dataCadastro: " + usuario1.getDataCadastro());

    }
}
