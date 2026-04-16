package com.bdconnect.alumnos.repository;

import com.bdconnect.alumnos.model.Alumnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnosRespository extends JpaRepository<Alumnos, Long> {

}
