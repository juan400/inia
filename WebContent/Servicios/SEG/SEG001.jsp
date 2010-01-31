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
					<td align="center" class="contenido">
					<a4j:region id="contenido"><h:panelGroup
						rendered="#{loginBean.init}" /> <h:panelGrid>
						<rich:panel headerClass="tituloPantalla"
							style="background-color: #ebf3fd;">
							<f:facet name="header">
								<h:outputText value="#{text.login_title}" />
							</f:facet>
							<h:panelGrid rendered="#{loginBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{loginBean.logout}" immediate="true"
									value="#{text.login_logout}" />
							</h:panelGrid>
							<h:panelGroup rendered="#{!loginBean.logged}">
								<h:panelGrid columns="2" width="300">

									<h:outputText value="#{text.login_userName}"
										styleClass="textoPlano" />
									<h:inputText value="#{loginBean.loginName}"
										styleClass="textoPlano" tabindex="1"
										onkeypress="validarEmailKeyPress(this, event)"/>

									<h:outputText value="#{text.login_password}"
										styleClass="textoPlano" />
									<h:inputSecret value="#{loginBean.password}"
										styleClass="textoPlano" tabindex="2"
										onblur="ValidarCampoLogin(this, event)" 
										onkeypress="ValidarCampoLogin(this, event)"/>

									<h:outputText value="" />									
									<h:panelGrid columns="2">
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" tabindex="3"
											action="#{loginBean.login}"
											value="#{text.login_login}" />
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" tabindex="4" 
											action="#{loginBean.registrarse}" immediate="true"
											value="#{text.login_register}" />
									</h:panelGrid>
									<f:facet name="footer">
										<h:outputText value="#{loginBean.error}"
											styleClass="mensajeError" />
									</f:facet>
									<h:outputText value="" />
									<h:panelGrid columns="2">
										<a4j:commandLink action="#{loginBean.olvidoContrasenia}" immediate="true"
											value="#{text.login_forgotPassword}" />
										
									</h:panelGrid>
								</h:panelGrid>
							</h:panelGroup>
						</rich:panel>
					</h:panelGrid><a4j:status for="contenido"
							onstart="Richfaces.showModalPanel('ajaxLoadingModalBox',{width:100, top:200,height:'90px'})"
							onstop="Richfaces.hideModalPanel('ajaxLoadingModalBox')"></a4j:status>
					</a4j:region> <rich:modalPanel id="ajaxLoadingModalBox" minHeight="100"
						minWidth="200" height="40" width="400" zindex="100" styleClass="">
						<rich:panel style="background-color: #ebf3fd; ">
							<center><h2><h:outputText value="Procesando..."
								styleClass="textoPlano">
							</h:outputText></h2></center>
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