package com.codigo.semana8.controller;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import com.codigo.semana8.service.CategoriaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }


    @GetMapping
    public List<Categoria> listarCategorias(){

        return categoriaService.obtenerTodosCategoria();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerCategoriaPorId(@PathVariable Long id){
        Categoria categoria = categoriaService.obtenerCategoriaXid(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<Categoria> obtenerCategoriaPorNombre1(@RequestParam("nombre") String nombre) throws JsonProcessingException {
        Categoria categoria = categoriaService.obtenerCategoriaXnombre(nombre);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Categoria> obtenerCategoriaPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Categoria categoria = categoriaService.obtenerCategoriaXnombre(nombre);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria){
        Categoria nuevoCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevoCategoria, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaActualizado){
        Categoria categoriaAct = categoriaService.actualizarCategoria(id, categoriaActualizado);
        return ResponseEntity.ok(categoriaAct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long id){
        Categoria categoriaEl = categoriaService.eliminadoLogicoCategoria(id);
        return ResponseEntity.ok(categoriaEl);
    }


}
