package it.prova.pizzastore.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.ordine.OrdineRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@Service
public class OrdineServiceImpl implements OrdineService {

	@Autowired
	private OrdineRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Ordine> listAll() {
		return (List<Ordine>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Ordine caricaSingoloOrdine(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Ordine aggiorna(Ordine ordineInstance) {
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

}
