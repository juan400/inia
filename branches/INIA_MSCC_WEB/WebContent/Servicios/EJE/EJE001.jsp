<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<h:panelGrid width="956" cellpadding="0" cellspacing="0">
							<rich:panel headerClass="tituloPantalla"
								style="background-color: #ebf3fd;">
								<f:facet name="header">
									<h:outputText
										value="Bienvenido #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}. Ultimo acceso #{loginBean.usuario._ultimoAcceso}" />
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:outputText styleClass="mensajeError" style="font-size: 12pt"
										value="#{text.login_notLogged}" />
									<center><a4j:commandButton
										style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="#{loginBean.logout}"
										immediate="true" value="#{text.login_login}" /> <a4j:commandButton
										style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="navegar" immediate="true"
										value="Ingresar" /></center>
								</h:panelGrid></center>
								<h:panelGroup rendered="#{loginBean.logged}">
									<h:panelGrid columns="2" rendered="#{loginBean.logged}"
										columnClasses="cols" width="200">
										<h:column>
											<h:panelGroup rendered="#{menuBean.init}" />
											<rich:panelMenu binding="#{menuBean.panelMenu}" />
										</h:column>

										<h:column>
											<h:panelGrid>
												<h:panelGrid columns="2" width="480">
													<h:panelGroup rendered="#{registroBean.init}" />
													<h:panelGrid>
														<rich:panel headerClass="tituloPantalla"
															style="background-color: #ebf3fd;">
															<f:facet name="header">
																<h:outputText value="#{text.login_newUser}" />
															</f:facet>
															<h:panelGrid rendered="#{!loginBean.logged}">
																<h:outputText styleClass="mensajeError"
																	value="#{text.login_notLogged}" />
																<a4j:commandButton
																	style="font-size: 10pt; color: #2d77c2;"
																	styleClass="textoPlano" action="cancelar"
																	value="#{text.login_login}" />
															</h:panelGrid>
															<h:panelGroup>
																<h:panelGrid columns="2" styleClass="textoPlano">

																	<h:outputText value="Fecha de ejecución" />
																	<rich:calendar id="calFechaEjecucion"
																		inputClass="rich-calendar-input"
																		value="#{escenarioBean.fechaEjecucion}"
																		enableManualInput="false" locale="ES" disabled="true"
																		showApplyButton="false"
																		datePattern="dd/MM/yyyy hh:mm a" popup="true"
																		cellWidth="24px" cellHeight="22px" style="width:200px">

																	</rich:calendar>

																	<h:outputText value="Seleccionar cultivo" />
																	<rich:comboBox>

																	</rich:comboBox>

																	<h:outputText value="Seleccionar estacion climatica" />
																	<rich:comboBox>

																	</rich:comboBox>

																	<h:outputText value="Seleccionar cultivar" />
																	<rich:comboBox>

																	</rich:comboBox>

																	<h:outputText value="" />
																</h:panelGrid>
																<h:panelGrid columns="3"
																	columnClasses="textoPlano,textoPlano">
																	<rich:panel headerClass="tituloPantalla"
																		style="background-color: #ebf3fd; ">
																		<f:facet name="header">
																			<h:outputText value="Fertilizacion de Siembra" />
																		</f:facet>
																		<h:outputText value="Fecha" />
																		<rich:calendar id="calFechaSiembra"
																			value="#{escenarioBean.fFertilizacionSiembra}"
																			enableManualInput="false" locale="ES"
																			showApplyButton="false"
																			datePattern="dd/MM/yyyy hh:mm a" popup="true"
																			cellWidth="24px" cellHeight="22px"
																			style="width:200px">

																		</rich:calendar>

																		<h:outputText value="Fuente" />
																		<rich:comboBox>

																		</rich:comboBox>

																		<h:outputText value="rate" />
																		<rich:comboBox>

																		</rich:comboBox>
																	</rich:panel>
																	<rich:panel headerClass="tituloPantalla"
																		style="background-color: #ebf3fd; ">
																		<f:facet name="header">
																			<h:outputText value="Refertilizacion 1" />
																		</f:facet>
																		<h:outputText value="Fecha" />
																		<rich:calendar id="calFechaRefer1"
																			value="#{escenarioBean.fFertilizacionSiembra}"
																			enableManualInput="false" locale="ES"
																			showApplyButton="false"
																			datePattern="dd/MM/yyyy hh:mm a" popup="true"
																			cellWidth="24px" cellHeight="22px"
																			style="width:200px">

																		</rich:calendar>
																		<h:outputText value="Fuente" />
																		<rich:comboBox>

																		</rich:comboBox>
																		<h:outputText value="rate" />
																		<rich:comboBox>

																		</rich:comboBox>
																	</rich:panel>
																	<rich:panel headerClass="tituloPantalla"
																		style="background-color: #ebf3fd; ">
																		<f:facet name="header">
																			<h:outputText value="Refertilizacion 2" />
																		</f:facet>
																		<h:outputText value="Fecha" />
																		<rich:calendar id="calFechaRefer2"
																			value="#{escenarioBean.fFertilizacionSiembra}"
																			enableManualInput="false" locale="ES"
																			showApplyButton="false"
																			datePattern="dd/MM/yyyy hh:mm a" popup="true"
																			cellWidth="24px" cellHeight="22px"
																			style="width:200px">

																		</rich:calendar>

																		<h:outputText value="Fuente" />
																		<rich:comboBox>

																		</rich:comboBox>

																		<h:outputText value="rate" />
																		<rich:comboBox>

																		</rich:comboBox>
																	</rich:panel>
																</h:panelGrid>
																<h:panelGrid columns="2"
																	columnClasses="textoPlano,textoPlano">

																	<h:outputText value="NombreSuelo" />
																	<h:inputText />
																	<h:outputText value="ProfundidadA" />
																	<h:inputText />
																	<h:outputText value="ProfundidadB" />
																	<h:inputText />
																	<h:outputText value="DensidadPlantas" />
																	<h:inputText />
																	<h:outputText value="WULI" />
																	<h:inputText />
																	<h:outputText value="WLLI" />
																	<h:inputText />
																	<h:outputText value="DPMI" />
																	<h:inputText />
																	<h:outputText value="RPMI" />
																	<h:inputText />
																	<h:outputText value="HUMI" />
																	<h:inputText />
																	<h:outputText value="NAULI" />
																	<h:inputText />
																	<h:outputText value="NALLI" />
																	<h:inputText />
																	<h:outputText value="NNULI" />
																	<h:inputText />
																	<h:outputText value="NNLLI" />
																	<h:inputText />
																	<h:panelGrid>
																		<h:outputText styleClass="mensajeError"
																			value="#{confirmacionBean.error}" />
																		<h:outputText styleClass="textoPlano"
																			value="#{confirmacionBean.exito}" />
																	</h:panelGrid>

																	<h:outputText value="" />
																	<h:panelGrid columns="2">
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
																	</h:panelGrid>
																	<f:facet name="footer">
																		<h:outputText styleClass="mensajeError"
																			value="#{registroBean.error}" />
																		<h:outputText styleClass="textoPlano"
																			value="#{registroBean.exito}" />
																	</f:facet>
																</h:panelGrid>
															</h:panelGroup>
														</rich:panel>
													</h:panelGrid>

												</h:panelGrid>
												<center><h:panelGrid columns="2">
													<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
														styleClass="textoPlano" tabindex="3" action="EJE001"
														value="Ejecutar MSCC" />
													<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
														styleClass="textoPlano" tabindex="4" action="EJE003"
														value="Ver Historico" />
												</h:panelGrid></center>
												<f:facet name="footer">
													<h:panelGrid>
														<rich:messages styleClass="mensajeError">
															<f:facet name="errorMarker">
																<h:graphicImage
																	value="/Recursos/Imagenes/Iconos/error.gif" />
															</f:facet>
														</rich:messages>
														<h:outputText styleClass="textoPlano"
															value="#{maestroBean.exito}" />
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

