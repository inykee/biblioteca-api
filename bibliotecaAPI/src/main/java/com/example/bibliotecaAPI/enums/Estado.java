package com.example.bibliotecaAPI.enums;

public enum Estado {
    DISPONIVEL,
    RESERVADO,
    INDISPONIVEL;

    public boolean isDisponivel() {
        return this == DISPONIVEL;
    }
}