package TesteCliente;

import java.util.ArrayList;
import java.util.List;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

public class DeleteCliente {
	public static void main(String[] args) {
		List<Cliente> lista1 = new ArrayList<Cliente>();

		lista1 = ClienteRepositorio.BuscarTodosOsClientes();

		for (Cliente c : lista1) {
			System.out.println(c);
		}

		ClienteRepositorio.DeletarCliente("56856452220");

		List<Cliente> lista2 = new ArrayList<Cliente>();

		lista2 = ClienteRepositorio.BuscarTodosOsClientes();

		for (Cliente c : lista2) {
			System.out.println(c);
		}

	}
}
