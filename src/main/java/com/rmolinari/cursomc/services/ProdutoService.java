package com.rmolinari.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rmolinari.cursomc.domain.Categoria;
import com.rmolinari.cursomc.domain.Produto;
import com.rmolinari.cursomc.repositories.CategoriaRepository;
import com.rmolinari.cursomc.repositories.ProdutoRepository;
import com.rmolinari.cursomc.services.excepetions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository catRepo;
	
	public List<Produto> findAll() {
		return repo.findAll();
	}
	
	public Produto buscar(Integer id) {
		Produto obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id+ ", Tipo: "+ Produto.class.getName());
		}
		return obj;
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesParPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesParPage, Direction.valueOf( direction), orderBy);
		List<Categoria> categorias = catRepo.findAll(ids);
		return repo.search(nome, categorias, pageRequest);
		
	}

}
