package it.prova.pizzastore.repository.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class CustomOrdineRepositoryImpl implements CustomOrdineRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Ordine> findByExample(Ordine example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select o from Ordine o where o.id = o.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}

		if (example.getChiuso() != null) {
			whereClauses.add("o.chiuso = :chiuso ");
			paramaterMap.put("chiuso", example.getChiuso());
		}
		
		if (example.getData() != null) {
			whereClauses.add("o.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		
		if (example.getCostoTotale() != null) {
			whereClauses.add("o.costoTotale = :costoTotale ");
			paramaterMap.put("costoTotale", example.getCostoTotale());
		}
		
		if (example.getCliente() != null && example.getCliente().getId() != null) {
			whereClauses.add("o.cliente.id = :idCliente ");
			paramaterMap.put("idCliente", example.getCliente().getId());
		}
		
		if (example.getFattorino() != null && example.getFattorino().getId() != null) {
			whereClauses.add("o.fattorino.id = :idFattorino ");
			paramaterMap.put("idFattorino", example.getFattorino().getId());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}
		return typedQuery.getResultList();
	}

	@Override
	public List<Ordine> findByExampleEagerPizze(Ordine example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select o from Ordine o left join o.pizze p where o.id = o.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		
		if (example.getData() != null) {
			whereClauses.add("o.data >= :data ");
			paramaterMap.put("data", example.getData());
		}
		
		if (example.getCliente() != null && example.getCliente().getId() != null) {
			whereClauses.add("o.cliente.id = :idCliente ");
			paramaterMap.put("idCliente", example.getCliente().getId());
		}
		
		if (example.getPizze() != null && !example.getPizze().isEmpty()) {
			whereClauses.add("p in :pizze ");
			paramaterMap.put("pizze", example.getPizze());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}
		return typedQuery.getResultList();
	}

}
