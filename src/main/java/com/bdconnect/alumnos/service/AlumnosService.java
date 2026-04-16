package com.bdconnect.alumnos.service;

import com.bdconnect.alumnos.dto.AlumnosDTO;
import com.bdconnect.alumnos.model.Alumnos;
import com.bdconnect.alumnos.repository.AlumnosRespository;
import org.springframework.stereotype.Service;

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
        AlumnosDTO response = new AlumnosDTO();
        response.setId(saved.getId());
        response.setNombre(saved.getNombre());
        response.setApellido(saved.getApellido());
        response.setEmail(saved.getEmail());
        response.setTelefono(saved.getTelefono());

        return response;
    }

}
