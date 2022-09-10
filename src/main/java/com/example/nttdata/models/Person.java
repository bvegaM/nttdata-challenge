package com.example.nttdata.models;

import com.example.nttdata.util.enums.GenreEnum;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Inheritance
@Table(name = "t_clients")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cli_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "cli_dni", unique = true, nullable = false)
    @Size(min = 10, max = 10, message = "La cedula debe tener 10 digitos")
    @NotNull(message = "La cedula es requerida")
    private String dni;

    @Column(name = "cli_name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "El nombre solo debe contener letras")
    @NotNull(message = "El nombre es requerido")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "cli_genre", nullable = false)
    @NotNull(message = "El genero es requerido")
    private GenreEnum genre;

    @Column(name = "cli_age", nullable = false)
    @Min(value = 18,message = "La edad debe ser minima de 18")
    @Max(value = 100, message = "La edad debe ser maxima de 100")
    @NotNull(message = "La edad es requerida")
    private Integer age;

    @Column(name = "cli_direction", nullable = false)
    @NotNull(message = "La direccion es requerida")
    private String direction;

    @Column(name = "cli_phone", nullable = false)
    @Pattern(regexp = "[0-9]+", message = "El telefono solo debe contener numeros")
    @NotNull(message = "El telefono es requerido")
    private String phone;

    public Person() {
    }
}
