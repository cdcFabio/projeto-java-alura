package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Role;

public class RoleValidation implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
	

	}
	
}
