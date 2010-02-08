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
								<h:outputText value="#{text.perfil_ListaPerfiles}" />
							</f:facet>
							<center><h:panelGrid rendered="#{loginBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{loginBean.logout}"
									value="#{text.login_logout}" />
							</h:panelGrid></center>
							<br></br>

							<center><rich:dataTable border="2" width="660px"
								rows="6" styleClass="textoDataTable" id="tablaPerfiles"
								value="#{perfilBean.perfiles}" var="perfil" rowKeyVar="row"
								headerClass="columnHeader" rowClasses="oddRow,evenRow">

								<f:facet name="header">
									<h:outputText value="Perfiles Registrados" />
								</f:facet>

								<rich:column width="150">
									<f:facet name="header">
										<h:outputText value="Nombre" />
									</f:facet>
									<h:outputText value="#{perfil._nombre}" id="nombre" />
								</rich:column>

								<rich:column width="350">
									<f:facet name="header">
										<h:outputText value="Descripción" />
									</f:facet>
									<h:outputText value="#{perfil._descripcion}" id="descripcion" />
								</rich:column>

								<rich:column width="100">
									<f:facet name="header">
										<h:outputText value="Estado" />
									</f:facet>
									<h:outputText value="#{perfil._estado}" id="estado" />
								</rich:column>

								<rich:column width="60">
									<f:facet name="header">
										<h:outputText value="Acciones" />
									</f:facet>

									<a4j:commandButton action="#{perfilBean.verConsulta}"
										image="/Recursos/Imagenes/Iconos/edit.gif"
										disabled="#{perfil._fijo}"
										style=" width : 19px; height : 18px;">
										<a4j:actionparam name="consultaElegida" value="#{perfil._id}" />
										<rich:toolTip value="Modificar" />
									</a4j:commandButton>

									<a4j:commandButton action="#{perfilBean.eliminar}"
										image="/Recursos/Imagenes/Iconos/delete.gif"
										disabled="#{perfil._fijo}"
										style=" border:0; width : 19px; height : 18px;">
										<a4j:actionparam name="consultaEliminar" value="#{perfil._id}" />
										<rich:toolTip value="Eliminar" />
									</a4j:commandButton>

								</rich:column>
								<f:facet name="footer">
									<rich:datascroller renderIfSinglePage="false" maxPages="5" />
								</f:facet>

							</rich:dataTable></center>
							<br></br>

							<center><h:panelGrid columns="3">
								<a4j:commandButton immediate="true"
									style="font-size: 10pt; color: #2d77c2; width : 87px;"
									styleClass="textoPlano" action="Alta"
									value="#{text.boton_Registrar}" />
								<a4j:commandButton immediate="true"
									style="font-size: 10pt; color: #2d77c2; width : 87px;"
									styleClass="textoPlano" action="cancelar"
									value="#{text.perfil_Cerrar}" />
							</h:panelGrid></center>

							<center><h:panelGrid>
								<h:outputText styleClass="mensajeError"
									value="#{perfilBean.error}" />
								<h:outputText styleClass="textoPlano"
									value="#{perfilBean.exito}" />
							</h:panelGrid></center>
						</rich:panel>
					</h:panelGrid></td>
				</tr>
			</tbody>
		</table>
	</h:form>
</f:view>
</body>
</html>