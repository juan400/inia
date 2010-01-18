<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.ajax4jsf.org/rich" prefix="richfaces"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>INIA - Proyecto: Modelo de simulación de crecimiento de
cultivos de secano.</title>
<link href="Recursos/css/inia2010-01-17.css" rel="stylesheet"
	type="text/css">
</head>
<body>
<f:view>
	<h:form>
		<div class="logo"></div>
	</h:form>
	<h:form>
		<table align="center" width="956px" border="1">
			<tr>
				<td width="200px"><h:form id="form">
					<h:panelGrid columns="2" columnClasses="cols,cols" width="400">
						<rich:panelMenu style="width:200px" mode="ajax"
							iconExpandedGroup="disc" iconCollapsedGroup="disc"
							iconExpandedTopGroup="chevronUp" iconGroupTopPosition="right"
							iconCollapsedTopGroup="chevronDown">
							<rich:panelMenuGroup label="Group 1">
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
							<rich:panelMenuGroup label="Group 2">
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
							<rich:panelMenuGroup label="Group 3">
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
					</h:panelGrid>
				</h:form></td>
				<td><rich:panel bodyClass="rich-laguna-panel-no-header">
					<a4j:outputPanel ajaxRendered="true" id="current">
						<jsp:include page="/Servicios/SEG/LoginRich.jsp" />

					</a4j:outputPanel>
				</rich:panel></td>

			</tr>
			<tr>
				<td colspan="2">Por si se necesita poner algo de pie en el
				contenido</td>
			</tr>
		</table>
	</h:form>
	<h:form>
		<div class="pie"></div>
	</h:form>
</f:view>
</body>