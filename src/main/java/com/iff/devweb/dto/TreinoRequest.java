package com.iff.devweb.dto;

import java.time.LocalDate;

public class TreinoRequest {

    private String nomeTreino;
    private String descricao;
    private String duracao;
    private LocalDate data;
    private String nivelDificuldade;

    public TreinoRequest(String nomeTreino, String descricao, String duracao, LocalDate data, String nivelDificuldade) {
        this.nomeTreino = nomeTreino;
        this.descricao = descricao;
        this.duracao = duracao;
        this.data = data;
        this.nivelDificuldade = nivelDificuldade;
    }

    public String getNomeTreino() {
        return nomeTreino;
    }

    public void setNomeTreino(String nomeTreino) {
        this.nomeTreino = nomeTreino;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(String nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }
}
