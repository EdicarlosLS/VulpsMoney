package com.vulpslab.vulpsmoney.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.models.Lancamento;
import com.vulpslab.vulpsmoney.models.Origem;

import java.util.List;


public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{
    List<Lancamento> findByCategoria(Categoria categoria);
    List<Lancamento> findByOrigem(Origem origem);

    @Query("SELECT l FROM Lancamento l WHERE YEAR(l.data) = :year")
    List<Lancamento> findByYear(@Param("year") int year);

    @Query("SELECT l FROM Lancamento l WHERE YEAR(l.data) = :year AND MONTH(l.data) = :month")
    List<Lancamento> findByYearAndMonth(@Param("year") int year, @Param("month") int month);

    @Query("SELECT l FROM Lancamento l WHERE YEAR(l.data) = :year AND MONTH(l.data) = :month AND l.categoria = :categoria")
    List<Lancamento> findByYearAndMonthAndCategoria(@Param("year") int year, @Param("month") int month, @Param("categoria") Categoria categoria);

    @Query("SELECT l FROM Lancamento l WHERE YEAR(l.data) = :year AND l.categoria = :categoria")
    List<Lancamento> findByYearAndCategoria(@Param("year") int year, @Param("categoria") Categoria categoria);
}
