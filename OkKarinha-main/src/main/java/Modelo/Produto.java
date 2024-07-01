package Modelo;

import java.math.BigDecimal;

public class Produto {
	
	private int Id;
	private String Nome;
	private int QuantEstoque;
	private BigDecimal PrecoVenda;
	private BigDecimal PrecoCompra;
	
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda, BigDecimal precoCompra) {
		super();
		Id = id;
		Nome = nome;
		QuantEstoque = quantEstoque;
		PrecoVenda = precoVenda;
		PrecoCompra  = precoCompra;
	}
	public Produto(int id, String nome, int quantEstoque, BigDecimal precoVenda) {
		super();
		Id = id;
		Nome = nome;
		QuantEstoque = quantEstoque;
		PrecoVenda = precoVenda;
		PrecoCompra  = null;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public int getQuantEstoque() {
		return QuantEstoque;
	}
	public void setQuantEstoque(int quantEstoque) {
		QuantEstoque = quantEstoque;
	}
	public BigDecimal getPrecoVenda() {
		return PrecoVenda;
	}
	public void setPrecoVenda(BigDecimal precoVenda) {
		PrecoVenda = precoVenda;
	}
	public BigDecimal getPrecoCompra() {
		return PrecoCompra;
	}
	public void setPrecoCompra(BigDecimal precoCompra) {
		PrecoCompra = precoCompra;
	}
	@Override
	public String toString() {
		return "Produto [Id=" + Id + ", Nome=" + Nome + ", QuantEstoque=" + QuantEstoque + ", PrecoVenda=" + PrecoVenda
				+ ", PrecoCompra=" + PrecoCompra + "]";
	}
	
	
}
