package com.rmolinari.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rmolinari.cursomc.domain.ItemPedido;
import com.rmolinari.cursomc.domain.PagamentoComBoleto;
import com.rmolinari.cursomc.domain.Pedido;
import com.rmolinari.cursomc.domain.Enuns.EstadoPagamento;
import com.rmolinari.cursomc.repositories.ItemPedidoRepository;
import com.rmolinari.cursomc.repositories.PagamentoRepository;
import com.rmolinari.cursomc.repositories.PedidoRepository;
import com.rmolinari.cursomc.repositories.ProdutoRepository;
import com.rmolinari.cursomc.services.excepetions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private ProdutoRepository prodRepo;
	
	@Autowired
	private PagamentoRepository pgtoRepo;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private BoletoService boletoService;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id+ ", Tipo: "+ Pedido.class.getName());
		}
		return obj;
	}
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento()instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgtoBoleto = (PagamentoComBoleto)obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgtoBoleto, obj.getInstante());
		}
		obj = repo.save(obj);
		pgtoRepo.save(obj.getPagamento());
		for(ItemPedido item: obj.getItensPedido()) {
			item.setDesconto(0.0);
			item.setPreco(prodRepo.findOne(item.getProduto().getId()).getPreco());
			item.setPedido(obj);
		}
		itemRepo.save(obj.getItensPedido());
		return obj;
		
	}

}








