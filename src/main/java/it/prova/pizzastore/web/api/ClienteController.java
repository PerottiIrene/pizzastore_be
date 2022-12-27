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

import it.prova.pizzastore.dto.ClienteDTO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.ClienteService;
import it.prova.pizzastore.web.api.exception.IdNotNullForInsertException;
import it.prova.pizzastore.web.api.exception.NotFoundException;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<ClienteDTO> getAll() {
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.listAll());
	}

	// gli errori di validazione vengono mostrati con 400 Bad Request ma
	// elencandoli grazie al ControllerAdvice
	@PostMapping
	public ClienteDTO createNew(@Valid @RequestBody ClienteDTO clienteInput) {
		// se mi viene inviato un id jpa lo interpreta come update ed a me (producer)
		// non sta bene
		if (clienteInput.getId() != null)
			throw new IdNotNullForInsertException("Non è ammesso fornire un id per la creazione");

		Cliente clienteInserito = clienteService.inserisciNuovo(clienteInput.buildClienteModel());
		return ClienteDTO.buildClienteDTOFromModel(clienteInserito);
	}
	
	@GetMapping("/{id}")
	public ClienteDTO findById(@PathVariable(value = "id", required = true) long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new NotFoundException("Cliente not found con id: " + id);

		return ClienteDTO.buildClienteDTOFromModel(cliente);
	}
	
	@PutMapping("/{id}")
	public ClienteDTO update(@Valid @RequestBody ClienteDTO clienteInput, @PathVariable(required = true) Long id) {
		Cliente cliente = clienteService.caricaSingoloCliente(id);

		if (cliente == null)
			throw new NotFoundException("cliente not found con id: " + id);

		clienteInput.setId(id);
		Cliente clienteAggiornato = clienteService.aggiorna(clienteInput.buildClienteModel());
		return ClienteDTO.buildClienteDTOFromModel(clienteAggiornato);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete( @PathVariable(required = true) Long id) {
		
		clienteService.rimuovi(id);
	}
	
	@PostMapping("/search")
	public List<ClienteDTO> search(@RequestBody ClienteDTO example){
		
		return ClienteDTO.createClienteDTOListFromModelList(clienteService.findByExample(example.buildClienteModel()));
	}
	

}
