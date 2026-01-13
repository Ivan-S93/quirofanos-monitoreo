package py.gov.hospital.quirofanos_monitor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import py.gov.hospital.quirofanos_monitor.model.Quirofano;
import py.gov.hospital.quirofanos_monitor.repository.QuirofanoRepository;

@RestController
@RequestMapping("/api/quirofanos")
public class QuirofanoController {
    
    private final QuirofanoRepository repository;

    public QuirofanoController(QuirofanoRepository repository) {
        this.repository = repository;
    }

    // Crear quirofanos
    @PostMapping
    public Quirofano crear(@RequestBody Quirofano q) {
        return repository.save(q);
    }

    // Listar quirofanos
    @GetMapping
    public List<Quirofano> listar() {
        return repository.findAll();
    }

    // Obtener por ID ( util luego)
    @GetMapping("/{id}")
    public Quirofano obtener(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Quirofano no encontrado"));
    }

}
