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
												immediate="true" value="Cerrar Cesión">
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
														<h:outputText value="#{text.cultivo_Ingresar}" />
													</f:facet>
													<h:panelGrid>
														<h:panelGrid columnClasses="textoPlano">
															Salida modelo INIA-Trigo
															<br />
															Corrida #xxxx 10/9/2009 11:30
															<br />
															
															Datos de Fenología
															<br />
															
															Siembra: Fecha de siembra
															<br /> 
															Emergencia:
															<br /> 
															Z22:
															<br />
															Z30:
															<br />
															Anthesis:
															<br />
															Madurez fisiologica:
															<br />
															
															Acumulación de materia seca: 8000 kgha-1
															<br />
															Rendimiento en grano (12% humedad): 3000 kgha-1
															<br />
															Indice de cosecha(PS garno/PS rastrojo): 30%
															<br />
															Contenido de nitrogeno del rastrojo: 3%
															<br />
															Contenido de proteína del grano: 12%
															<br />
															<rich:simpleTogglePanel switchType="client" style="background-color: #ebf3fd;"
																label="Acumulación de biomasa y Indice de area foliar">
																<rich:paint2D width="500" height="400"
																	data="#{paintData}" format="png"
																	paint="#{paintBean.paintXYSplineRendererCSH_LAI}" />
															</rich:simpleTogglePanel>
															<div class="div_near_spacer" /><rich:simpleTogglePanel
																switchType="client" opened="false"
																label="Balance de agua en el suelo">
																<rich:paint2D width="500" height="400"
																	data="#{paintData}" format="png"
																	paint="#{paintBean.paintEurodollar}" />
															</rich:simpleTogglePanel>
															<div class="div_near_spacer" /><rich:simpleTogglePanel style="background-color: #ebf3fd;"
																switchType="client" opened="false"
																label="Balance de nitrogeno en suelo">
																<rich:paint2D width="500" height="400"
																	data="#{paintData}" format="png"
																	paint="#{paintBean.paintXYSplineRendererNN_NUPTN}" />
															</rich:simpleTogglePanel>
															<div class="div_near_spacer" /><rich:simpleTogglePanel
																switchType="client" opened="false" style="background-color: #ebf3fd;"
																label="Garficador generico">

																<h:panelGrid columns="2"
																	columnClasses="textoPlano,textoPlano" width="500px">

																	<h:outputLabel value="Seleccione variable inicial" />
																	<rich:comboBox value="#{cultivoBean.estado}"
																		style=" higth : 18px;" enableManualInput="false"
																		styleClass="combo" width="220">
																		<f:selectItems value="#{cultivoBean.estados}" />
																	</rich:comboBox>

																	<h:outputLabel value="Seleccione variable referencia" />
																	<rich:comboBox value="#{cultivoBean.estado}"
																		style=" higth : 18px;" enableManualInput="false"
																		styleClass="combo" width="220">
																		<f:selectItems value="#{cultivoBean.estados}" />
																	</rich:comboBox>
																</h:panelGrid>
																<rich:paint2D width="500" height="400"
																	data="#{paintData}" format="png"
																	paint="#{paintBean.paintScatterPlot}" />
															</rich:simpleTogglePanel>
															<div class="div_near_spacer" />
														</h:panelGrid>
														<center><h:panelGrid>
															<a4j:commandButton immediate="true"
																style="font-size: 10pt; color: #2d77c2; width : 120px;"
																styleClass="textoPlano" action="cancelar"
																value="#{text.boton_Cerrar}" />
														</h:panelGrid></center>
														<center><h:panelGrid id="mensages">
															<rich:messages styleClass="mensajeError">
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
																</f:facet>
															</rich:messages>
															<h:outputText styleClass="textoPlano"
																value="#{propiedadesBean.exito}" />
														</h:panelGrid></center>
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