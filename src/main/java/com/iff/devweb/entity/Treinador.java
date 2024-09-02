package com.iff.devweb.entity;

import jakarta.persistence.Column;

import java.io.Serializable;
import java.time.LocalDate;

public class Treinador extends Usuario implements Serializable {

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false)
    private String registroProf;

    @Column(nullable = false)
    private LocalDate dataContratacao;
}
