package com.codigo.semana8.service;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import com.codigo.semana8.repository.EditorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditorService {

    private final EditorRepository editorRepository;

    public EditorService(EditorRepository editorRepository) {
        this.editorRepository = editorRepository;
    }

    public List<Editor> obtenerTodosEditores(){
        return editorRepository.findAll();
    }

    public Editor obtenerEditorXid(Long id){
        Optional<Editor> editor = editorRepository.findById(id);
        if(editor.isPresent()){
            return editor.get();
        }else {
            throw new RuntimeException("Editor no encontrrado"+id);
        }
    }

    public Editor obtenerEditorXnombre(String nombre) throws JsonProcessingException {
        Editor editor1 = new Editor();
        if(nombre.contains("{")){
            ObjectMapper objectMapper = new ObjectMapper();
            editor1 = objectMapper.readValue(nombre, Editor.class);
        }else {
            editor1.setNombre(nombre);
        }
        Optional<Editor> editor = editorRepository.findByNombre(editor1.getNombre());

        if(editor.isPresent()){
            return editor.get();
        }else{
            throw new RuntimeException("Editor no encontrado");
        }
    }

    public Editor crearEditor(Editor editor){
        return editorRepository.save(editor);
    }

    public Editor actualizarEditor(Long id, Editor editorActualizado){
        Editor editorExistente=obtenerEditorXid(id);
        editorExistente.setNombre(editorActualizado.getNombre());
        editorExistente.setEstado(editorActualizado.getEstado());

        return editorRepository.save(editorExistente);
    }

    public Editor eliminadoLogicoEditor(Long id){
        Editor editorExistente=obtenerEditorXid(id);
        Integer estadoCero=0;
        editorExistente.setEstado(estadoCero);

        return editorRepository.save(editorExistente);
    }

}
