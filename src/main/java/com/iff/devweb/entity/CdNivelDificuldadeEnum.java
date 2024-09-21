package com.iff.devweb.entity;

public enum CdNivelDificuldadeEnum {
    Facil(1),
    Intermediario(2),
    Avancado(3),
    Intenso(4),
    Extremo(5);

    private final int value;

    CdNivelDificuldadeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CdNivelDificuldadeEnum fromValue(int value) {
        for (CdNivelDificuldadeEnum nivel : CdNivelDificuldadeEnum.values()) {
            if (nivel.getValue() == value) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}