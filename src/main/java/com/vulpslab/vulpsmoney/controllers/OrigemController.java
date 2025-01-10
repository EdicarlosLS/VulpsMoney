package com.vulpslab.vulpsmoney.controllers;

import java.util.List;
import java.util.Optional;

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

import com.vulpslab.vulpsmoney.models.Lancamento;
import com.vulpslab.vulpsmoney.models.Origem;
import com.vulpslab.vulpsmoney.repositories.LancamentoRepository;
import com.vulpslab.vulpsmoney.repositories.OrigemRepository;

@RestController
public class OrigemController {

    @Autowired
    private OrigemRepository repository;
    
    @Autowired
    private LancamentoRepository lancamentoRepository;

    @GetMapping("/origens")
    public ResponseEntity<List<Origem>> index(){
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping("/origens")
    public ResponseEntity<Origem> store(@RequestBody Origem origem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(origem));
    }

    @GetMapping("/origens/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        Optional<Origem> origemOpt = repository.findById(id);
        if(origemOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Origem " + id + " não encontrada");
        }
        return ResponseEntity.status(HttpStatus.OK).body(origemOpt.get());
    }

    @PutMapping("/origens/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Origem _origem){
        Optional<Origem> origemOpt = repository.findById(id);
        if(origemOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Origem " + id + " não encontrada");
        }
        Origem origem = origemOpt.get();
        origem.setTitulo(_origem.getTitulo());
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(origem));
    }

    @DeleteMapping("/origens/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Origem> origemOpt = repository.findById(id);
        if(origemOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Origem " + id + " não encontrada");
        }

        if(id == 1){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Origem padrão não pode ser deletada.");
        }

        List<Lancamento> lancamentos = lancamentoRepository.findByOrigem(origemOpt.get());
        if(lancamentos.size() > 0){
            Origem origemPadrao = repository.findById(1L).get();
            for (Lancamento lancamento : lancamentos) {
                lancamento.setOrigem(origemPadrao);
                lancamentoRepository.save(lancamento);
            }
        }

        repository.delete(origemOpt.get());
        return ResponseEntity.status(HttpStatus.OK).body("Origem deletada com sucesso.");
    }
}
