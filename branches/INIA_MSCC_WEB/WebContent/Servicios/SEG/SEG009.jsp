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
						rendered="#{loginBean.init}" /> <h:panelGrid>
						<rich:panel headerClass="tituloPantalla"
							style="background-color: #ebf3fd;">
							<f:facet name="header">
								<h:outputText value="#{text.perfil_NuevoPerfil}" />
							</f:facet>
							<center><h:panelGrid rendered="#{loginBean.logged}" 
								width="">
								<h:outputText styleClass="mensajeError"
									value="#{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{loginBean.logout}"
									value="#{text.login_logout}" />
							</h:panelGrid></center>
							<br></br>

							<h:panelGrid columns="3" columnClasses="textoPlano,textoPlano">
								<h:outputText value="#{text.perfil_Nombre}" />
								<h:inputText label="Name" id="name" required="true" requiredMessage="Debe ingresar el Nombre."
									value="#{perfilBean.nombre}" 
									onkeypress="ValidarCampoLetras(this, event)"
									style=" width : 245px;">
									<f:validateLength maximum="45"> </f:validateLength>
									 
								</h:inputText>
								<rich:message for="name">
									<f:facet name="errorMarker">
										<h:graphicImage value="Recursos/Imagenes/Iconos/error.gif" />
									</f:facet>
								</rich:message>

								<h:outputText value="#{text.perfil_Descripcion}" />
								<h:inputTextarea id="descripcion"
									value="#{perfilBean.descripcion}"
									onkeypress="ValidarLargoMultiline(this, event, 220)"
									style=" width : 245px; height : 71px;" />
									
								<td></td>
								<h:outputText value="#{text.perfil_Estado}" />
								<rich:comboBox defaultLabel="Seleccionar Estado"
									value="#{perfilBean.estado}" width="245px">
									<f:selectItem itemValue="Activo" />
									<f:selectItem itemValue="Bloqueado" />
									<f:selectItem itemValue="Inactivo" />
								</rich:comboBox>

								<td style="width: 2px;"></td>
								<h:outputText value="" />
								<h:panelGrid columns="2">
									<a4j:commandButton 
										style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="#{perfilBean.registrar}"
										value="#{text.boton_Aceptar}" />
									<a4j:commandButton immediate="true" style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="cancelar"
										value="#{text.boton_Cancelar}" />
								</h:panelGrid>
							</h:panelGrid>
						</rich:panel>
					</h:panelGrid>
				</tr>
			</tbody>
		</table>
	</h:form>
</f:view>