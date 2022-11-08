package com.backend.GS.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name= "TABLE_CARRO_GS")
public class Carro {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "É obrigatório colocar a placa.")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String placa;
	
	@NotNull(message = "É obrigatório colocar o nome da pessoa.")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String NOME_PESSOA;

	@NotNull(message = "É obrigatório colocar o modelo do carro.")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String MODELO_CARRO;


	@NotNull(message = "É obrigatório colocar a data.")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
	private String DATA_ESTACIONAMENTO;
	
	@NotNull(message = "É obrigatório colocar o nome da pessoa.")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
	private Long ESTACIONAMENTO_CODIGO;




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getNOME_PESSOA() {
		return NOME_PESSOA;
	}

	public void setNOME_PESSOA(String nOME_PESSOA) {
		NOME_PESSOA = nOME_PESSOA;
	}

	public String getMODELO_CARRO() {
		return MODELO_CARRO;
	}

	public void setMODELO_CARRO(String mODELO_CARRO) {
		MODELO_CARRO = mODELO_CARRO;
	}

	public String getDATA_ESTACIONAMENTO() {
		return DATA_ESTACIONAMENTO;
	}

	public void setDATA_ESTACIONAMENTO(String dATA_ESTACIONAMENTO) {
		DATA_ESTACIONAMENTO = dATA_ESTACIONAMENTO;
	}

	public Long getEstacionamento() {
		return ESTACIONAMENTO_CODIGO;
	}

	public void setEstacionamento(Long eSTACIONAMENTO_CODIGO) {
		ESTACIONAMENTO_CODIGO = eSTACIONAMENTO_CODIGO;
	}
	
}
