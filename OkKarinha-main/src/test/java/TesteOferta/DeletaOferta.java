package TesteOferta;

import Modelo.Oferta;
import Modelo.Servico;
import Repositorio.OfertaRepositorio;
import Repositorio.ServicoRepositorio;

public class DeletaOferta {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		
		for (Oferta oferta : servico.getListaOrfeta()) {
			System.out.println(oferta);
		}
		
		OfertaRepositorio.DeletarOferta(servico.getListaOrfeta().get(1).getId());
		System.out.println("-----------------------------");
		
		Servico servico2 = ServicoRepositorio.BuscarServicoPorId(3);
		
		for (Oferta oferta : servico2.getListaOrfeta()) {
			System.out.println(oferta);
		}
	}
}
