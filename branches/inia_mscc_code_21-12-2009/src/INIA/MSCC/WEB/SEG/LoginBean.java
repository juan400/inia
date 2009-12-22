package INIA.MSCC.WEB.SEG;

import java.io.Serializable;

import INIA.MSCC.DAO.UsuarioDAO;
import INIA.MSCC.NEGOCIO.SEG.ENTIDADES.Usuario;
import INIA.MSCC.NEGOCIO.SEG.LOGICA.GlobalLN;

public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public boolean isInit() {
		return false;
	}
	
	public boolean isLogged() {
		return false;//GlobalLN.getInstance().isLogged();
	}
	
	private String nombre;
	private String password;
	private String error;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getError() {
		return error;
	}

	public String login() 
	{
		//GlobalLN.getInstance().getTextBundle();
		Usuario u = UsuarioDAO.login(nombre, password);
		
		if (u != null) 
		{
			GlobalLN g = GlobalLN.getInstance();
			g.setLogged(true);
			g.setUsuarioId(u.getId());

			error = "";
			GlobalLN.getInstance().setOpcion("listado");
			return "login-ok";
		} 
		else 
		{
			error = "El nombre de usuario y password no concuerdan";
			return "login-error";
		}
	}
	
	public String logout() 
	{
		if (!isLogged()) return "";
		
		GlobalLN g = GlobalLN.getInstance();
		g.setLogged(false);
		g.setUsuarioId(-1);

		error = "";
		return "";
	}
	
	public String registrarse() {
		return "registrarse";
	}

}