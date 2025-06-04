package com.example.blog.controller;

import com.example.blog.model.Author;
import com.example.blog.model.Post;
import com.example.blog.service.AuthorService;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publicaciones")  // Ruta
public class PostController {

    private final PostService postService;
    private final AuthorService authorService;

    public PostController(PostService postService, AuthorService authorService) {
        this.postService = postService;
        this.authorService = authorService;
    }

    /**
     * POST /api/publicaciones/crear/autor/{autorId}
     */
    @PostMapping("/crear/autor/{autorId}")
    public ResponseEntity<Post> crear(@PathVariable Long autorId, @Valid @RequestBody Post post) {
        Author autor = authorService.obtenerPorId(autorId);
        post.setAuthor(autor);
        return new ResponseEntity<>(postService.crearPost(post), HttpStatus.CREATED);
    }

    /**
     * GET /api/publicaciones/todas
     */
    @GetMapping("/todas")
    public List<Post> listar() {
        return postService.obtenerTodos();
    }

    /**
     * GET /api/publicaciones/{id}
     */
    @GetMapping("/{id}")
    public Post obtener(@PathVariable Long id) {
        return postService.obtenerPorId(id);
    }

    /**
     * PUT /api/publicaciones/actualizar/{id}
     */
    @PutMapping("/actualizar/{id}")
    public Post actualizar(@PathVariable Long id, @Valid @RequestBody Post postActualizado) {
        return postService.actualizarPost(id, postActualizado);
    }

    /**
     * DELETE /api/publicaciones/eliminar/{id}
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        postService.eliminarPost(id);
        return ResponseEntity.noContent().build();
    }
}
