package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Pizza;

public interface PizzaService {
	
	public List<Pizza> listAll() ;

	public Pizza caricaSingoloPizza(Long id);
	
	public Pizza aggiorna(Pizza pizzaInstance);
	
	public void rimuovi(Long idToDelete);

	public Pizza inserisciNuovo(Pizza pizzaInstance);
	
	public List<Pizza> findByExample(Pizza example);
	
	public void changeAbilitation(Long id);

}
