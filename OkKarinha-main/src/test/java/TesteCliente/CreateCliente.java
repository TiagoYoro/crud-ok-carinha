package TesteCliente;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

public class CreateCliente {

	public static void main(String[] args) {
		Cliente cliente = new Cliente("56856452220", "Andre Almeida", "61988543208", "andreAlmeida@gmail.com");
		ClienteRepositorio.CriarCliente(cliente);
	}
}
