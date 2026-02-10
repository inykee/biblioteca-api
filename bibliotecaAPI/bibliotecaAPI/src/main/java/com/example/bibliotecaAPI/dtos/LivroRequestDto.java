package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Categoria;
import com.example.bibliotecaAPI.models.Livro;

public record LivroRequestDto(
        String titulo,
        String autor,
        Integer anoPublicacao,
        String isbn,
        Categoria categoria
) {

    public Livro toLivro(Livro livro) {
        livro.setTitulo(this.titulo());
        livro.setAutor(this.autor());
        livro.setAnoPublicacao(this.anoPublicacao());
        livro.setIsbn(this.isbn());
        livro.setCategoria(this.categoria());
        return livro;
    }
}