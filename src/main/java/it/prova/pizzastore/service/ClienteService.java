package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Utente;

public interface ClienteService {
	
	public List<Cliente> listAll() ;

	public Cliente caricaSingoloCliente(Long id);
	
	public Cliente aggiorna(Cliente clienteInstance);
	
	public void rimuovi(Long idToDelete);

	public Cliente inserisciNuovo(Cliente clienteInstance);
	
	public List<Cliente> findByExample(Cliente example);
	
	public void changeAbilitationCliente(Long id);

}
