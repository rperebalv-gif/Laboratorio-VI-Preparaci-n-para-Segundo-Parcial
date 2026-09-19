package com.practice.laboratorioIV.Controller;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.laboratorioIV.Service.LibroService;
import com.practice.laboratorioIV.dto.LibroRequest;
import com.practice.laboratorioIV.dto.LibroResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    // POST /libros -> 201
    @PostMapping
    public ResponseEntity<LibroResponse> registrar(@RequestBody LibroRequest request) {
        LibroResponse creado = libroService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // GET /libros -> 200
    @GetMapping
    public ResponseEntity<List<LibroResponse>> consultarTodos() {
        return ResponseEntity.ok(libroService.consultarTodos());
    }

    // GET /libros/titulo/{titulo} -> 200 / 404
    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LibroResponse> consultarPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(libroService.consultarPorTitulo(titulo));
    }

    // PUT /libros/{id} -> 200 / 404
    @PutMapping("/{id}")
    public ResponseEntity<LibroResponse> actualizar(@PathVariable Long id, @RequestBody LibroRequest request) {
        return ResponseEntity.ok(libroService.actualizar(id, request));
    }

    // DELETE /libros/{id} -> 204 / 404
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
