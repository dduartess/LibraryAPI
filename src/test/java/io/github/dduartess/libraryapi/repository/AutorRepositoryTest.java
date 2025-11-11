package io.github.dduartess.libraryapi.repository;

import io.github.dduartess.libraryapi.model.Autor;
import io.github.dduartess.libraryapi.model.GeneroLivro;
import io.github.dduartess.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("Abmael Duarte");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(2002,4,10));

        var autorSalvo = repository.save(autor);
        System.out.println("Autor salvo " + autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("d60c0017-4135-4bb8-a813-69cde10ab260");

        Optional<Autor> possivelAutor = repository.findById(id);

        if (possivelAutor.isPresent()) {

            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Abmael Duarte");
            autorEncontrado.setNacionalidade("Brasileiro");
            autorEncontrado.setDataNascimento(LocalDate.of(1970, 04, 03));

            repository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = repository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + repository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("dd9a8a56-fea2-40a9-adfe-fb889a1de097");
        repository.deleteById(id);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Gelson Alves");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1972, 9, 15));

        Livro livro = new Livro();
        livro.setIsbn("1241244-822232334");
        livro.setPreco(BigDecimal.valueOf(75));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("O rancho fundo");
        livro.setDataPublicacao(LocalDate.of(2025,8,22));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("1212341244-822232334");
        livro2.setPreco(BigDecimal.valueOf(75));
        livro2.setGenero(GeneroLivro.FICCAO);
        livro2.setTitulo("O riacho seco");
        livro2.setDataPublicacao(LocalDate.of(2021,8,22));
        livro2.setAutor(autor);

        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);

        repository.save(autor);
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("24e36dac-65c4-4513-a305-3aeb48f66ecc");
        var autor = repository.findById(id).get();

        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }
}
