package it.prova.pizzastore.dto;

import java.util.List;

public class StatisticheDTO {
	
	private Integer ricaviTotali;
	
	private Integer numeroOrdini;
	
	private Integer numeroPizze;
	
	private List<ClienteDTO> listaClienti;
	
	public StatisticheDTO() {}

	public Integer getRicaviTotali() {
		return ricaviTotali;
	}

	public void setRicaviTotali(Integer ricaviTotali) {
		this.ricaviTotali = ricaviTotali;
	}

	public Integer getNumeroOrdini() {
		return numeroOrdini;
	}

	public void setNumeroOrdini(Integer numeroOrdini) {
		this.numeroOrdini = numeroOrdini;
	}

	public Integer getNumeroPizze() {
		return numeroPizze;
	}

	public void setNumeroPizze(Integer numeroPizze) {
		this.numeroPizze = numeroPizze;
	}

	public List<ClienteDTO> getListaClienti() {
		return listaClienti;
	}

	public void setListaClienti(List<ClienteDTO> listaClienti) {
		this.listaClienti = listaClienti;
	}
	
	

}
