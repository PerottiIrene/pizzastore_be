package it.prova.pizzastore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ruolo")
public class Ruolo {
	
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_PIZZAIOLO = "ROLE_PIZZAIOLO";
	public static final String ROLE_PROPRIETARIO = "ROLE_PROPRIETARIO";
	public static final String ROLE_FATTORINO = "ROLE_FATTORINO";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "codice")
	private String codice;
	
	public Ruolo() {}
	
	public Ruolo(String descrizione, String codice) {
		super();
		this.descrizione = descrizione;
		this.codice = codice;
	}
	
	public Ruolo(Long id) {
		super();
		this.id = id;
	}

	public Ruolo(Long id, String descrizione, String codice) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.codice = codice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public static String getRoleAdmin() {
		return ROLE_ADMIN;
	}

	public static String getRolePizzaiolo() {
		return ROLE_PIZZAIOLO;
	}

	public static String getRoleProprietario() {
		return ROLE_PROPRIETARIO;
	}

	public static String getRoleFattorino() {
		return ROLE_FATTORINO;
	}
	
	

}
