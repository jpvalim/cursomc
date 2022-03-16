package com.jpv.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.Cidade;
import com.jpv.cursomc.repositories.CidadeRepository;
import com.jpv.cursomc.services.exceptions.DataIntegrityException;
import com.jpv.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;

	public Cidade findById(Integer id) {
		Optional<Cidade> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cidade.class.getName()));
	}
	
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Cidade update(Cidade obj) {
		Cidade newObj = findById(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	
	public void delete(Integer id) {
		findById(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Cidade> findCidades(Integer idEstado){
		return repo.findCidades(idEstado);
	}
	
		
		
	private void updateData(Cidade newObj, Cidade obj) {
		newObj.setNome(obj.getNome());
	}
}
