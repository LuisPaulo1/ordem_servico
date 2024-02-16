package com.udemy.ordem_servico.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.ordem_servico.domain.OrdemServico;
import com.udemy.ordem_servico.domain.enuns.Prioridade;
import com.udemy.ordem_servico.domain.enuns.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrdemServicoDTO implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataAbertura;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataFechamento;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Integer prioridade;

	@NotBlank(message = "O campo ID DO CLIENTE é requerido")
	private String observacoes;

	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private Integer status;

	private Integer tecnico;
	private Integer cliente;

	public OrdemServicoDTO(OrdemServico obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.observacoes = obj.getObservacoes();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.status = obj.getStatus().getCodigo();
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCodigo();
	}
	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCodigo();
	}
}
