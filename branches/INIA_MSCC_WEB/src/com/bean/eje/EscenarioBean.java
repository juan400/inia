package com.bean.eje;

import java.io.Serializable;
import java.util.Date;

import com.bean.comun.MaestroBean;

public class EscenarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fechaEjecucion = new Date();
	private Date fSiembra = new Date();
	private String estacionClimatica = "LE";
	private String cultivar = "DonAlberto";
	
	//Fertilizacion
	private Date fFertilizacionSiembra = new Date();
	private String fuenteFertilizacionSiembra = "18-46-0";
	private String rateFertilizacionSiembra = "100";

	//Refertilizacion 1
	private Date fRefertilizacion1 = new Date();
	private String fuenteRefertilizacion1 = "18-46-0";
	private String rateRefertilizacion1 = "100";

	//Refertilizacion 2
	private Date fRefertilizacion2 = new Date();
	private String fuenteRefertilizacion2 = "18-46-0";
	private String rateRefertilizacion2 = "100";

	private String nombreSueloConeat = "10.1";
	private double profundidadA = 0.2;
	private double profundidadB = 0.2;
	private double densidadPlantas = 200;
	private double wuli = 50;
	private double wlli = 50;
	private double dpmi =200;
	private double rpmi = 100;
	private double humi = 2.0;
	private double nauli = 10;
	private double nalli = 10;
	private double nnuli = 20;
	private double nnlli = 20;
	
	public String ejecutarEscenario(){
		String resultado = "";
		//TODO llamada a los métodos relacionados con la ejecucion del MSCC
		return resultado; 
	}
	
	private boolean generarEscenario(){
		//TODO llamar al método para armar el template
		return false;
	}
	
	
	public Date getfSiembra() {
		return fSiembra;
	}
	public void setfSiembra(Date fSiembra) {
		this.fSiembra = fSiembra;
	}
	public String getEstacionClimatica() {
		return estacionClimatica;
	}
	public void setEstacionClimatica(String estacionClimatica) {
		this.estacionClimatica = estacionClimatica;
	}
	public String getCultivar() {
		return cultivar;
	}
	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}
	public Date getfFertilizacionSiembra() {
		return fFertilizacionSiembra;
	}
	public void setfFertilizacionSiembra(Date fFertilizacionSiembra) {
		this.fFertilizacionSiembra = fFertilizacionSiembra;
	}
	public String getFuenteFertilizacionSiembra() {
		return fuenteFertilizacionSiembra;
	}
	public void setFuenteFertilizacionSiembra(String fuenteFertilizacionSiembra) {
		this.fuenteFertilizacionSiembra = fuenteFertilizacionSiembra;
	}
	public String getRateFertilizacionSiembra() {
		return rateFertilizacionSiembra;
	}
	public void setRateFertilizacionSiembra(String rateFertilizacionSiembra) {
		this.rateFertilizacionSiembra = rateFertilizacionSiembra;
	}
	public Date getfRefertilizacion1() {
		return fRefertilizacion1;
	}
	public void setfRefertilizacion1(Date fRefertilizacion1) {
		this.fRefertilizacion1 = fRefertilizacion1;
	}
	public String getFuenteRefertilizacion1() {
		return fuenteRefertilizacion1;
	}
	public void setFuenteRefertilizacion1(String fuenteRefertilizacion1) {
		this.fuenteRefertilizacion1 = fuenteRefertilizacion1;
	}
	public String getRateRefertilizacion1() {
		return rateRefertilizacion1;
	}
	public void setRateRefertilizacion1(String rateRefertilizacion1) {
		this.rateRefertilizacion1 = rateRefertilizacion1;
	}
	public Date getfRefertilizacion2() {
		return fRefertilizacion2;
	}
	public void setfRefertilizacion2(Date fRefertilizacion2) {
		this.fRefertilizacion2 = fRefertilizacion2;
	}
	public String getFuenteRefertilizacion2() {
		return fuenteRefertilizacion2;
	}
	public void setFuenteRefertilizacion2(String fuenteRefertilizacion2) {
		this.fuenteRefertilizacion2 = fuenteRefertilizacion2;
	}
	public String getRateRefertilizacion2() {
		return rateRefertilizacion2;
	}
	public void setRateRefertilizacion2(String rateRefertilizacion2) {
		this.rateRefertilizacion2 = rateRefertilizacion2;
	}
	public String getNombreSueloConeat() {
		return nombreSueloConeat;
	}
	public void setNombreSueloConeat(String nombreSueloConeat) {
		this.nombreSueloConeat = nombreSueloConeat;
	}
	public double getProfundidadA() {
		return profundidadA;
	}
	public void setProfundidadA(double profundidadA) {
		this.profundidadA = profundidadA;
	}
	public double getProfundidadB() {
		return profundidadB;
	}
	public void setProfundidadB(double profundidadB) {
		this.profundidadB = profundidadB;
	}
	public double getDensidadPlantas() {
		return densidadPlantas;
	}
	public void setDensidadPlantas(double densidadPlantas) {
		this.densidadPlantas = densidadPlantas;
	}
	public double getWuli() {
		return wuli;
	}
	public void setWuli(double wuli) {
		this.wuli = wuli;
	}
	public double getWlli() {
		return wlli;
	}
	public void setWlli(double wlli) {
		this.wlli = wlli;
	}
	public double getDpmi() {
		return dpmi;
	}
	public void setDpmi(double dpmi) {
		this.dpmi = dpmi;
	}
	public double getRpmi() {
		return rpmi;
	}
	public void setRpmi(double rpmi) {
		this.rpmi = rpmi;
	}
	public double getHumi() {
		return humi;
	}
	public void setHumi(double humi) {
		this.humi = humi;
	}
	public double getNauli() {
		return nauli;
	}
	public void setNauli(double nauli) {
		this.nauli = nauli;
	}
	public double getNalli() {
		return nalli;
	}
	public void setNalli(double nalli) {
		this.nalli = nalli;
	}
	public double getNnuli() {
		return nnuli;
	}
	public void setNnuli(double nnuli) {
		this.nnuli = nnuli;
	}
	public double getNnlli() {
		return nnlli;
	}
	public void setNnlli(double nnlli) {
		this.nnlli = nnlli;
	}
	public Date getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(Date fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}

}
