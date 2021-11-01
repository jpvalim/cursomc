package com.jpv.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.Produto;
import com.jpv.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repo;
	
	public Produto findById(Integer id) {
		Optional<Produto> obj =  repo.findById(id);
		return obj.orElse(null);
	}
}
