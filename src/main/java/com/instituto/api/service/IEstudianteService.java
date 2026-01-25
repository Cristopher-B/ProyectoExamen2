package com.instituto.api.service;

import com.instituto.api.model.Estudiante;
import java.util.List;

public interface IEstudianteService {
    List<Estudiante> getEstudiantes();
    Estudiante findEstudiante(Long id);
    Estudiante saveEstudiante(Estudiante estudiante);
    Estudiante updateEstudiante(Long id, Estudiante estudiante);
    void deleteEstudiante(Long id);
    Estudiante patchEstudiante(Long id, Estudiante detalles);
}