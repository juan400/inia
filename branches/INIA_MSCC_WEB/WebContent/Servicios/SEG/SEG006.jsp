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
						<h:panelGroup rendered="#{loginBean.init}" />
						<h:panelGrid>
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd;">
								<f:facet name="header">
									<h:outputText value="#{text.reenvioPassword_Titulo}" />
								</f:facet>
								<h:panelGrid>
									<h:panelGroup rendered="#{!loginBean.logged}">
										<h:panelGrid columns="2" width="400">

											<h:outputText value="#{text.registro_Email}"
												styleClass="textoPlano" />
											<h:inputText id="txtEmail"
												value="#{recuperarContraseniaBean.email}"
												onblur="validarEmailBlur(this, event)" required="true"
												requiredMessage="Debe ingresar su e-mail de contacto"
												onkeypress="validarEmailKeyPress(this, event)"
												styleClass="textoPlano" tabindex="1">
												<a4j:support
													action="#{recuperarContraseniaBean.validarEmail}"
													event="onchange" ajaxSingle="true" reRender="txtEmail" />
											</h:inputText>

											<h:outputText value="#{text.registro_Frase}"
												styleClass="textoPlano" />
											<h:inputText id="txtfrase"
												value="#{recuperarContraseniaBean.frase}"
												styleClass="textoPlano" tabindex="2" required="true"
												onkeypress="ValidarCampoLetras(this,event)"
												requiredMessage="Debe ingresar frase secreta">
												<f:validateLength minimum="5" maximum="250" />
												<a4j:support
													action="#{recuperarContraseniaBean.validarFrase}"
													event="onchange" ajaxSingle="true" reRender="txtfrase" />
											</h:inputText>
										</h:panelGrid>
										<center><h:panelGrid columns="2">
											<a4j:commandButton style="font-size: 10pt; color: #2d77c2; width : 120px;"
												style="font-size: 10pt; color: #2d77c2; width : 120px;"tabindex="3"
												action="#{recuperarContraseniaBean.enviarPassword}"
												value="#{text.boton_Guardar}" />
											<a4j:commandButton style="font-size: 10pt; color: #2d77c2; width : 120px;"
												styleClass="textoPlano" tabindex="4" action="cancelar"
												immediate="true" value="#{text.boton_Cancelar}" />
										</h:panelGrid></center>
									</h:panelGroup>

									<center><f:facet name="footer">
										<h:panelGrid>
											<rich:messages styleClass="mensajeError">
												<f:facet name="errorMarker">
													<h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />
												</f:facet>
											</rich:messages>
											<h:outputText styleClass="textoPlano"
												value="#{recuperarContraseniaBean.exito}" />
										</h:panelGrid>
									</f:facet></center>
								</h:panelGrid>
							</rich:panel>
						</h:panelGrid>
						<a4j:status for="contenido"
							onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:'200px', top:200,height:'100px'})"
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