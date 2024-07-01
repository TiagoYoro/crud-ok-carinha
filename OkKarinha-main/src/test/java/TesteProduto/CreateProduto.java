package TesteProduto;

import java.math.BigDecimal;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class CreateProduto {

	public static void main(String[] args) {
		Produto produto = new Produto(3, "Oleo", 15, new BigDecimal("10.00"));
		Produto produto2 = new Produto(4, "pano", 15, new BigDecimal("12.00"), new BigDecimal("4.50"));
		ProdutoRepositorio.CriarProduto(produto);
		ProdutoRepositorio.CriarProduto(produto2);
	}
}
