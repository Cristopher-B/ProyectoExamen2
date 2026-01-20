package com.instituto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiantes")
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe proporcionar un formato de email válido")
    @Column(unique = true)
    private String email;

    @Min(value = 16, message = "La edad mínima permitida es 16 años")
    @Max(value = 60, message = "La edad máxima permitida es 60 años")
    private int edad;

    @NotBlank(message = "La carrera es obligatoria")
    private String carrera;
}