package com.example.blog.service;

import com.example.blog.model.Author;
import com.example.blog.repository.AuthorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author crear(Author author) {
        return authorRepository.save(author);
    }

    public List<Author> listar() {
        return authorRepository.findAll();
    }

    public Author obtenerPorId(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado"));
    }

    public void eliminar(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no encontrado");
        }
        authorRepository.deleteById(id);
    }
}
