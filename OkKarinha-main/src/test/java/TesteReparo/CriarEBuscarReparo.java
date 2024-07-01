package TesteReparo;

import java.math.BigDecimal;

import Modelo.Reparo;
import Modelo.Servico;
import Repositorio.ServicoRepositorio;

public class CriarEBuscarReparo {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		
		BigDecimal preco = new BigDecimal("250.45");
		String placa = "ASD852";
		String descricao = "Trocamos o motor da moto por um novo";
		String mecanico = "Fernandinho Cunha Barreiro";
		String modelo  = "MilkRacer";
		
		
		servico.FazerReparo(preco, placa, descricao, mecanico, modelo);
		System.out.println(servico.getListaReparo().get(0));
		
		System.out.println("----------------------------");
		Servico servico2Servico = ServicoRepositorio.BuscarServicoPorId(3);
		for(Reparo r : servico2Servico.getListaReparo()) {
			System.out.println(r);
		}
	}
}
