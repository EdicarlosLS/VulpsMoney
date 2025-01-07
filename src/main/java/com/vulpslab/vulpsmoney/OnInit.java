package com.vulpslab.vulpsmoney;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Repository;

import com.vulpslab.vulpsmoney.models.Categoria;
import com.vulpslab.vulpsmoney.models.Origem;
import com.vulpslab.vulpsmoney.repositories.CategoriaRepository;
import com.vulpslab.vulpsmoney.repositories.OrigemRepository;

@Repository
public class OnInit implements ApplicationRunner{

	private CategoriaRepository categoriaRepository;
	
	private OrigemRepository origemRepository;

    public OnInit(CategoriaRepository categoriaRepository, OrigemRepository origemRepository) {
        this.categoriaRepository = categoriaRepository;
        this.origemRepository = origemRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Optional<Categoria> categoriaOp = categoriaRepository.findById(1L);
		if(categoriaOp.isEmpty()){
            Categoria categoria = new Categoria();
            categoria.setTitulo("Sem categoria");
			categoriaRepository.save(categoria);
		}

        Optional<Origem> origemOp = origemRepository.findById(1L);
		if(origemOp.isEmpty()){
            Origem origem = new Origem();
            origem.setTitulo("Carteira");
			origemRepository.save(origem);
		}
        
    }
}
