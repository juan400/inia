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
<script type="text/javascript" language="javascript"
	src="<%=request.getContextPath()%>/Recursos/Scripts/JSComun.js"></script>
</head>
<body>
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
					<td align="center" class="contenido"><h:panelGroup
						rendered="#{loginBean.init}" /> <h:panelGrid>
						<rich:panel headerClass="tituloPantalla"
							style="background-color: #ebf3fd; ">
							<f:facet name="header">
								<h:outputText value="#{text.login_newUser}" />
							</f:facet>
							<h:panelGrid rendered="#{registroBean.logged}">
								<h:outputText styleClass="mensajeError"
									value="#{registroBean.loginName} #{text.login_alreadyLogged}" />
								<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
									styleClass="textoPlano" action="#{registroBean.cancelar}"
									value="#{text.boton_Cancelar}" />
							</h:panelGrid>
							<h:panelGroup rendered="#{!registroBean.logged}">
								<h:panelGrid columns="2" columnClasses="textoPlano,textoPlano">

									<h:outputText value="#{text.registro_Nombre}" />
									<h:inputText value="#{registroBean.nombre}" required="true"
										onkeypress="ValidarCampoLetras(this, event)"
										onblur="ValidarCampoLetras(this, event)" />

									<h:outputText value="#{text.registro_Apellido}" />
									<h:inputText value="#{registroBean.apellido}" required="true"
										onkeypress="ValidarCampoLetras(this, event)"
										onblur="ValidarCampoLetras(this, event)" />

									<h:outputText value="#{text.registro_Email}" />
									<h:inputText value="#{registroBean.email}" required="true"
										onblur="validarEmailBlur(this, event)" 
										onkeypress="validarEmailKeyPress(this, event)"/>

									<h:outputText value="#{text.registro_Ciudad}" />
									<rich:comboBox value="#{registroBean.ciudad}" />

									<h:outputText value="#{text.registro_Departamento_Estado_Provincia}" />
									<rich:comboBox value="#{registroBean.departamento}"  />

									<h:outputLabel value="#{text.registro_Pais}"  />
									<rich:comboBox value="#{registroBean.pais}"  />
									
									<h:outputText value="#{text.registro_Direccion}" />
									<h:inputText value="#{registroBean.direccion}" required="true" 
										onkeypress="ValidarCampoConCaracteresEspeciales(this, event)"
										onblur="ValidarCampoConCaracteresEspeciales(this, event)" />

									<h:outputText value="#{text.registro_Teléfono}" />
									<h:inputText value="#{registroBean.telefono}" 
										onblur="ValidarCampoTelefono(this, event)" 
										onkeypress="ValidarCampoTelefono(this, event)"/> 

									<h:outputText value="#{text.registro_Celular}" />
									<h:inputText value="#{registroBean.celular}" 
										onblur="ValidarCampoTelefono(this, event)" 
										onkeypress="ValidarCampoTelefono(this, event)"/>


									<h:outputText value="" />
									<h:panelGrid columns="2">
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" action="#{registroBean.registrar}"
											value="#{text.boton_Aceptar}"/>
										<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" action="#{registroBean.cancelar}"
											value="#{text.boton_Cancelar}"/>
									</h:panelGrid>
									<f:facet name="footer">
										<h:outputText value="#{registroBean.error}"
											styleClass="mensajeError" />
									</f:facet>
								</h:panelGrid>
							</h:panelGroup>
							<h:panelGroup rendered="#{registroBean.activado}">
								<h:panelGrid columns="2" columnClasses="tituloTabla, textoPlano">
									<h:outputText value="#{text.registro_Contrasenia}" />
									<h:inputText value="#{registroBean.contrasenia}" />

									<h:outputText value="#{text.registro_ConfirmacionContrasenia}" />
									<h:inputText value="#{registroBean.confirmacion}" />

									<h:outputText value="#{text.registro_Frase}" />
									<h:inputText value="#{registroBean.nombre}" />

									<h:outputText value="#{text.login_registerSubject}" />
									<h:inputText value="#{registroBean.email}" />

									<h:outputText value="" />
									<a4j:commandButton style="font-size: 10pt; color: #2d77c2;"
											styleClass="textoPlano" 
											action="#{registroBean.registrar}" value="#{text.login_ok}" />
									<f:facet name="footer">
										<h:outputText value="#{registroBean.error}"
											styleClass="mensajeError" />
									</f:facet>
								</h:panelGrid>
							</h:panelGroup>
						</rich:panel>
					</h:panelGrid></td>
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