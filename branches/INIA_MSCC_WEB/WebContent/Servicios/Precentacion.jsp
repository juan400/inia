<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<h:form>
		<f:loadBundle basename="com.bean.text" var="text" />
		<h:panelGroup rendered="#{loginBean.init}" />
		<h:panelGroup rendered="#{recuperarContraseniaBean.init}" />
		<h:panelGrid>
			<rich:panel headerClass="tituloPantalla"
				style="background-color: #ebf3fd;">
				<f:facet name="header">
					<h:outputText value="#{text.confiramcion_Titulo}" />
				</f:facet>

				<h:panelGroup rendered="#{!recuperarContraseniaBean.activado}">
					<h:panelGrid columns="2" width="480">

					</h:panelGrid>
					<h:panelGrid columns="2" width="480">
						
						aca va el contenido de la paigna

					</h:panelGrid>
					<center><h:panelGrid columns="2">
						<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
							styleClass="textoPlano" tabindex="3"
							action="#{confirmacionBean.Confirmar}"
							value="#{text.boton_Guardar}" />
						<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
							styleClass="textoPlano" tabindex="4" action="Cancelar"
							value="#{text.boton_Cancelar}" />
					</h:panelGrid></center>
					<h:panelGrid>
						<rich:messages styleClass="mensajeError">
							<f:facet name="passedMarker">
								<h:graphicImage value="/Recursos/Imagenes/Iconos/passed.gif" />
							</f:facet>
							<f:facet name="errorMarker">
								<h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />
							</f:facet>
						</rich:messages>
						<h:outputText styleClass="mensajeError"
							value="#{confirmacionBean.error}" />
						<h:outputText styleClass="textoPlano"
							value="#{confirmacionBean.exito}" />
					</h:panelGrid>
				</h:panelGroup>
			</rich:panel>
		</h:panelGrid>
	</h:form>
</f:view>