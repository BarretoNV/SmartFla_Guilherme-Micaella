package com.iff.devweb.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.LocalDate;

public class Aluno extends Usuario implements Serializable {

    @Column(nullable = false)
    private LocalDate dataMatricula;

    // id do treinador
    // id do treino
}
