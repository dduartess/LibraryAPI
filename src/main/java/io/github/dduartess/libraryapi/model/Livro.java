package io.github.dduartess.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "livro")
@Data
@ToString(exclude = "autor")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(length = 20, nullable = false)
    private String isbn;
    @Column(length = 150, nullable = false)
    private String titulo;
    private LocalDate dataPublicacao;
    @Column(length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private GeneroLivro genero;
    @Column(precision = 18 , scale = 2)
    private BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="id_autor")
    private Autor autor;
}
