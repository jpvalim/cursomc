package com.jpv.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.jpv.cursomc.domain.Estado;
import com.jpv.cursomc.dto.EstadoDTO;
import com.jpv.cursomc.repositories.EstadoRepository;
import com.jpv.cursomc.services.exceptions.DataIntegrityException;
import com.jpv.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repo;

	public Estado findById(Integer id) {
		Optional<Estado> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Estado.class.getName()));
	}
	
	public Estado insert(Estado obj) {
		obj.setId(null);
		return repo.save(obj);
		
	}
	
	public Estado update(Estado obj) {
		Estado newObj = findById(obj.getId());
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
	
	public List<Estado> findAllOrderByNome(){
		return repo.findAllByOrderByNome();
	}
	
		
	public Estado fromDTO(EstadoDTO objDto) {
		return new Estado(objDto.getId(), objDto.getNome());
	}
	
	private void updateData(Estado newObj, Estado obj) {
		newObj.setNome(obj.getNome());
	}
}
