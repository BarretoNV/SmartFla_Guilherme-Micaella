package com.iff.devweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("1")
public class Aluno extends Usuario implements Serializable {

    @Column
    private LocalDate dataMatricula;

    @OneToOne
    private Treinador treinador;

    public Aluno() {
    }

    public Aluno(Long id, String nome, String email, LocalDate dataNasc, String tel, String endereco, LocalDateTime dataCadastro, LocalDate dataMatricula, Treinador treinador) {
        super(id, nome, email, dataNasc, tel, endereco, dataCadastro);
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
