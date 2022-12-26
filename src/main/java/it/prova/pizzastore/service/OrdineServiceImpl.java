package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Ordine;
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
	public void aggiorna(Ordine ordineInstance) {
		repository.save(ordineInstance);

	}

	@Override
	public void rimuovi(Long idToDelete) {
		repository.deleteById(idToDelete);
	}

	@Override
	public void inserisciNuovo(Ordine ordineInstance) {
		ordineInstance.setChiuso(false);
		repository.save(ordineInstance);

	}

	@Override
	public List<Ordine> findByExample(Ordine example) {
		// TODO Auto-generated method stub
		return null;
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

}
