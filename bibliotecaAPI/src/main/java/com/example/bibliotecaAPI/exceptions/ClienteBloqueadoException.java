package com.example.bibliotecaAPI.exceptions;

public class ClienteBloqueadoException extends RuntimeException {

    private Integer numeroRegraNegocio;

    public ClienteBloqueadoException(String message, Integer numeroRegraNegocio) {
        super(message);
        this.numeroRegraNegocio = numeroRegraNegocio;
    }

    public Integer getNumeroRegraNegocio() {
        return numeroRegraNegocio;
    }

    public void setNumeroRegraNegocio(Integer numeroRegraNegocio) {
        this.numeroRegraNegocio = numeroRegraNegocio;
    }
}