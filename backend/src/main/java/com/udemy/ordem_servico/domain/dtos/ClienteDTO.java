package com.udemy.ordem_servico.domain.dtos;

import com.udemy.ordem_servico.domain.Cliente;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotBlank(message = "O campo NOME é requerido")
	private String nome;

	@CPF
	@NotBlank(message = "O campo CPF é requerido")
	private String cpf;
	
	@NotBlank(message = "O campo TELEFONE é requerido")
	private String telefone;

	public ClienteDTO(Cliente obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}
}
