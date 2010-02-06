<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<h:panelGroup rendered="#{registroBean.init}" />
		<h:panelGrid>
			<rich:panel headerClass="tituloPantalla"
				style="background-color: #ebf3fd; ">
				<f:facet name="header">
					<h:outputText value="#{text.login_newUser}" />
				</f:facet>
				<h:panelGrid rendered="#{!loginBean.logged}">
					<h:outputText styleClass="mensajeError"
						value="#{text.login_notLogged}" />
					<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
						styleClass="textoPlano" action="cancelar"
						value="#{text.login_login}" />
				</h:panelGrid>
				<h:panelGroup rendered="#{!loginBean.logged}">
					<h:panelGrid columns="2" columnClasses="textoPlano,textoPlano">

						<h:outputText value="#{text.registro_Nombre}" />
						<h:inputText value="#{}" required="true"
							onkeypress="ValidarCampoLetras(this, event)"
							onblur="ValidarCampoLetras(this, event)" />

						<h:outputText value="#{text.registro_Apellido}" />
						<h:inputText value="#{}" required="true"
							onkeypress="ValidarCampoLetras(this, event)"
							onblur="ValidarCampoLetras(this, event)" />

						<h:outputText value="#{text.registro_Email}" />
						<h:inputText id="txtEmail" value="#{}"
							required="true" onblur="validarEmailBlur(this, event)"
							onkeypress="validarEmailKeyPress(this, event)">
							<a4j:support reRender="txtEmail"
								action="#{registroBean.validarEmail}" event="onblur"
								ajaxSingle="true"></a4j:support>
						</h:inputText>

						<h:outputLabel value="#{text.registro_Pais}" />
						<rich:comboBox value="#{}"
							enableManualInput="false" styleClass="combo">
							<f:selectItems value="#{}" />
							<a4j:support action="#{}"
								event="onchange" ajaxSingle="true" reRender="cmbDepartamentos" />
						</rich:comboBox>

						<h:outputText
							value="#{text.registro_Departamento_Estado_Provincia}" />
						<rich:comboBox id="cmbDepartamentos" enableManualInput="false"
							value="#{}">
							<f:selectItems value="#{}" />
							<a4j:support action="#{}"
								event="onchange" ajaxSingle="true" reRender="cmbCiudad" />
						</rich:comboBox>

						<h:outputText value="#{text.registro_Ciudad}" />
						<rich:comboBox id="cmbCiudad"
							value="#{}" enableManualInput="false">
							<f:selectItems value="#{}" />
							<a4j:support action="#{}"
								event="onchange" ajaxSingle="true" />
						</rich:comboBox>

						<h:outputText value="#{text.registro_Direccion}" />
						<h:inputText value="#{}" required="true"
							onkeypress="ValidarCampoConCaracteresEspeciales(this, event)"
							onblur="ValidarCampoConCaracteresEspeciales(this, event)" />

						<h:outputText value="#{text.registro_Teléfono}" />
						<h:inputText value="#{}"
							onblur="ValidarCampoTelefono(this, event)"
							onkeypress="ValidarCampoTelefono(this, event)" />

						<h:outputText value="#{text.registro_Celular}" />
						<h:inputText value="#{}"
							onblur="ValidarCampoTelefono(this, event)"
							onkeypress="ValidarCampoTelefono(this, event)" />


						<h:panelGrid>
							<h:outputText styleClass="mensajeError"
								value="#{}" />
							<h:outputText styleClass="textoPlano"
								value="#{}" />
						</h:panelGrid>
						<h:outputText value="" />
						<h:panelGrid columns="2">
							<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
								styleClass="textoPlano" action="#{}"
								value="#{text.boton_Aceptar}" />
							<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
								styleClass="textoPlano" action="#{}"
								value="#{text.boton_Cancelar}" />
						</h:panelGrid>
						<f:facet name="footer">
							<h:outputText styleClass="mensajeError"
								value="#{}" />
							<h:outputText styleClass="textoPlano"
								value="#{}" />
						</f:facet>
					</h:panelGrid>
				</h:panelGroup>
			</rich:panel>
		</h:panelGrid>
	</h:form>
</f:view>