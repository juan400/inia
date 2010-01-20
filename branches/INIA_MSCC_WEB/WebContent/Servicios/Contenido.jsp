<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<html>
<head>
<title>INIA - Proyecto: Modelo de simulación de crecimiento de
cultivos de secano.</title>
<link href="../Recursos/css/inia2010-01-17.css" rel="stylesheet"
	type="text/css">
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
					<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0"
						width="100%" height="100%">
						<tr>
							<td>
							<table
								style="vertical-align: top; background-color: white; background-repeat: repeat-y; background-image: url('../Recursos/Imagenes/fondoTablaMenu.jpg'); background-position: left;"
								align="left" width="200px">
								<tr>
									<td><h:panelGrid columnClasses="cols" width="200">
										<rich:panelMenu styleClass="menu" style="width:200px"
											mode="ajax" iconExpandedGroup="disc"
											iconCollapsedGroup="disc" iconExpandedTopGroup="chevronUp"
											iconGroupTopPosition="right"
											iconCollapsedTopGroup="chevronDown">
											<rich:panelMenuGroup label="Adminstracion">
												<rich:panelMenuItem label="Item 1.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 1.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 1.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 1.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 1.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 1.3" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
											<rich:panelMenuGroup label="Seguridad">
												<rich:panelMenuItem label="Item 2.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 2.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 2.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 2.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 2.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 2.3" />
												</rich:panelMenuItem>
												<rich:panelMenuGroup label="Group 2.4">
													<rich:panelMenuItem label="Item 2.4.1"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="Item 2.4.1" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Item 2.4.2"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="Item 2.4.2" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Item 2.4.3"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="Item 2.4.3" />
													</rich:panelMenuItem>
												</rich:panelMenuGroup>
												<rich:panelMenuItem label="Item 2.5"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 2.5" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
											<rich:panelMenuGroup label="Gestión de Escenarios de MSCC">
												<rich:panelMenuItem label="Item 3.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.3" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
											<rich:panelMenuGroup
												label="Ejecución de escenarios para MSCC">
												<rich:panelMenuItem label="Item 3.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.3" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
											<rich:panelMenuGroup
												label="Administración de Datos Climatológicos">
												<rich:panelMenuItem label="Item 3.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.3" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
											<rich:panelMenuGroup label="Log de Uso Transaccional">
												<rich:panelMenuItem label="Item 3.1"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.1" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.2"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.2" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Item 3.3"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="Item 3.3" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
										</rich:panelMenu>
									</h:panelGrid></td>
								</tr>
							</table>
							</td>
							<td>
							<table id="contenido"
								style="vertical-align: top; background-color: white; background-repeat: 
								repeat-y; background-image: url('../Recursos/Imagenes/fondoTablaContenedora.jpg'); 
								background-position: right;"
								align="left" width="750px">
								<tr>
									<td><rich:panel bodyClass="rich-laguna-panel-no-header">
										<a4j:outputPanel ajaxRendered="true" id="current">
											<jsp:include page="/Servicios/Precentacion.jsp" />
										</a4j:outputPanel>
									</rich:panel></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td>
					<div class="pie"></div>
					</td>
				</tr>
			</tfoot>
		</table>
	</h:form>
</f:view>
</body></html>