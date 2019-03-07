package com.rmolinari.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rmolinari.cursomc.domain.Cidade;
import com.rmolinari.cursomc.domain.Cliente;
import com.rmolinari.cursomc.domain.Endereco;
import com.rmolinari.cursomc.domain.Enuns.TipoCliente;
import com.rmolinari.cursomc.dto.ClienteDTO;
import com.rmolinari.cursomc.dto.ClienteNewDTO;
import com.rmolinari.cursomc.repositories.ClienteRepository;
import com.rmolinari.cursomc.repositories.EnderecoRepository;
import com.rmolinari.cursomc.services.excepetions.DataIntegrityException;
import com.rmolinari.cursomc.services.excepetions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private EnderecoRepository repoEnd;
	
	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado, id: " + id+ ", Tipo: "+ Cliente.class.getName());
		}
		return obj;
	}
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);//garante que crie um novo, pois se já existir, ele atualiza e não cria
		repo.save(obj);
		repoEnd.save(obj.getEndereco());
		return obj;
	}

	public Cliente update(Cliente obj) {
		Cliente newObj=  find(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		return repo.save(newObj);
	}

	public void delete(Integer id) {
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cliente com pedidos associados");
		}
	}

	public List<Cliente> findAll() {
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesParPage, String orderBy, String direction){
		PageRequest pageRequest = new PageRequest(page, linesParPage, Direction.valueOf( direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(),objDto.getNome(),objDto.getEmail(),null,null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(),
				TipoCliente.toEnum(objDto.getTipo()));
		
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cli, cid);
		
		cli.getEndereco().add(end);	
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2()!=null)cli.getTelefones().add(objDto.getTelefone2());
		if(objDto.getTelefone3()!=null)cli.getTelefones().add(objDto.getTelefone3());
		
		return cli;
	}
	
	

}
