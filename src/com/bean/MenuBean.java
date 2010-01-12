package com.bean;

import org.richfaces.component.html.HtmlDropDownMenu;

public class MenuBean {

	public String inicio() {
		//Global.getInstance().setOpcion("inicio");
		return "inicio";
	}
	
	public String modelo() {
		//Global.getInstance().setOpcion("modelo");
		return "modelo";
	}
	
	public String listado() {
		//Global.getInstance().setOpcion("listado");
		return "listado";
	}
	
	public String objetos() {
		//Global.getInstance().setOpcion("objetos");
		return "objetos";
	}

	public String getOpcion() {
		return "";//Global.getInstance().getOpcion();
	}

	public void setOpcion(String opcion) {
		//Global.getInstance().setOpcion(opcion);
	}
	
	public HtmlDropDownMenu generarMenu()
	{
		HtmlDropDownMenu menu = new HtmlDropDownMenu();
		
		
		
		return menu;
		
		
	}
}
