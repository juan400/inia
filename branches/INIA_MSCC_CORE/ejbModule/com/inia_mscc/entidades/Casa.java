package com.inia_mscc.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="TL_Casa")
public class Casa {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String puerta;
	@Column
	private String ventana;
	
	
	public Casa(){
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPuerta() {
		return puerta;
	}
	public void setPuerta(String puerta) {
		this.puerta = puerta;
	}
	public String getVentana() {
		return ventana;
	}
	public void setVentana(String ventana) {
		this.ventana = ventana;
	}
	
	
	
	
	
}
