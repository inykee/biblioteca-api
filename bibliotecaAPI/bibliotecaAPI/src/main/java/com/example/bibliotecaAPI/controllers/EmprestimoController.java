package com.example.bibliotecaAPI.controllers;

import com.example.bibliotecaAPI.dtos.EmprestimoRequestDto;
import com.example.bibliotecaAPI.dtos.EmprestimoResponseDto;
import com.example.bibliotecaAPI.models.Emprestimo;
import com.example.bibliotecaAPI.repositories.ClienteRepository;
import com.example.bibliotecaAPI.repositories.EmprestimoRepository;
import com.example.bibliotecaAPI.repositories.ExemplarRepository;
import com.example.bibliotecaAPI.services.EmprestimoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    private final ClienteRepository clienteRepository;
    private final EmprestimoService emprestimoService;

    public EmprestimoController(
            EmprestimoRepository emprestimoRepository,
            ExemplarRepository exemplarRepository,
            ClienteRepository clienteRepository,
            EmprestimoService emprestimoService
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.clienteRepository = clienteRepository;
        this.emprestimoService = emprestimoService;
    }

    // ------------------ GET ALL COM PAGINAÇÃO ------------------
    @GetMapping
    public ResponseEntity<Page<EmprestimoResponseDto>> findAll(
            @RequestParam(name = "numeroPagina", defaultValue = "0") int numeroPagina,
            @RequestParam(name = "quantidade", defaultValue = "5") int quantidade
    ) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        Page<EmprestimoResponseDto> emprestimos = emprestimoRepository
                .findAll(pageRequest)
                .map(EmprestimoResponseDto::toDto);

        return ResponseEntity.ok(emprestimos);
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDto> findById(@PathVariable Integer id) {
        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(EmprestimoResponseDto.toDto(emprestimo));
    }

    // ------------------ POST ------------------
    @PostMapping
    public ResponseEntity<EmprestimoResponseDto> save(@RequestBody EmprestimoRequestDto dto) {
        Emprestimo emprestimo = dto.toEmprestimo(
                new Emprestimo(),
                exemplarRepository,
                clienteRepository
        );

        Emprestimo emprestimoCriado = emprestimoService.criarEmprestimo(emprestimo);

        return ResponseEntity
                .created(URI.create("/emprestimos/" + emprestimoCriado.getIdEmprestimo()))
                .body(EmprestimoResponseDto.toDto(emprestimoCriado));
    }

    // ------------------ PUT ------------------
    @PutMapping("/{id}")
    public ResponseEntity<EmprestimoResponseDto> update(
            @PathVariable Integer id,
            @RequestBody EmprestimoRequestDto dto
    ) {
        Emprestimo emprestimoExistente = emprestimoRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        Emprestimo emprestimoAtualizado = dto.toEmprestimo(
                emprestimoExistente,
                exemplarRepository,
                clienteRepository
        );

        emprestimoRepository.save(emprestimoAtualizado);

        return ResponseEntity.ok(EmprestimoResponseDto.toDto(emprestimoAtualizado));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        emprestimoService.deletarEmprestimo(id);
        return ResponseEntity.noContent().build();
    }
}