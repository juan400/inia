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
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.perfil_ActPerfil}" />
													</f:facet>
													<h:panelGrid>
														<h:panelGrid columns="2"
															columnClasses="textoPlano,textoDataTable">
															<h:outputText value="#{text.perfil_Nombre}" />
															<h:inputText label="Name" id="name" required="true"
																requiredMessage="Debe ingresar el Nombre."
																value="#{perfilBean.nombre}"
																onkeypress="ValidarCampoLetras(this, event)"
																style=" width : 245px;">
																<f:validateLength maximum="45">
																</f:validateLength>
															</h:inputText>

															<h:outputText value="#{text.perfil_Descripcion}" />
															<h:inputTextarea id="descripcion"
																value="#{perfilBean.descripcion}"
																onkeypress="ValidarLargoMultiline(this, event, 220)"
																style=" width : 245px; height : 71px;" />

															<h:outputText value="#{text.perfil_Estado}" />
															<rich:comboBox styleClass="textoDataTable"
																value="#{perfilBean.estado}" width="245px">
																<f:selectItem itemValue="Activo" />
																<f:selectItem itemValue="Inactivo" />
															</rich:comboBox>

															<td style="width: 2px;"></td>
															<h:outputText value="" />
															
														</h:panelGrid>

														<center><rich:dataTable border="2" width="400px"
															id="tablaTransaccion" rows="6"
															styleClass="textoDataTable"
															value="#{perfilBean.transaccionesActivas}" var="transaccion"
															rowKeyVar="row" headerClass="columnHeader"
															rowClasses="oddRow,evenRow">
															<f:facet name="header">
																<h:outputText value="Transacciones" />
															</f:facet>

															<rich:column width="20">
																<h:selectBooleanCheckbox
																	value="#{transaccion._asociada}" id="codigo">
																</h:selectBooleanCheckbox>
															</rich:column>

															<rich:column width="300">
																<f:facet name="header">
																	<h:outputText value="Descripción" />
																</f:facet>
																<h:outputText value="#{transaccion._descripcionBase}"
																	id="descripcion" />
															</rich:column>

															<rich:column width="80">
																<f:facet name="header">
																	<h:outputText value="Estado" />
																</f:facet>
																<h:outputText value="#{transaccion._estado}" id="estado" />
															</rich:column>

															<f:facet name="footer">
																<rich:datascroller renderIfSinglePage="false"
																	maxPages="5" />
															</f:facet>
														</rich:dataTable></center>
														

														<CENTER><h:panelGrid columns="2">
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2; width : 71px;"
																styleClass="textoPlano"
																action="#{perfilBean.actualizar}"
																value="#{text.boton_Aceptar}" />
															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 71px;"
																styleClass="textoPlano" action="salir"
																value="#{text.boton_Cancelar}" />
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
																	value="#{perfilBean.exito}" />
															</h:panelGrid>
														</f:facet>
													</h:panelGrid>
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