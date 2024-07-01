package TesteServico;

import Modelo.Cliente;
import Modelo.Servico;
import Repositorio.ClienteRepositorio;
import Repositorio.ServicoRepositorio;

public class AtualizarServico {
	public static void main(String[] args) {
		Servico servico1 = ServicoRepositorio.BuscarServicoPorId(1);
		Cliente cliente = ClienteRepositorio.BuscarClientePorCPF("58822115973");
		
		System.out.println(servico1);
		
		servico1.setCliente(cliente);
		
		ServicoRepositorio.AtualizarServico(servico1);
		
		Servico servico2 = ServicoRepositorio.BuscarServicoPorId(1);
		
		System.out.println(servico2);
	}
}
