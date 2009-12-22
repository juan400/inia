package INIA.MSCC.WEB.SEG;

import java.util.*;

public class BeanLogin {
	
	private long creditCard;
	public long getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(long creditCard) {
		this.creditCard = creditCard;
	}

	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private int zipCode;
	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	private Date date;
	
	private String usuario;
	private String clave;
	
	public Date getDate() { return(date); }
	
	public void setDate(Date date) {
	this.date = date;
	}
	
	public void setUsuario( String usuario) {
		this.usuario = usuario;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public void setClave( String clave ) {
		this.clave = clave;
	}
	
	public String getClave() {
		return clave;
	}
	
	public String getValidacion() {
		if ( usuario.equals( "Pedro") && clave.equals("Pedro"))
			return "si";
		else
			return "no";
	}
	
	public class NameBean
	{
		private String name;
		private String address;
		
		public NameBean(String pname, String paddress) {
			// TODO Auto-generated constructor stub
			name = pname;
			address = paddress;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

	}
	
	public NameBean[] getNames() {
	NameBean[] names =
	{ new NameBean("Marty Hall","6 Meadowsweet Ct., Reisterstown MD 21136"),
	new NameBean("Bill Gates",	"One Microsoft Way, Redmond WA 98052"),
	new NameBean("George W. Bush",	"1600 Pennsylvania Avenue, Washington DC 20500") };
	return(names);
	}
	
	private String[][] states =
	{ { "Alabama", "AL" },
	{ "Alaska", "AK" },
	{ "Arizona", "AZ" },
	{ "Arkansas", "AR" },
	{ "California", "CA" }};
	public String[][] getStates() {
	{ return(states); }
	}
}
