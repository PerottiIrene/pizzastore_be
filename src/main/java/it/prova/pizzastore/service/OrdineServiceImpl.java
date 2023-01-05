package it.prova.pizzastore.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.repository.ordine.OrdineRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@Service
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository repository;
	
	@Autowired
	private UtenteService utenteService;

	@Override
	@Transactional(readOnly = true)
	public List<Ordine> listAll(boolean eager) {
		if (eager)
			return (List<Ordine>) repository.findAllEager();
		
		return (List<Ordine>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ordine caricaSingoloOrdine(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Ordine aggiorna(Ordine ordineInstance) {
		ordineInstance.setCostoTotale(calcolaSommaPrezziPizze(ordineInstance.getId()));
		return repository.save(ordineInstance);

	}

	@Override
	public void rimuovi(Long idToDelete) {
		Ordine ordine=repository.findById(idToDelete).orElse(null);
		ordine.setChiuso(true);
		repository.save(ordine);
	}

	@Override
	public Ordine inserisciNuovo(Ordine ordineInstance) {
		ordineInstance.setData(LocalDate.now());
		ordineInstance.setChiuso(false);
		repository.save(ordineInstance);
		ordineInstance.setCostoTotale(repository.sommaPrezziPizze(ordineInstance.getId()));
		return repository.save(ordineInstance);

	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		return repository.findByExample(example);
	}

	@Override
	public void changeAbilitation(Long id) {
		Ordine ordineInstance = repository.findById(id).orElse(null);
		if (ordineInstance == null) {
			throw new NotFoundException("ordine non trovato");
		}

		if (ordineInstance.getChiuso()) {
			ordineInstance.setChiuso(false);
		} else {
			ordineInstance.setChiuso(true);
		}
		
		repository.save(ordineInstance);

	}

	@Override
	public List<Ordine> findByExampleEagerPizze(Ordine example) {
		return repository.findByExampleEagerPizze(example);
	}

	@Override
	public List<Ordine> ordiniByFattorino() {
		String username=SecurityContextHolder.getContext().getAuthentication().getName();
		Utente fattorinoInSessione=utenteService.findByUsername(username);
		
		return repository.findAllOrdiniApertiFattorino(fattorinoInSessione.getId());
	}

	@Override
	public Integer calcolaSommaPrezziPizze(Long idOrdine) {
		return repository.sommaPrezziPizze(idOrdine);
	}

	@Override
	public List<Ordine> ordiniBetween(LocalDate dataInizio, LocalDate dataFine) {
		return repository.listaOrdiniBetween(dataInizio, dataFine);
	}

	@Override
	public Integer ricaviTotaliOrdini(LocalDate dataInizio, LocalDate dataFine) {
		List<Ordine> listaOrdini=repository.listaOrdiniBetween(dataInizio, dataFine);
		Integer ricavoTot=0;
		
		for(Ordine ordineItem:listaOrdini) {
			ricavoTot+=ordineItem.getCostoTotale();
		}
		
		return ricavoTot;
	}

	@Override
	public Integer numeroOrdiniNellIntervalloDiDate(LocalDate dataInizio, LocalDate dataFine) {
		List<Ordine> listaOrdini=repository.listaOrdiniBetween(dataInizio, dataFine);
		Integer numeroOrdini=listaOrdini.size();
		return numeroOrdini;
	}

	@Override
	public List<Cliente> clientiConCostoTotaleOrdineOltre(LocalDate dataInizio, LocalDate dataFine) {
		List<Ordine> listaOrdini=repository.listaOrdiniBetween(dataInizio, dataFine);
		List<Cliente> clientiConOrdineOltre=new ArrayList<>();
		
		for(Ordine ordineItem:listaOrdini) {
			if(ordineItem.getCostoTotale() > 100) {
				clientiConOrdineOltre.add(ordineItem.getCliente());
			}
		}
		
		return clientiConOrdineOltre;
	}

	@Override
	public Integer numeroPizzeOrdinateNellIntervalloDiDate(LocalDate dataInizio, LocalDate dataFine) {
		return repository.numeroPizzeOrdinateNellIntervalloDiDate(dataInizio, dataFine);
	}

	@Override
	public Ordine caricaSingoloOrdineEager(Long id) {
		return repository.findByIdEager(id);
	}

}
