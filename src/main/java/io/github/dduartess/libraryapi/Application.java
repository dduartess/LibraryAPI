package io.github.dduartess.libraryapi;

import io.github.dduartess.libraryapi.model.Autor;
import io.github.dduartess.libraryapi.repository.AutorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {SpringApplication.run(Application.class, args);}
}
