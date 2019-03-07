package com.rmolinari.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

@Service
public class DBService {

	@Autowired
	private CategoriaRepository catRep;

	@Autowired
	private ItemPedidoRepository itemPedRep;

	@Autowired
	private PagamentoRepository pgtoRep;


	@Autowired
	private PedidoRepository pedRep;

	@Autowired
	private ClienteRepository cliRep;

	@Autowired
	private EnderecoRepository endRep;

	@Autowired
	private ProdutoRepository prodRep;

	@Autowired
	private EstadoRepository estadoRep;

	@Autowired
	private CidadeRepository cidadeRep;

	public void instantiateTestDatabase() throws ParseException {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrônico");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria ");

		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		Produto p4 = new Produto(null,"mesa", 22.00);
		Produto p5 = new Produto(null,"toalha", 33.00);
		Produto p6 = new Produto(null,"colcha", 44.00);
		Produto p7 = new Produto(null,"tv", 2000.00);
		Produto p8 = new Produto(null,"roçadeira", 33.00);
		Produto p9 = new Produto(null,"abajour", 22.00);
		Produto p10 = new Produto(null,"pendente", 55.00);
		Produto p11 = new Produto(null,"shampoo", 44.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2,p4));
		cat3.getProdutos().addAll(Arrays.asList(p5,p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9,p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1,cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));


		catRep.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		prodRep.save(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11));

		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "Osasco", est2);
		Cidade c3 = new Cidade(null, "Campinas ", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));

		estadoRep.save(Arrays.asList(est1,est2));
		cidadeRep.save(Arrays.asList(c1,c2,c3));

		Cliente cli1 = new Cliente(null,"Sua Mãe", "a@g.com", "123456789-12", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null,"Sua Irmã", "i@g.com", "123456789-13", TipoCliente.PESSOAJURIDICA);

		cli1.getTelefones().addAll(Arrays.asList("9123456789", "9111111111"));

		Endereco end1 = new Endereco(null,"Rua x", "23", "casa", "vila x", "06290-000", cli1, c1 );
		Endereco end2 = new Endereco(null,"Rua y", "23", "casa", "vila x", "06290-001", cli1, c2 );

		cliRep.save(Arrays.asList(cli1,cli2));
		endRep.save(Arrays.asList(end1,end2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("22/04/1983 00:00"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("24/04/1989 00:00"), cli1, end2);

		Pagamento pgto1 = new PagamentoComCartao( null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pgto1);


		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("22/04/1983 00:00"), null);
		ped2.setPagamento(pgto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

		pgtoRep.save(Arrays.asList(pgto1,pgto2));
		pedRep.save(Arrays.asList(ped1,ped2));


		ItemPedido ip1 = new ItemPedido(ped1,p1,0.00,1,2000.00);
		ItemPedido ip2 = new ItemPedido(ped1,p3,4.00,1,3000.00);
		ItemPedido ip3 = new ItemPedido(ped2,p2,0.00,5,2000.00);

		ped1.getItensPedido().addAll(Arrays.asList(ip1,ip2));
		ped2.getItensPedido().addAll(Arrays.asList(ip3));

		p1.getItensPedido().addAll(Arrays.asList(ip1));
		p2.getItensPedido().addAll(Arrays.asList(ip3));
		p3.getItensPedido().addAll(Arrays.asList(ip2));

		itemPedRep.save(Arrays.asList(ip1,ip2,ip3));

	}
}
