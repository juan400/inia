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
<link
	href="<%=request.getContextPath()%>/Recursos/css/inia2010-01-17.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/Recursos/Scripts/JSComun.js"></script>
</head>
<body>
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
					<td align="center" class="contenido"><h:panelGroup
						rendered="#{perfilBean.init}" /> <h:panelGrid>
						<rich:panel headerClass="tituloPantalla"
							style="background-color: #ebf3fd;">
							<f:facet name="header">
								<h:outputText value="#{text.Perfil_NuevoPerfil}" />
							</f:facet>
							<center><h:panelGrid rendered="#{loginBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{loginBean.logout}"
									value="#{text.login_logout}" />
							</h:panelGrid></center>
							<br>

							<h:panelGrid columns="1" width="200">
								<h:dataTable border="2" value="#{perfilBean.perfiles}"
									var="perfil">
									<h:column>
										<f:facet name="header">
											<h:outputText value="Perfiles" />
										</f:facet>
										<h:outputText value="#{perfil._nombre}" />
									</h:column>
									<h:column>
										<f:facet name="header">
											<h:outputText value="Descripción" />
										</f:facet>
										<h:outputText value="#{perfil._descripcion}" />
									</h:column>
								</h:dataTable>

							</h:panelGrid>
						</rich:panel>
					</h:panelGrid>
		</table>
	</h:form>
</f:view>
</body>
</html>