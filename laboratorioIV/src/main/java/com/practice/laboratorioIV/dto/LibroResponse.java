package com.practice.laboratorioIV.dto;

import com.practice.laboratorioIV.Model.EstadoLibro;
import com.practice.laboratorioIV.Model.Libro;

public record LibroResponse(

    Long id,
    String titulo,
    String autor,
    String isbn,
    int anioPublicacion,
    EstadoLibro estado
){
public static LibroResponse desde(Libro libro){
    return  new LibroResponse(
  libro.getId(),
  libro.getTitulo(),
  libro.getAutor(),
  libro.getIsbn(),
  libro.getAnioPublicacion(),
  libro.getEstado()


    );
}
}
