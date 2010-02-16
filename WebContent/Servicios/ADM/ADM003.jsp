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
									<h:outputText
										value="Bienvenido #{loginBean.usuario._datos._nombre} #{loginBean.usuario._datos._apellido}. Ultimo acceso #{loginBean.usuario._ultimoAcceso}" />
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:panelGrid>
										<center><h:outputText styleClass="mensajeError"
											style="font-size: 12pt" value="#{text.login_notLogged}" /></center>
									</h:panelGrid>
									<h:panelGrid columns="2">
										<center><a4j:commandButton
											style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" action="#{loginBean.logout}"
											value="#{text.login_login}" /></center>
									</h:panelGrid>
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

												<h:panelGroup rendered="#{valorSeleccionBean.init}" />
												<h:panelGrid>
													<rich:panel headerClass="tituloPantalla"
														style="background-color: #ebf3fd;">
														<f:facet name="header">
															<h:outputText
																value="#{text.valorSeleccion_ListaCriterios}" />
														</f:facet>
														<center><h:panelGrid columns="2"
															columnClasses="textoPlano,tectoPlano"
															style=" width : 408px;">

															<h:outputText
																value="#{text.valorSeleccion_ComboCriterios}"
																styleClass="textoPlano" />
															<rich:comboBox
																value="#{valorSeleccionBean.criterioSeleccionado}"
																enableManualInput="false" styleClass="combo"
																disabled="#{valorSeleccionBean.disableSeleccionCriterio}"
																width="214">
																<f:selectItems value="#{valorSeleccionBean.criterio}" />
																<a4j:support
																	action="#{valorSeleccionBean.TakeSelectionListaCriterio}"
																	event="onchange" ajaxSingle="true"
																	reRender="panelValoresSeleccion" />
															</rich:comboBox>
														</h:panelGrid></center>

														<h:panelGrid id="panelValoresSeleccion">
															<h:panelGrid>
																<center><rich:dataTable border="2" rows="5"
																	styleClass="textoDataTable" id="tablaValorSeleccion"
																	value="#{valorSeleccionBean.listaValores}" var="valor"
																	rowKeyVar="row" headerClass="columnHeader"
																	rowClasses="oddRow,evenRow" style=" width : 500px;">

																	<f:facet name="header">
																		<h:outputText
																			value="#{text.valorSeleccion_TablaValores}" />
																	</f:facet>

																	<rich:column width="50px">
																		<f:facet name="header">
																			<h:outputText value="#{text.valorSeleccion_Codigo}" />
																		</f:facet>
																		<h:outputText value="#{valor._codigo}" id="codigo" />
																	</rich:column>

																	<rich:column width="250px">
																		<f:facet name="header">
																			<h:outputText
																				value="#{text.valorSeleccion_Descripcion}" />
																		</f:facet>
																		<h:outputText value="#{valor._descripcion}"
																			id="descripcion" />
																	</rich:column>

																	<rich:column width="100px">
																		<f:facet name="header">
																			<h:outputText value="#{text.valorSeleccion_Unidad}" />
																		</f:facet>
																		<h:outputText value="#{valor._unidadMedida}"
																			id="unidad" />
																	</rich:column>

																	<rich:column>
																		<f:facet name="header">
																			<h:outputText value="Acciones" />
																		</f:facet>

																		<a4j:commandButton
																			action="#{valorSeleccionBean.ModificarValorSeleccion}"
																			image="/Recursos/Imagenes/Iconos/edit.gif"
																			immediate="true" style="width : 27px; height : 21px;">
																			<a4j:actionparam name="valorElegido"
																				value="#{valor._codigo}" />
																			<rich:toolTip value="Modificar" />
																		</a4j:commandButton>

																		<a4j:commandButton
																			action="#{valorSeleccionBean.EliminarValorSeleccion}"
																			image="/Recursos/Imagenes/Iconos/delete.gif"
																			immediate="true"
																			style=" border:0; width : 27px; height : 21px;"
																			reRender="panelValoresSeleccion">
																			<a4j:actionparam name="valorElegido"
																				value="#{valor._codigo}" />
																			<rich:toolTip value="Eliminar" />
																		</a4j:commandButton>

																	</rich:column>
																	<f:facet name="footer">
																		<rich:datascroller renderIfSinglePage="false"
																			maxPages="6" />
																	</f:facet>
																</rich:dataTable></center>
															</h:panelGrid>
														</h:panelGrid>
														<td></td>
														<br>
														<rich:panel headerClass="tituloPantalla"
															style="background-color: #ebf3fd;  width : 500px;">
															<f:facet name="header">
																<h:outputText value="#{text.valorSeleccion_Valor}" />
															</f:facet>
															<center><h:panelGrid columns="2"
																id="datosPropiedad"
																columnClasses="textoPlano,textoPlano">

																<h:outputText value="#{text.valorSeleccion_Codigo}" />
																<h:inputText value="#{valorSeleccionBean.codigo}"
																	required="true"
																	requiredMessage="Debe Ingresar el código"
																	styleClass="textoPlano" maxlength="6"
																	onkeypress="ValidarCampoLetras(this, event)"
																	style=" width : 230px;" />

																<h:outputText value="#{text.valorSeleccion_Descripcion}" />
																<h:inputText value="#{valorSeleccionBean.descripcion}"
																	required="true"
																	requiredMessage="Debe Ingresar descripción"
																	styleClass="textoPlano" maxlength="220"
																	onkeypress="ValidarCampoLetras(this, event)"
																	style=" width : 230px;">
																</h:inputText>

																<h:outputText value="#{text.valorSeleccion_Unidad}" />
																<h:inputText value="#{valorSeleccionBean.unidadMedida}"
																	styleClass="textoPlano" maxlength="45"
																	onkeypress="ValidarCampoLetras(this, event)"
																	style=" width : 230px;">
																</h:inputText>
																<h:outputText />
															</h:panelGrid> <h:panelGrid columns="2">
																<a4j:commandButton
																	style="font-size: 10pt; color: #2d77c2; width : 125px;"
																	styleClass="textoPlano"
																	value="#{text.valorSeleccion_BotonAceptar}"
																	disabled="#{propiedadesBean.disableAceptarPropiedad}">
																	<a4j:support event="onclick" ajaxSingle="false"
																		reRender="panelPropiedades"
																		action="#{valorSeleccionBean.AceptarValorSeleccion}" />
																</a4j:commandButton>
															</h:panelGrid></center>
														</rich:panel>
														<br></br>

														<center><h:panelGrid columns="2">
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2; width : 87px;"
																styleClass="textoPlano" action="Alta"
																value="#{text.boton_Registrar}" />

															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 87px;"
																styleClass="textoPlano" action="cancelar"
																value="#{text.boton_Cancelar}" />
														</h:panelGrid></center>
														<h:panelGrid id="mensages">
															<rich:messages styleClass="mensajeError">
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
																</f:facet>
															</rich:messages>
															<h:outputText styleClass="textoPlano"
																value="#{valorSeleccionBean.exito}" />
														</h:panelGrid>
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