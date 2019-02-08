package com.rmolinari.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmolinari.cursomc.domain.Pedido;
import com.rmolinari.cursomc.repositories.PedidoRepository;
import com.rmolinari.cursomc.services.Excepetions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id+ ", Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}

}
