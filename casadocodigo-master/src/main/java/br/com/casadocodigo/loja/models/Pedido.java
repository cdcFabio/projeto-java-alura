package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class Pedido {
	
	private int id;
	
	private  BigDecimal valor;
	
	private Calendar data;
	
	private List<Produto> produtos;
	
	

	public Pedido(int id, BigDecimal valor, Calendar data, List<Produto> produtos) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.produtos = produtos;
	}
	
	public Pedido() {};

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}
	
	
	

}
