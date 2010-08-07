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
												value="Usuario #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}  -  Ultimo acceso #{loginBean.fechaEjecucionFormt}">
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
									<h:outputText styleClass="mensajeError" style="font-size: 12pt"
										value="#{text.login_notLogged}" />
									<center><a4j:commandButton
										style="font-size: 10pt; color: #2d77c2; width : 120px;"
										styleClass="textoPlano" action="#{loginBean.logout}"
										immediate="true" value="#{text.login_Login}" /></center>
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
												<h:panelGroup rendered="#{escenarioBean.modificacion}" />
												<h:panelGrid>
													<rich:panel headerClass="tituloPantalla"
														style="background-color: #ebf3fd;">
														<f:facet name="header">
															<h:outputText value="#{text.escenario_Modificar}" />
														</f:facet>

														<h:panelGrid columns="2" width="500px"
															columnClasses="textoPlano,textoPlano">
															<h:outputText value="#{text.escenario_Fecha}" />
															<rich:calendar id="calFecha"
																inputClass="rich-calendar-input"
																value="#{escenarioBean.fecha}" enableManualInput="false"
																locale="ES" showApplyButton="false"
																datePattern="dd/MM/yyyy" popup="true" cellWidth="24px"
																cellHeight="22px" style="width:200px">
																<a4j:support
																	action="#{escenarioBean.takeSelectionCalendar}"
																	event="onchange" ajaxSingle="true"
																	reRender="tablaArchivo" />
															</rich:calendar>

															<h:outputLabel value="#{text.escenario_SelecCultivo}" />
															<rich:comboBox
																requiredMessage="Debe seleccionar un cultivo"
																value="#{escenarioBean.cultivoElegido}" required="true"
																enableManualInput="false" styleClass="combo" width="220">
																<f:selectItems value="#{escenarioBean.cultivos}" />
																<a4j:support
																	action="#{escenarioBean.takeSelectionCultivo}"
																	event="onchange" ajaxSingle="true"
																	reRender="upload,cmdRegiones" />
																<rich:toolTip
																	value="Seleccionar el cultivo al cual asociar el escenario que va a registrar." />
															</rich:comboBox>

															<h:outputLabel value="#{text.escenario_Region}" />
															<rich:comboBox disabled="#{escenarioBean.disableRegion}"
																value="#{escenarioBean.regionElegida}" required="true"
																enableManualInput="false" styleClass="combo"
																requiredMessage="Debe seleccionar una Región"
																id="cmdRegiones" width="220">
																<f:selectItems value="#{escenarioBean.regiones}" />
																<a4j:support
																	action="#{escenarioBean.takeSelectionRegion}"
																	event="onchange" ajaxSingle="true" reRender="upload" />
																<rich:toolTip
																	value="Seleccionar el cultivo al cual asociar el escenario que va a registrar." />
															</rich:comboBox>

															<h:outputLabel value="#{text.cultivo_Estado}" />
															<rich:comboBox value="#{escenarioBean.estado}"
																style=" higth : 18px;" enableManualInput="false"
																styleClass="combo" width="220">
																<f:selectItems value="#{escenarioBean.estados}" />
															</rich:comboBox>

														</h:panelGrid>
														<center><a4j:commandButton immediate="true"
															style="font-size: 10pt; color: #2d77c2; width : 120px;"
															styleClass="textoPlano"
															action="#{escenarioBean.buscarEscenarios}"
															value="#{text.boton_Buscar}" /></center>

														<h:panelGrid id="panelArchivo">
															<center><rich:dataTable border="2" rows="5"
																styleClass="textoDataTable" id="tablaArchivo"
																value="#{escenarioBean.escenarios}" var="escenario"
																rowKeyVar="row" headerClass="columnHeader"
																rowClasses="oddRow,evenRow">

																<f:facet name="header">
																	<h:outputText value="#{text.archivo_ListaArchi}" />
																</f:facet>

																<rich:column width="50px">
																	<f:facet name="header">
																		<h:outputText value="#{text.archivo_nombre}" />
																	</f:facet>
																	<h:outputText
																		value="#{escenario._archivoEscenario._nombre}" />
																</rich:column>

																<rich:column width="100px">
																	<f:facet name="header">
																		<h:outputText value="#{text.archivo_fechaHora}" />
																	</f:facet>
																	<h:outputText value="#{escenario._fechaHora}" />
																</rich:column>

																<rich:column width="100px">
																	<f:facet name="header">
																		<h:outputText value="#{text.archivo_estado}" />
																	</f:facet>
																	<h:outputText value="#{escenario._estado}" />
																</rich:column>

																<rich:column width="100">
																	<f:facet name="header">
																		<h:outputText value="#{text.archivo_tipo}" />
																	</f:facet>
																	<h:outputText value="#{escenario._cultivo._nombre}" />
																</rich:column>
																
																<rich:column width="80">
																	<f:facet name="header">
																		<h:outputText value="Acciones" />
																	</f:facet>
																	<a4j:commandButton
																		action="#{escenarioBean.VerEscenario}"
																		image="/Recursos/Imagenes/Iconos/edit.gif"
																		immediate="true" style="width : 27px; height : 21px;"
																		reRender="Modificar">
																		<a4j:actionparam name="propiedadElegida"
																			value="#{escenario._id}" />
																		<rich:toolTip value="Modificar" />
																	</a4j:commandButton>

																	<a4j:commandButton
																		action="#{escenarioBean.Eliminar}"
																		image="/Recursos/Imagenes/Iconos/delete.gif"
																		immediate="true"
																		style=" border:0; width : 27px; height : 21px;"
																		reRender="panelPropiedades">
																		<a4j:actionparam name="propiedadElegida"
																			value="#{escenario._id}" />
																		<rich:toolTip value="Eliminar" />
																	</a4j:commandButton>
																</rich:column>

																<f:facet name="footer">
																	<rich:datascroller renderIfSinglePage="false"
																		maxPages="6" />
																</f:facet>
															</rich:dataTable></center>
														</h:panelGrid>
														<h:outputText />

														<center><h:panelGrid columns="3">
															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 120px;"
																styleClass="textoPlano"
																action="#{escenarioBean.ModificarEscenario}"
																value="#{text.boton_Registrar}" />

															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 120px;"
																styleClass="textoPlano" action="cancelar"
																value="#{text.boton_Cancelar}" />
														</h:panelGrid></center>
														<center><h:panelGrid id="mensajes">
															<rich:messages styleClass="mensajeError">
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
																</f:facet>
															</rich:messages>
															<h:outputText styleClass="textoPlano"
																value="#{escenarioBean.exito}" />
														</h:panelGrid></center>
													</rich:panel>
												</h:panelGrid>
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