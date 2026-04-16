package com.bdconnect.alumnos.service;

import com.bdconnect.alumnos.dto.AlumnosDTO;
import com.bdconnect.alumnos.model.Alumnos;
import com.bdconnect.alumnos.repository.AlumnosRespository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlumnosService {

    private final AlumnosRespository alumnosRespository;

    public AlumnosService(AlumnosRespository alumnosRespository) {
        this.alumnosRespository = alumnosRespository;
    }

    public AlumnosDTO saveAlumnos(AlumnosDTO alumnoDTO) {
        // Ussamos el patron builder para crear la entidad a partir del DTO
        Alumnos entity = Alumnos.builder()
                .nombre(alumnoDTO.getNombre())
                .apellido(alumnoDTO.getApellido())
                .email(alumnoDTO.getEmail())
                .telefono(alumnoDTO.getTelefono())
                .build();

        // Guardar en la base datos
        Alumnos saved = alumnosRespository.save(entity);

        // La respuesta ahora se tranforma a DTO para devolver al postman
        AlumnosDTO response = AlumnosDTO.builder()
                .id(saved.getId())
                .nombre(saved.getNombre())
                .apellido(saved.getApellido())
                .email(saved.getEmail())
                .telefono(saved.getTelefono())
                .build();

        return response;
    }

    public List<AlumnosDTO> getAllAlumnos() {
        List<Alumnos> alumnosList = alumnosRespository.findAll();
        return alumnosList.stream().map(alumno -> AlumnosDTO.builder()
                .id(alumno.getId())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .email(alumno.getEmail())
                .telefono(alumno.getTelefono())
                .build()).collect(Collectors.toList());
    }

    public AlumnosDTO getAlumnosById(Long id) {
        Alumnos alumno = alumnosRespository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        return AlumnosDTO.builder()
                .id(alumno.getId())
                .nombre(alumno.getNombre())
                .apellido(alumno.getApellido())
                .email(alumno.getEmail())
                .telefono(alumno.getTelefono())
                .build();
    }

    public boolean deleteAlumnos(Long id) {
        if (alumnosRespository.existsById(id)) {
            alumnosRespository.deleteById(id);
            return true;
        }
        return false;
    }

    public AlumnosDTO updateAlumnos(Long id, AlumnosDTO alumnoDTO) {
        Alumnos alumno = alumnosRespository.findById(id).orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        alumno.setNombre(alumnoDTO.getNombre());
        alumno.setApellido(alumnoDTO.getApellido());
        alumno.setEmail(alumnoDTO.getEmail());
        alumno.setTelefono(alumnoDTO.getTelefono());

        Alumnos updated = alumnosRespository.save(alumno);

        return AlumnosDTO.builder()
                .id(updated.getId())
                .nombre(updated.getNombre())
                .apellido(updated.getApellido())
                .email(updated.getEmail())
                .telefono(updated.getTelefono())
                .build();
    }

}
