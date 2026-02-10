package com.example.bibliotecaAPI.enums;

public enum Situacao {
    ATIVO,
    BLOQUEADO;

    public boolean isAtivo() {
        return this == ATIVO;
    }
}