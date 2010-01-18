package com.bean.comun;


public class MenuBean {

//	MenuGroup
//	HtmlMenuGroup
//	DropDownMenu
//	MenuGroupRenderer
//	MenuGroupTag
	
	public String inicio() {
		MaestroBean.getInstance().setOpcion("inicio");
		return "inicio";
	}
	
	public String modelo() {
		MaestroBean.getInstance().setOpcion("modelo");
		return "modelo";
	}
	
	public String listado() {
		MaestroBean.getInstance().setOpcion("listado");
		return "listado";
	}
	
	public String objetos() {
		MaestroBean.getInstance().setOpcion("objetos");
		return "objetos";
	}

	public String getOpcion() {
		return MaestroBean.getInstance().getOpcion();
	}

	public void setOpcion(String opcion) {
		MaestroBean.getInstance().setOpcion(opcion);
	}
}
