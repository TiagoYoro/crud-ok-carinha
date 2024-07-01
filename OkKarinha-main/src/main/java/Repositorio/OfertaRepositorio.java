package Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConectionFabric;
import Modelo.Oferta;
import Modelo.Produto;
import Modelo.Servico;

public class OfertaRepositorio {
	// Cria uma nova linha na tabela relacao_servico_produto
	public static int CriarOferta(Servico s, Produto p, int quantidade) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(
					"INSERT INTO relacao_servico_produto (Servico_ID, Produto_ID, Quant) VALUES(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, s.getId());
			stmt.setInt(2, p.getId());
			stmt.setInt(3, quantidade);

			stmt.executeUpdate();

			// Obtém o Id gerado
			rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				int novoId = rs.getInt(1);
				return novoId; // Retorna o Id gerado
			} else {
				throw new RuntimeException("Não foi possível obter o Id gerado.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar criar uma linha na relacao_servico_produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca uma orfeta pelo id e devolve em um objeto orfeta
	public static Oferta BuscarOfertaPorId(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM relacao_servico_produto WHERE ID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			;
			return CriarObjetoOrfeta(rs).get(0);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar valores na tabela relacao_servico_produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca todos os valores da tabela relacao_servico_produto e devolve em uma
	public static List<Oferta> BuscarTodasAsOfertasDoServico(int idS) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM relacao_servico_produto WHERE Servico_ID = ?");
			stmt.setInt(1, idS);
			rs = stmt.executeQuery();
			return CriarObjetoOrfeta(rs);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar valores na tabela relacao_servico_produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	private static List<Oferta> CriarObjetoOrfeta(ResultSet rs) {
		try {
			List<Oferta> lista = new ArrayList<>();
			while (rs.next()) {
				int Id = rs.getInt("ID");
				int quantidade = rs.getInt("Quant");
				Produto produto = ProdutoRepositorio.BuscarProdutoPorId(rs.getInt("Produto_ID"));
				Oferta oferta = new Oferta(Id, quantidade, produto);
				lista.add(oferta);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("Erro em criar uma lista de Oferta com os valores do banco de dados: " + e);
		}
	}

	// Atualiza na tabela relacao_servico_produto os valores recebidos de um objeto Oferta
	public static void AtualizarOferta(Oferta o) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE relacao_servico_produto SET Produto_ID = ?, Quant = ? WHERE ID = ?");
			stmt.setInt(1, o.getProduto().getId());
			stmt.setInt(2, o.getQuantidade());
			stmt.setInt(3, o.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar atualizar a tabela relacao_servico_produto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
	
	// Deleta um oferta na tabela relacao_servico_produto referente ao id
		public static void DeletarOferta(int id) {
			Connection con = ConectionFabric.getConection();
			PreparedStatement stmt = null;

			try {
				stmt = con.prepareStatement("DELETE FROM relacao_servico_produto WHERE ID = ?");
				stmt.setInt(1, id);

				stmt.executeUpdate();
			} catch (SQLException e) {
				throw new RuntimeException("Erro ao tentarr deletar uma linha na tabela relacao_servico_produto: " + e);
			} finally {
				ConectionFabric.closeConection(con, stmt);
			}
		}
}
