package one.digitalinovation.gof.service;

import ch.qos.logback.core.net.server.Client;
import one.digitalinovation.gof.model.Cliente;

public interface ClienteService {

	Iterable<Client> buscarTodos();
	
	Cliente buscarPorId(Long id);
	
	void inserir (Cliente cliente);
	
	void atualizar (Long id, Cliente cliente);
	
	void deletar (Long id);
}
