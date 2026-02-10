package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.models.Emprestimo;
import com.example.bibliotecaAPI.repositories.ClienteRepository;
import com.example.bibliotecaAPI.repositories.ExemplarRepository;

import java.time.LocalDate;

public record EmprestimoRequestDto(
        Integer idExemplar,
        Integer idCliente,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {

    public Emprestimo toEmprestimo(
            Emprestimo emprestimo,
            ExemplarRepository exemplarRepository,
            ClienteRepository clienteRepository
    ) {
        emprestimo.setExemplar(exemplarRepository.getReferenceById(this.idExemplar()));
        emprestimo.setCliente(clienteRepository.getReferenceById(this.idCliente()));
        emprestimo.setDataEmprestimo(this.dataEmprestimo());
        emprestimo.setDataDevolucao(this.dataDevolucao());

        return emprestimo;
    }
}