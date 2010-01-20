<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>INIA - Modelo de Simulación de Crecimiento de Cultivos de Secano.</title>
<link href="<%= request.getContextPath() %>/Recursos/css/inia2010-01-17.css" rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript" src="<%= request.getContextPath() %>/Recursos/Scripts/JSComun.js"></script>
</head>
<body>
<f:loadBundle basename="com.bean.text" var="text" />
<f:view>
	<h:form styleClass="form" >
		<f:loadBundle basename="com.bean.text" var="text" />
		<table align="center" width="956px">
			<thead>
				<tr>
					<td>
					<div class="logo"></div>
					</td>
				</tr>
			</thead>
			<tbody class="">
				<tr>
					<td align="center"><h:form>
						<h:panelGroup rendered="#{registroBean.init}" />
						<h:panelGrid>
							<f:facet name="header" >
								<h:outputText value="#{text.login_newUser}" />
							</f:facet>
							<h:panelGrid rendered="#{registroBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{loginBean.loginName} #{text.login_alreadyLogged}" />
								<a4j:commandButton styleClass="textoPlano" action="#{registroBean.cancelar}"
										value="#{text.boton_Cancelar}" />
							</h:panelGrid>
							<h:panelGroup rendered="#{!registroBean.logged}">
								<h:panelGrid columns="2" columnClasses="textoPlano,textoPlano">

									<h:outputText value="#{text.registro_Nombre}" />
									<h:inputText value="#{registroBean.nombre}" onkeypress="validarSoloLetras(this, event)" 
									onchange="validarSoloLetras(this, event)"/>

									<h:outputText value="#{text.registro_Apellido}" />
									<h:inputText value="#{registroBean.apellido}" />

									<h:outputText value="#{text.registro_Email}" />
									<h:inputText id="txtEmail" onchange="validarEmail(this, event)" onblur="validarEmail(this, event)" onkeypress="validarEmail(this, event)" value="#{registroBean.email}" />

									<h:outputText value="#{text.registro_Teléfono}" />
									<h:inputText value="#{registroBean.telefono}" />

									<h:outputText value="#{text.registro_Celular}" />
									<h:inputText value="#{registroBean.celular}" />

									<h:outputText value="#{text.registro_Direccion}" />
									<h:inputText value="#{registroBean.direccion}" />

									<h:outputText value="#{text.registro_Ciudad}" />
									<rich:comboBox value="#{registroBean.ciudad}" />

									<h:outputText value="#{text.registro_Departamento_Estado_Provincia}" />
									<rich:comboBox value="#{registroBean.departamento}" />

									<h:outputLabel value="#{text.registro_Pais}" />
									<rich:comboBox value="#{registroBean.pais}" />
									
									<h:outputText value=""/>
									<h:panelGrid columns="2">
										<a4j:commandButton action="#{registroBean.registrar}"
										value="#{text.boton_Aceptar}" styleClass="textoPlano"/>
									<a4j:commandButton action="#{registroBean.cancelar}"
										value="#{text.boton_Cancelar}" styleClass="textoPlano"/>
									</h:panelGrid>
									<f:facet name="footer">
									 
										<h:outputText value="#{registroBean.error}"
											styleClass="mensajeError" />
									</f:facet>
								</h:panelGrid>
							</h:panelGroup>
							<h:panelGroup rendered="#{registroBean.activado}">
								<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano">
									<h:outputText value="#{text.registro_Contrasenia}" />
									<h:inputText value="#{registroBean.contrasenia}" />

									<h:outputText value="#{text.registro_ConfirmacionContrasenia}" />
									<h:inputText value="#{registroBean.confirmacion}" />

									<h:outputText value="#{text.registro_Frase}" />
									<h:inputText value="#{registroBean.nombre}" />

									<h:outputText value="#{text.login_registerSubject}" />
									<h:inputText value="#{registroBean.email}" />

									<h:outputText value="" />
									<h:commandLink styleClass="textoPlano"
										action="#{registroBean.registrar}" value="#{text.login_ok}" />
									<f:facet name="footer">
										<h:outputText value="#{registroBean.error}"
											styleClass="mensajeError" />
									</f:facet>
								</h:panelGrid>
							</h:panelGroup>
						</h:panelGrid>
					</h:form></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
					<div class="copyrigth"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body>
</html>