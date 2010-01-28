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
				<tr style="height: 2px;">
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
							<br></br>
	
							<center>
							<rich:extendedDataTable border="2" width="600px" height="100" styleClass="textoDataTable"  
							selectionMode="single" selection="#{perfilBean.perfil}"
								value="#{perfilBean.perfiles}" var="perfil"
								headerClass="columnHeader" 								
								rowClasses="oddRow,evenRow" >
								
								<rich:column width="150" sortable="false" > 
									<f:facet name="header">
										<h:outputText value="Nombre" />
									</f:facet>
									<h:outputText value="#{perfil._nombre}"/>
								</rich:column>
								
								<rich:column width="350" sortable="false" >
									<f:facet name="header">
										<h:outputText value="Descripción" />
									</f:facet>
									<h:outputText value="#{perfil._descripcion}" />
								</rich:column>
								
								<rich:column width="100" sortable="false" >
									<f:facet name="header">
										<h:outputText value="Estado" />
									</f:facet>
									<h:outputText value="#{perfil._estado}" />
								</rich:column>

							</rich:extendedDataTable></center>
						</rich:panel>
					</h:panelGrid></td>
				</tr>
			</tbody>
		</table>
	</h:form>
</f:view>
</body>
</html>