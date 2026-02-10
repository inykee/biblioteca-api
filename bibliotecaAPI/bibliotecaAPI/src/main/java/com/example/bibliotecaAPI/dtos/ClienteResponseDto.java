package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Situacao;
import com.example.bibliotecaAPI.models.Cliente;

public record ClienteResponseDto(
        Integer idCliente,
        String nomeCompleto,
        String telefone,
        String email,
        Situacao situacao
) {

    public static ClienteResponseDto toDto(Cliente cliente) {
        return new ClienteResponseDto(
                cliente.getIdCliente(),
                cliente.getNomeCompleto(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getSituacao()
        );
    }
}