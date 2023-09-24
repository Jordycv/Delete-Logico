package com.codigo.semana8.controller;

import com.codigo.semana8.model.Autor;
import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import com.codigo.semana8.service.EditorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editores")
public class EditorController {

    private final EditorService editorService;

    public EditorController(EditorService editorService) {
        this.editorService = editorService;
    }

    @GetMapping
    public List<Editor> listarEditores(){

        return editorService.obtenerTodosEditores();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Editor> obtenerEditorPorId(@PathVariable Long id){
        Editor editor = editorService.obtenerEditorXid(id);
        return ResponseEntity.ok(editor);
    }

    @GetMapping("/buscarPorNombre")
    public ResponseEntity<Editor> obtenerEditorPorNombre1(@RequestParam("nombre") String nombre) throws JsonProcessingException {
        Editor editor = editorService.obtenerEditorXnombre(nombre);
        return ResponseEntity.ok(editor);
    }

    @PostMapping("/buscarPorNombre")
    public ResponseEntity<Editor> obtenerEditorPorNombre2(@RequestBody String nombre) throws JsonProcessingException {
        Editor editor = editorService.obtenerEditorXnombre(nombre);
        return ResponseEntity.ok(editor);
    }
    @PostMapping
    public ResponseEntity<Editor> crearEditor(@RequestBody Editor editor){
        Editor nuevoEditor = editorService.crearEditor(editor);
        return new ResponseEntity<>(nuevoEditor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editor> actualizarEditor(@PathVariable Long id, @RequestBody Editor editorActualizado){
        Editor editorAct= editorService.actualizarEditor(id, editorActualizado);
        return ResponseEntity.ok(editorAct);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Editor> eliminarEditor(@PathVariable Long id){
        Editor editorEl = editorService.eliminadoLogicoEditor(id);
        return ResponseEntity.ok(editorEl);
    }

}
