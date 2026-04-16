package com.bdconnect.alumnos.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AlumnosDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
}
