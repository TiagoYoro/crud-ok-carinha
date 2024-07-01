package Modelo;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Repositorio.OfertaRepositorio;
import Repositorio.ReparoRepositorio;

public class Servico {

	int Id;
	Cliente Cliente;
	Date Data;
	List<Oferta> ListaOrfeta = new ArrayList<>();
	List<Reparo> ListaReparo = new ArrayList<>();

	public Servico(int id, Modelo.Cliente cliente, Date data) {
		super();
		Id = id;
		Cliente = cliente;
		Data = data;
		ListaOrfeta = OfertaRepositorio.BuscarTodasAsOfertasDoServico(id);
		ListaReparo = ReparoRepositorio.BuscarTodosOsReparosDoServico(id);
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Date getData() {
		return Data;
	}

	public void setData(Date data) {
		Data = data;
	}

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente cliente) {
		Cliente = cliente;
	}

	public List<Oferta> getListaOrfeta() {
		return ListaOrfeta;
	}

	public void setListaOrfeta(List<Oferta> listaOrfeta) {
		this.ListaOrfeta = listaOrfeta;
	}

	public List<Reparo> getListaReparo() {
		return ListaReparo;
	}

	public void setListaReparo(List<Reparo> listaReparo) {
		ListaReparo = listaReparo;
	}

	@Override
	public String toString() {
		return "Servico [Id=" + Id + ", Cliente=" + Cliente + ", Data=" + Data + ", listaOrfeta=" + ListaOrfeta + "]";
	}

	public void ComprarProduto(Produto p, int quantidade) {
		int ofertaId = OfertaRepositorio.CriarOferta(this, p, quantidade);
		this.ListaOrfeta.add(OfertaRepositorio.BuscarOfertaPorId(ofertaId));

	}

	public void FazerReparo(BigDecimal preco, String placa, String descricao, String mecanico, String modelo) {
		int ReparoId = ReparoRepositorio.CriarReparo(this, preco, placa, descricao, mecanico, modelo);
		this.ListaReparo.add(ReparoRepositorio.BuscarReparoPorId(ReparoId));
	}
}
