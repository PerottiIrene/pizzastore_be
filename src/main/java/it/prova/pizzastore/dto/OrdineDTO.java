package it.prova.pizzastore.dto;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Ruolo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdineDTO {
	
	private Long id;

	@NotBlank(message = "{codice.notblank}")
	@Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri")
	private String codice;

	@NotNull(message = "{costoTotale.notnull}")
	private Integer costoTotale;

	private LocalDate data;

	private Boolean chiuso;
	
//	@NotNull(message = "{cliente.notnull}")
	private ClienteDTO cliente;
	
//	@NotNull(message = "{utente.notnull}")
	private UtenteDTO fattorino;
	
	private Set<PizzaDTO> pizze=new HashSet<>();
//	private Long[] pizzeIds;
	
	public OrdineDTO() {}

	public OrdineDTO(Long id,
			@NotBlank(message = "{codice.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codice,
			@NotNull(message = "{costoTotale.notnull}") Integer costoTotale, LocalDate data, Boolean chiuso, ClienteDTO cliente,
			UtenteDTO fattorino, Set<PizzaDTO> pizze) {
		super();
		this.id = id;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.data = data;
		this.chiuso = chiuso;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.pizze = pizze;
	}
	
	public OrdineDTO(Long id,
			@NotBlank(message = "{codice.notblank}") @Size(min = 4, max = 15, message = "Il valore inserito '${validatedValue}' deve essere lungo tra {min} e {max} caratteri") String codice,
			@NotNull(message = "{costoTotale.notnull}") Integer costoTotale,
			@NotNull(message = "{data.notnull}") LocalDate data, Boolean chiuso) {
		super();
		this.id = id;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.data = data;
		this.chiuso = chiuso;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public Integer getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(Integer costoTotale) {
		this.costoTotale = costoTotale;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getChiuso() {
		return chiuso;
	}

	public void setChiuso(Boolean chiuso) {
		this.chiuso = chiuso;
	}

	public ClienteDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}

	public UtenteDTO getFattorino() {
		return fattorino;
	}

	public void setFattorino(UtenteDTO fattorino) {
		this.fattorino = fattorino;
	}

	public Set<PizzaDTO> getPizze() {
		return pizze;
	}

	public void setPizze(Set<PizzaDTO> pizze) {
		this.pizze = pizze;
	}
	
	public Ordine buildOrdineModel() {
		Ordine result = new Ordine(this.id, this.data, this.codice, this.costoTotale, this.chiuso);
		
		if (this.cliente != null)
			result.setCliente(this.cliente.buildClienteModel());
		
		if (this.fattorino != null)
			result.setFattorino(this.fattorino.buildUtenteModel(true));
		
		if (this.pizze != null)
			result.setPizze(pizze.stream().map(PizzaDTO::buildPizzaModel).collect(Collectors.toSet()));
//			result.setPizze(Arrays.asList(pizzeIds).stream().map(id -> new Pizza(id)).collect(Collectors.toSet()));

		return result;
	}

	public static OrdineDTO buildOrdineDTOFromModel(Ordine ordineModel,boolean includePizze, boolean includeCliente, boolean includeFattorino) {
		OrdineDTO result = new OrdineDTO(ordineModel.getId(), ordineModel.getCodice(), ordineModel.getCostoTotale(),
				ordineModel.getData(), ordineModel.getChiuso());
		
		if(includePizze)
			result.setPizze(PizzaDTO.createPizzaDTOSetFromModelSet(ordineModel.getPizze()));

//		if (!ordineModel.getPizze().isEmpty())
//			result.pizzeIds = ordineModel.getPizze().stream().map(r -> r.getId()).collect(Collectors.toList())
//					.toArray(new Long[] {});
		
		if (includeCliente)
			result.setCliente(ClienteDTO.buildClienteDTOFromModel(ordineModel.getCliente()));
		
		if (includeFattorino)
			result.setFattorino(UtenteDTO.buildUtenteDTOFromModel(ordineModel.getFattorino()));
		
//		if(!ordineModel.getCliente().equals(null))
//			result.clienteId=ordineModel.getCliente().getId();
//		
//		if(!ordineModel.getFattorino().equals(null))
//			result.fattorinoId=ordineModel.getFattorino().getId();

		return result;
	}

	public static List<OrdineDTO> createOrdineDTOListFromModelList(List<Ordine> modelListInput ) {
		return modelListInput.stream().map(ordineEntity -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordineEntity, false,false,false);
		}).collect(Collectors.toList());
	}

	public static Set<OrdineDTO> createOrdineDTOSetFromModelSet(Set<Ordine> modelListInput) {
		return modelListInput.stream().map(ordineEntity -> {
			return OrdineDTO.buildOrdineDTOFromModel(ordineEntity,false,false,false);
		}).collect(Collectors.toSet());
	}
	
	

}
