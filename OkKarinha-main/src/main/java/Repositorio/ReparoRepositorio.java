package Repositorio;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConectionFabric;
import Modelo.Reparo;
import Modelo.Servico;

public class ReparoRepositorio {

	// Cria uma nova linha na tabela reparo_de_moto
	public static int CriarReparo(Servico s, BigDecimal preco, String placa, String descricao, String mecanico,
			String modelo) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement(
					"INSERT INTO reparo_de_moto (Servico_ID, Preco, Placa, Descricao, Mecanico, Modelo)"
							+ " VALUES(?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, s.getId());
			stmt.setBigDecimal(2, preco);
			stmt.setString(3, placa);
			stmt.setString(4, descricao);
			stmt.setString(5, mecanico);
			stmt.setString(6, modelo);

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
			throw new RuntimeException("Erro ao tentar criar uma linha na tabela reparo_de_moto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca um reparo pelo id e devolve em um objeto Reparo
	public static Reparo BuscarReparoPorId(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM reparo_de_moto WHERE ID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			;
			return CriarObjetoReparo(rs).get(0);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar valores na tabela reparo_de_moto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca todos os valores da tabela reparo_de_moto e devolve em uma lista de
	// Reparo
	public static List<Reparo> BuscarTodosOsReparosDoServico(int idS) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM reparo_de_moto WHERE Servico_ID = ?");
			stmt.setInt(1, idS);
			rs = stmt.executeQuery();
			return CriarObjetoReparo(rs);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar valores na tabela reparo_de_moto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Criar uma lista de objeto Reparo com bases os dados do BD
	private static List<Reparo> CriarObjetoReparo(ResultSet rs) {
		try {
			List<Reparo> lista = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt("ID");
				BigDecimal preco = rs.getBigDecimal("Preco");
				String placa = rs.getString("Placa");
				String descricao = rs.getString("Descricao");
				String mecanico = rs.getString("Mecanico");
				String modelo = rs.getString("Modelo");
				Reparo reparo = new Reparo(id, preco, placa, descricao, mecanico, modelo);
				lista.add(reparo);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("Erro em criar uma lista de Reparo com os valores do banco de dados: " + e);
		}
	}

	// Atualiza na tabela reparo_de_moto os valores recebidos de um objeto Reparo.
	public static void AtualizarReparo(Reparo r) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement(
					"UPDATE reparo_de_moto SET Preco =  ?, Placa = ?, Descricao = ?, Mecanico =?,  Modelo = ? WHERE ID = ?");
			stmt.setBigDecimal(1, r.getPreco());
			stmt.setString(2, r.getPlaca());
			stmt.setString(3, r.getDescricao());
			stmt.setString(4, r.getMecanico());
			stmt.setString(5, r.getModelo());
			stmt.setInt(6, r.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar atualizar a tabela reparo_de_moto: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
	
	// Deleta um reparo na tabela reparo_de_moto referente ao id
			public static void DeletarReparo(int id) {
				Connection con = ConectionFabric.getConection();
				PreparedStatement stmt = null;

				try {
					stmt = con.prepareStatement("DELETE FROM reparo_de_moto WHERE ID = ?");
					stmt.setInt(1, id);

					stmt.executeUpdate();
				} catch (SQLException e) {
					throw new RuntimeException("Erro ao tentar deletar uma linha na tabela reparo_de_moto: " + e);
				} finally {
					ConectionFabric.closeConection(con, stmt);
				}
			}
}
