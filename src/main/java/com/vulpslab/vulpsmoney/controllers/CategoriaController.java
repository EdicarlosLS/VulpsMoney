package com.vulpslab.vulpsmoney.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.repositories.CategoriaRepository;

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
        repository.delete(categoriaOpt.get());
        
        return ResponseEntity.status(HttpStatus.OK).body("Categoria deletada com sucesso.");
    }
}
