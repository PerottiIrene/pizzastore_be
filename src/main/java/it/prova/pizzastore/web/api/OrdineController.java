package it.prova.pizzastore.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pizzastore.dto.OrdineDTO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.OrdineService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineService;

	@GetMapping
	public List<OrdineDTO> getAll() {
		return OrdineDTO.createOrdineDTOListFromModelList(ordineService.listAll(),true,true,true);
	}

	// gli errori di validazione vengono mostrati con 400 Bad Request ma
	// elencandoli grazie al ControllerAdvice
	@PostMapping
	public OrdineDTO createNew(@Valid @RequestBody OrdineDTO ordineInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (ordineInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Ordine ordineInserito = ordineService.inserisciNuovo(ordineInput.buildOrdineModel(true));
		return OrdineDTO.buildOrdineDTOFromModel(ordineInserito,true,true,true);
	}

	@GetMapping("/{id}")
	public OrdineDTO findById(@PathVariable(value = "id", required = true) long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);

		if (ordine == null)
			throw new NotFoundException("Ordine not found con id: " + id);

		return OrdineDTO.buildOrdineDTOFromModel(ordine,true,true,true);
	}

	@PutMapping("/{id}")
	public OrdineDTO update(@Valid @RequestBody OrdineDTO ordineInput, @PathVariable(required = true) Long id) {
		Ordine ordine = ordineService.caricaSingoloOrdine(id);

		if (ordine == null)
			throw new NotFoundException("ordine not found con id: " + id);

		ordineInput.setId(id);
		Ordine ordineAggiornato = ordineService.aggiorna(ordineInput.buildOrdineModel(true));
		return OrdineDTO.buildOrdineDTOFromModel(ordineAggiornato,true,true,true);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {

		ordineService.rimuovi(id);
	}

}
