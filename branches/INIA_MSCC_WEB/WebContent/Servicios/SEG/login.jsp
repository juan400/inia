<!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<title>Login Page</title>
</head>
<body>
<f:subview id="login">
	<f:loadBundle basename="com.bean.text" var="text" />
	<div class="logo"></div>
	<h:form>
		<h:panelGroup rendered="#{loginBean.init}" />
		<h:panelGrid headerClass="tituloPantalla">
			<h:panelGrid rendered="#{loginBean.logged}">
				<h:outputText styleClass="mensajeError"
					value="#{text.login_alreadyLogged}" />
				<h:commandLink styleClass="textoPlano" action="#{loginBean.logout}"
					value="#{text.login_logout}" />
			</h:panelGrid>
			<h:panelGroup rendered="#{!loginBean.logged}">
				<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano">

					<h:outputText value="#{text.login_userName}" />
					<h:inputText value="#{loginBean.loginName}" />

					<h:outputText value="#{text.login_password}" />
					<h:inputSecret value="#{loginBean.password}" />

					<h:outputText value="" />
					<h:panelGrid columns="2">
						<h:commandLink styleClass="textoPlano" action="#{loginBean.login}"
							value="#{text.login_login}" />
						<h:commandLink styleClass="textoPlano"
							action="#{loginBean.registrarse}" value="#{text.login_register}" />
					</h:panelGrid>

					<f:facet name="footer">
						<h:outputText value="#{loginBean.error}" styleClass="mensajeError" />
					</f:facet>
				</h:panelGrid>
			</h:panelGroup>
		</h:panelGrid>
	</h:form>
</f:subview>