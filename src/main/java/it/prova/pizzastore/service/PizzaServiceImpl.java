package it.prova.pizzastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.repository.pizza.PizzaRepository;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@Service
public class PizzaServiceImpl implements PizzaService{
	
	@Autowired
	private PizzaRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<Pizza> listAll() {
		return (List<Pizza>) repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Pizza caricaSingoloPizza(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Pizza aggiorna(Pizza pizzaInstance) {
		return repository.save(pizzaInstance);
		
	}

	@Override
	public void rimuovi(Long idToDelete) {
		Pizza pizza=repository.findById(idToDelete).orElse(null);
		pizza.setAttivo(false);
		repository.save(pizza);
	}

	@Override
	public Pizza inserisciNuovo(Pizza pizzaInstance) {
		pizzaInstance.setAttivo(true);
		return repository.save(pizzaInstance);
	}

	@Override
	public List<Pizza> findByExample(Pizza example) {
		return repository.findByExample(example);
	}

	@Override
	public void changeAbilitation(Long id) {
		Pizza pizza=repository.findById(id).orElse(null);
		
		if(pizza == null) {
			throw new NotFoundException("elemento non trovato");
		}
		
		if(pizza.getAttivo()) {
			pizza.setAttivo(false);
		}else {
			pizza.setAttivo(true);
		}
		
		repository.save(pizza);
		
	}

}
