package com.bdconnect.alumnos.service;

import com.bdconnect.alumnos.dto.PostExternoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Servicio que realiza solicitudes HTTP a una API externa (o microservicio)
 * usando WebClient de Spring WebFlux de forma no bloqueante.
 *
 * En Reactor:
 * - Mono representa 0 o 1 elemento asincrono (por ejemplo, un solo post).
 * - Flux representa 0..N elementos asincronos (por ejemplo, una lista de posts).
 *
 * La URL base está configurada en WebClientConfig.
 * Para apuntar a tu propio microservicio, cambia la baseUrl en WebClientConfig.
 */
@Service
public class PostExternoService {

    private final WebClient webClient;

    public PostExternoService(WebClient jsonPlaceholderWebClient) {
        this.webClient = jsonPlaceholderWebClient;
    }

    /**
     * Obtiene todos los posts de la API externa de forma reactiva.
     * Devuelve un Flux: un stream no bloqueante que puede emitir varios elementos.
     */
    public Flux<PostExternoDTO> obtenerTodosLosPosts() {
        return webClient.get()
                .uri("/posts")
                .retrieve()
                .bodyToFlux(PostExternoDTO.class);
    }

    /**
     * Obtiene un post por ID de forma reactiva.
     * Devuelve un Mono: un contenedor reactivo para cero o un unico resultado.
     */
    public Mono<PostExternoDTO> obtenerPostPorId(Long id) {
        return webClient.get()
                .uri("/posts/{id}", id)
                .retrieve()
                .bodyToMono(PostExternoDTO.class);
    }

    /**
     * Obtiene todos los posts de forma bloqueante (útil cuando el resto
     * de la aplicación usa MVC sincrónico).
     * Lista de posts
     */
    public List<PostExternoDTO> obtenerTodosLosPostsSync() {
        return obtenerTodosLosPosts().collectList().block();
    }

    /**
     * Obtiene un post por ID de forma bloqueante.
     * id identificador del post
     * PostExternoDTO
     */
    public PostExternoDTO obtenerPostPorIdSync(Long id) {
        return obtenerPostPorId(id).block();
    }

    /**
     * Crea un nuevo post en la API externa (demo de POST con body).
     * post datos del post a crear
     * Mono con el post creado (respuesta de la API)
     */
    public Mono<PostExternoDTO> crearPost(PostExternoDTO post) {
        return webClient.post()
                .uri("/posts")
                .bodyValue(post)
                .retrieve()
                .bodyToMono(PostExternoDTO.class);
    }
}

