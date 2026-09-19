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
    private final List<Libro> libros = new ArrayList<>();
    private Long siguienteId = 1L;
    
    
public LibroResponse registrar(LibroRequest request) {
        boolean isbnDuplicado = libros.stream()
                .anyMatch(l -> l.getIsbn().equalsIgnoreCase(request.isbn()));

        if (isbnDuplicado) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Ya existe un libro con el ISBN: " + request.isbn());
        }

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




    public List<LibroResponse> consuLibroTodos(){
        return libros.stream()
                 .map(LibroResponse::desde)
                 .collect(Collectors.toList());
    }

    public LibroResponse consultarPorTitulo(String titulo){
    return libros.stream()
            .filter(l -> l.getTitulo().equalsIgnoreCase(titulo))
            .findFirst()
            .map(LibroResponse::desde)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "No se encontro ningun libro con el titulo: "+ titulo
            ));
        }


        public LibroResponse actualizar(Long id,LibroRequest request){
            Libro libro = buscarPorId(id);


            libro.setTitulo(request.titulo());
            libro.setAutor(request.autor());
            libro.setIsbn(request.isbn());
            libro.setAnioPublicacion.(request.anioPublicacion());
            libro.setEstado(request.estado());
            return LibroResponse.desde(libro);

        }


    
}
