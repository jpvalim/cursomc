package com.jpv.cursomc.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jpv.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping (method=RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "informatica");
		Categoria cat2 = new Categoria(2, "escritorio");
		List<Categoria> lista = new ArrayList<>();
		lista.add(cat1);
		lista.add(cat2);
		return lista;
	}
	
}
