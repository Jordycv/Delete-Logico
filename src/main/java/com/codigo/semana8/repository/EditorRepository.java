package com.codigo.semana8.repository;

import com.codigo.semana8.model.Categoria;
import com.codigo.semana8.model.Editor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditorRepository extends JpaRepository<Editor,Long> {

    Optional<Editor> findByNombre(String nombre);
}
