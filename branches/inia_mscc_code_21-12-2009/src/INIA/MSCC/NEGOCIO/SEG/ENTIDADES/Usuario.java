package INIA.MSCC.NEGOCIO.SEG.ENTIDADES;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Entity;
import org.hibernate.annotations.Table;

@Entity @Table(appliesTo="tl_seg_usua_usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String nombre;
	private String password;
	private long idDatosUsuario;

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}    
	@Basic
    @Column(name = "usua_str_nombre")
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Basic
    @Column(name = "usua_str_password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Basic
    @Column(name= "usua_num_id_dato_usuario")
	public long getIdDatosUsuario() {
		return idDatosUsuario;
	}
	public void setIdDatosUsuario(long idDatosUsuario) {
		this.idDatosUsuario = idDatosUsuario;
	}	
}
