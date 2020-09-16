package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	UsuarioDAO dao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView listar() {
		
		List<Usuario> usuarios = dao.listar();
		ModelAndView modelAndView = new ModelAndView("usuarios/listaUsuarios");
		modelAndView.addObject("usuarios", usuarios);
		
		return modelAndView;
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	@Transactional
	public ModelAndView gravar( @Valid Usuario usuario, BindingResult result, 
			RedirectAttributes redirectAttributes){
		
	if(dao.buscaPorEmail(usuario.getEmail())!=null){
		result.rejectValue("email", "field.emailcadastrado");
	}
	
	if(result.hasErrors()) {
		return form(usuario);
	}
	
	usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));
	dao.gravar(usuario);
	
	redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");
	
		return new ModelAndView("redirect:/usuarios");
	}
	
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {
		
		return new ModelAndView("usuarios/usuarioForm");
	}

}
