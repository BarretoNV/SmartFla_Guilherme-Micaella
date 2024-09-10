package com.iff.devweb.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
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

    @OneToMany(mappedBy = "treino")
    private List<Aluno> alunos;

    public Treino () {}

    public Treino(Long id, String nomeTreino, String descricao, Integer duracao, LocalDate data, LocalDate horario, String tipoTreino, String nivelDificuldade, String observacoes, String feedbackTreinador, String feedbackAluno, List<Aluno> alunos) {
        this.id = id;
        this.nomeTreino = nomeTreino;
        this.descricao = descricao;
        this.duracao = duracao;
        this.data = data;
        this.horario = horario;
        this.tipoTreino = tipoTreino;
        this.nivelDificuldade = nivelDificuldade;
        this.observacoes = observacoes;
        this.feedbackTreinador = feedbackTreinador;
        this.feedbackAluno = feedbackAluno;
        this.alunos = alunos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getHorario() {
        return horario;
    }

    public void setHorario(LocalDate horario) {
        this.horario = horario;
    }

    public String getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(String tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public String getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(String nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getFeedbackTreinador() {
        return feedbackTreinador;
    }

    public void setFeedbackTreinador(String feedbackTreinador) {
        this.feedbackTreinador = feedbackTreinador;
    }

    public String getFeedbackAluno() {
        return feedbackAluno;
    }

    public void setFeedbackAluno(String feedbackAluno) {
        this.feedbackAluno = feedbackAluno;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
}
