//package com.bean;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//public class MarcaSuggestionBean {
//
//	private List<Marca> marcaList;
//	private String marca;
//	private TransporteServices trServ = new TransporteProvider();
//
//	public String getMarca() {
//		return marca;
//	}
//
//	public void setMarca(String marca) {
//		this.marca = marca;
//	}
//
//	@PostConstruct
//	public void init() {
//		marcaList = trServ.obtenerTodasLasMarcasCamiones();
//	}
//
//	public List<Marca> getStatesList() {
//		return marcaList;
//	}
//
//	public ArrayList<Marca> suggest(Object value) {
//		String input = (String) value;
//		ArrayList<Marca> result = new ArrayList<Marca>();
//		for (Marca marca : marcaList) {
//			if ((marca.getMarca().toLowerCase()).startsWith(input.toLowerCase()))
//				result.add(marca);
//		}
//		return result;
//	}
//
//}
