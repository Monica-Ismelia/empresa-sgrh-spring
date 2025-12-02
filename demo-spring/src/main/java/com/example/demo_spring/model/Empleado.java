package com.example.demo_spring.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "empleados") // plural recomendado
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 150)
    private String correo;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salario;

    public Empleado() {}

    public Empleado(String nombre, String correo, BigDecimal salario) {
        this.nombre = nombre;
        this.correo = correo;
        this.salario = salario;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }
}

