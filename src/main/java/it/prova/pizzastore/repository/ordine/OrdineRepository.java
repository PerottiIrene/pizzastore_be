package it.prova.pizzastore.repository.ordine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Ordine;

public interface OrdineRepository extends CrudRepository<Ordine, Long>,CustomOrdineRepository{
	
	@Query("from Ordine o join o.fattorino f where f.id =?1 and o.chiuso=false")
	List<Ordine> findAllOrdiniApertiFattorino(Long idFattorino);

}
