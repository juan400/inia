<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
	<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>

	<body bgcolor="#FFFF9D">
	<FONT color="#000080" FACE="Arial,Helvetica,Times" SIZE=2>
<f:view>
		<h:form id="LoginForm" >
			<h:outputText value="No se le permite el acceso "/><h:outputText value="#{BeanLogin.usuario}"/>
			<%-- En Login.jsp la accion esta asociada a un método; aqui la acción esta asociada
				 a una etiqueta ('volver'). Ver en 'faces-config' que aparece 'volver' como <from-outcome> de
				 una regla --%>
			<p>
			<h:commandLink action="volver">
				<%-- En vez de commandButton puede hacerse un enlace 'clásico' con <h:outputText value="Volver"/> --%>
				<h:commandButton id="submit" value="Volver" />
			</h:commandLink>
			</p>
    		</h:form>
    	</f:view>
</body>
</html>