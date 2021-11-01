package com.jpv.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.Categoria;
import com.jpv.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria findById(Integer id) {
		Optional<Categoria> obj =  repo.findById(id);
		return obj.orElse(null);
	}
}
