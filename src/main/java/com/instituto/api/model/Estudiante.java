package com.instituto.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

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
    @Size(min = 10, message = "La cédula debe tener al menos 10 dígitos")
    @Column(unique = true)
    private String cedula;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe incluir un '@' y un dominio válido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "La carrera es obligatoria")
    @Pattern(
            regexp = "Administración|Marketing|Contabilidad|Farmacia|Desarrollo de software|Turismo",
            message = "Carrera no válida. Debe ser una de las preestablecidas."
    )
    private String carrera;

    @Min(18)
    @Max(60)
    private int edad;
}
