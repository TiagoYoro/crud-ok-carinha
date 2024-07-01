package Repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConectionFabric;
import Modelo.Cliente;

public class ClienteRepositorio {

	// Adiciona um novo cliente a tabela cliente apartir de objeto Cliente
	public static void CriarCliente(Cliente c) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO cliente (CPF, Nome, Telefone, Email) VALUES(?, ?, ?, ?)");
			stmt.setString(1, c.getCPF());
			stmt.setString(2, c.getNome());
			stmt.setString(3, c.getTelefone());
			stmt.setString(4, c.getEmail());

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar criar uma linha na tabela cliente: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
			;
		}
	}

	// Busca um cliente pelo CPF e devolve em um objeto Cliente
	public static Cliente BuscarClientePorCPF(String CPF) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM cliente WHERE CPF = ?");
			stmt.setString(1, CPF);

			rs = stmt.executeQuery();

			return CriarObjetoCliente(rs).get(0);

		} catch (SQLException e) {
			throw new RuntimeException("Erro em buscar na tabelacliente: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Busca todos os valores da tabela cliente e devolve em uma lista de Cliente
	public static List<Cliente> BuscarTodosOsClientes() {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("SELECT * FROM cliente");
			rs = stmt.executeQuery();

			return CriarObjetoCliente(rs);

		} catch (SQLException e) {
			throw new RuntimeException("Erro em buscar todos os valores da tabela cliente: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt, rs);
		}
	}

	// Recebe os valores de uma busca e devolve uma lista de Clientes com os valores
	// recebidos.
	private static List<Cliente> CriarObjetoCliente(ResultSet rs) {
		try {
			List<Cliente> lista = new ArrayList<>();
			while (rs.next()) {
				String CPF = rs.getString("CPF");
				String Nome = rs.getString("Nome");
				String Telefone = rs.getString("Telefone");
				String Email = rs.getString("Email");
				Cliente cliente = new Cliente(CPF, Nome, Telefone, Email);
				lista.add(cliente);
			}
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(
					"Erro em criar uma lista de objetos Cliente com os valores do banco de dados: " + e);
		}
	}

	// Edita um cliente no banco de dados
	// Atualiza no banco de dados os valores recebidos de um objeto Cliente
	public static void AtualizarCliente(Cliente c) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE cliente SET Nome = ?, Telefone = ?, Email = ? WHERE CPF = ?");
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getTelefone());
			stmt.setString(3, c.getEmail());
			stmt.setString(4, c.getCPF());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar atualizar a tabela cliente: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}

	// Deleta o cliente referente ao CPF
	// Deleta o cliente da tabela cliente comrespondente ao CPF
	public static void DeletarCliente(String CPF) {
		Connection con = ConectionFabric.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM cliente WHERE CPF = ?");
			stmt.setString(1, CPF);

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar deleta um cliente da tabela cliente: " + e);
		} finally {
			ConectionFabric.closeConection(con, stmt);
		}
	}
}
