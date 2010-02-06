<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<h:panelGroup rendered="#{subirEscenarioBean.init}" />
		<h:panelGrid>
			<rich:panel headerClass="tituloPantalla"
				style="background-color: #ebf3fd;">
				<f:facet name="header">
					<h:outputText value="Ingresar Escenario" />
				</f:facet>
				<rich:fileUpload fileUploadListener="#{subirEscenarioBean.listener}"
					maxFilesQuantity="1" id="upload" 
					immediateUpload="false" acceptedTypes="py"
					allowFlash="true" listHeight="60">
					<a4j:support event="onuploadcomplete" reRender="mensages" />
				</rich:fileUpload>

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
				<h:panelGrid id="mensages">
					<rich:messages styleClass="mensajeError">
						<f:facet name="passedMarker">
							<h:graphicImage value="/Recursos/Imagenes/Iconos/passed.gif" />
						</f:facet>
						<f:facet name="errorMarker">
							<h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />
						</f:facet>
					</rich:messages>
					<h:outputText styleClass="mensajeError"
						value="#{subirEscenarioBean.error}" />
					<h:outputText styleClass="textoPlano"
						value="#{subirEscenarioBean.exito}" />
				</h:panelGrid>
			</rich:panel>
		</h:panelGrid>
	</h:form>
</f:view>