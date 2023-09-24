package com.codigo.semana8.service;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.repository.CategoriaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> obtenerTodosCategoria(){
        return categoriaRepository.findAll();
    }

    public Categoria obtenerCategoriaXid(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if(categoria.isPresent()){
            return categoria.get();
        }else {
            throw new RuntimeException("Categoria no encontrrado");
        }
    }

    public Categoria obtenerCategoriaXnombre(String nombre) throws JsonProcessingException {
        Categoria categoria1 = new Categoria();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            categoria1 = objectMapper.readValue(nombre, Categoria.class);
        }else {
            categoria1.setNombre(nombre);
        }
        Optional<Categoria> categoria = categoriaRepository.findByNombre(categoria1.getNombre());

        if(categoria.isPresent()){
            return categoria.get();
        }else{
            throw new RuntimeException("Categoria no encontrado");
        }
    }


    public Categoria crearCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria actualizarCategoria(Long id, Categoria categoriaActualizado){
        Categoria categoriaExistente=obtenerCategoriaXid(id);
        categoriaExistente.setNombre(categoriaActualizado.getNombre());
        categoriaExistente.setEstado(categoriaActualizado.getEstado());

        return categoriaRepository.save(categoriaExistente);
    }

    public Categoria eliminadoLogicoCategoria(Long id){
        Categoria categoriaExistente=obtenerCategoriaXid(id);
        Integer estadoCero=0;
        categoriaExistente.setEstado(estadoCero);

        return categoriaRepository.save(categoriaExistente);
    }


}
