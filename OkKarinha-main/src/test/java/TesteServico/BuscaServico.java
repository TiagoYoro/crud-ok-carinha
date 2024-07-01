package TesteServico;

import java.util.List;

import Modelo.Servico;
import Repositorio.ServicoRepositorio;

public class BuscaServico {
	public static void main(String[] args) {
		Servico servico1 = ServicoRepositorio.BuscarServicoPorId(1);
		System.out.println(servico1);
		
		List <Servico> ListaServico = ServicoRepositorio.BuscarTodosOsServicos();
		
		for(Servico servico : ListaServico) {
			System.out.println(servico);
		}
	}

}
