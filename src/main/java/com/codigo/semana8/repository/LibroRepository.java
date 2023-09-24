package com.codigo.semana8.repository;

import com.codigo.semana8.model.Editor;
import com.codigo.semana8.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    Optional<Libro> findByTitulo(String titulo);
}
