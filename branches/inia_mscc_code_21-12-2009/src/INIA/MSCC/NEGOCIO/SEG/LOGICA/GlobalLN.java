package INIA.MSCC.NEGOCIO.SEG.LOGICA;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class GlobalLN {
	
	private GlobalLN() {}
	
	@SuppressWarnings("unchecked")
	public static GlobalLN getInstance() 
	{
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		Map session = context.getSessionMap();
		GlobalLN g = (GlobalLN)session.get("Global");
		if (g == null) {
			g = new GlobalLN();
			session.put("Global", g);
		}
		return g;
	}
	
	private long usuarioId = -1;
	private boolean logged = false;
	
	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}
	
	private List<Integer> metodosElegidos;

	public List<Integer> getMetodosElegidos() {
		return metodosElegidos;
	}

	public void setMetodosElegidos(List<Integer> metodosElegidos) {
		this.metodosElegidos = metodosElegidos;
	}
	
//	private ObjetoGenerico objetoElegido = null;
//
//	public ObjetoGenerico getObjetoElegido() {
//		return objetoElegido;
//	}
//
//	public void setObjetoElegido(ObjetoGenerico objetoElegido) {
//		this.objetoElegido = objetoElegido;
//	}
//	
//	private TipoObjeto tipoBuscado = null;

	private Integer consultaADesplegar;

	public Integer getConsultaADesplegar() {
		return consultaADesplegar;
	}

	public void setConsultaADesplegar(Integer consultaADesplegar) {
		this.consultaADesplegar = consultaADesplegar;
	}
	
	private Integer consultaAEjecutar;

	public Integer getConsultaAEjecutar() {
		return consultaAEjecutar;
	}

	public void setConsultaAEjecutar(Integer consultaAEjecutar) {
		this.consultaAEjecutar = consultaAEjecutar;
	}
	
	private boolean copiarConsulta;

	public boolean isCopiarConsulta() {
		return copiarConsulta;
	}

	public void setCopiarConsulta(boolean copiarConsulta) {
		this.copiarConsulta = copiarConsulta;
	}

	private boolean mustInit = false;

	public boolean isMustInit() {
		return mustInit;
	}

	public void setMustInit(boolean init) {
		this.mustInit = init;
	}
	
	private Integer factorElegido = null;

	public Integer getFactorElegido() {
		return factorElegido;
	}

	public void setFactorElegido(Integer factorElegido) {
		this.factorElegido = factorElegido;
	}

//	public TipoObjeto getTipoBuscado() {
//		return tipoBuscado;
//	}
//
//	public void setTipoBuscado(TipoObjeto tipoBuscado) {
//		this.tipoBuscado = tipoBuscado;
//	}
//	
//	private Metrica metricaParaMetodos;
//
//	public Metrica getMetricaParaMetodos() {
//		return metricaParaMetodos;
//	}
//
//	public void setMetricaParaMetodos(Metrica metricaParaMetodos) {
//		this.metricaParaMetodos = metricaParaMetodos;
//	}
	
	private boolean recargarArbol;

	public boolean isRecargarArbol() {
		return recargarArbol;
	}

	public void setRecargarArbol(boolean recargarArbol) {
		this.recargarArbol = recargarArbol;
	}

	private String tipoACrear;

	public String getTipoACrear() {
		return tipoACrear;
	}

	public void setTipoACrear(String tipoACrear) {
		this.tipoACrear = tipoACrear;
	}
	
//	private Dimension dimensionElegidaParaAgregar;
//	
//	private Factor factorElegidoParaAgregar;
//
//	public Dimension getDimensionElegidaParaAgregar() {
//		return dimensionElegidaParaAgregar;
//	}
//
//	public void setDimensionElegidaParaAgregar(Dimension dimensionElegidaParaAgregar) {
//		this.dimensionElegidaParaAgregar = dimensionElegidaParaAgregar;
//	}
//
//	public Factor getFactorElegidoParaAgregar() {
//		return factorElegidoParaAgregar;
//	}
//
//	public void setFactorElegidoParaAgregar(Factor factorElegidoParaAgregar) {
//		this.factorElegidoParaAgregar = factorElegidoParaAgregar;
//	}
	
	private Integer objetivoParaHistorial;

	public Integer getObjetivoParaHistorial() {
		return objetivoParaHistorial;
	}

	public void setObjetivoParaHistorial(Integer objetivoParaHistorial) {
		this.objetivoParaHistorial = objetivoParaHistorial;
	}
	
	private String opcion;

	public String getOpcion() {
		return opcion;
	}

	public void setOpcion(String opcion) {
		this.opcion = opcion;
	}
	
	private ResourceBundle textBundle;
	
	public ResourceBundle getTextBundle() {
		if (textBundle == null) {
			textBundle = ResourceBundle.getBundle("text", FacesContext.getCurrentInstance().getExternalContext().getRequestLocale());
		}
		return textBundle;
	}
	
	private ResourceBundle config;
	
	public ResourceBundle getConfig() {
		if (config == null) {
			config = ResourceBundle.getBundle("qgerbil");
		}
		return config;
	}
	
	private boolean reload = false;

	public boolean isReload() {
		return reload;
	}

	public void setReload(boolean reload) {
		this.reload = reload;
	}
}
