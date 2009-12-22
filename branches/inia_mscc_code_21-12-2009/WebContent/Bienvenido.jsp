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
			<h:outputText value="Bienvenido "/><h:outputText value="#{BeanLogin.usuario}"/>
    		</h:form>
    	</f:view>
</body>
</html>