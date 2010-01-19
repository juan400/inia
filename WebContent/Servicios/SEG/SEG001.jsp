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
				<td>
				<div class="logo"></div>
				</td>
			</thead>
			<tbody>
				<td align="center"><h:panelGroup rendered="#{loginBean.init}" />
				<h:panelGrid headerClass="tituloPantalla" style="bordercolo: #DBDFF1;">
					<h:panelGrid rendered="#{loginBean.logged}">
						<h:outputText styleClass="mensajeError"
							value="#{text.login_alreadyLogged}" />
						<h:commandLink styleClass="textoPlano"
							action="#{loginBean.logout}" value="#{text.login_logout}" />
					</h:panelGrid>
					<h:panelGroup rendered="#{!loginBean.logged}" >
						<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano" width="300">

							<h:outputText value="#{text.login_userName}" />
							<h:inputText value="#{loginBean.loginName}" />

							<h:outputText value="#{text.login_password}" />
							<h:inputSecret value="#{loginBean.password}" />

							<h:outputText value="" />
							<h:panelGrid columns="2">
								<h:commandButton styleClass="textoPlano"
									action="#{loginBean.login}" value="#{text.login_login}" />
								<h:commandButton styleClass="textoPlano"
									action="#{loginBean.registrarse}"
									value="#{text.login_register}" />
							</h:panelGrid>
							<f:facet name="footer">
								<h:outputText value="#{loginBean.error}"
									styleClass="mensajeError" />
							</f:facet>
							<h:outputText value="" />
							<h:panelGrid columns="2">

								<h:commandLink styleClass="textoPlano"
									action="#{loginBean.login}"
									value="#{text.login_forgotPassword}" />
							</h:panelGrid>
						</h:panelGrid>
					</h:panelGroup>
				</h:panelGrid></td>
			</tbody>
			<tfoot>
				<td>
				<div class="pie"></div>
				</td>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body>