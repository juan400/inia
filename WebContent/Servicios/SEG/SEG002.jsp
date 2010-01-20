<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>INIA - Modelo de Simulación de Crecimiento de Cultivos de
Secano.</title>
<link href="../../Recursos/css/inia2010-01-17.css" rel="stylesheet"
	type="text/css">
</head>
<body>
<f:loadBundle basename="com.bean.text" var="text" />
<f:view>
	<h:form styleClass="form">
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
						<h:panelGroup rendered="#{registrarBean.init}" />
						<h:panelGrid headerClass="tituloPantalla">
							<f:facet name="header">
								<h:outputText value="#{text.login_newUser}" />
							</f:facet>
							<h:panelGrid rendered="#{registrarBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{loginBean.loginName} #{text.login_alreadyLogged}" />
								<h:commandLink styleClass="textoPlano"
									action="#{registrarBean.logout}" value="#{text.login_logout}" />
							</h:panelGrid>
							<h:panelGroup rendered="#{!registrarBean.logged}">
								<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano">

									<h:outputText value="#{text.registro_Nombre}" />
									<h:inputText value="#{registrarBean.nombre}" />

									<h:outputText value="#{text.registro_Apellido}" />
									<h:inputText value="#{registrarBean.email}" />
									
									<h:outputText value="#{text.registro_Email}" />
									<h:inputText value="#{registrarBean.email}" />

									<h:outputText value="#{text.registro_Teléfono}" />
									<h:inputText value="#{registrarBean.email}" />
									
									
									<h:outputText value="#{text.registro_Celular}" />
									<h:inputText value="#{registrarBean.email}" />
									
									
									<h:outputText value="#{text.registro_Direccion}" />
									<rich:comboBox value="#{registrarBean.email}" />
									
									<h:outputText value="#{text.registro_Ciudad}" />
									<rich:comboBox value="#{registrarBean.email}" />
									
									<h:outputText value="#{text.registro_Departamento_Estado_Provincia}" />
									<rich:comboBox value="#{registrarBean.email}" />
									
									<h:outputText value="#{text.registro_Pais}" />
									<rich:comboBox value="#{registrarBean.email}" />
									
									<h:outputText value="#{text.registro_CodigoPostal}" />
									<h:inputText value="#{registrarBean.email}" />
									<h:outputText value="" />
									<h:commandLink styleClass="textoPlano"
										action="#{registrarBean.registrar}" value="#{text.login_ok}" />

									<f:facet name="footer">
										<h:outputText value="#{registrarBean.error}"
											styleClass="mensajeError" />
									</f:facet>
								</h:panelGrid>
							</h:panelGroup>
							<h:panelGroup rendered="#{registrarBean.activado}">
								<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano">
									<h:outputText value="#{text.registro_Contrasenia}" />
									<h:inputText value="#{registrarBean.nombre}" />

									<h:outputText value="#{text.registro_ConfirmacionContrasenia}" />
									<h:inputText value="#{registrarBean.email}" />

									<h:outputText value="#{text.registro_Frase}" />
									<h:inputText value="#{registrarBean.nombre}" />

									<h:outputText value="#{text.login_registerSubject}" />
									<h:inputText value="#{registrarBean.email}" />

									<h:outputText value="" />
									<h:commandLink styleClass="textoPlano"
										action="#{registrarBean.registrar}" value="#{text.login_ok}" />
									<f:facet name="footer">
										<h:outputText value="#{registrarBean.error}"
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
					<div class="pie"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body>
</html>