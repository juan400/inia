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
						<h:panelGrid width="950" cellpadding="0" cellspacing="0">
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
												immediate="true" value="Cerrar Sesión">
											</h:commandLink>
										</h:column>
									</h:panelGrid>
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:panelGrid>
										<center><h:outputText styleClass="mensajeError"
											style="font-size: 12pt" value="#{text.login_notLogged}" /></center>

										<center><a4j:commandButton
											style="font-size: 10pt; color: #2d77c2; width : 120px;"
											styleClass="textoPlano" action="#{loginBean.logout}"
											value="#{text.login_Login}" /></center>
									</h:panelGrid>
								</h:panelGrid></center>

								<h:panelGroup rendered="#{loginBean.logged}">
									<h:panelGrid columns="2" columnClasses="cols" width="200">
										<h:column>
											<h:panelGroup rendered="#{menuBean.init}" />
											<rich:panelMenu binding="#{menuBean.panelMenu}" />
										</h:column>

										<h:column>
											<h:panelGroup rendered="#{cultivoBean.init}" />
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.ejecucion_Nueva}" />
													</f:facet>
													<h:panelGroup>
														<h:panelGrid columns="2" styleClass="textoPlano">

															<h:outputText value="Fecha de ejecución" />
															<rich:calendar id="calFechaEjecucion"
																inputClass="rich-calendar-input"
																value="#{escenarioBean.fechaEjecucion}"
																enableManualInput="false" locale="ES" disabled="true"
																showApplyButton="false" datePattern="dd/MM/yyyy hh:mm a"
																popup="true" cellWidth="24px" cellHeight="22px"
																style="width:200px">

															</rich:calendar>

															<h:outputText value="Seleccionar cultivo"
																styleClass="textoPlano" />
															<rich:comboBox
																value="#{propiedadesBean.cultivoSeleccionado}"
																enableManualInput="false" styleClass="combo"
																disabled="#{propiedadesBean.disableSeleccionCultivo}"
																width="248px">
																<f:selectItems value="#{propiedadesBean.cultivos}" />
																<a4j:support
																	action="#{propiedadesBean.TakeSelectionCultivo}"
																	event="onchange" ajaxSingle="true"
																	reRender="panelPropiedades" />
															</rich:comboBox>

															<h:outputText value="Seleccionar estacion climatica" />
															<rich:comboBox value="#{escenarioBean.estacionClimatica}">

															</rich:comboBox>

															<h:outputText value="Nombre del cultivar" />
															<h:inputText value="#{escenarioBean.cultivar}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoLetras(this, event)" />

														</h:panelGrid>
														<h:panelGrid columns="2" styleClass="textoPlano">
															<h:outputText
																value="Basado en proyecciones climatológicas" />
															<h:selectBooleanCheckbox
																value="#{escenarioBean.proyectarClima}" />
														</h:panelGrid>

														<h:panelGrid columns="3"
															columnClasses="textoPlano,textoPlano">
															<rich:panel headerClass="tituloPantalla"
																style="background-color: #ebf3fd; width:203px;">
																<f:facet name="header">
																	<h:outputText value="Fertilizacion" />
																</f:facet>
																<h:panelGrid styleClass="textoPlano">
																	<h:outputText value="Fecha" />
																	<rich:calendar id="calFechaSiembra"
																		value="#{escenarioBean.fFertilizacionSiembra}"
																		enableManualInput="false" locale="ES"
																		showApplyButton="false"
																		datePattern="dd/MM/yyyy hh:mm a" popup="true"
																		cellWidth="24px" cellHeight="22px" style="width:200px">

																	</rich:calendar>

																	<h:outputText value="Fuente" />
																	<rich:comboBox id="comboFuente1"
																		value="#{escenarioBean.fuenteFertilizacionSiembra}">

																	</rich:comboBox>

																	<h:outputText value="Rate" />
																	<rich:comboBox id="comboRate1"
																		value="#{escenarioBean.rateFertilizacionSiembra}">

																	</rich:comboBox>
																</h:panelGrid>
															</rich:panel>
															<rich:panel headerClass="tituloPantalla"
																style="background-color: #ebf3fd; ; width:203px;">
																<f:facet name="header">
																	<h:outputText value="Refertilizacion 1" />
																</f:facet>
																<h:panelGrid styleClass="textoPlano">
																	<h:outputText value="Fecha" />
																	<rich:calendar id="calFechaRefer1"
																		value="#{escenarioBean.fRefertilizacion1}"
																		enableManualInput="false" locale="ES"
																		showApplyButton="false"
																		datePattern="dd/MM/yyyy hh:mm a" popup="true"
																		cellWidth="24px" cellHeight="22px" style="width:200px">

																	</rich:calendar>
																	<h:outputText value="Fuente" />
																	<rich:comboBox id="comboFuente2"
																		value="#{escenarioBean.fuenteRefertilizacion1}">

																	</rich:comboBox>
																	<h:outputText value="Rate" />
																	<rich:comboBox id="comboRate2"
																		value="#{escenarioBean.rateRefertilizacion1}">

																	</rich:comboBox>
																</h:panelGrid>
															</rich:panel>
															<rich:panel headerClass="tituloPantalla"
																style="background-color: #ebf3fd; width:203px;">
																<f:facet name="header">
																	<h:outputText value="Refertilizacion 2" />
																</f:facet>
																<h:panelGrid styleClass="textoPlano">
																	<h:outputText value="Fecha" />
																	<rich:calendar id="calFechaRefer2"
																		value="#{escenarioBean.fRefertilizacion2}"
																		enableManualInput="false" locale="ES"
																		showApplyButton="false"
																		datePattern="dd/MM/yyyy hh:mm a" popup="true"
																		cellWidth="24px" cellHeight="22px" style="width:200px">

																	</rich:calendar>

																	<h:outputText value="Fuente" />
																	<rich:comboBox id="comboFuente3"
																		value="#{escenarioBean.fuenteRefertilizacion2}">

																	</rich:comboBox>

																	<h:outputText value="Rate" />
																	<rich:comboBox id="comboRate3"
																		value="#{escenarioBean.rateRefertilizacion2}">

																	</rich:comboBox>
																</h:panelGrid>
															</rich:panel>
														</h:panelGrid>
														<h:panelGrid columns="2"
															columnClasses="textoPlano,textoPlano">

															<h:outputText value="Nombre Coneat" />
															<h:inputText value="#{escenarioBean.nombreSueloConeat}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText value="Profundidad A" />
															<h:inputText value="#{escenarioBean.profundidadA}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText value="Profundidad B" />
															<h:inputText value="#{escenarioBean.profundidadB}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText value="Densidad Plantas" />
															<h:inputText value="#{escenarioBean.densidadPlantas}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Indice agua en la capa superior del suelo" />
															<h:inputText value="#{escenarioBean.wuli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Indice agua en la capa inferior del suelo" />
															<h:inputText value="#{escenarioBean.wlli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText value="Descompocición del material vegetal" />
															<h:inputText value="#{escenarioBean.dpmi}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText value="Material vegetal resistente" />
															<h:inputText value="#{escenarioBean.rpmi}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Materia orgánica en el suelo humedecido" />
															<h:inputText value="#{escenarioBean.humi}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Amonio N en la capa superior del suelo" />
															<h:inputText value="#{escenarioBean.nauli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Amonio N en la capa inferior del suelo" />
															<h:inputText value="#{escenarioBean.nalli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Nitrato N en la capa superior del suelo" />
															<h:inputText value="#{escenarioBean.nnuli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />
															<h:outputText
																value="Nitrato N en la capa inferior del suelo" />
															<h:inputText value="#{escenarioBean.nnlli}"
																styleClass="textoPlano"
																onkeypress="ValidarCampoNumericoDouble(this, event)" />

														</h:panelGrid>

														<center><h:outputText value="" /> <h:panelGrid
															columns="2">
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2;"
																styleClass="textoPlano"
																action="#{escenarioBean.ejecutarEscenario}"
																value="#{text.boton_Aceptar}" />
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2;"
																styleClass="textoPlano"
																action="#{escenarioBean.cancelar}"
																value="#{text.boton_Cancelar}" />
														</h:panelGrid></center>

														<center><h:panelGrid id="mensages">
															<rich:messages styleClass="mensajeError">
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
																</f:facet>
															</rich:messages>
															<h:outputText styleClass="textoPlano"
																value="#{escenarioBean.exito}" />
														</h:panelGrid></center>
													</h:panelGroup>
												</rich:panel>
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