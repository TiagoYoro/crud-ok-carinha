package TesteOferta;

import Modelo.Produto;
import Modelo.Servico;
import Repositorio.ProdutoRepositorio;
import Repositorio.ServicoRepositorio;

public class CriarOferta {
	public static void main(String[] args) {		
		Servico servico = ServicoRepositorio.BuscarServicoPorId(3);
		
		Produto produto = ProdutoRepositorio.BuscarProdutoPorId(3);
		
		servico.ComprarProduto(produto, 3);
		
		System.out.println(servico.getListaOrfeta().get(0));
	}
}
