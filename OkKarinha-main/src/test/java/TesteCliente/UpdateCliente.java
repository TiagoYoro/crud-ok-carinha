package TesteCliente;

import Modelo.Cliente;
import Repositorio.ClienteRepositorio;

public class UpdateCliente {
	public static void main(String[] args) {
		Cliente cliente1 = ClienteRepositorio.BuscarClientePorCPF("33320359593");
		System.out.println(cliente1);
		cliente1.setTelefone("51988982030");
		ClienteRepositorio.AtualizarCliente(cliente1);

		Cliente cliente2 = ClienteRepositorio.BuscarClientePorCPF("33320359593");
		System.out.println(cliente2);
	}
}
