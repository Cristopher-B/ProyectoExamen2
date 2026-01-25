package com.instituto.api.repository;

import com.instituto.api.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEstudianteRepository extends JpaRepository<Estudiante, Long> {
    boolean existsByCedula(String cedula);
    boolean existsByEmail(String email);
    boolean existsByNombreAndApellido(String nombre, String apellido);
    Optional<Estudiante> findByCedula(String cedula);
    Optional<Estudiante> findByEmail(String email);
}
