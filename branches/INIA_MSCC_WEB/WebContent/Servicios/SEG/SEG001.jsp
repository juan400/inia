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
<link href="Recursos/css/inia2010-01-17.css" rel="stylesheet"
	type="text/css">
</head>
<body>
<f:loadBundle basename="com.bean.text" var="text" />
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<table align="center" width="956px">
			<thead>
				<tr>
					<td>
					<div class="logo"></div>
					</td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td align="center"><h:panelGroup rendered="#{loginBean.init}" />
					<h:panelGrid headerClass="tituloPantalla">
						<f:facet name="header">
							<h:outputText value="#{text.login_title}" />
						</f:facet>
						<h:panelGrid rendered="#{loginBean.logged}">
							<h:outputText styleClass="mensajeError"
								value="#{text.login_alreadyLogged}" />
							<a4j:commandButton
								action="#{loginBean.logout}" value="#{text.login_logout}" />
						</h:panelGrid>
						<h:panelGroup rendered="#{!loginBean.logged}">
							<h:panelGrid columns="2" width="300">

								<h:outputText value="#{text.login_userName}" />
								<h:inputText value="#{loginBean.loginName}" />

								<h:outputText value="#{text.login_password}" />
								<h:inputSecret value="#{loginBean.password}" />

								<h:outputText value="" />
								<h:panelGrid columns="2">
									<a4j:commandButton styleClass="textoPlano"
										action="#{loginBean.login}" value="#{text.login_login}" />
									<a4j:commandButton styleClass="textoPlano"
										action="#{loginBean.registrarse}"
										value="#{text.login_register}" />
								</h:panelGrid>
								<f:facet name="footer">
									<h:outputText value="#{loginBean.error}"
										styleClass="mensajeError" />
								</f:facet>
								<h:outputText value="" />
								<h:panelGrid columns="2">

									<h:commandLink action="#{loginBean.login}"
										value="#{text.login_forgotPassword}" />
								</h:panelGrid>
							</h:panelGrid>
						</h:panelGroup>
					</h:panelGrid></td>
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