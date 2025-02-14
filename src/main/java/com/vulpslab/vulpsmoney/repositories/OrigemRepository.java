package com.vulpslab.vulpsmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vulpslab.vulpsmoney.models.Origem;
import java.util.List;


public interface OrigemRepository extends JpaRepository<Origem, Long>{
    List<Origem> findByTitulo(String titulo);
}
