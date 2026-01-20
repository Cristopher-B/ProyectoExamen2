package com.instituto.api.controller;

import com.instituto.api.model.Estudiante;
import com.instituto.api.service.IEstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudianteService service;

    @GetMapping
    public List<Estudiante> getAll() {
        return service.getEstudiantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findEstudiante(id));
    }

    @PostMapping
    public ResponseEntity<Estudiante> save(@Valid @RequestBody Estudiante estudiante) {
        return new ResponseEntity<>(service.saveEstudiante(estudiante), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(service.updateEstudiante(id, estudiante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteEstudiante(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente");
    }
}