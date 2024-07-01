package TesteProduto;

import java.util.List;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class ReadProduto {
	public static void main(String[] args) {
		//Produto produto1 = ProdutoRepositorio.BuscarProdutoPorId(7);
		
		//System.out.println(produto1);
		
		List<Produto> ListaProdutos = ProdutoRepositorio.BuscarTodosOsProdutos();
		for (Produto produto : ListaProdutos) {
			System.out.println(produto);
		}
	}

}
