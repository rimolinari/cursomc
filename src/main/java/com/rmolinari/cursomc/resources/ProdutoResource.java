package com.rmolinari.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rmolinari.cursomc.domain.Categoria;
import com.rmolinari.cursomc.domain.Produto;
import com.rmolinari.cursomc.dto.CategoriaDTO;
import com.rmolinari.cursomc.dto.ProdutoDTO;
import com.rmolinari.cursomc.resources.uteis.URL;
import com.rmolinari.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService service;
	

	
	@RequestMapping(value= "/{id}",method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome", defaultValue="") String nome,
			@RequestParam(value="categorias", defaultValue="") String categorias,
			@RequestParam(value="page", defaultValue="0") Integer page,
			@RequestParam(value="lines", defaultValue="24")Integer linesParPage,
			@RequestParam(value="order", defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		String nomeDecode = URL.decodeParam(nome);
		List<Integer> categoriasList = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecode, categoriasList, page, linesParPage, orderBy, direction);
		Page<ProdutoDTO> listDto = list.map(obj->new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}
}
