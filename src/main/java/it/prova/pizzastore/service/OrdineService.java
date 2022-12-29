package it.prova.pizzastore.service;

import java.time.LocalDate;
import java.util.List;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	
	public List<Ordine> listAll() ;

	public Ordine caricaSingoloOrdine(Long id);
	
	public Ordine aggiorna(Ordine ordineInstance);
	
	public void rimuovi(Long idToDelete);

	public Ordine inserisciNuovo(Ordine ordineInstance);
	
	public List<Ordine> findByExample(Ordine example);
	
	public List<Ordine> findByExampleEagerPizze(Ordine example);
	
	public void changeAbilitation(Long id);
	
	public List<Ordine> ordiniByFattorino();
	
	public Integer calcolaSommaPrezziPizze(Long idOrdine);
	
	public List<Ordine> ordiniBetween(LocalDate dataInizio,LocalDate dataFine);
	
	public Integer ricaviTotaliOrdini(LocalDate dataInizio,LocalDate dataFine);
	
	public Integer numeroOrdiniNellIntervalloDiDate(LocalDate dataInizio,LocalDate dataFine);
	
	public Integer numeroPizzeOrdinateNellIntervalloDiDate(LocalDate dataInizio,LocalDate dataFine);
	
	public List<Cliente> clientiConCostoTotaleOrdineOltre(LocalDate dataInizio,LocalDate dataFine);

}
