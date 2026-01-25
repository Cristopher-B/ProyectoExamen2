package com.instituto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "La cédula es obligatoria")
    @Size(min = 10, max = 10, message = "La cédula debe tener exactamente 10 dígitos")
    @Column(unique = true, nullable = false, length = 10)
    private String cedula;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe incluir un '@' y un dominio válido")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "La carrera es obligatoria")
    @Pattern(
            regexp = "Administración|Marketing|Contabilidad|Farmacia|Desarrollo de software|Turismo",
            message = "Carrera no válida. Debe ser una de las preestablecidas."
    )
    private String carrera;

    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 60, message = "La edad máxima es 60 años")
    private int edad;
}