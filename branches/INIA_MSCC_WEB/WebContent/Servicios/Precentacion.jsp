<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view >
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
									<h:outputText
										value="Bienvenido #{maestroBean.usuario.nombre} #{maestroBean.usuario.apellido}" />
								</f:facet>
								<center><h:panelGrid rendered="#{!loginBean.logged}">
									<h:outputText styleClass="mensajeError" style="font-size: 12pt"
										value="#{text.login_notLogged}" />
									<center><a4j:commandButton
										style="font-size: 10pt; color: #2d77c2;"
										styleClass="textoPlano" action="#{loginBean.logout}"
										immediate="true" value="#{text.login_login}" /></center>
								</h:panelGrid></center>
								<h:panelGroup rendered="#{loginBean.logged}">
									<h:panelGrid columns="2" rendered="#{loginBean.logged}"
										columnClasses="cols" width="200">
										<h:column>
											<rich:panelMenu styleClass="menu" style="width:200px"
												mode="server" iconExpandedGroup="disc"
												iconCollapsedGroup="disc" iconExpandedTopGroup="chevronUp"
												iconGroupTopPosition="right"
												iconCollapsedTopGroup="chevronDown">
												<rich:panelMenuGroup label="Seguridad"
													styleClass="textoMenu">
													<rich:panelMenuItem action='irLogin'>
														<h:outputLabel styleClass="textoMenuSecundario"
															rendered="#{loginBean.logged}"
															value="#{text.login_userName} : #{loginBean.loginName}" />
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoMenuSecundario"
															rendered="#{loginBean.logged}"
															action="#{loginBean.logout}" value="#{text.login_logout}"
															image="../Recursos/Imagenes/Iconos/door_out.png" />
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoMenuSecundario"
															rendered="#{!loginBean.logged}"
															action="#{loginBean.login}" value="#{text.login_login}"
															image="../Recursos/Imagenes/Iconos/door_in.png" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Datos de su cuenta"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG004.jsf" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Dar de baja su cuenta"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG005.jsf" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Cambiar su contraseña"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG007.jsf" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Item 2.5"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG007.jsf" />
													</rich:panelMenuItem>
												</rich:panelMenuGroup>
												<rich:panelMenuGroup label="Adminstracion"
													styleClass="textoMenu">
													<rich:panelMenuItem label="Precentacion"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="Precentacion.jsp" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Modificar datos personales"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG004.jsf" />
													</rich:panelMenuItem>
												</rich:panelMenuGroup>
											</rich:panelMenu>
										</h:column>
										<h:column>
											<h:panelGroup rendered="#{recuperarContraseniaBean.init}" />
											<h:panelGrid>
												<rich:panel headerClass="tituloPantalla"
													style="background-color: #ebf3fd;">
													<f:facet name="header">
														<h:outputText value="#{text.confiramcion_Titulo}" />
													</f:facet>

													<h:panelGroup
														rendered="#{!recuperarContraseniaBean.activado}">
														<h:panelGrid columns="2" width="480">

														</h:panelGrid>
														<h:panelGrid columns="2" width="480">


														</h:panelGrid>
														<center><h:panelGrid columns="2">
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2;"
																styleClass="textoPlano" tabindex="3"
																action="#{confirmacionBean.Confirmar}"
																value="#{text.boton_Guardar}" />
															<a4j:commandButton
																style="font-size: 10pt; color: #2d77c2;"
																styleClass="textoPlano" tabindex="4" action="Cancelar"
																value="#{text.boton_Cancelar}" />
														</h:panelGrid></center>
														<h:panelGrid>
															<rich:messages styleClass="mensajeError">
																<f:facet name="passedMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/passed.gif" />
																</f:facet>
																<f:facet name="errorMarker">
																	<h:graphicImage
																		value="/Recursos/Imagenes/Iconos/error.gif" />
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