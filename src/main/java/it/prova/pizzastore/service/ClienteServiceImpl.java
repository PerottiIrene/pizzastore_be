package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.cliente.ClienteRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> listAll() {
		return (List<Cliente>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente caricaSingoloCliente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Cliente aggiorna(Cliente clienteInstance) {
		return repository.save(clienteInstance);

	}

	@Override
	public void rimuovi(Long idToDelete) {
		Cliente cliente=repository.findById(idToDelete).orElse(null);
		cliente.setAttivo(false);
		repository.save(cliente);
	}

	@Override
	public Cliente inserisciNuovo(Cliente clienteInstance) {
		clienteInstance.setAttivo(true);
		return repository.save(clienteInstance);
	}

	@Override
	public List<Cliente> findByExample(Cliente example) {
		return repository.findByExample(example);
	}

	@Override
	public void changeAbilitationCliente(Long id) {
		Cliente clienteInstance = repository.findById(id).orElse(null);
		if (clienteInstance == null) {
			throw new NotFoundException("cliente non trovato");
		}

		if (clienteInstance.getAttivo()) {
			clienteInstance.setAttivo(false);
		} else {
			clienteInstance.setAttivo(true);
		}
		
		repository.save(clienteInstance);

	}

}
