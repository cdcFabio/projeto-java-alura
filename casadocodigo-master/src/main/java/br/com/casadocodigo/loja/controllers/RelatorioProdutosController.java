package br.com.casadocodigo.loja.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;

@Controller
public class RelatorioProdutosController {
	
	@Autowired
	ProdutoDAO produtoDao;
	
	@RequestMapping(value="/relatorio-produtos", method=RequestMethod.GET)
	@ResponseBody
	public String relatorio(@RequestParam (required = false) String data) throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectMapper objectMapper = new ObjectMapper();
	
		List<Produto> listar =  data!=null ? produtoDao.listar(data) : produtoDao.listar();
	
	    
		String listaJson = objectMapper.writeValueAsString(listar);
		
	
		return "{ \"dataGeracao\":"+Calendar.getInstance().getTimeInMillis()+", \"quantidade\": "+listar.size()+", \"produtos\":"+listaJson+"}";
	 
		
	}
	
}
