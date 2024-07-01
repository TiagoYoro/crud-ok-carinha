package TesteOferta;

import Modelo.Oferta;
import Modelo.Servico;
import Repositorio.ServicoRepositorio;

public class BuscaOferta {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		for(Oferta oferta : servico.getListaOrfeta()) {
			System.out.println(oferta);
		}
	}
}
