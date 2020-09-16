package br.com.casadocodigo.loja.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Usuario;

public class UsuarioValidation implements Validator{

	
	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "senha", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "confirmasenha", "field.required");
		
		
		Usuario usuario = (Usuario) target;
		
		if(usuario.getSenha().length()<5) {
			errors.rejectValue("senha", "field.length");
		}
			
		if(!(usuario.getSenha().equals(usuario.getConfirmasenha()))) {
			errors.rejectValue("confirmasenha", "field.diferent");
		}		

	}
	
}
