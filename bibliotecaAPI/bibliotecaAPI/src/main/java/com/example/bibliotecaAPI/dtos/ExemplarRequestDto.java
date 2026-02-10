package com.example.bibliotecaAPI.dtos;

import com.example.bibliotecaAPI.enums.Estado;
import com.example.bibliotecaAPI.models.Exemplar;
import com.example.bibliotecaAPI.repositories.LivroRepository;

public record ExemplarRequestDto(
        Integer idLivro,
        Integer referencia,
        Estado estado
) {

    public Exemplar toExemplar(Exemplar exemplar, LivroRepository livroRepository) {
        exemplar.setLivro(livroRepository.getReferenceById(this.idLivro()));
        exemplar.setReferencia(this.referencia());
        exemplar.setEstado(this.estado());
        return exemplar;
    }
}