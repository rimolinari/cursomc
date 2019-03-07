package com.rmolinari.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rmolinari.cursomc.domain.Categoria;
import com.rmolinari.cursomc.domain.Cidade;
import com.rmolinari.cursomc.domain.Cliente;
import com.rmolinari.cursomc.domain.Endereco;
import com.rmolinari.cursomc.domain.Estado;
import com.rmolinari.cursomc.domain.ItemPedido;
import com.rmolinari.cursomc.domain.Pagamento;
import com.rmolinari.cursomc.domain.PagamentoComBoleto;
import com.rmolinari.cursomc.domain.PagamentoComCartao;
import com.rmolinari.cursomc.domain.Pedido;
import com.rmolinari.cursomc.domain.Produto;
import com.rmolinari.cursomc.domain.Enuns.EstadoPagamento;
import com.rmolinari.cursomc.domain.Enuns.TipoCliente;
import com.rmolinari.cursomc.repositories.CategoriaRepository;
import com.rmolinari.cursomc.repositories.CidadeRepository;
import com.rmolinari.cursomc.repositories.ClienteRepository;
import com.rmolinari.cursomc.repositories.EnderecoRepository;
import com.rmolinari.cursomc.repositories.EstadoRepository;
import com.rmolinari.cursomc.repositories.ItemPedidoRepository;
import com.rmolinari.cursomc.repositories.PagamentoRepository;
import com.rmolinari.cursomc.repositories.PedidoRepository;
import com.rmolinari.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	


	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
				
	}

}

