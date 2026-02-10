package com.example.bibliotecaAPI.controllers;

import com.example.bibliotecaAPI.dtos.LivroRequestDto;
import com.example.bibliotecaAPI.dtos.LivroResponseDto;
import com.example.bibliotecaAPI.models.Livro;
import com.example.bibliotecaAPI.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    // ------------------ GET ALL COM PAGINAÇÃO ------------------
    @GetMapping
    public ResponseEntity<Page<LivroResponseDto>> findAll(
            @RequestParam(name = "numeroPagina", defaultValue = "0") int numeroPagina,
            @RequestParam(name = "quantidade", defaultValue = "5") int quantidade
    ) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        Page<LivroResponseDto> livros = livroRepository
                .findAll(pageRequest)
                .map(LivroResponseDto::toDto);

        return ResponseEntity.ok(livros);
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDto> findById(@PathVariable Integer id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        return ResponseEntity.ok(LivroResponseDto.toDto(livro));
    }

    // ------------------ POST ------------------
    @PostMapping
    public ResponseEntity<LivroResponseDto> save(@RequestBody LivroRequestDto dto) {
        Livro livro = dto.toLivro(new Livro());
        livroRepository.save(livro);

        return ResponseEntity
                .created(URI.create("/livros/" + livro.getIdLivro()))
                .body(LivroResponseDto.toDto(livro));
    }

    // ------------------ PUT ------------------
    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDto> update(
            @PathVariable Integer id,
            @RequestBody LivroRequestDto dto
    ) {
        Livro livroExistente = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado."));

        Livro livroAtualizado = dto.toLivro(livroExistente);
        livroRepository.save(livroAtualizado);

        return ResponseEntity.ok(LivroResponseDto.toDto(livroAtualizado));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!livroRepository.existsById(id)) {
            throw new EntityNotFoundException("Livro não encontrado.");
        }

        livroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}