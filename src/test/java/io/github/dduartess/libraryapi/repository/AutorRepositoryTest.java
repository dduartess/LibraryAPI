package io.github.dduartess.libraryapi.repository;

import io.github.dduartess.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    AutorRepository repository;

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
}
