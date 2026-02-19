package com.example.bibliotecaAPI.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emprestimos")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emprestimo")
    private Integer idEmprestimo;

    @NotNull(message = "O exemplar deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_exemplar", nullable = false)
    private Exemplar exemplar;

    @NotNull(message = "O cliente deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @NotNull(message = "A data do empréstimo deve ser informada")
    @PastOrPresent(message = "A data do empréstimo não pode ser futura")
    @Column(name = "data_emprestimo", nullable = false)
    private LocalDate dataEmprestimo;

    @NotNull(message = "A data de devolução deve ser informada")
    @Future(message = "A data de devolução deve ser futura")
    @Column(name = "data_devolucao", nullable = false)
    private LocalDate dataDevolucao;

}