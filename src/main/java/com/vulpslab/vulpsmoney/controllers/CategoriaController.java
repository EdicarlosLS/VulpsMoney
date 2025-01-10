package com.vulpslab.vulpsmoney.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.models.Lancamento;
import com.vulpslab.vulpsmoney.repositories.CategoriaRepository;
import com.vulpslab.vulpsmoney.repositories.LancamentoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> index() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Categoria> store(@RequestBody Categoria categoria){
     return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));   
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        Optional<Categoria> categoriaOpt =  repository.findById(id);
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria " + id + " não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoriaOpt.get());
    }
    
    @PutMapping("/categorias/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Categoria _categoria) {
        Optional<Categoria> categoriaOpt =  repository.findById(id);
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria " + id + " não encontrada.");
        }
        
        Categoria categoria = categoriaOpt.get();
        categoria.setTitulo(_categoria.getTitulo());
        
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(categoria));
        
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity<String> destroy(@PathVariable Long id){
        Optional<Categoria> categoriaOpt =  repository.findById(id);
        if(categoriaOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria " + id + " não encontrada.");
        }

        if(id == 1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Categoria padrão não pode ser deletada.");
        }

        List<Lancamento> lancamentos = lancamentoRepository.findByCategoria(categoriaOpt.get());
        if(lancamentos.size() > 0){
            Categoria categoriaPadrao = repository.findById(1L).get();
            for (Lancamento lancamento : lancamentos) {
                lancamento.setCategoria(categoriaPadrao);
                lancamentoRepository.save(lancamento);
            }
        }

        repository.delete(categoriaOpt.get());
        
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso.");
    }
}
