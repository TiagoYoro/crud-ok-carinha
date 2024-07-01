package TesteReparo;

import Modelo.Reparo;
import Modelo.Servico;
import Repositorio.ReparoRepositorio;
import Repositorio.ServicoRepositorio;

public class DeletaReparo {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(1);
		for(Reparo r : servico.getListaReparo()) {
			System.out.println(r);
		}
		
		System.out.println("--------------------");
		Reparo reparo = servico.getListaReparo().get(0);
		ReparoRepositorio.DeletarReparo(reparo.getId());
		
		Servico servico2 = ServicoRepositorio.BuscarServicoPorId(1);
		for(Reparo r : servico2.getListaReparo()) {
			System.out.println(r);
		}
	}
}
