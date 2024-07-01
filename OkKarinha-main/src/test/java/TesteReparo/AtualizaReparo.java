package TesteReparo;

import java.math.BigDecimal;

import Modelo.Servico;
import Repositorio.ReparoRepositorio;
import Repositorio.ServicoRepositorio;

public class AtualizaReparo {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		System.out.println(servico.getListaReparo().get(0));
		
		System.out.println("---------------------------");
		
		servico.getListaReparo().get(0).setPreco(new BigDecimal("350.88"));
		ReparoRepositorio.AtualizarReparo(servico.getListaReparo().get(0));
		
		Servico servico2 = ServicoRepositorio.BuscarServicoPorId(3);
		System.out.println(servico2.getListaReparo().get(0));
	}
}
