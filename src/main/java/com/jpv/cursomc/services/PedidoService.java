package com.jpv.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.Pedido;
import com.jpv.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj =  repo.findById(id);
		return obj.orElse(null);
	}
}
