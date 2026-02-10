package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Situacao;
import com.example.bibliotecaAPI.models.Cliente;

public record ClienteRequestDto(
        String nomeCompleto,
        String cpf,
        String telefone,
        String email,
        Situacao situacao
) {

    public Cliente toCliente(Cliente cliente) {
        cliente.setNomeCompleto(this.nomeCompleto());
        cliente.setCpf(this.cpf());
        cliente.setTelefone(this.telefone());
        cliente.setEmail(this.email());
        cliente.setSituacao(this.situacao());
        return cliente;
    }
}