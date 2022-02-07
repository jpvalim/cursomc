package com.jpv.cursomc.dto;

import java.io.Serializable;

import com.jpv.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public ProdutoDTO() {}
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome=obj.getNome();
		preco = obj.getPreco();
	}
		
	private Integer id;
	private String nome;
	private Double preco;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	
	
}
