package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Categoria;
import com.example.bibliotecaAPI.models.Livro;

public record LivroResponseDto(
        Integer idLivro,
        String titulo,
        String autor,
        Integer anoPublicacao,
        Categoria categoria
) {

    public static LivroResponseDto toDto(Livro livro) {
        return new LivroResponseDto(
                livro.getIdLivro(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getAnoPublicacao(),
                livro.getCategoria()
        );
    }
}