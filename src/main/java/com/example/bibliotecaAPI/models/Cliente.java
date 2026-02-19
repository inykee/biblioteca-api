package com.example.bibliotecaAPI.models;

import com.example.bibliotecaAPI.enums.Situacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @NotBlank(message = "O nome completo deve ser informado")
    @Size(min = 3, max = 255, message = "O nome completo deve ter entre 3 e 255 caracteres")
    @Column(name = "nome_completo", nullable = false, length = 255)
    private String nomeCompleto;

    @NotBlank(message = "O CPF deve ser informado")
    @CPF(message = "O CPF informado é inválido")
    @Column(name = "cpf", nullable = false, length = 14, unique = true)
    private String cpf;

    @NotBlank(message = "O telefone deve ser informado")
    @Pattern(
            regexp = "\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}",
            message = "O telefone informado é inválido"
    )
    @Column(name = "telefone", nullable = false, length = 19)
    private String telefone;

    @NotBlank(message = "O email deve ser informado")
    @Email(message = "O email informado é inválido")
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;

    @NotNull(message = "A situação do cliente deve ser informada")
    @Enumerated(EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao;

}