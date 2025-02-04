package br.com.adrianodib.resource_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author adriano.dib
 *
 */
@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Autor já existe.")
public class AutorAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 100L;
}
