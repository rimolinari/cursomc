package com.rmolinari.cursomc.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.rmolinari.cursomc.domain.Cliente;
import com.rmolinari.cursomc.domain.Enuns.TipoCliente;
import com.rmolinari.cursomc.dto.ClienteNewDTO;
import com.rmolinari.cursomc.repositories.ClienteRepository;
import com.rmolinari.cursomc.resources.excepetions.FieldMessage;
import com.rmolinari.cursomc.services.validations.uteis.BR;
public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
		
	}
	@Override
	public boolean isValid(ClienteNewDTO	 objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		// inclua os testes aqui, inserindo erros na lista
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) & !BR.isValidCPF(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ Inválido"));
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) & !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
			list.add(new FieldMessage("cpfOuCnpj", "CPF ou CNPJ Inválido"));
		
		if(repo.findByEmail(objDto.getEmail())!=null) {
			list.add(new FieldMessage("email", "Email já cadastrado"));
		}
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
			.addPropertyNode(e.getFieldName()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}