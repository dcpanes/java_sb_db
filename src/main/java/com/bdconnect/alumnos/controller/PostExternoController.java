package com.bdconnect.alumnos.controller;

import com.bdconnect.alumnos.dto.PostExternoDTO;
import com.bdconnect.alumnos.service.PostExternoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Controlador que expone endpoints para consumir la API externa
 * a través de PostExternoService (WebClient).
 * Rutas disponibles:
 *  GET  /externos/posts          -> Todos los posts (síncrono, devuelve JSON array)
 *  GET  /externos/posts/{id}     -> Un post por ID (síncrono)
 *  GET  /externos/posts/reactivo -> Todos los posts como FlFluxux (stream reactivo)
 *  POST /externos/posts          -> Crear un post en la API externa
 */
@RestController
@RequestMapping("/externos")
public class PostExternoController {

    private final PostExternoService postExternoService;

    public PostExternoController(PostExternoService postExternoService) {
        this.postExternoService = postExternoService;
    }

    /**
     * Obtiene todos los posts de forma síncrona (compatible con Spring MVC).
     */
    @GetMapping("/posts")
    public ResponseEntity<List<PostExternoDTO>> getAllPosts() {
        List<PostExternoDTO> posts = postExternoService.obtenerTodosLosPostsSync();
        return ResponseEntity.ok(posts);
    }

    /**
     * Obtiene un post por ID de forma síncrona.
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<PostExternoDTO> getPostById(@PathVariable Long id) {
        PostExternoDTO post = postExternoService.obtenerPostPorIdSync(id);
        return ResponseEntity.ok(post);
    }

    /**
     * Obtiene todos los posts de forma reactiva (Flux / Server-Sent Events).
     * Útil si quieres usar streaming reactivo.
     */
    @GetMapping(value = "/posts/reactivo", produces = "application/json")
    public Flux<PostExternoDTO> getAllPostsReactivo() {
        return postExternoService.obtenerTodosLosPosts();
    }

    /**
     * Crea un nuevo post en la API externa.
     */
    @PostMapping("/posts")
    public Mono<PostExternoDTO> crearPost(@RequestBody PostExternoDTO post) {
        return postExternoService.crearPost(post);
    }
}

