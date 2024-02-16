package com.udemy.ordem_servico.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class StandardError implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Integer status;
	private Long timestamp;
	private String error;

	public StandardError(Integer status, Long timestamp, String error) {
		this.status = status;
		this.timestamp = timestamp;
		this.error = error;
	}
}
