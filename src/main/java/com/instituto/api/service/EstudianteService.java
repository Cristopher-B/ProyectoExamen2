package com.instituto.api.service;

import com.instituto.api.exception.ResourceNotFoundException;
import com.instituto.api.model.Estudiante;
import com.instituto.api.repository.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstudianteService implements IEstudianteService {

    @Autowired
    private IEstudianteRepository repo;

    @Override
    public List<Estudiante> getEstudiantes() {
        return repo.findAll();
    }

    @Override
    public Estudiante findEstudiante(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado con id: " + id));
    }

    @Override
    public Estudiante saveEstudiante(Estudiante estudiante) {
        return repo.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Long id, Estudiante estudiante) {
        Estudiante existente = this.findEstudiante(id);
        existente.setNombre(estudiante.getNombre());
        existente.setEmail(estudiante.getEmail());
        existente.setEdad(estudiante.getEdad());
        existente.setCarrera(estudiante.getCarrera());
        return repo.save(existente);
    }

    @Override
    public void deleteEstudiante(Long id) {
        Estudiante e = this.findEstudiante(id);
        repo.delete(e);
    }
}