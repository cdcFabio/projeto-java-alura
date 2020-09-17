package br.com.casadocodigo.loja.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.dao.RoleDAO;
import br.com.casadocodigo.loja.dao.UsuarioDAO;
import br.com.casadocodigo.loja.models.Role;
import br.com.casadocodigo.loja.models.Usuario;
import br.com.casadocodigo.loja.validation.UsuarioValidation;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioDAO usuarioDao;

	@Autowired
	RoleDAO roleDao;

	@Autowired
	PasswordEncoder pass;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new UsuarioValidation());
	}

	/*********** Lista Usuário *************/
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView listar() {

		ModelAndView modelAndView = new ModelAndView("usuarios/listaUsuarios");
		modelAndView.addObject("usuarios", usuarioDao.listar());

		return modelAndView;
	}

	/*********** Grava Usuário *************/
	@RequestMapping(method = RequestMethod.POST)
	@Transactional
	public ModelAndView gravar(@Valid Usuario usuario, BindingResult result, RedirectAttributes redirectAttributes) {

		if (usuarioDao.buscaPorEmail(usuario.getEmail()) != null) {
			result.rejectValue("email", "field.emailcadastrado");
		}

		if (result.hasErrors()) {
			return form(usuario);
		}

		usuario.setRoles(Arrays.asList(new Role("ROLE_USER")));

		/********************** criptografando senha **************************/
		usuario.setSenha(pass.encode(usuario.getSenha()));
		usuario.setConfirmasenha(pass.encode(usuario.getConfirmasenha()));

		usuarioDao.gravar(usuario);

		redirectAttributes.addFlashAttribute("message", "Usuario cadastrado com sucesso!");

		return new ModelAndView("redirect:/usuarios");
	}

	/*********** Formulário para cadastro de Usuário *************/
	@RequestMapping("/form")
	public ModelAndView form(Usuario usuario) {

		return new ModelAndView("usuarios/usuarioForm");
	}

	/*********** Carrega permissões Usuário *************/
	@RequestMapping(value = "/roles", method = RequestMethod.GET)
	public ModelAndView roles(@RequestParam(required = true) String email, RedirectAttributes redirectAttributes) {

		Usuario user = usuarioDao.buscaPorEmail(email);
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", "Usuario não encontrado com o e-mail = "+email);
			return new ModelAndView("redirect:/usuarios");
		}

		ModelAndView modelAndView = new ModelAndView("usuarios/alteraRole");

		modelAndView.addObject("usuario", user);

		modelAndView.addObject("lista", roleDao.listar());

		return modelAndView;
	}

	/*********** Altera permissões Usuário *************/

	@RequestMapping(value = "/roles", method = RequestMethod.POST)
	@Transactional
	public ModelAndView alterar(@ModelAttribute("usuario") Usuario user, String email) {
		
		Usuario usuarioAtual = usuarioDao.buscaPorEmail(email);
		usuarioAtual.setRoles(user.getRoles());
		usuarioDao.gravar(usuarioAtual);

		return new ModelAndView("redirect:/usuarios");

	}

}
