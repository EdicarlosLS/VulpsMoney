package com.vulpslab.vulpsmoney.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.models.Lancamento;
import com.vulpslab.vulpsmoney.models.Origem;
import com.vulpslab.vulpsmoney.repositories.CategoriaRepository;
import com.vulpslab.vulpsmoney.repositories.LancamentoRepository;
import com.vulpslab.vulpsmoney.repositories.OrigemRepository;

@RestController
public class LancamentoController {

    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private OrigemRepository origemRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    

    @PostMapping("/lancamentos")
    public ResponseEntity<Object> store(@RequestBody Lancamento lancamento){

        Optional<Categoria> categoriaOpt =  categoriaRepository.findById(lancamento.getCategoria().getId());
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria inválida.");
        }

        Optional<Origem> origemOpt = origemRepository.findById(lancamento.getOrigem().getId());
        if(origemOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Origem inválida.");
        }

        lancamento.setCategoria(categoriaOpt.get());
        lancamento.setOrigem(origemOpt.get());
     
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoRepository.save(lancamento));

    }

    @GetMapping("/lancamentos")
    public ResponseEntity<List<Lancamento>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoRepository.findAll());
    }

    @GetMapping("/lancamentos/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        Optional<Lancamento> lancamentoOpt = lancamentoRepository.findById(id);
        if(lancamentoOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lancamento " + id + " não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(lancamentoOpt.get());
    }

    @PutMapping("/lancamentos/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Lancamento _lancamento){
        Optional<Lancamento> lancamentoOpt = lancamentoRepository.findById(id);
        if(lancamentoOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lancamento " + id + " não encontrado.");
        }

        Optional<Categoria> categoriaOpt =  categoriaRepository.findById(_lancamento.getCategoria().getId());
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria inválida.");
        }

        Optional<Origem> origemOpt = origemRepository.findById(_lancamento.getOrigem().getId());
        if(origemOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Origem inválida.");
        }

        Lancamento lancamento = lancamentoOpt.get();
        BeanUtils.copyProperties(_lancamento, lancamento, "id");

        return ResponseEntity.status(HttpStatus.OK).body(lancamentoRepository.save(lancamento));
    }

    @DeleteMapping("/lancamentos/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id){
        Optional<Lancamento> lancamentoOpt = lancamentoRepository.findById(id);
        if(lancamentoOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lancamento " + id + " não encontrado.");
        }
        lancamentoRepository.delete(lancamentoOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lancamento deletado com sucesso.");
    }

    @GetMapping("/lancamentos/resumo")
    public ResponseEntity<Object> summary(){
        double acumulado = 0;
        List<Lancamento> lancamentos = lancamentoRepository.findAll();
        for(Lancamento l : lancamentos){
            acumulado += l.getValor();
        }

        return ResponseEntity.ok(acumulado);
    }

    @GetMapping("/lancamentos/resumo/{year}")
    public ResponseEntity<Object> summaryByYear(@PathVariable int year){
        double acumulado = 0;
        List<Lancamento> lancamentos = lancamentoRepository.findByYear(year);
        for(Lancamento l : lancamentos){
            acumulado += l.getValor();
        }

        return ResponseEntity.ok(acumulado);
    }

    @GetMapping("/lancamentos/resumo/{year}/{month}")
    public ResponseEntity<Object> summaryByYearAndMonth(@PathVariable int year, @PathVariable int month){
        double acumulado = 0;
        List<Lancamento> lancamentos = lancamentoRepository.findByYearAndMonth(year, month);
        for(Lancamento l : lancamentos){
            acumulado += l.getValor();
        }

        return ResponseEntity.ok(acumulado);
    }

    @GetMapping("/lancamentos/resumo/{year}/{month}/categoria/{categoria}")
    public ResponseEntity<Object> summaryByYearAndMonthAndCategoria(@PathVariable int year, @PathVariable int month, @PathVariable String categoria){
        double acumulado = 0;

        List<Categoria> categorias = categoriaRepository.findByTitulo(categoria);
        if(categorias.size() == 0){
            return ResponseEntity.ok(acumulado);
        }

        List<Lancamento> lancamentos = lancamentoRepository.findByYearAndMonthAndCategoria(year, month, categorias.get(0));
        for(Lancamento l : lancamentos){
            acumulado += l.getValor();
        }

        return ResponseEntity.ok(acumulado);
    }
    
    @GetMapping("/lancamentos/resumo/{year}/categoria/{categoria}")
    public ResponseEntity<Object> summaryByYearAndCategoria(@PathVariable int year, @PathVariable String categoria){
        double acumulado = 0;

        List<Categoria> categorias = categoriaRepository.findByTitulo(categoria);
        if(categorias.size() == 0){
            return ResponseEntity.ok(acumulado);
        }

        List<Lancamento> lancamentos = lancamentoRepository.findByYearAndCategoria(year, categorias.get(0));
        for(Lancamento l : lancamentos){
            acumulado += l.getValor();
        }

        return ResponseEntity.ok(acumulado);
    }

}
