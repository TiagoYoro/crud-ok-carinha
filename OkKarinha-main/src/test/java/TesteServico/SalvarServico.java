package TesteServico;

import java.sql.Date;

import Modelo.Cliente;
import Modelo.Servico;
import Repositorio.ClienteRepositorio;
import Repositorio.ServicoRepositorio;

public class SalvarServico {
	public static void main(String[] args) {
		Cliente cliente = new Cliente("58822115973", "Alexandro Perera", "61977561284", "Alexandro@hotmail.com");
		ClienteRepositorio.CriarCliente(cliente);

		Servico servico = new Servico(2, cliente, new Date(System.currentTimeMillis()));
		ServicoRepositorio.CriarServico(servico);
	}
}
