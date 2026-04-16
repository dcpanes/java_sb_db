package com.bdconnect.alumnos.controller;

import com.bdconnect.alumnos.dto.AlumnosDTO;
import com.bdconnect.alumnos.service.AlumnosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlumnosController {

    private AlumnosService alumnosService;
    public AlumnosController(AlumnosService alumnosService) {
        this.alumnosService = alumnosService;
    }

    @PostMapping("/alumnos")
    public AlumnosDTO saveAlumnos(@RequestBody AlumnosDTO alumno) {
        return alumnosService.saveAlumnos(alumno);
    }

    @GetMapping("/alumnos")
    public List<AlumnosDTO> getAllAlumnos() {
        return alumnosService.getAllAlumnos();
    }
}
