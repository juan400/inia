<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	<head>
		<meta name="locality" content="Spain">
		<meta name="lang" content="es">
		<title>Ejemplo de login</title>
		<meta http-equiv="Pragma" content="no-cache">
		<meta http-equiv="Expires" content="-1">	
	</head>
	
	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
	<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
	
	<body bgcolor="#FFFF9D">
	<FONT color="#000080" FACE="Arial,Helvetica,Times" SIZE=2>
	<center><H3>Validación</H3></center>
	<HR>
	<br><br>
		<f:view>
			<h:form id="LoginForm" >
				<h:outputText value="Usuario: "/>
				<h:inputText id="usuario" value="#{BeanLogin.usuario}" required="true"/>
				<h:message for="usuario"/>
				<br />
				<h:outputText value="Clave: "/>  
				<h:inputSecret id="clave" value="#{BeanLogin.clave}" required="true"/>
				<h:message for="clave"/>
				<br /><br />	  
				<h:commandButton id="submit" action="#{BeanLogin.getValidacion}" value="Entrar" />
			</h:form>
		</f:view>
	</FONT>
	</body>
	</html>