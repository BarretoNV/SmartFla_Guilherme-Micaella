package com.iff.devweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name = "Treinos")
public class Treino implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nomeTreino;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer duracao;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalDate horario;

    @Column(nullable = false)
    private String tipoTreino;

    @Column(nullable = false)
    private String nivelDificuldade;

    @Column(nullable = false)
    private String observacoes;

    @Column(nullable = false)
    private String feedbackTreinador;

    @Column(nullable = false)
    private String feedbackAluno;
}
