package one.digitalinovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import one.digitalinovation.gof.model.Cliente;
import one.digitalinovation.gof.model.ClienteRepository;
import one.digitalinovation.gof.model.Endereco;
import one.digitalinovation.gof.model.EnderecoRepository;
import one.digitalinovation.gof.service.ClienteService;
import one.digitalinovation.gof.service.ViaCepService;
@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);//OPTIONAL CASO O CLIENTE NÃO EXISTA, 
		return cliente.get(); 										//CRIAR CONDIÇÃO PARA RETONAR NULO SE NÃO EXISTIR
	}

	@Override
	public void inserir(Cliente cliente) { 	//MÉTODO USADO PARA SALVAR LIENTE COM CEP E ATUALIZAR CLIENTE (CONCEITO DRY).
		salvarClienteComCep(cliente);		
	}

	private void salvarClienteComCep(Cliente cliente) {//VERIFICA SE O ENDEREÇO JÁ EXIXSTIA.
		String cep = cliente.getEndereco().getCep();	//INSERE O CLIENTE VINCULADO AO ENDEREÇO NOVO OU QUE JÁ EXISTIA.
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(()->{ //SE NÃO EXITIR CONSOME A API DO "VIA CEP" PARA RECUPERAR O ENDEREÇO.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) { //BUSCA CLIENTE PELO ID, CASO EXISTA.
		Optional<Cliente> clienteBancoDados = clienteRepository.findById(id);
		if (clienteBancoDados.isPresent()) {
			salvarClienteComCep(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
}
