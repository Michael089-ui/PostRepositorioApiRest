package com.example.blog.service;

import com.example.blog.model.Post;
import com.example.blog.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post crearPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> obtenerTodos() {
        return postRepository.findAll();
    }

    public Post obtenerPorId(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado"));
    }

    public Post actualizarPost(Long id, Post postActualizado) {
        Post post = obtenerPorId(id);
        post.setTitulo(postActualizado.getTitulo());
        post.setContenido(postActualizado.getContenido());
        return postRepository.save(post);
    }

    public void eliminarPost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post no encontrado");
        }
        postRepository.deleteById(id);
    }
}
