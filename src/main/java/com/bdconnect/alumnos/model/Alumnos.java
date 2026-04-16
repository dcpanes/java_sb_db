package com.bdconnect.alumnos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "alumnos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alumnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;
}