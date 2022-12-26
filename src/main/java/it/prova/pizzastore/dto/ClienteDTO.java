package it.prova.pizzastore.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Cliente;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDTO {
	
	private Long id;

	@NotBlank(message = "{indirizzo.notblank}")
	@Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String indirizzo;

	@NotBlank(message = "{nome.notblank}")
	private String nome;

	@NotBlank(message = "{cognome.notblank}")
	private String cognome;

	private Boolean attivo;
	
	public ClienteDTO() {}

	public ClienteDTO(Long id,
			@NotBlank(message = "{indirizzo.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String indirizzo,
			@NotBlank(message = "{nome.notblank}") String nome, @NotBlank(message = "{cognome.notnull}") String cognome,
			Boolean attivo) {
		super();
		this.id = id;
		this.indirizzo = indirizzo;
		this.nome = nome;
		this.cognome = cognome;
		this.attivo = attivo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Boolean getAttivo() {
		return attivo;
	}

	public void setAttivo(Boolean attivo) {
		this.attivo = attivo;
	}
	
	public Cliente buildClienteModel() {
		Cliente result = new Cliente(this.id, this.indirizzo, this.nome, this.cognome, this.attivo);

		return result;
	}

	public static ClienteDTO buildClienteDTOFromModel(Cliente clienteModel) {
		ClienteDTO result = new ClienteDTO(clienteModel.getId(), clienteModel.getIndirizzo(), clienteModel.getNome(),
				clienteModel.getCognome(), clienteModel.getAttivo());

		return result;
	}

	public static List<ClienteDTO> createClienteDTOListFromModelList(List<Cliente> modelListInput) {
		return modelListInput.stream().map(clienteEntity -> {
			return ClienteDTO.buildClienteDTOFromModel(clienteEntity);
		}).collect(Collectors.toList());
	}

	public static Set<ClienteDTO> createClienteDTOSetFromModelSet(Set<Cliente> modelListInput) {
		return modelListInput.stream().map(clienteEntity -> {
			return ClienteDTO.buildClienteDTOFromModel(clienteEntity);
		}).collect(Collectors.toSet());
	}


}
