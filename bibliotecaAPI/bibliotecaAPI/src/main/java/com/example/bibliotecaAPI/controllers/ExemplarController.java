package com.example.bibliotecaAPI.controllers;

import com.example.bibliotecaAPI.dtos.ExemplarRequestDto;
import com.example.bibliotecaAPI.dtos.ExemplarResponseDto;
import com.example.bibliotecaAPI.models.Exemplar;
import com.example.bibliotecaAPI.repositories.ExemplarRepository;
import com.example.bibliotecaAPI.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/exemplares")
public class ExemplarController {

    private final ExemplarRepository exemplarRepository;
    private final LivroRepository livroRepository;

    public ExemplarController(ExemplarRepository exemplarRepository,
                              LivroRepository livroRepository) {
        this.exemplarRepository = exemplarRepository;
        this.livroRepository = livroRepository;
    }

    // ------------------ GET ALL COM PAGINAÇÃO ------------------
    @GetMapping
    public ResponseEntity<Page<ExemplarResponseDto>> findAll(
            @RequestParam(name = "numeroPagina", defaultValue = "0") int numeroPagina,
            @RequestParam(name = "quantidade", defaultValue = "5") int quantidade
    ) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        Page<ExemplarResponseDto> exemplares = exemplarRepository
                .findAll(pageRequest)
                .map(ExemplarResponseDto::toDto);

        return ResponseEntity.ok(exemplares);
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<ExemplarResponseDto> findById(@PathVariable Integer id) {
        Exemplar exemplar = exemplarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar não encontrado."));

        return ResponseEntity.ok(ExemplarResponseDto.toDto(exemplar));
    }

    // ------------------ POST ------------------
    @PostMapping
    public ResponseEntity<ExemplarResponseDto> save(@RequestBody ExemplarRequestDto dto) {
        Exemplar exemplar = dto.toExemplar(new Exemplar(), livroRepository);
        exemplarRepository.save(exemplar);

        return ResponseEntity
                .created(URI.create("/exemplares/" + exemplar.getIdExemplar()))
                .body(ExemplarResponseDto.toDto(exemplar));
    }

    // ------------------ PUT ------------------
    @PutMapping("/{id}")
    public ResponseEntity<ExemplarResponseDto> update(
            @PathVariable Integer id,
            @RequestBody ExemplarRequestDto dto
    ) {
        Exemplar exemplarExistente = exemplarRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Exemplar não encontrado."));

        Exemplar exemplarAtualizado = dto.toExemplar(exemplarExistente, livroRepository);
        exemplarRepository.save(exemplarAtualizado);

        return ResponseEntity.ok(ExemplarResponseDto.toDto(exemplarAtualizado));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!exemplarRepository.existsById(id)) {
            throw new EntityNotFoundException("Exemplar não encontrado.");
        }

        exemplarRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}