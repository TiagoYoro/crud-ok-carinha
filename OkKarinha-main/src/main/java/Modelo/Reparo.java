package Modelo;

import java.math.BigDecimal;

public class Reparo {

	private int Id;
	private BigDecimal Preco;
	private String Placa;
	private String Descricao;
	private String Mecanico;
	private String Modelo;

	public Reparo(int id, BigDecimal preco, String placa, String descricao, String mecanico, String modelo) {
		super();
		Id = id;
		Preco = preco;
		Placa = placa;
		Descricao = descricao;
		Mecanico = mecanico;
		Modelo = modelo;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public BigDecimal getPreco() {
		return Preco;
	}

	public void setPreco(BigDecimal preco) {
		Preco = preco;
	}

	public String getPlaca() {
		return Placa;
	}

	public void setPlaca(String placa) {
		Placa = placa;
	}

	public String getDescricao() {
		return Descricao;
	}

	public void setDescricao(String descricao) {
		Descricao = descricao;
	}

	public String getMecanico() {
		return Mecanico;
	}

	public void setMecanico(String mecanico) {
		Mecanico = mecanico;
	}

	public String getModelo() {
		return Modelo;
	}

	public void setModelo(String modelo) {
		Modelo = modelo;
	}

	@Override
	public String toString() {
		return "ReparoDeMoto [Id=" + Id + ", Preco=" + Preco + ", Placa=" + Placa + ", Descricao=" + Descricao
				+ ", Mecanico=" + Mecanico + ", Modelo=" + Modelo + "]";
	}

}
