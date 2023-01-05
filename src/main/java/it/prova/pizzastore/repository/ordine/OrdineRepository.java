package it.prova.pizzastore.repository.ordine;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public interface OrdineRepository extends CrudRepository<Ordine, Long>,CustomOrdineRepository{
	
	@Query("from Ordine o join o.fattorino f where f.id =?1 and o.chiuso=false")
	List<Ordine> findAllOrdiniApertiFattorino(Long idFattorino);
	
	@Query(value= "select sum(p.prezzo) from Pizza p join ordine_pizza op on p.id=op.pizza_id where ordine_id = ?1", nativeQuery = true)
	Integer sommaPrezziPizze(Long idOrdine);
	
	@Query("from Ordine o left join fetch o.pizze p where o.data between ?1 and ?2")
	List<Ordine> listaOrdiniBetween(LocalDate dataInizio, LocalDate dataFine);
	
	@Query(value= "select count(op.pizza_id) from ordine_pizza op join Ordine o on o.id=op.ordine_id where o.data between ?1 and ?2", nativeQuery = true)
	Integer numeroPizzeOrdinateNellIntervalloDiDate(LocalDate dataInizio,LocalDate dataFine);
	
	@Query("from Ordine o join fetch o.cliente c left join fetch o.pizze p where o.id = ?1")
	Ordine findByIdEager(Long id);
	
	@Query("select o from Ordine o join fetch o.cliente")
	List<Ordine> findAllEager();

}
