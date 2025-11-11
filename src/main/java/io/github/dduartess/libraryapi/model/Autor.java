package io.github.dduartess.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "autor")
@Getter
@Setter
@ToString
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private LocalDate dataNascimento;
    private String nacionalidade;

    @Deprecated
    public Autor(){
    }

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL)
    private List<Livro> livros;
}
