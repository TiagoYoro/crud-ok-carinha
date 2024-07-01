package Repositorio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConectionFabric;
import Modelo.Produto;

public class ProdutoRepositorio {
	// Adiciona um novo produto a tabela produto apartir de objeto Produto
	public static void CriarProduto(Produto p) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO produto (Nome, Quant_Estoque, Preco_Venda, Preco_Compra) VALUES(?, ?, ?, ?)");
			stmt.setString(1, p.getNome());
			stmt.setInt(2, p.getQuantEstoque());
			stmt.setBigDecimal(3, p.getPrecoVenda());
			stmt.setBigDecimal(4, p.getPrecoCompra());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar criar uma linha na tabela produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}

	// Busca um produto pelo Id e devolve em um objeto Produto
	public static Produto BuscarProdutoPorId(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM produto WHERE ID = ?");
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			return CriarObjetoProduto(rs).get(0); 

		} catch (SQLException e) {
			throw new RuntimeException("Erro em buscar um produto do BD: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}

	}

	// Busca todos os valores da tabela produto e devolve em uma lista de Produto
	public static List<Produto> BuscarTodosOsProdutos() {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM produto");
			rs = stmt.executeQuery();

			return CriarObjetoProduto(rs);

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar todos os clientes BD: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Recebe os valores de uma busca e devolve uma lista de Produto.
	private static List<Produto> CriarObjetoProduto(ResultSet rs) {
		try {
			List<Produto> lista = new ArrayList<>();
			while (rs.next()) {
				int Id = rs.getInt("ID");
				String Nome = rs.getString("Nome");
				int QuantEstoque = rs.getInt("Quant_Estoque");
				BigDecimal PrecoVenda = rs.getBigDecimal("Preco_Venda");
				BigDecimal PrecoCompra = rs.getBigDecimal("Preco_Compra");
				Produto produto = new Produto(Id, Nome, QuantEstoque, PrecoVenda, PrecoCompra);
				lista.add(produto);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("Erro em criar umalista deProduto com os valores do banco de dados: " + e);
		}
	}

	// Atualiza na tabela produto os valores recebidos de um objeto Produto
	public static void AtualizarProduto(Produto p) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"UPDATE produto SET Nome = ?, Quant_Estoque = ?, Preco_Venda = ?, Preco_Compra = ? WHERE ID = ?");
			stmt.setString(1, p.getNome());
			stmt.setInt(2, p.getQuantEstoque());
			stmt.setBigDecimal(3, p.getPrecoVenda());
			stmt.setBigDecimal(4, p.getPrecoCompra());
			stmt.setInt(5, p.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualisar a tabela produto no banco de dados: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}

	// Deleta um produto na tabela produto referente ao id
	public static void DeletarProduto(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM produto WHERE ID = ?");
			stmt.setInt(1, id);

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro em deletar um objeto Produto com os valores do banco de dados: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
}
