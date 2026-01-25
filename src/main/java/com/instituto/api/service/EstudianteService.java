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
        return repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Estudiante no encontrado con id: " + id)
        );
    }

    @Override
    public Estudiante saveEstudiante(Estudiante estudiante) {
        formatearNombres(estudiante);
        validarDuplicados(estudiante, null);
        return repo.save(estudiante);
    }

    @Override
    public Estudiante updateEstudiante(Long id, Estudiante detalles) {

        Estudiante existente = this.findEstudiante(id);
        validarDuplicados(detalles, id);

        if (detalles.getCedula() != null) {
            existente.setCedula(detalles.getCedula());
        }
        if (detalles.getNombre() != null) {
            existente.setNombre(detalles.getNombre());
        }
        if (detalles.getApellido() != null) {
            existente.setApellido(detalles.getApellido());
        }
        if (detalles.getEmail() != null) {
            existente.setEmail(detalles.getEmail());
        }
        if (detalles.getCarrera() != null) {
            existente.setCarrera(detalles.getCarrera());
        }
        if (detalles.getEdad() != 0) {
            existente.setEdad(detalles.getEdad());
        }
        formatearNombres(existente);
        return repo.save(existente);
    }

    @Override
    public Estudiante patchEstudiante(Long id, Estudiante detalles) {
        Estudiante existente = this.findEstudiante(id);

        if (detalles.getCedula() != null && !detalles.getCedula().equals(existente.getCedula())) {
            if (detalles.getCedula().length() != 10) {
                throw new IllegalArgumentException("La nueva cédula debe tener 10 dígitos");
            }
            validarCedulaUnica(detalles.getCedula(), id);
            existente.setCedula(detalles.getCedula());
        }

        if (detalles.getEmail() != null && !detalles.getEmail().equals(existente.getEmail())) {
            validarEmailUnico(detalles.getEmail(), id);
            existente.setEmail(detalles.getEmail());
        }

        if (detalles.getNombre() != null) existente.setNombre(detalles.getNombre());
        if (detalles.getApellido() != null) existente.setApellido(detalles.getApellido());
        if (detalles.getCarrera() != null) {
            if (detalles.getCarrera().matches("Administración|Marketing|Contabilidad|Farmacia|Desarrollo de software|Turismo")) {
                existente.setCarrera(detalles.getCarrera());
            } else {
                throw new IllegalArgumentException("Carrera no permitida");
            }
        }
        if (detalles.getEdad() >= 18 && detalles.getEdad() <= 60) {
            existente.setEdad(detalles.getEdad());
        }

        formatearNombres(existente);
        return repo.save(existente);
    }

    private void validarCedulaUnica(String cedula, Long id) {
        repo.findByCedula(cedula).filter(e -> !e.getId().equals(id))
                .ifPresent(e -> { throw new IllegalArgumentException("La cédula ya existe"); });
    }

    private void validarEmailUnico(String email, Long id) {
        repo.findByEmail(email).filter(e -> !e.getId().equals(id))
                .ifPresent(e -> { throw new IllegalArgumentException("El email ya existe"); });
    }

    @Override
    public void deleteEstudiante(Long id) {
        Estudiante estudiante = this.findEstudiante(id);
        repo.delete(estudiante);
    }

    private void validarDuplicados(Estudiante estudiante, Long idActual) {

        // CÉDULA
        repo.findByCedula(estudiante.getCedula())
                .filter(e -> !e.getId().equals(idActual))
                .ifPresent(e -> {
                    throw new IllegalArgumentException("La cédula ya está registrada");
                });

        // EMAIL
        repo.findByEmail(estudiante.getEmail())
                .filter(e -> !e.getId().equals(idActual))
                .ifPresent(e -> {
                    throw new IllegalArgumentException("El correo ya está registrado");
                });

        // NOMBRE + APELLIDO
        if (repo.existsByNombreAndApellido(
                estudiante.getNombre(),
                estudiante.getApellido()
        )) {
            if (idActual == null ||
                    !repo.findById(idActual)
                            .map(e -> e.getNombre().equals(estudiante.getNombre())
                                    && e.getApellido().equals(estudiante.getApellido()))
                            .orElse(false)) {

                throw new IllegalArgumentException(
                        "Ya existe un estudiante con el mismo nombre y apellido"
                );
            }
        }
    }

    private void formatearNombres(Estudiante estudiante) {
        estudiante.setNombre(capitalizarTexto(estudiante.getNombre()));
        estudiante.setApellido(capitalizarTexto(estudiante.getApellido()));
    }

    private String capitalizarTexto(String texto) {
        if (texto == null || texto.isBlank()) return texto;

        String[] palabras = texto.toLowerCase().trim().split("\\s+");
        StringBuilder resultado = new StringBuilder();

        for (String palabra : palabras) {
            resultado.append(Character.toUpperCase(palabra.charAt(0)))
                    .append(palabra.substring(1))
                    .append(" ");
        }
        return resultado.toString().trim();
    }
}