package it.prova.pizzastore.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ordine")
public class Ordine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "data")
	private LocalDate data;
	@Column(name = "codice")
	private String codice;
	@Column(name = "costoTotale")
	private Integer costoTotale;
	@Column(name = "chiuso")
	private Boolean chiuso;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utente_id")
	private Utente fattorino;
	
	@ManyToMany
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private Set<Pizza> pizze = new HashSet<>();
	
	public Ordine() {}

	public Ordine(Long id, LocalDate data, String codice, Integer costoTotale, Boolean chiuso, Cliente cliente,
			Utente fattorino, Set<Pizza> pizze) {
		super();
		this.id = id;
		this.data = data;
		this.codice = codice;
		this.costoTotale = costoTotale;
		this.chiuso = chiuso;
		this.cliente = cliente;
		this.fattorino = fattorino;
		this.pizze = pizze;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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

	public Boolean getChiuso() {
		return chiuso;
	}

	public void setChiuso(Boolean chiuso) {
		this.chiuso = chiuso;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utente getFattorino() {
		return fattorino;
	}

	public void setFattorino(Utente fattorino) {
		this.fattorino = fattorino;
	}

	public Set<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(Set<Pizza> pizze) {
		this.pizze = pizze;
	}
	
	
	
	

}
