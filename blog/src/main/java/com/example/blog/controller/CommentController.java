package com.example.blog.controller;

import com.example.blog.model.Comment;
import com.example.blog.model.Post;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
public class CommentController {

    private final CommentService commentService;
    private final PostService postService;

    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @PostMapping("/crear/publicacion/{publicacionId}") // POST /api/comentarios/crear/publicacion/{publicacionId}
    public ResponseEntity<Comment> crearComentario(@PathVariable Long publicacionId,
                                                   @Valid @RequestBody Comment comentario) {
        Post post = postService.obtenerPorId(publicacionId);
        comentario.setPost(post);
        return new ResponseEntity<>(commentService.crearComentario(comentario), HttpStatus.CREATED);
    }

    @GetMapping("/todos") // GET /api/comentarios/todos
    public List<Comment> listarComentarios() {
        return commentService.listarComentarios();
    }

    @GetMapping("/{id}") // GET /api/comentarios/{id}
    public Comment obtenerComentario(@PathVariable Long id) {
        return commentService.obtenerPorId(id);
    }

    @DeleteMapping("/eliminar/{id}") // DELETE /api/comentarios/eliminar/{id}
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        commentService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
