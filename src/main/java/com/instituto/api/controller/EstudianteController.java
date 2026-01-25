package com.instituto.api.controller;

import com.instituto.api.model.Estudiante;
import com.instituto.api.service.IEstudianteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteController {

    @Autowired
    private IEstudianteService service;

    @GetMapping
    public ResponseEntity<List<Estudiante>> getAll() {
        List<Estudiante> lista = service.getEstudiantes();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findEstudiante(id)); // El Service lanza el 404 si no existe
    }

    @PostMapping
    public ResponseEntity<Estudiante> save(@Valid @RequestBody Estudiante estudiante) {
        Estudiante nuevo = service.saveEstudiante(estudiante);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED); // Código 201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> update(@PathVariable Long id, @Valid @RequestBody Estudiante estudiante) {
        return ResponseEntity.ok(service.updateEstudiante(id, estudiante)); // Código 200
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estudiante> patch(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        Estudiante actualizado = service.patchEstudiante(id, estudiante);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.deleteEstudiante(id);
        return ResponseEntity.ok(Map.of("message", "Estudiante con ID " + id + " eliminado con éxito"));
    }
}