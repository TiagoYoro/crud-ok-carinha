package TesteOferta;

import Modelo.Oferta;
import Modelo.Servico;
import Repositorio.OfertaRepositorio;
import Repositorio.ProdutoRepositorio;
import Repositorio.ServicoRepositorio;

public class AtualizarOferta {
	public static void main(String[] args) {
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		for (Oferta oferta : servico.getListaOrfeta()) {
			System.out.println(oferta);
		}
		servico.getListaOrfeta().get(2).setQuantidade(2);
		servico.getListaOrfeta().get(2).setProduto(ProdutoRepositorio.BuscarProdutoPorId(1));
		
		System.out.println("-------------------------");
		
		for (Oferta oferta : servico.getListaOrfeta()) {
			OfertaRepositorio.AtualizarOferta(oferta);
			System.out.println(oferta);
		}
		
		System.out.println("-------------------------");
		
		Servico servico2 = ServicoRepositorio.BuscarServicoPorId(3);
		for (Oferta oferta : servico2.getListaOrfeta()) {
			System.out.println(oferta);
		}
	}
}
