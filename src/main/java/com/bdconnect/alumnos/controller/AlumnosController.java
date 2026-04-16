package com.bdconnect.alumnos.controller;

import com.bdconnect.alumnos.dto.AlumnosDTO;
import com.bdconnect.alumnos.service.AlumnosService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/alumnos/{id}")
    public AlumnosDTO getAlumnosById(@PathVariable Long id) {
        return alumnosService.getAlumnosById(id);
    }

    @DeleteMapping("/alumnos/{id}")
    public String deleteAlumnos(@PathVariable Long id) {
        return alumnosService.deleteAlumnos(id) ? "Alumno eliminado correctamente" : "Error al eliminar el alumno";
    }

    @PutMapping("/alumnos/{id}")
    public AlumnosDTO updateAlumnos(@PathVariable Long id, @RequestBody AlumnosDTO alumno) {
        return alumnosService.updateAlumnos(id, alumno);
    }
}
