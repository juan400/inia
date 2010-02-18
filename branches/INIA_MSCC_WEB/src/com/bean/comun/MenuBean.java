package com.bean.comun;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

import javax.el.MethodExpression;
import javax.faces.context.FacesContext;

import org.ajax4jsf.component.html.HtmlActionParameter;
import org.richfaces.component.html.HtmlPanelMenu;
import org.richfaces.component.html.HtmlPanelMenuGroup;
import org.richfaces.component.html.HtmlPanelMenuItem;

import com.inia_mscc.modulos.adm.entidades.Transaccion;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Proceso;
import com.inia_mscc.modulos.comun.entidades.Enumerados.NombreProceso;
import com.inia_mscc.modulos.comun.entidades.Enumerados.TransaccionesNoContenidas;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class MenuBean extends MaestroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String current;
	private String pagina;
	private boolean singleMode;
	private boolean navegacion;
	private HtmlPanelMenu panelMenu;
	private List<Transaccion> transacciones;

	public boolean isInit() {
		return false;
	}

	public HtmlPanelMenu getPanelMenu() {
		return panelMenu;
	}

	public void setPanelMenu(HtmlPanelMenu panelMenu) {
		this.panelMenu = panelMenu;
	}

	public MenuBean() {
		singleMode = true;
		current = "/Servicios/Contenedora.jsp";
		panelMenu = new HtmlPanelMenu();
		panelMenu.setStyleClass("menu");
		panelMenu.setStyle("width:200px");
		panelMenu.setMode("server");
		panelMenu.setIconExpandedGroup("disc");
		panelMenu.setIconCollapsedGroup("disc");
		panelMenu.setIconExpandedTopGroup("chevronUp");
		panelMenu.setIconGroupTopPosition("right");
		panelMenu.setIconCollapsedTopGroup("chevronDown");
		panelMenu.setRendered(true);
		try {
			super.setUsuario((Usuario) super
					.getSesion(Usuario.class.toString()));
			transacciones = super.getUsuario().get_datos().get_perfil()
					.get_transaccionesSistema();
			HtmlPanelMenuGroup menuGroupADM = new HtmlPanelMenuGroup();
			HtmlPanelMenuGroup menuGroupSEG = new HtmlPanelMenuGroup();
			HtmlPanelMenuGroup menuGroupGEM = new HtmlPanelMenuGroup();
			HtmlPanelMenuGroup menuGroupEJE = new HtmlPanelMenuGroup();
			HtmlPanelMenuGroup menuGroupHPE = new HtmlPanelMenuGroup();
			if (transacciones != null) {
				menuGroupADM.setName(Proceso.ADM.name());
				menuGroupADM.setLabel(NombreProceso.Administración.name());
				menuGroupADM.setStyleClass("textoMenu");
				menuGroupSEG.setName(Proceso.SEG.name());
				menuGroupSEG.setLabel(NombreProceso.Seguridad.name());
				menuGroupSEG.setStyleClass("textoMenu");
				menuGroupGEM.setName(Proceso.GEM.name());
				menuGroupGEM.setLabel(NombreProceso.Escenarios.name());
				menuGroupGEM.setStyleClass("textoMenu");
				menuGroupEJE.setName(Proceso.EJE.name());
				menuGroupEJE.setLabel(NombreProceso.Ejecución.name());
				menuGroupEJE.setStyleClass("textoMenu");
				menuGroupHPE.setName(Proceso.HPE.name());
				menuGroupHPE.setLabel(NombreProceso.Historial.name());
				menuGroupHPE.setStyleClass("textoMenu");
				for (Transaccion unaTransa : transacciones) {
					if (unaTransa.get_estado().equals(Estado.Activo)) {
						if (unaTransa.get_codigoBase().startsWith(
								Proceso.ADM.name())) {
							if (!unaTransa.get_codigoBase().equalsIgnoreCase(
									TransaccionesNoContenidas.ADM002.name())

									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.ADM006
															.name())

									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.ADM007
															.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.ADM009
															.name())) {
								menuGroupADM.getChildren().add(
										cargarItemMenu(unaTransa));
							}
						}
						if (unaTransa.get_codigoBase().startsWith(
								Proceso.SEG.name())) {
							if (!unaTransa.get_codigoBase().equalsIgnoreCase(
									TransaccionesNoContenidas.SEG001.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.SEG002
															.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.SEG003
															.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.SEG006
															.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.SEG009
															.name())
									&& !unaTransa
											.get_codigoBase()
											.equalsIgnoreCase(
													TransaccionesNoContenidas.SEG010
															.name())) {
								menuGroupSEG.getChildren().add(
										cargarItemMenu(unaTransa));
							}
						}
						if (unaTransa.get_codigoBase().startsWith(
								Proceso.GEM.name())) {
							menuGroupGEM.getChildren().add(
									cargarItemMenu(unaTransa));
						}
						if (unaTransa.get_codigoBase().startsWith(
								Proceso.EJE.name())) {
							menuGroupEJE.getChildren().add(
									cargarItemMenu(unaTransa));
						}
						if (unaTransa.get_codigoBase().startsWith(
								Proceso.HPE.name())) {
							menuGroupHPE.getChildren().add(
									cargarItemMenu(unaTransa));
						}
					}
				}
			}
			if (!menuGroupSEG.getChildren().isEmpty()) {
				panelMenu.getChildren().add(menuGroupSEG);
			}
			if (!menuGroupEJE.getChildren().isEmpty()) {
				panelMenu.getChildren().add(menuGroupEJE);
			}
			if (!menuGroupGEM.getChildren().isEmpty()) {
				panelMenu.getChildren().add(menuGroupGEM);
			}
			if (!menuGroupHPE.getChildren().isEmpty()) {
				panelMenu.getChildren().add(menuGroupHPE);
			}
			if (!menuGroupADM.getChildren().isEmpty()) {
				panelMenu.getChildren().add(menuGroupADM);
			}
			this.setPanelMenu(panelMenu);
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
	}

	private HtmlPanelMenuItem cargarItemMenu(Transaccion pTransa) {
		HtmlPanelMenuItem menuItem = new HtmlPanelMenuItem();
		menuItem.setName(pTransa.get_codigoBase());
		menuItem.setLabel(pTransa.get_descripcion());
		menuItem.setStyleClass("textoMenuSecundario");
		menuItem.setImmediate(true);
		menuItem.setReRender("contenido");
		MethodExpression me = FacesContext.getCurrentInstance()
				.getApplication().getExpressionFactory()
				.createMethodExpression(
						FacesContext.getCurrentInstance().getELContext(),
						"#{menuBean.updateCurrent}", String.class,
						new Class[] {});
		menuItem.setActionExpression(me);
		HtmlActionParameter actionParam = new HtmlActionParameter();
		actionParam.setName("current");
		// actionParam.setValue(pTransa.get_url());
		actionParam.setValue(pTransa.get_codigoBase());
		// TODO Se agrega el parametro "current" al item
		menuItem.getChildren().add(actionParam);
		actionParam.setParent(menuItem);
		return menuItem;
	}

	public boolean isSingleMode() {
		return singleMode;
	}

	public void setSingleMode(boolean singleMode) {
		this.singleMode = singleMode;
	}

	public String updateCurrent() {
		try {
			navegacion = true;
			FacesContext context = FacesContext.getCurrentInstance();
			setCurrent(URI.create(
					context.getExternalContext().getRequestParameterMap().get(
							"current")).toString());
			setPagina(context.getExternalContext().getRequestParameterMap()
					.get("current").toString());
			System.out.println(this.getCurrent());
			setSingleMode(true);
		} catch (Exception ex) {
			super.setError(ex.getMessage());
		}
		return getPagina();
	}

	public String getPagina() {
		return pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public boolean isNavegacion() {
		return navegacion;
	}

	public void setNavegacion(boolean navegacion) {
		this.navegacion = navegacion;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

}
