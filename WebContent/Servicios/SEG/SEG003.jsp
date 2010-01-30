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
<script src="<%=request.getContextPath()%>/Recursos/Scripts/JSComun.js"
	type="text/javascript" language="javascript"></script>
</head>
<body>
<f:loadBundle basename="com.bean.text" var="text" />
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
					<td align="center" class="contenido"><a4j:region
						id="contenido">
						<h:panelGroup rendered="#{confirmacionBean.init}" />
						<h:panelGrid>
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd;">
								<f:facet name="header">
									<h:outputText value="#{text.confiramcion_Titulo}" />
								</f:facet>
								<h:panelGrid rendered="#{confirmacionBean.activado}">
									<h:outputText styleClass="mensajeError"
										value="#{confirmacionBean.error}" />
									<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="#{loginBean.logout}"
										tabindex="1" value="#{text.login_login}" />
								</h:panelGrid>

								<h:panelGroup rendered="#{!confirmacionBean.activado}">
									<h:panelGrid columns="2" width="480">

										<h:outputText value="#{text.confirmacion_Usuario}"
											styleClass="textoPlano" />
										<h:outputText value="#{confirmacionBean.nombre}"
											styleClass="textoPlano" />

										<h:outputText value="#{text.confirmacion_Login}"
											styleClass="textoPlano" />
										<h:outputText value="#{confirmacionBean.loginName}"
											styleClass="textoPlano" />

										<h:outputText value="#{text.registro_Contrasenia}"
											styleClass="textoPlano" />
										<h:inputSecret id="txtContrasenia"
											label="Debe ingresar Contraseña"
											value="#{confirmacionBean.contrasenia}" required="true"
											requiredMessage="Debe ingresar Contraseña"
											onkeypress="ValidarCampoLogin(this, event)"
											styleClass="textoPlano" tabindex="1" maxlength="13">
											<f:validateLength minimum="6" maximum="13" />
										</h:inputSecret>

										<h:outputText value="#{text.registro_ConfirmacionContrasenia}"
											styleClass="textoPlano" />
										<h:inputSecret id="txtConfirmarContrasenia"
											label="Debe ingresar confirmación de contraseña"
											value="#{confirmacionBean.confirmacion}" required="true"
											onkeypress="ValidarCampoLogin(this, event)"
											requiredMessage="Debe ingresar confirmación de contraseña"
											styleClass="textoPlano" tabindex="2" maxlength="13">
											<f:validateLength minimum="6" maximum="13" />
										</h:inputSecret>

										<h:outputText value="#{text.registro_Frase}"
											styleClass="textoPlano" />
										<h:inputText id="txtfrase" value="#{confirmacionBean.frase}"
											label="Debe ingresar una frase secreta"
											styleClass="textoPlano" tabindex="3" required="true"
											onkeypress="ValidarCampoLetras(this,event)"
											requiredMessage="Debe ingresar frase secreta">
											<f:validateLength minimum="5" maximum="250" />
										</h:inputText>
									</h:panelGrid>

									<h:panelGrid columns="2">
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" tabindex="3"
											action="#{confirmacionBean.Confirmar}"
											value="#{text.boton_Guardar}" />
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" tabindex="4" action="Cancelar"
											value="#{text.boton_Cancelar}" />
									</h:panelGrid>
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
						<a4j:status for="contenido"
							onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:100, top:200,height:'90px'})"
							onstop="Richfaces.hideModalPanel('ajaxLoadingModalBox')"></a4j:status>
					</a4j:region> <rich:modalPanel id="ajaxLoadingModalBox" minHeight="100"
						minWidth="200" height="40" width="400" zindex="100" styleClass="">
						<rich:panel style="background-color: #ebf3fd; ">
							<center>
							<h2><h:outputText value="Procesando..."
								styleClass="textoPlano">
							</h:outputText></h2>
							</center>
						</rich:panel>
					</rich:modalPanel></td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
					<div class="copyrigth"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body>
</html>