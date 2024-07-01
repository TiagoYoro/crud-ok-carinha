package TesteProduto;

import java.math.BigDecimal;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class UpdateProduto {
	public static void main(String[] args) {
		Produto produto = ProdutoRepositorio.BuscarProdutoPorId(3);

		System.out.println(produto);

		produto.setPrecoCompra(new BigDecimal("3.75"));
		ProdutoRepositorio.AtualizarProduto(produto);

		Produto produto2 = ProdutoRepositorio.BuscarProdutoPorId(3);
		System.out.println(produto2);

	}
}
