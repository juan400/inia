package com.bean.seg;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.bean.comun.MaestroBean;
import com.inia_mscc.modulos.adm.entidades.Ciudad;
import com.inia_mscc.modulos.adm.entidades.Departamento;
import com.inia_mscc.modulos.adm.entidades.Pais;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Estado;
import com.inia_mscc.modulos.comun.entidades.Enumerados.EstadoUsuario;
import com.inia_mscc.modulos.comun.entidades.Enumerados.Servicio;
import com.inia_mscc.modulos.seg.entidades.Perfil;
import com.inia_mscc.modulos.seg.entidades.Usuario;

public class DatosUsuarioBean extends MaestroBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String formatoTelefono;
	private String celular;
	private String formatoCelular;
	private String direccion;
	private String paisElegido;
	private String departamentoElegido;
	private String ciudadElegido;
	private String codigoPostal;
	private String estado;
	private Perfil perfil;
	private SelectItem[] paises;
	private SelectItem[] departamentos;
	private SelectItem[] ciudades;
	private List<Pais> listPaises;
	private List<Departamento> listDepartamentos;
	private List<Ciudad> listCiudades;
	private Pais pais;
	private Departamento depto;
	private Ciudad ciudad;
	private List<Usuario> usuarios;
	private Usuario usuario = new Usuario();

	public boolean isInit() {
		this.usuarios = this.getSegFachada(Servicio.Usuario).ObtenerUsuarios();
		return false;
	}

	public String alta() {
		this.setError("");
		this.setExito("");
		return "Alta";
	}

	public String verUsuario() {
		this.alta();
		Map paramMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap();
		String usuarioElegido = (String) paramMap.get("usuarioElegido");
		Iterator<Usuario> it = usuarios.iterator();
		while (it.hasNext()) {
			Usuario usuarioSeleccionado = (Usuario) it.next();
			if (usuarioSeleccionado.get_id() == (long) Long
					.parseLong(usuarioElegido)) {
				usuario = usuarioSeleccionado;

				nombre = usuario.get_datos().get_nombre();
				apellido = usuario.get_datos().get_apellido();
				estado = usuario.get_datos().get_estado().toString();
				perfil = usuario.get_datos().get_perfil();
			}
		}
		return "resultados";
	}

	public DatosUsuarioBean() throws Exception {
		try {
			this.setError("");
			this.setExito("");
			this.setUsuario((Usuario) this.getSesion(Usuario.class.toString()));
			if (this.getUsuario() != null) {
				pais = new Pais();
				pais = this.getUsuario().get_datos().get_pais();
				depto = new Departamento();
				depto = this.getUsuario().get_datos().get_departamento();
				ciudad = new Ciudad();
				ciudad = this.getUsuario().get_datos().get_ciudad();

				nombre = this.getUsuario().get_datos().get_nombre();
				apellido = this.getUsuario().get_datos().get_apellido();
				email = this.getUsuario().get_datos().get_mail();
				direccion = this.getUsuario().get_datos().get_direccion();
				telefono = this.getUsuario().get_datos().get_tele();
				celular = this.getUsuario().get_datos().get_cel();

				listPaises = this.getAdmFachada(Servicio.RelacionPCD)
						.ObtenerPaises();
				if (pais != null) {
					listDepartamentos = this
							.getAdmFachada(Servicio.RelacionPCD)
							.ObtenerDepartamentosXPais(pais);
					if (depto != null) {
						listCiudades = this.getAdmFachada(Servicio.RelacionPCD)
								.ObtenerCiudadesXDeptos(depto);
					} else {
						listCiudades = this.getAdmFachada(Servicio.RelacionPCD)
								.ObtenerCiudades();
					}
				} else {
					listDepartamentos = super.getAdmFachada(
							Servicio.RelacionPCD).ObtenerDepartamentos();
					listCiudades = super.getAdmFachada(Servicio.RelacionPCD)
							.ObtenerCiudades();
				}
				paises = new SelectItem[listPaises.size() + 1];
				paises[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int i = 1;
				for (Pais p : listPaises) {
					SelectItem si = new SelectItem(p.get_nombre());
					paises[i] = si;
					i++;
				}
				departamentos = new SelectItem[listDepartamentos.size() + 1];
				departamentos[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int j = 1;
				for (Departamento d : listDepartamentos) {
					SelectItem si = new SelectItem(d.get_nombre());
					departamentos[j] = si;
					j++;
				}
				ciudades = new SelectItem[listCiudades.size() + 1];
				ciudades[0] = new SelectItem(this
						.getTextBundleKey("combo_seleccione"));
				int l = 1;
				for (Ciudad c : listCiudades) {
					SelectItem si = new SelectItem(c.get_nombre());
					ciudades[l] = si;
					l++;
				}
				if (this.getUsuario().get_datos().get_pais() != null) {
					paisElegido = this.getUsuario().get_datos().get_pais()
							.get_nombre();
				}
				if (this.getUsuario().get_datos().get_departamento() != null) {
					departamentoElegido = this.getUsuario().get_datos()
							.get_departamento().get_nombre();
				}
				if (this.getUsuario().get_datos().get_ciudad() != null) {
					ciudadElegido = this.getUsuario().get_datos().get_ciudad()
							.get_nombre();
				}
				if (paisElegido.isEmpty()) {
					paisElegido = paises[0].getValue().toString();
				}
				if (departamentoElegido.isEmpty()) {
					departamentoElegido = departamentos[0].getValue()
							.toString();
				}
				if (ciudadElegido.isEmpty()) {
					ciudadElegido = ciudades[0].getValue().toString();
				}
			} else {
				this
						.setError("Se a perdido la sesion de su usuario, vuelva a ingresar.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionEmail() {
		try {
			if (!this.getUsuario().get_datos().get_mail().equals(email)) {
				if (!this.getSegFachada(Servicio.Usuario).ComprobarEmail(email)) {
					this
							.setError("El e-mail ingresado ya esta registrado en el sistema.");
					this.email = this.getUsuario().get_datos().get_mail();
				} else {
					this.setError("");
				}
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionCiudad() {
		try {
			ciudad = new Ciudad();
			ciudad.set_nombre(getCiudadElegido());
			ciudad = this.getAdmFachada(Servicio.RelacionPCD).ObtenerCiudad(
					ciudad);
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionDepartamento() {
		try {
			depto = new Departamento();
			depto.set_nombre(getDepartamentoElegido());
			depto = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamento(depto);
			listCiudades = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerCiudadesXDeptos(depto);
			ciudades = new SelectItem[listCiudades.size() + 1];
			ciudades[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int l = 1;
			for (Ciudad c : listCiudades) {
				SelectItem si = new SelectItem(c.get_nombre());
				ciudades[l] = si;
				l++;
			}
			ciudadElegido = ciudades[0].getValue().toString();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public void takeSelectionPais() {
		try {
			pais = new Pais();
			pais.set_nombre(getPaisElegido());
			pais = this.getAdmFachada(Servicio.RelacionPCD).ObtenerPais(pais);
			listDepartamentos = this.getAdmFachada(Servicio.RelacionPCD)
					.ObtenerDepartamentosXPais(pais);
			departamentos = new SelectItem[listDepartamentos.size() + 1];
			departamentos[0] = new SelectItem(this
					.getTextBundleKey("combo_seleccione"));
			int j = 1;
			for (Departamento d : listDepartamentos) {
				SelectItem si = new SelectItem(d.get_nombre());
				departamentos[j] = si;
				j++;
			}
			departamentoElegido = departamentos[0].getValue().toString();

		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
	}

	public String modificar() {
		String retorno = "";
		try {
			this.getUsuario().get_datos().set_nombre(nombre);
			this.getUsuario().get_datos().set_apellido(apellido);
			this.getUsuario().get_datos().set_mail(email);
			this.getUsuario().get_datos().set_pais(pais);
			this.getUsuario().get_datos().set_departamento(depto);
			this.getUsuario().get_datos().set_ciudad(ciudad);
			this.getUsuario().get_datos().set_direccion(direccion);
			this.getUsuario().get_datos().set_cel(celular);
			this.getUsuario().get_datos().set_tele(telefono);
			this.getUsuario().get_datos().set_fechaRegistro(new Date());
			this.getUsuario().get_datos().set_timeStamp(new Date());
			this.getSegFachada(Servicio.Usuario).ActualizarDatosUsuario(
					this.getUsuario().get_datos());
			if (this.getUsuario().get_datos() != null) {
				this.setError("");
				this.setExito("Se a modificado los datos de su cuenta.");
				retorno = "SEG001";
				this.setUsuario(null);
				this.setSesion(Usuario.class.toString(), null);
			} else {
				this
						.setError("No ha sido posible registrar el usuario, revise los datos ingresados y intentelo nuevamente.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public String baja() {
		String retorno = "";
		try {
			this.getUsuario().set_estadoUsuario(EstadoUsuario.Inactivo);
			this.getUsuario().get_datos().set_estado(Estado.Inactivo);
			this.getSegFachada(Servicio.Usuario).DarBajaBloquearUsuario(
					this.getUsuario());
			if (this.getUsuario().get_estadoUsuario().equals(
					EstadoUsuario.Inactivo)) {
				this.setError("");
				this
						.setExito("Se a inactivado su cuenta, de esta manera queda dado de baja.");
				retorno = "ok";
			} else {
				this
						.setError("No ha sido posible inactivar su cuenta, intentelo nuevamente.");
			}
		} catch (Exception ex) {
			this.setError(ex.getMessage());
		}
		return retorno;
	}

	public boolean isLogged() {
		return MaestroBean.getInstance().isLogged();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getFormatoTelefono() {
		return formatoTelefono;
	}

	public void setFormatoTelefono(String formatoTelefono) {
		this.formatoTelefono = formatoTelefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFormatoCelular() {
		return formatoCelular;
	}

	public void setFormatoCelular(String formatoCelular) {
		this.formatoCelular = formatoCelular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPaisElegido() {
		return paisElegido;
	}

	public void setPaisElegido(String paisElegido) {
		this.paisElegido = paisElegido;
	}

	public String getDepartamentoElegido() {
		return departamentoElegido;
	}

	public void setDepartamentoElegido(String departamentoElegido) {
		this.departamentoElegido = departamentoElegido;
	}

	public String getCiudadElegido() {
		return ciudadElegido;
	}

	public void setCiudadElegido(String ciudadElegido) {
		this.ciudadElegido = ciudadElegido;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public SelectItem[] getPaises() {
		return paises;
	}

	public void setPaises(SelectItem[] paises) {
		this.paises = paises;
	}

	public SelectItem[] getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(SelectItem[] departamentos) {
		this.departamentos = departamentos;
	}

	public SelectItem[] getCiudades() {
		return ciudades;
	}

	public void setCiudades(SelectItem[] ciudades) {
		this.ciudades = ciudades;
	}

	public List<Pais> getListPaises() {
		return listPaises;
	}

	public void setListPaises(List<Pais> listPaises) {
		this.listPaises = listPaises;
	}

	public List<Departamento> getListDepartamentos() {
		return listDepartamentos;
	}

	public void setListDepartamentos(List<Departamento> listDepartamentos) {
		this.listDepartamentos = listDepartamentos;
	}

	public List<Ciudad> getListCiudades() {
		return listCiudades;
	}

	public void setListCiudades(List<Ciudad> listCiudades) {
		this.listCiudades = listCiudades;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Departamento getDepto() {
		return depto;
	}

	public void setDepto(Departamento depto) {
		this.depto = depto;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

}
