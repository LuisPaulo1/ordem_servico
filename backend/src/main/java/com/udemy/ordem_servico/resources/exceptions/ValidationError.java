package com.udemy.ordem_servico.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ValidationError extends StandardError {

	@Serial
	private static final long serialVersionUID = 1L;

	private final List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Integer status, Long timestamp, String error) {
		super(status, timestamp, error);
	}

	public void addError(String field, String message) {
		this.errors.add(new FieldMessage(field, message));
	}
}