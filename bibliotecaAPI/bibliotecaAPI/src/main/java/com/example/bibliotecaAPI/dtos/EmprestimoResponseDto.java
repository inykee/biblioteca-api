package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Categoria;
import com.example.bibliotecaAPI.enums.Estado;
import com.example.bibliotecaAPI.models.Emprestimo;

import java.time.LocalDate;

public record EmprestimoResponseDto(
        Integer idEmprestimo,

        /* ------------------ Cliente ------------------ */
        String nomeCompleto,

        /* ------------------ Livro ------------------ */
        String titulo,
        String autor,
        Integer anoPublicacao,
        Categoria categoria,

        /* ------------------ Exemplar ------------------ */
        Integer referencia,
        Estado estado,

        /* ------------------ Empréstimo ------------------ */
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {

    public static EmprestimoResponseDto toDto(Emprestimo emprestimo) {
        return new EmprestimoResponseDto(
                emprestimo.getIdEmprestimo(),

                /* Cliente */
                emprestimo.getCliente().getNomeCompleto(),

                /* Livro */
                emprestimo.getExemplar().getLivro().getTitulo(),
                emprestimo.getExemplar().getLivro().getAutor(),
                emprestimo.getExemplar().getLivro().getAnoPublicacao(),
                emprestimo.getExemplar().getLivro().getCategoria(),

                /* Exemplar */
                emprestimo.getExemplar().getReferencia(),
                emprestimo.getExemplar().getEstado(),

                /* Empréstimo */
                emprestimo.getDataEmprestimo(),
                emprestimo.getDataDevolucao()
        );
    }
}