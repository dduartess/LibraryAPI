package io.github.dduartess.libraryapi.repository;

import io.github.dduartess.libraryapi.model.Autor;
import io.github.dduartess.libraryapi.model.GeneroLivro;
import io.github.dduartess.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository repository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("1241244-8234");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("UFO");
        livro.setDataPublicacao(LocalDate.of(2001,8,22));

        Autor autor = autorRepository.
                findById(UUID.fromString("04354415-d1a7-489f-b207-f9c983b29299")).orElse(null);
        livro.setAutor(autor);
        repository.save(livro);
        System.out.println(livro.getTitulo() + " - Cadastrado com sucesso!");
    }

}