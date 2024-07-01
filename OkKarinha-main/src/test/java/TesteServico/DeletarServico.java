package TesteServico;

import java.util.List;

import Modelo.Servico;
import Repositorio.ServicoRepositorio;

public class DeletarServico {
	public static void main(String[] args) {
		List<Servico> lista1 = ServicoRepositorio.BuscarTodosOsServicos();
		
		for(Servico servico1 : lista1) {
			System.out.println(servico1);
		}
		
		Servico servico = ServicoRepositorio.BuscarServicoPorId(2);
		ServicoRepositorio.DeletarServico(servico.getId());
		
		List<Servico> lista2 = ServicoRepositorio.BuscarTodosOsServicos();
		
		for(Servico servico2 : lista2) {
			System.out.println(servico2);
		}
	}
}
