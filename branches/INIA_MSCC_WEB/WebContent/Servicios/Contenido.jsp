<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
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
											mode="server" iconExpandedGroup="disc"
											iconCollapsedGroup="disc" iconExpandedTopGroup="chevronUp"
											iconGroupTopPosition="right"
											iconCollapsedTopGroup="chevronDown">
											<rich:toolBarGroup location="right" itemSeparator="grid">
									        	<h:outputLabel  rendered="#{loginBean.logged}" value="#{text.login_userName} : #{loginBean.nombre}"/>
									            <h:commandButton rendered="#{loginBean.logged}" action="#{loginBean.logout}" label="#{text.login_logout}" image="/Recursos/Imagenes/Iconos/door_out.png"/> 
									            <h:commandButton rendered="#{!loginBean.logged}" action="#{loginBean.login}" label="#{text.login_login}" image="/Recursos/Imagenes/Iconos/door_in.png"/>
									        </rich:toolBarGroup>
											<rich:panelMenuGroup label="Adminstracion">
												<rich:panelMenuItem label="Precentacion"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="/Servicios/Precentacion.jsp" />
												</rich:panelMenuItem>
												<rich:panelMenuItem label="Login"
													action="#{panelMenu.updateCurrent}">
													<f:param name="current" value="/Servicios/SEG/Registro.jsp" />
												</rich:panelMenuItem>
											</rich:panelMenuGroup>
										</rich:panelMenu>
									</h:panelGrid></td>
								</tr>
							</table>
							</td>
							<td>
							<table id="contenido"
								style="vertical-align: top; background-color: white; background-repeat: repeat-y; background-image: url('../Recursos/Imagenes/fondoTablaContenedora.jpg'); background-position: right;"
								align="left" width="750px">
								<tr>
									<td>

									<rich:panel	>
										<f:facet name="header">
											<h:outputText value="Current selection" />
										</f:facet>
										<a4j:outputPanel ajaxRendered="true" style="heigth: 100%">
											 
											 <jsp:include page="<%=request.getContextPath()%>'#{panelMenu.current}"></jsp:include>
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
</body>
</html>