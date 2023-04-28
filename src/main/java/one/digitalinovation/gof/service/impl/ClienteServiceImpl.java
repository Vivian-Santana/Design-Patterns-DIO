package one.digitalinovation.gof.service.impl;

import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.server.Client;
import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.service.ClienteService;
@Service
public class ClienteServiceImpl implements ClienteService{

	@Override
	public Iterable<Client> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inserir(Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletar(Long id) {
		// TODO Auto-generated method stub
		
	}
}
