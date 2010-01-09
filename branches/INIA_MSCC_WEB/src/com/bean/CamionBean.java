//package com.bean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//public class CamionBean {
//
//	
//	private String placa;
//	private String marca;
//	private String anio;
//	private List<Integer> anios;
//	private String pais;
//	
//	
//	private FachadaTransporte facTrans =  new FachadaTransporte();
//	
//	public List<Integer> getAnios() {
//		return anios;
//	}
//	public void setAnios(List<Integer> anios) {
//		this.anios = anios;
//	}
//	public String getPlaca() {
//		return placa;
//	}
//	public void setPlaca(String placa) {
//		this.placa = placa;
//	}
//	public String getMarca() {
//		return marca;
//	}
//	public void setMarca(String marca) {
//		this.marca = marca;
//	}
//	public String getAnio() {
//		return anio;
//	}
//	public void setAnio(String anio) {
//		this.anio = anio;
//	}
//	
//	
//	public String salvarCamion(){
//		pais = "Uruguay";
//		facTrans.salvarCamion(placa,marca,anio,pais);
//		return null;
//	}
//	
//	@PostConstruct
//	public List<Integer> anios(){
//		anios = new ArrayList<Integer>();
//		for (int i = 1970; i < 2020; i++) {
//			anios.add(i);
//		}
//		return anios;
//	}
//	
//	
//	
//}
