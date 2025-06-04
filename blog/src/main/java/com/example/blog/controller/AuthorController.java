package com.example.blog.controller;

import com.example.blog.model.Author;
import com.example.blog.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/crear") // POST /api/autores/crear
    public ResponseEntity<Author> crearAutor(@Valid @RequestBody Author autor) {
        return new ResponseEntity<>(authorService.crear(autor), HttpStatus.CREATED);
    }

    @GetMapping("/todos") // GET /api/autores/todos
    public ResponseEntity<List<Author>> listarAutores() {
        return ResponseEntity.ok(authorService.listar());
    }

    @GetMapping("/{id}") // GET /api/autores/{id}
    public ResponseEntity<Author> obtenerAutor(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.obtenerPorId(id));
    }

    @DeleteMapping("/eliminar/{id}") // DELETE /api/autores/eliminar/{id}
    public ResponseEntity<Void> eliminarAutor(@PathVariable Long id) {
        authorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
