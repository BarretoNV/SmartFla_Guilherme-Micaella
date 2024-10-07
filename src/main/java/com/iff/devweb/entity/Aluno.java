package com.iff.devweb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("1")
public class Aluno extends Usuario implements Serializable {

    @PastOrPresent
    @NotNull(message = "Campo obrigatório")
    @Column
    private LocalDate dataMatricula;

    @ManyToOne
    @JoinColumn(name = "treinador_id")
    private Treinador treinador;

    @ManyToOne
    @JoinColumn(name = "treino_id")
    private Treino treino;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String email, LocalDate dataNasc, String tel, String endereco, LocalDateTime dataCadastro, LocalDate dataMatricula, Treinador treinador, Treino treino) {
        super(id, nome, email, dataNasc, tel, endereco, dataCadastro);
        this.treino = treino;
        this.dataMatricula = dataMatricula;
        this.treinador = treinador;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public Treinador getTreinador() {
        return treinador;
    }

    public void setTreinador(Treinador treinador) {
        this.treinador = treinador;
    }
}
