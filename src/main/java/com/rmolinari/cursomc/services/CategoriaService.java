package com.rmolinari.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rmolinari.cursomc.domain.Categoria;
import com.rmolinari.cursomc.dto.CategoriaDTO;
import com.rmolinari.cursomc.repositories.CategoriaRepository;
import com.rmolinari.cursomc.services.excepetions.DataIntegrityException;
import com.rmolinari.cursomc.services.excepetions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id+ ", Tipo: "+ Categoria.class.getName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);//garante que crie um novo, pois se já existir, ele atualiza e não cria
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());//se nao achar, lança exceção
		return repo.save(obj);
	}

	public void delete(Integer id) {
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos associados");
		}
	}

	public List<Categoria> findAll() {
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesParPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesParPage, Direction.valueOf( direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(),objDto.getNome());
	}
	
		
	}


