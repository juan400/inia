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
					<td align="center" valign="top" class="contenido"><a4j:region
						id="contenido">
						<h:panelGroup rendered="#{loginBean.init}" />
						<h:panelGrid width="956" cellpadding="0" cellspacing="0">
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd;">
								<f:facet name="header">
									<h:panelGrid columns="2" width="900px">
										<h:column>
											<h:outputText
												style="font-size: 9pt; color: #2d77c2; width: 750; aling: left"
												value="Usuario #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}  -  Ultimo acceso #{loginBean.usuario._ultimoAcceso}">
											</h:outputText>
										</h:column>
										<h:column>
											<h:commandLink
												style="font-size: 8pt; color: #2d77c2; width: 100; aling: right"
												styleClass="textoPlano" action="#{loginBean.logout}"
												immediate="true" value="Cerrar Cesión">
											</h:commandLink>
										</h:column>
									</h:panelGrid>
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:outputText styleClass="mensajeError" style="font-size: 12pt"
										value="#{text.login_notLogged}" />
									<a4j:commandButton
										style="font-size: 10pt; color: #2d77c2; width : 105px;"
										styleClass="textoPlano" action="#{loginBean.logout}"
										immediate="true" value="#{text.login_Login}" /> 
								</h:panelGrid></center>

								<h:panelGroup rendered="#{loginBean.logged}">
									<h:panelGrid columns="2" rendered="#{loginBean.logged}"
										columnClasses="cols" width="200">
										<h:column>
											<h:panelGroup style="aling: top"
												rendered="#{menuBean.init}" />
											<rich:panelMenu binding="#{menuBean.panelMenu}" />
										</h:column>
										<h:column>
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd; ">
													<f:facet name="header">
														<h:outputText
															value="#{text.usuario_ModDatos}" />
													</f:facet>
													<h:panelGrid columns="2"
														columnClasses="textoPlano,textoPlano" width="500px">

														<h:outputText value="#{text.registro_Nombre}" />
														<h:inputText value="#{datosUsuarioBean.nombre}"
															required="true" requiredMessage="Debe ingresar su nombre"
															styleClass="textoPlano"
															onkeypress="ValidarCampoLetras(this, event)" />

														<h:outputText value="#{text.registro_Apellido}" />
														<h:inputText value="#{datosUsuarioBean.apellido}"
															required="true"
															requiredMessage="Debe ingresar su apellido"
															styleClass="textoPlano"
															onkeypress="ValidarCampoLetras(this, event)" />

														<h:outputText value="#{text.registro_Email}" />
														<h:inputText id="txtEmail"
															value="#{datosUsuarioBean.email}"
															onblur="validarEmailBlur(this, event)" required="true"
															requiredMessage="Debe ingresar su e-mail de contacto"
															onkeypress="validarEmailKeyPress(this, event)"
															styleClass="textoPlano">
															<a4j:support
																action="#{datosUsuarioBean.takeSelectionEmail}"
																event="onchange" ajaxSingle="true" reRender="txtEmail" />
														</h:inputText>

														<h:outputLabel value="#{text.registro_Pais}" />
														<rich:comboBox value="#{datosUsuarioBean.paisElegido}"
															enableManualInput="false" styleClass="combo">
															<f:selectItems value="#{datosUsuarioBean.paises}" />
															<a4j:support
																action="#{datosUsuarioBean.takeSelectionPais}"
																event="onchange" ajaxSingle="true"
																reRender="cmbDepartamentos" />
														</rich:comboBox>

														<h:outputText
															value="#{text.registro_Departamento_Estado_Provincia}" />
														<rich:comboBox id="cmbDepartamentos"
															enableManualInput="false"
															value="#{datosUsuarioBean.departamentoElegido}">
															<f:selectItems value="#{datosUsuarioBean.departamentos}" />
															<a4j:support
																action="#{datosUsuarioBean.takeSelectionDepartamento}"
																event="onchange" ajaxSingle="true" reRender="cmbCiudad" />
														</rich:comboBox>

														<h:outputText value="#{text.registro_Ciudad}" />
														<rich:comboBox id="cmbCiudad"
															value="#{datosUsuarioBean.ciudadElegido}"
															enableManualInput="false">
															<f:selectItems value="#{datosUsuarioBean.ciudades}" />
															<a4j:support
																action="#{datosUsuarioBean.takeSelectionCiudad}"
																event="onchange" ajaxSingle="true" />
														</rich:comboBox>

														<h:outputText value="#{text.registro_Direccion}" />
														<h:inputText value="#{datosUsuarioBean.direccion}"
															required="true" styleClass="textoPlano"
															requiredMessage="Debe ingresar su dirección de domicilio"
															onkeypress="ValidarCampoConCaracteresEspeciales(this, event)" />

														<h:outputText value="#{text.registro_Telefono}" />
														<h:inputText value="#{datosUsuarioBean.telefono}"
															styleClass="textoPlano"
															onkeypress="ValidarCampoNumerico(this, event)" />

														<h:outputText value="#{text.registro_Celular}" />
														<h:inputText value="#{datosUsuarioBean.celular}"
															styleClass="textoPlano"
															onkeypress="ValidarCampoNumerico(this, event)"
															maxlength="9" />
													</h:panelGrid>
													<center><h:panelGrid columns="2">
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoPlano" tabindex="3"
															action="#{datosUsuarioBean.modificar}"
															value="#{text.boton_Guardar}" />
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoPlano" action="cancelar"
															immediate="true" value="#{text.boton_Cancelar}" />

													</h:panelGrid></center>
												</rich:panel>
												<f:facet name="footer">
													<h:panelGrid>
														<rich:messages styleClass="mensajeError">
															<f:facet name="errorMarker">
																<h:graphicImage
																	value="/Recursos/Imagenes/Iconos/error.gif" />
															</f:facet>
														</rich:messages>
														<h:outputText styleClass="textoPlano"
															value="#{datosUsuarioBean.exito}" />
													</h:panelGrid>
												</f:facet>
											</h:panelGrid>
										</h:column>
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