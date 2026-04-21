package com.bdconnect.alumnos.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO que mapea la respuesta de la API externa JSONPlaceholder (/posts).
 * Puedes adaptarlo al contrato de tu propio microservicio.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostExternoDTO {

    /*
     * @JsonProperty("userId") le dice a Jackson (el serializador/deserializador de JSON)
     * que este campo Java debe mapearse con la clave "userId" del JSON.
     *
     * Sin esta anotación, Jackson buscaría una clave llamada igual que el campo Java.
     * Aquí el nombre coincide, pero la anotación es útil cuando la clave del JSON
     * y el nombre del campo Java son DIFERENTES.
     *
     * Ejemplo de JSON que esta clase puede leer:
     * {
     *   "userId": 1,
     *   "id": 10,
     *   "title": "Titulo del post",
     *   "body": "Cuerpo del post"
     * }
     *
     * También funciona al revés (serialización):
     * si conviertes este objeto a JSON, el campo userId saldrá como "userId" en el JSON.
     */
    @JsonProperty("userId")
    private Long userId;

    // Mapea la clave "id" del JSON con el campo Java 'id'
    @JsonProperty("id")
    private Long id;

    // Mapea la clave "title" del JSON con el campo Java 'title'
    @JsonProperty("title")
    private String title;

    // Mapea la clave "body" del JSON con el campo Java 'body'
    @JsonProperty("body")
    private String body;
}

