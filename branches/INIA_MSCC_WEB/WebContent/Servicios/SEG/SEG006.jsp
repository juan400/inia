<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<a4j:region id="contenido">
			<h:panelGrid>
				<rich:panel headerClass="tituloPantalla"
					style="background-color: #ebf3fd;">
					<f:facet name="header">
						<h:outputText value="#{text.reenvioPassword_Titulo}" />
					</f:facet>
					<h:panelGroup>
						<h:panelGrid columns="2" width="480"
							columnClasses="textoPlano,textoPlano">

							<h:outputText value="#{text.registro_Email}" />
							<h:inputText id="txtEmail" styleClass="textoPlano"
								value="#{recuperarContraseniaBean.email}" tabindex="1"
								onblur="validarEmailBlur(this, event)" required="true"
								requiredMessage="Debe ingresar su e-mail de contacto"
								onkeypress="validarEmailKeyPress(this, event)">
								<a4j:support action="#{recuperarContraseniaBean.validarEmail}"
									event="onchange" ajaxSingle="true" reRender="mensajes" />
							</h:inputText>

							<h:outputText value="#{text.registro_Frase}"
								styleClass="textoPlano" />
							<h:inputText id="txtfrase"
								value="#{recuperarContraseniaBean.frase}"
								label="Debe ingresar una frase secreta" styleClass="textoPlano"
								tabindex="2" required="true"
								onkeypress="ValidarCampoLetras(this,event)"
								requiredMessage="Debe ingresar frase secreta">
								<f:validateLength minimum="5" maximum="250" />
								<a4j:support action="#{recuperarContraseniaBean.validarFrase}"
									event="onchange" ajaxSingle="true" reRender="mensajes" />
							</h:inputText>
						</h:panelGrid>
						<center><h:panelGrid columns="2"
							columnClasses="textoPlano,textoPlano">
							<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
								styleClass="textoPlano" tabindex="5"
								action="#{recuperarContraseniaBean.EnviarPassword}"
								value="#{text.boton_Guardar}" />
							<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
								styleClass="textoPlano" tabindex="6" action="cancelar"
								immediate="true" value="#{text.boton_Cancelar}" />
						</h:panelGrid></center>
						<h:panelGrid id="mensajes">
							<rich:messages styleClass="mensajeError">
								<f:facet name="passedMarker">
									<h:graphicImage value="/Recursos/Imagenes/Iconos/passed.gif" />
								</f:facet>
								<f:facet name="errorMarker">
									<h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />
								</f:facet>
							</rich:messages>
							<h:panelGrid>
								<h:outputText styleClass="mensajeError"
									value="#{recuperarContraseniaBean.error}" />
								<h:outputText styleClass="textoPlano"
									value="#{recuperarContraseniaBean.exito}" />
							</h:panelGrid>
						</h:panelGrid>
					</h:panelGroup>
				</rich:panel>
			</h:panelGrid>
			<a4j:status for="contenido"
				onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:100, top:200,height:'90px'})"
				onstop="Richfaces.hideModalPanel('ajaxLoadingModalBox')"></a4j:status>
		</a4j:region>
		<rich:modalPanel id="ajaxLoadingModalBox" minHeight="100"
			minWidth="200" height="40" width="400" zindex="100" styleClass="">
			<rich:panel style="background-color: #ebf3fd; ">
				<center>
				<h2><h:outputText value="Procesando..." styleClass="textoPlano">
				</h:outputText></h2>
				</center>
			</rich:panel>
		</rich:modalPanel>
	</h:form>
</f:view>