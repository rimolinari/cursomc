package com.rmolinari.cursomc.domain;

import javax.persistence.Entity;

import com.rmolinari.cursomc.domain.Enuns.EstadoPagamento;

@Entity
public class PagamentoComCartao extends Pagamento {

	private static final long serialVersionUID = 1L;
	private Integer nParcelas;
	

	public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer nParcelas) {
		super(id, estado, pedido);
		this.nParcelas = nParcelas;
	}
	
	public PagamentoComCartao() {}

	public Integer getnParcelas() {
		return nParcelas;
	}

	public void setnParcelas(Integer nParcelas) {
		this.nParcelas = nParcelas;
	}
	

}
