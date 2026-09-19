package com.practice.laboratorioIV.Model;

public class Libro {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private int anioPublicacion;
    private EstadoLibro estado;

    public Libro(){

    }

    public Libro(Long id, String titulo,String autor,String isbn,int anioPublicacion,EstadoLibro estado){
        this.id=id;
        this.titulo=titulo;
        this.autor=autor;
        this.isbn=isbn;
        this.anioPublicacion=anioPublicacion;
        this.estado=estado;
    }



    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getTitulo(){
        return titulo;
    }
    public void setTitulo(String titulo){
        this.titulo= titulo;
    }
    public String  getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor=autor;
    }

    public String getIsbn(){
        return isbn;
    }

    public void setIsbn(String isbn){
        this.isbn=isbn;
    }

    public int getAnioPublicacion(){
         return anioPublicacion;

    }
    public void setAnioPublicacion(int anioPublicacion){
        this.anioPublicacion=anioPublicacion;
    }

    public EstadoLibro getEstado(){
        return estado;
    }


    public void setEstado(EstadoLibro estado){
        this.estado = estado;
    }



}


