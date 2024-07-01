package TesteProduto;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class DeleteProduto {
	public static void main(String[] args) {
		Produto p = ProdutoRepositorio.BuscarProdutoPorId(4);

		System.out.println(p);

		ProdutoRepositorio.DeletarProduto(p.getId());
	}
}
