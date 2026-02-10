package com.example.bibliotecaAPI.services;

import com.example.bibliotecaAPI.enums.Estado;
import com.example.bibliotecaAPI.exceptions.ClienteBloqueadoException;
import com.example.bibliotecaAPI.exceptions.ExemplarNaoDisponivelException;
import com.example.bibliotecaAPI.models.Cliente;
import com.example.bibliotecaAPI.models.Emprestimo;
import com.example.bibliotecaAPI.models.Exemplar;
import com.example.bibliotecaAPI.repositories.ClienteRepository;
import com.example.bibliotecaAPI.repositories.EmprestimoRepository;
import com.example.bibliotecaAPI.repositories.ExemplarRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ExemplarRepository exemplarRepository;
    private final ClienteRepository clienteRepository;

    public EmprestimoService(
            EmprestimoRepository emprestimoRepository,
            ExemplarRepository exemplarRepository,
            ClienteRepository clienteRepository
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.exemplarRepository = exemplarRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
        Exemplar exemplar = emprestimo.getExemplar();
        Cliente cliente = emprestimo.getCliente();

        // Verifica se o cliente está ativo
        if (!cliente.getSituacao().isAtivo()) {
            throw new ClienteBloqueadoException(
                    "O cliente está bloqueado e não pode realizar empréstimos.", 1
            );
        }

        // Verifica se o exemplar está disponível
        if (!exemplar.getEstado().isDisponivel()) {
            throw new ExemplarNaoDisponivelException(
                    "O exemplar selecionado não está disponível para empréstimo.", 2
            );
        }

        // Reserva o exemplar
        exemplar.setEstado(Estado.RESERVADO);
        exemplarRepository.save(exemplar);

        // Salva o empréstimo
        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public void deletarEmprestimo(Integer id) {
        Optional<Emprestimo> emprestimoOptional = emprestimoRepository.findById(id);

        if (emprestimoOptional.isEmpty()) {
            throw new EntityNotFoundException("Empréstimo com ID " + id + " não encontrado.");
        }

        Emprestimo emprestimo = emprestimoOptional.get();

        // Libera o exemplar associado
        Exemplar exemplar = emprestimo.getExemplar();
        exemplar.setEstado(Estado.DISPONIVEL);
        exemplarRepository.save(exemplar);

        // Deleta o empréstimo
        emprestimoRepository.delete(emprestimo);
    }
}