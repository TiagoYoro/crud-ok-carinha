package Repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConectionFabric;
import Modelo.Cliente;
import Modelo.Servico;

public class ServicoRepositorio {

	// Adiciona um novo serviço a tabela servico apartir de objeto Servico
	public static void CriarServico(Servico s) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO servico (Cliente_CPF, Data) VALUES(?, ?)");
			stmt.setString(1, s.getCliente().getCPF());
			stmt.setDate(2, s.getData());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar criar uma linha na tabela sevico: " + e);
		} finally {
			{
				ConectionFabric.closeConection(con, stmt);
			}
		}
	}

	// Busca um servico pelo Id e devolve em um objeto servico
	public static Servico BuscarServicoPorId(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM servico WHERE ID = ?");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			;
			return CriarObjetoServico(rs).get(0);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar valores na tabela sevico: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca todos os valores da tabela servico e devolve em uma lista de Servico
	public static List<Servico> BuscarTodosOsServicos() {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM servico");
			rs = stmt.executeQuery();

			return CriarObjetoServico(rs);
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao busca todos os valores na tabela servico: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Recebe os valores de uma busca e devolve uma lista de Servico.
	private static List<Servico> CriarObjetoServico(ResultSet rs) {
		try {
			List<Servico> lista = new ArrayList<>();
			while (rs.next()) {
				int Id = rs.getInt("ID");
				Cliente cliente = ClienteRepositorio.BuscarClientePorCPF(rs.getString("Cliente_CPF"));
				Date data = rs.getDate("Data");
				Servico servico = new Servico(Id, cliente, data);
				lista.add(servico);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException("Erro em criar uma lista de Servico com os valores do banco de dados: " + e);
		}
	}

	// Atualiza na tabela servico os valores recebidos de um objeto Servico
	public static void AtualizarServico(Servico s) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE servico SET Cliente_CPF = ?, Data = ? WHERE ID = ?");
			stmt.setString(1, s.getCliente().getCPF());
			stmt.setDate(2, s.getData());
			stmt.setInt(3, s.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar atualizar a tabela servico: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}

	// Deleta um servico na tabela servico referente ao id
	public static void DeletarServico(int id) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM servico WHERE ID = ?");
			stmt.setInt(1, id);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentarr deletar uma linha na tabela servico: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
}
