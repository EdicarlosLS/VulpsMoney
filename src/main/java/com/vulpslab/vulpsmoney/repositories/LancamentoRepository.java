package com.vulpslab.vulpsmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.models.Lancamento;
import com.vulpslab.vulpsmoney.models.Origem;

import java.util.List;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    List<Lancamento> findByCategoria(Categoria categoria);
    List<Lancamento> findByOrigem(Origem origem);
}
