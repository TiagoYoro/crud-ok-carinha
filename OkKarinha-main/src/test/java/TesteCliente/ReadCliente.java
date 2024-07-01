package TesteCliente;

import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

public class ReadCliente {
	public static void main(String[] args) {
		//Cliente cliente = ClienteRepositorio.BuscarClientePorCPF("56856452220");
		//System.out.println(cliente);

		List<Cliente> lista = new ArrayList<Cliente>();

		lista = ClienteRepositorio.BuscarTodosOsClientes();

		for (Cliente c : lista) {
			System.out.println(c);
		}
	}
}
