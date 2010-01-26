package com.bean.pruebas;

import javax.faces.event.ActionEvent;

import com.inia_mscc.modulos.comun.entidades.Enumerados;
import com.inia_mscc.modulos.seg.SEGFachada;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class LoginAction
{
    
    private SEGFachada segFachada = new SEGFachada(Enumerados.Servicio.Perfil);
	private Bean bean; 
    
    public void listener(ActionEvent event) {
    		@SuppressWarnings("unused")
			Usuario u = segFachada.Login(bean.getName(), bean.getPassword());
//
//    		if (u != null) {
//    			MaestroBean maestro = MaestroBean.getInstance();
//    			maestro.setLogged(true);
//    			maestro.setUsuario(u);
//    			FacesContext.getCurrentInstance()
//                .getApplication()
//                .getNavigationHandler()
//                .handleNavigation(FacesContext.getCurrentInstance(), null, "registered");
//    		} else {
//    			FacesContext.getCurrentInstance().addMessage(
//                        event.getComponent().getClientId(
//                                FacesContext.getCurrentInstance()),
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                        		"El nombre de usuario y password no concuerdan",
//                                "El nombre de usuario y password no concuerdan"));
//    		}
    }
    
    public String ok() {
        return "loggedIn";
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }
}   
