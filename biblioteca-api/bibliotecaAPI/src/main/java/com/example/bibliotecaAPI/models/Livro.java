package com.example.bibliotecaAPI.models;

import com.example.bibliotecaAPI.enums.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Integer idLivro;

    @NotBlank(message = "O título do livro deve ser informado")
    @Size(min = 2, max = 255, message = "O título do livro deve ter entre 2 e 255 caracteres")
    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @NotBlank(message = "O autor do livro deve ser informado")
    @Size(min = 3, max = 255, message = "O autor do livro deve ter entre 3 e 255 caracteres")
    @Column(name = "autor", nullable = false, length = 255)
    private String autor;

    @NotNull(message = "O ano de publicação deve ser informado")
    @Min(value = 1000, message = "O ano de publicação informado é inválido")
    @Max(value = 2100, message = "O ano de publicação informado é inválido")
    @Column(name = "ano_publicacao", nullable = false)
    private Integer anoPublicacao;

    @NotBlank(message = "O ISBN deve ser informado")
    @Pattern(
            regexp = "^(97[89])?\\d{9}[\\dX]$",
            message = "O ISBN informado é inválido"
    )
    @Column(name = "isbn", nullable = false, length = 17, unique = true)
    private String isbn;

    @NotNull(message = "A categoria do livro deve ser informada")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

}