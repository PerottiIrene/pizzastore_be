package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll() ;

	public Ordine caricaSingoloOrdine(Long id);
	
	public Ordine aggiorna(Ordine ordineInstance);
	
	public void rimuovi(Long idToDelete);

	public Ordine inserisciNuovo(Ordine ordineInstance);
	
	public List<Ordine> findByExample(Ordine example);
	
	public void changeAbilitation(Long id);

}
