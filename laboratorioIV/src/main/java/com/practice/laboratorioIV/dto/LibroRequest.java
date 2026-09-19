package com.practice.laboratorioIV.dto;

import com.practice.laboratorioIV.Model.EstadoLibro;

public record LibroRequest(
     String titulo,
     String autor,
     String isbn,
     int anioPublicacion,
     EstadoLibro estado
    ){}

