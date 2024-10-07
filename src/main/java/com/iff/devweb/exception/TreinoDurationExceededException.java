package com.iff.devweb.exception;

public class TreinoDurationExceededException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String duracao;

    public TreinoDurationExceededException(String duracao) {
        super("Duração do treino excedeu o limite de 8 horas: " + duracao);
        this.duracao = duracao;
    }

    public String getDuracao() {
        return duracao;
    }
}
