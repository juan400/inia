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

							<rich:contextMenu attached="false" id="menu" submitMode="ajax"
								oncollapse="row.style.backgroundColor='#{a4jSkin.tableBackgroundColor}'">
								<rich:menuItem value="Edit Record" ajaxSingle="true"
									oncomplete="#{rich:component('editPanel')}.show()">
									<a4j:actionparam name="vin" value="{carVin}" />
									<a4j:actionparam name="row" value="{currentRow}" />
								</rich:menuItem>
								<rich:menuItem value="Remove Record" ajaxSingle="true"
									oncomplete="#{rich:component('deletePanel')}.show()">
									<a4j:actionparam name="vin" value="{carVin}" />
									<a4j:actionparam name="row" value="{currentRow}" />
								</rich:menuItem>
							</rich:contextMenu>

							<center><rich:dataTable border="2" width="660px"
								rows="10" styleClass="textoDataTable"
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
										image="/Recursos/Imagenes/Iconos/edit.gif">
										<a4j:actionparam name="consultaElegida" value="#{perfil._id}" />
									</a4j:commandButton>
									<rich:toolTip value="Modificar" />

									<a4j:commandLink ajaxSingle="true" id="deletelink"
										oncomplete="#{rich:component('deletePanel')}.show()">
										<h:graphicImage value="/Recursos/Imagenes/Iconos/delete.gif"
											style="border:0" />
									</a4j:commandLink>
									<rich:toolTip value="Eliminar" />
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

								<a4j:commandButton
									style="font-size: 10pt; color: #2d77c2; width : 87px;"
									styleClass="textoPlano" action="Permisos"
									value="#{text.boton_Permisos}" />

								<a4j:commandButton immediate="true"
									style="font-size: 10pt; color: #2d77c2; width : 87px;"
									styleClass="textoPlano" action="cancelar"
									value="#{text.perfil_Cerrar}" />
							</h:panelGrid></center>
						</rich:panel>
					</h:panelGrid></td>
				</tr>
			</tbody>
		</table>
	</h:form>

	<rich:modalPanel id="deletePanel" autosized="true" width="200">
		<f:facet name="header">
			<h:outputText value="¿Desea eliminar este Perfil?"
				style="padding-right:15px;" />
		</f:facet>
		<f:facet name="controls">
			<h:panelGroup>
				<h:graphicImage value="/Recursos/Imagenes/Iconos/close.png"
					styleClass="hidelink" id="hidelink2" />
				<rich:componentControl for="deletePanel" attachTo="hidelink2"
					operation="hide" event="onclick" />
			</h:panelGroup>
		</f:facet>
		<h:form>
			<table width="100%">
				<tbody>
					<tr>
						<td align="center" width="50%"><a4j:commandButton value="Yes"
							ajaxSingle="true" action="#{perfilBean.eliminar}"
							oncomplete="#{rich:component('deletePanel')}.hide();"
							reRender="table" /></td>
						<td align="center" width="50%"><a4j:commandButton
							value="Cancel"
							onclick="#{rich:component('deletePanel')}.hide();return false;" />
						</td>
					</tr>
				</tbody>
			</table>
		</h:form>
	</rich:modalPanel>
</f:view>
</body>
</html>