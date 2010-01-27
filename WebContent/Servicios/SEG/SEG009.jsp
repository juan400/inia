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
						rendered="#{loginBean.init}" /> <h:panelGrid>
						<rich:panel headerClass="tituloPantalla"
							style="background-color: #ebf3fd;">
							<f:facet name="header">
								<h:outputText value="#{text.Perfil_NuevoPerfil}" />
							</f:facet>
							<center><h:panelGrid rendered="#{loginBean.logged}"
								width="400">
								<h:outputText styleClass="mensajeError"
									value="#{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{loginBean.logout}"
									value="#{text.login_logout}" />
							</h:panelGrid></center>
							<br>

							<h:panelGrid columns="2" columnClasses="textoPlano,textoPlano">
								<h:outputText value="#{text.Perfil_Nombre}" />
								<h:inputText value="#{perfilBean.nombre}" required="true"
									style=" width : 245px;">
									
								</h:inputText>

								<h:outputText value="#{text.Perfil_Descripcion}" />
								<h:inputTextarea id="descripcion"
									onkeypress="ValidarLargoMultiline(this, event, 220)"
									value="#{perfilBean.descripcion}"
									style=" width : 245px; height : 71px;" />
									
								<h:outputText value="#{text.Perfil_Estado}" />
								<rich:comboBox defaultLabel="Seleccionar Estado" value="#{perfilBean.estado}" width="245px">
									<f:selectItem itemValue="Activo" />
									<f:selectItem itemValue="Bloqueado" />
									<f:selectItem itemValue="Inactivo" />
								</rich:comboBox>
								
								<h:outputText value="" />
								<h:panelGrid columns="2">
									<a4j:commandButton style="font-size: 10pt; color: #2d77c2;" 
										styleClass="textoPlano" action="#{perfilBean.registrar}"
										value="#{text.boton_Aceptar}" />
									<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="#{perfilBean.cancelar}"
										value="#{text.boton_Cancelar}" />
								</h:panelGrid>

							</h:panelGrid>
						</rich:panel>
					</h:panelGrid>
					
								<h:messages style="color:darkred"/></td>
				</tr>
			</tbody>
		</table>
	</h:form>
</f:view>