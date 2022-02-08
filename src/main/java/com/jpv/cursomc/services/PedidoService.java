package com.jpv.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.ItemPedido;
import com.jpv.cursomc.domain.PagamentoComBoleto;
import com.jpv.cursomc.domain.Pedido;
import com.jpv.cursomc.domain.enums.EstadoPagamento;
import com.jpv.cursomc.repositories.ItemPedidoRepository;
import com.jpv.cursomc.repositories.PagamentoRepository;
import com.jpv.cursomc.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public Pedido findById(Integer id) {
		Optional<Pedido> obj =  repo.findById(id);
		return obj.orElse(null);
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		
		//se o pagamento for boleto dependeria de um webservice que gera um boleto. Mas no caso aqui irá somente calcular a data de vencimento do boleto
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		
		repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.findById(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
			
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
				
		return obj;
		
	}
}
