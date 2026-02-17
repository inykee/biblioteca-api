package com.example.bibliotecaAPI.models;

import com.example.bibliotecaAPI.enums.Estado;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exemplares")
public class Exemplar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exemplar")
    private Integer idExemplar;

    @NotNull(message = "O livro do exemplar deve ser informado")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livro", nullable = false)
    private Livro livro;

    @NotNull(message = "A referência do exemplar deve ser informada")
    @Min(value = 1, message = "A referência do exemplar deve ser maior que zero")
    @Column(name = "referencia", nullable = false)
    private Integer referencia;

    @NotNull(message = "O estado do exemplar deve ser informado")
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

}