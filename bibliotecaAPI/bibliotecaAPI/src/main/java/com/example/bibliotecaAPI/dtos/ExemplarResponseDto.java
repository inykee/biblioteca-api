package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Categoria;
import com.example.bibliotecaAPI.enums.Estado;
import com.example.bibliotecaAPI.models.Exemplar;

public record ExemplarResponseDto(
        Integer idExemplar,

        /* ------------------ Livro ------------------ */
        String titulo,
        String autor,
        Integer anoPublicacao,
        Categoria categoria,

        /* ------------------ Exemplar ------------------ */
        Integer referencia,
        Estado estado
) {

    public static ExemplarResponseDto toDto(Exemplar exemplar) {
        return new ExemplarResponseDto(
                exemplar.getIdExemplar(),

                /* Livro */
                exemplar.getLivro().getTitulo(),
                exemplar.getLivro().getAutor(),
                exemplar.getLivro().getAnoPublicacao(),
                exemplar.getLivro().getCategoria(),

                /* Exemplar */
                exemplar.getReferencia(),
                exemplar.getEstado()
        );
    }
}