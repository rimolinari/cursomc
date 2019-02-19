package com.rmolinari.cursomc.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.rmolinari.cursomc.domain.Cliente;

public class ClienteDTO implements Serializable{

		private static final long serialVersionUID = 1L;
		
		private Integer id;
		
		@NotEmpty(message="obrigatório")
		@Length(min=5, max=500, message="entre 5 e 50 carac")
		private String nome;
		
		@NotEmpty(message="obrigatório")
		@Length(min=5, max=50, message="entre 5 e 50 carac")
		@Email(message="email inválido")
		private String email;

		public ClienteDTO() {}
		
		public ClienteDTO(Cliente obj) {
			nome = obj.getNome();
			id = obj.getId();
			email = obj.getEmail();
		}
		

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

}
