package it.prova.pizzastore.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DateStatisticheDTO {
	
	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataInizio;
	
	@NotNull(message = "{data.notnull}")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataFine;
	
	public DateStatisticheDTO() {}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}
	
	

}
