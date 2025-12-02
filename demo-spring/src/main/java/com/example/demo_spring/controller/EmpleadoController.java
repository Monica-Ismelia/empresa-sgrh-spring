package com.example.demo_spring.controller;

import com.example.demo_spring.model.Empleado;
import com.example.demo_spring.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.math.BigDecimal;


@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoRepository repository;

    // ----------------------------------------------
    // GET → Listar
    // ----------------------------------------------
    @GetMapping
    public List<Empleado> listar() {
        return repository.findAll();
    }

    // ----------------------------------------------
    // POST → Crear
    // ----------------------------------------------
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Empleado empleado) {
        if (repository.existsByCorreo(empleado.getCorreo())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado: " + empleado.getCorreo());
        }
        return ResponseEntity.ok(repository.save(empleado));
    }

    // ----------------------------------------------
    // PUT → Actualización completa
    // ----------------------------------------------
    @PutMapping("/{id}")
public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Empleado emp) {
    return repository.findById(id)
            .map(empleado -> {
                // Validación de correo único
                if (!empleado.getCorreo().equals(emp.getCorreo()) &&
                        repository.existsByCorreo(emp.getCorreo())) {
                    return ResponseEntity.badRequest().body("El correo ya está en uso: " + emp.getCorreo());
                }

                empleado.setNombre(emp.getNombre());
                empleado.setCorreo(emp.getCorreo());
                empleado.setSalario(emp.getSalario()); // BigDecimal viene del JSON

                return ResponseEntity.ok(repository.save(empleado));
            })
            .orElse(ResponseEntity.notFound().build());
}

    // ----------------------------------------------
    // PATCH → Actualización parcial (solo campos enviados)
    // ----------------------------------------------
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcial(
            @PathVariable Long id,
            @RequestBody Map<String, Object> cambios) {

        Optional<Empleado> optionalEmp = repository.findById(id);

        if (optionalEmp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Empleado empleado = optionalEmp.get();

        cambios.forEach((campo, valor) -> {
            switch (campo) {

                case "nombre":
                    empleado.setNombre(valor.toString());
                    break;

                case "correo":
                    if (repository.existsByCorreo(valor.toString())
                            && !valor.equals(empleado.getCorreo())) {
                        throw new RuntimeException("El correo ya está registrado: " + valor);
                    }
                    empleado.setCorreo(valor.toString());
                    break;

               case "salario":
                empleado.setSalario(new BigDecimal(valor.toString()));
                 break;

            }
        });

        return ResponseEntity.ok(repository.save(empleado));
    }

    // ----------------------------------------------
    // DELETE → Eliminar
    // ----------------------------------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok("Empleado eliminado");
    }
}
