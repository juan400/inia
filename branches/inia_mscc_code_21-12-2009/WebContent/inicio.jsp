<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
                xmlns:ui="http://java.sun.com/jsf/facelets"
                xmlns:a4j="http://richfaces.org/a4j"
                xmlns:rich="http://richfaces.org/rich">
</ui:composition>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INIA - Modelo de Simulación de Crecimiento de Cultivos de
Secano.</title>
<link rel="stylesheet" href="css/qbox.css" type="text/css">
</head>
<body>
<f:view>
<!--	<f:loadBundle basename="INIA/MSCC/MENSAJES/text" var="text" />-->
	<div class="logo"></div>
	<a4j:form>
		<rich:panel header="RichFaces Greeter" style="width: 315px">
			<h:outputText value="Your name: " />
			<h:inputText value="#{LoginBean.nombre}">
				<f:validateLength minimum="1" maximum="30" />
			</h:inputText>
			<BR/>
<!--			<h:outputText value="Your password: " />-->
<!--			<h:inputText value="#{LoginBean.password}">-->
<!--				<f:validateLength minimum="8" maximum="13" />-->
<!--			</h:inputText>-->
			<a4j:commandButton value="Get greeting" reRender="greeting" />
			<h:panelGroup id="greeting">
				<h:outputText value="Hello, " rendered="#{not empty LoginBean.nombre}" />
				<h:outputText value="#{LoginBean.nombre}" />
				<h:outputText value="!" rendered="#{not empty LoginBean.nombre}" />
			</h:panelGroup>
		</rich:panel>
	</a4j:form>
</f:view>
</body>
</html>