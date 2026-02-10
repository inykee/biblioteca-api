package com.example.bibliotecaAPI.controllers;

import com.example.bibliotecaAPI.dtos.ClienteRequestDto;
import com.example.bibliotecaAPI.dtos.ClienteResponseDto;
import com.example.bibliotecaAPI.models.Cliente;
import com.example.bibliotecaAPI.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository clienteRepository;

    public ClienteController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // ------------------ GET ALL COM PAGINAÇÃO ------------------
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDto>> findAll(
            @RequestParam(name = "numeroPagina", defaultValue = "0") int numeroPagina,
            @RequestParam(name = "quantidade", defaultValue = "5") int quantidade
    ) {
        PageRequest pageRequest = PageRequest.of(numeroPagina, quantidade);
        Page<ClienteResponseDto> clientes = clienteRepository
                .findAll(pageRequest)
                .map(ClienteResponseDto::toDto);

        return ResponseEntity.ok(clientes);
    }

    // ------------------ GET BY ID ------------------
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return ResponseEntity.ok(ClienteResponseDto.toDto(cliente));
    }

    // ------------------ POST ------------------
    @PostMapping
    public ResponseEntity<ClienteResponseDto> save(@RequestBody ClienteRequestDto dto) {
        Cliente cliente = dto.toCliente(new Cliente());
        clienteRepository.save(cliente);

        return ResponseEntity
                .created(URI.create("/clientes/" + cliente.getIdCliente()))
                .body(ClienteResponseDto.toDto(cliente));
    }

    // ------------------ PUT ------------------
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> update(
            @PathVariable Integer id,
            @RequestBody ClienteRequestDto dto
    ) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        Cliente clienteAtualizado = dto.toCliente(clienteExistente);
        clienteRepository.save(clienteAtualizado);

        return ResponseEntity.ok(ClienteResponseDto.toDto(clienteAtualizado));
    }

    // ------------------ DELETE ------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }

        clienteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}