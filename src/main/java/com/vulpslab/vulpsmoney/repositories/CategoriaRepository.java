package com.vulpslab.vulpsmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vulpslab.vulpsmoney.models.Categoria;
import java.util.List;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByTitulo(String titulo);
}
