package com.backend.GS.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name= "TABLE_ESTACIONAMENTO_GS")
public class Estacionamento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long CD;

	@NotNull(message = "O local é obrigatório.")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String local;



	public long getCodigo() {
		return CD;
	}

	public void setCodigo(long CD) {
		this.CD = CD;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	
}
