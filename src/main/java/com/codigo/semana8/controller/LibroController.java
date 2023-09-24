package com.codigo.semana8.controller;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.model.Libro;
import com.codigo.semana8.service.LibroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public List<Libro> listarLibros(){

        return libroService.obtenerTodosLibros();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable Long id){
        Libro libro = libroService.obtenerLibroXid(id);
        return ResponseEntity.ok(libro);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<Libro> obtenerLibroPorNombre1(@RequestParam("titulo") String titulo) throws JsonProcessingException {
        Libro libro = libroService.obtenerLibroXtitulo(titulo);
        return ResponseEntity.ok(libro);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Libro> obtenerLibroPorNombre2(@RequestBody String titulo) throws JsonProcessingException {
        Libro libro = libroService.obtenerLibroXtitulo(titulo);
        return ResponseEntity.ok(libro);
    }

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro){
        Libro nuevoLibro = libroService.crearLibro(libro);
        return new ResponseEntity<>(nuevoLibro, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long id, @RequestBody Libro libroActualizado){
        Libro libroAct= libroService.actualizarLibro(id, libroActualizado);
        return ResponseEntity.ok(libroAct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Libro> eliminarLibro(@PathVariable Long id){
        Libro libroEl = libroService.eliminadoLogicoLibro(id);
        return ResponseEntity.ok(libroEl);
    }
}
