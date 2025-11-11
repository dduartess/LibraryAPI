package io.github.dduartess.libraryapi.repository;

import io.github.dduartess.libraryapi.model.Autor;
import io.github.dduartess.libraryapi.model.GeneroLivro;
import io.github.dduartess.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

        Autor autor = autorRepository.findById(UUID.fromString("04354415-d1a7-489f-b207-f9c983b29299")).orElse(null);
        livro.setAutor(autor);
        repository.save(livro);
        System.out.println(livro.getTitulo() + " - Cadastrado com sucesso!");
    }

    @Test
    void SalvarEmSequencia(){
        Livro livro = new Livro();
        livro.setTitulo("Cronicas de um babaca");
        livro.setIsbn("26155-5515");
        livro.setPreco(BigDecimal.valueOf(50));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setDataPublicacao(LocalDate.of(2025,11,10));

        Autor autor = new Autor();
        autor.setNome("Gabriela Duarte");
        autor.setDataNascimento(LocalDate.of(2008,07,11));
        autor.setNacionalidade("Brasileira");

        livro.setAutor(autor);
        repository.save(livro);
        System.out.println(livro.getTitulo() + " - Livro cadastrado com sucesso!");
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("1241244-822334");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("GISLEIA UMA EMPREENDEDORA CENTOPEIA");
        livro.setDataPublicacao(LocalDate.of(2025,8,22));


        Autor autor = new Autor();
        autor.setNome("Abmael Duarte");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1978,4,3));

        livro.setAutor(autor);
        repository.save(livro);
        System.out.println(livro.getTitulo() + " - Cadastrado com sucesso!");
    }

    @Test
    void atualizarAutorDoLivro(){
       UUID id = UUID.fromString("8746e284-d253-4f52-af90-63142d420aec");
       var livroParaAtualizar = repository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("24e36dac-65c4-4513-a305-3aeb48f66ecc");
        Autor Gabriela = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(Gabriela);

        repository.save(livroParaAtualizar);
        System.out.println("Autor do Livro " + livroParaAtualizar.getTitulo() + " atualizado com sucesso!");
    }

    @Test
    void deletarLivro(){
        UUID id = UUID.fromString("8692a38b-7cd8-40c1-97c4-9bd45d425928");
        repository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("494489a5-91a6-4f92-9e65-bbc4d078e32e");
        Livro livro = repository.findById(id).orElse(null);

        System.out.println("Livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisaPorTituloTest(){
        List<Livro> lista = repository.findByTitulo("UFO");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorIsbn(){
        List<Livro> lista = repository.findByIsbn("1241244-822334");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisaPorPrecoETitulo(){
        var preco = BigDecimal.valueOf(100);
        var titulo = "GISLEIA UMA EMPREENDEDORA CENTOPEIA";
       List<Livro> livro = repository.findByTituloAndPreco(titulo,  preco);
       livro.forEach(System.out::println);
   }
}