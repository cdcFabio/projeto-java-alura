package br.com.casadocodigo.loja.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.casadocodigo.loja.models.Pedido;

@Controller
public class PedidosServicoController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping("/pedidos")
	public ModelAndView listarPedidos() {
		
		ModelAndView modelAndView = new ModelAndView("pedidos");
		
		String uri = "https://book-payment.herokuapp.com/orders";
	    String resposta = restTemplate.getForObject(uri, String.class);

		List<Pedido> pedidosImportados=null;
		
		try {
			pedidosImportados = carregaLista(resposta);
		} catch (IOException e) {
			throw new RuntimeException("Formato não comprendido");
		}
		
		
		modelAndView.addObject("pedidos",pedidosImportados);
		
		return modelAndView;
	}
	
	
	
	private List<Pedido> carregaLista(String Json) throws JsonParseException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		return mapper.readValue(Json, new TypeReference<List<Pedido>>(){});
	}
			
	

}
