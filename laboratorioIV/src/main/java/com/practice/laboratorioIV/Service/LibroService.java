package com.practice.laboratorioIV.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.practice.laboratorioIV.Model.Libro;
import com.practice.laboratorioIV.dto.LibroRequest;
import com.practice.laboratorioIV.dto.LibroResponse;

@Service
public class LibroService {

    // Lista en memoria para almacenar los libros
    private final List<Libro> libros = new ArrayList<>();
    private Long siguienteId = 1L;

    // 1. Registrar libro
    public LibroResponse registrar(LibroRequest request) {
        boolean isbnDuplicado = libros.stream()
                .anyMatch(l -> l.getIsbn().equalsIgnoreCase(request.isbn()));

        if (isbnDuplicado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un libro con el ISBN: " + request.isbn());
        }

        // Se leen las propiedades del record usando request.propiedad()
        Libro libro = new Libro(
                siguienteId++,
                request.titulo(),
                request.autor(),
                request.isbn(),
                request.anioPublicacion(),
                request.estado()
        );

        libros.add(libro);
        return LibroResponse.desde(libro);
    }

    // 2. Consultar todos los libros
    public List<LibroResponse> consultarTodos() {
        return libros.stream()
                .map(LibroResponse::desde)
                .collect(Collectors.toList());
    }

    // 3. Consultar libro por título
    public LibroResponse consultarPorTitulo(String titulo) {
        return libros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .map(LibroResponse::desde)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró ningún libro con el título: " + titulo));
    }

    // 4. Actualizar libro
    public LibroResponse actualizar(Long id, LibroRequest request) {
        Libro libro = buscarPorId(id);

        libro.setTitulo(request.titulo());
        libro.setAutor(request.autor());
        libro.setIsbn(request.isbn());
        libro.setAnioPublicacion(request.anioPublicacion());
        libro.setEstado(request.estado());

        return LibroResponse.desde(libro);
    }

    // 5. Eliminar libro
    public void eliminar(Long id) {
        Libro libro = buscarPorId(id);
        libros.remove(libro);
    }

    // Método auxiliar privado
    private Libro buscarPorId(Long id) {
        return libros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontró el libro con id: " + id));
    }
}