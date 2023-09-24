package com.codigo.semana8.service;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.model.Libro;
import com.codigo.semana8.repository.LibroRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public List<Libro> obtenerTodosLibros(){
        return libroRepository.findAll();
    }

    public Libro obtenerLibroXid(Long id){
        Optional<Libro> libro = libroRepository.findById(id);
        if(libro.isPresent()){
            return libro.get();
        }else {
            throw new RuntimeException("Libro no encontrrado" + id);
        }
    }

    public Libro obtenerLibroXtitulo(String titulo) throws JsonProcessingException {
        Libro libro1 = new Libro();
        if(titulo.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            libro1 = objectMapper.readValue(titulo, Libro.class);
        }else {
            libro1.setTitulo(titulo);
        }
        Optional<Libro> libro = libroRepository.findByTitulo(libro1.getTitulo());

        if(libro.isPresent()){
            return libro.get();
        }else{
            throw new RuntimeException("Libro no encontrado");
        }
    }

    public Libro crearLibro(Libro libro){
        return libroRepository.save(libro);
    }

    public Libro actualizarLibro(Long id, Libro libroActualizado){
        Libro libroExistente=obtenerLibroXid(id);
        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setEstado(libroActualizado.getEstado());
        libroExistente.setAutor(libroActualizado.getAutor());
        libroExistente.setCategorias(libroActualizado.getCategorias());
        libroExistente.setEditor(libroActualizado.getEditor());

        return libroRepository.save(libroExistente);
    }

    public Libro eliminadoLogicoLibro(Long id){
        Libro libroExistente=obtenerLibroXid(id);
        Integer estadoCero=0;
        libroExistente.setEstado(estadoCero);

        return libroRepository.save(libroExistente);
    }

}
