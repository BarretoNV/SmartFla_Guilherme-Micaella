package com.iff.devweb.entity;

public enum CdTipoTreinoEnum {
    Forca(1),
    Aerobico(2),
    Funcional(3),
    Resistencia(4),
    HIIT(5),
    Flexibilidade(6),
    Pilates(7),
    Musculacao(8),
    CrossFit(9),
    Mobilidade(10),
    Core(11),
    Cardiorrespiratorio(12),
    Potencia(13),
    Reabilitacao(14),
    Yoga(15);

    private final int value;

    CdTipoTreinoEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static CdTipoTreinoEnum fromValue(int value) {
        for (CdTipoTreinoEnum tipo : CdTipoTreinoEnum.values()) {
            if (tipo.getValue() == value) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Valor inválido: " + value);
    }
}
