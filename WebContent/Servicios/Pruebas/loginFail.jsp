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
					
					<rich:panel>
            <f:facet name="header">
                <h:outputText value="Validation Form"/>
            </f:facet>
            
            <h:form>
                <h:panelGrid columns="3">
                    <h:outputText value="Name:" />
                    <h:inputText label="Name" id="name" required="true" >
                        <f:validateLength minimum="3"  />
                    </h:inputText>
                    <rich:message for="name">
                        <f:facet name="passedMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/passed.gif" /> 
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />   
                        </f:facet>
                    </rich:message>
                    
                    <h:outputText value="Job:" />
                    <h:inputText label="Job" id="job" required="true" >
                        <f:validateLength minimum="3" maximum="50"  />
                    </h:inputText>
                    <rich:message for="job" ="true">
                        <f:facet name="passedMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/passed.gif" /> 
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/error.gif" />  
                        </f:facet>
                    </rich:message>
                    

                    
                    <h:outputText value="Address:" />
                    <h:inputText label="Address" id="address" required="true" >
                        <f:validateLength minimum="10" />
                    </h:inputText>
                    <rich:message for="address">
                        <f:facet name="passedMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/passed.gif" /> 
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/error.gif" />  
                        </f:facet>
                    </rich:message>
                    
                    <h:outputText value="Zip:" />
                    <h:inputText label="Zip" id="zip" required="true" >
                        <f:validateLength minimum="4" maximum="9"  />
                    </h:inputText>
                    <rich:message for="zip">
                        <f:facet name="passedMarker">
                            <h:graphicImage  value="/Recursos/Imagenes/Iconos/passed.gif" /> 
                        </f:facet>
                        <f:facet name="errorMarker">
                            <h:graphicImage value="/Recursos/Imagenes/Iconos/error.gif" />   
                        </f:facet>
                    </rich:message>
                    
                    <f:facet name="footer">
                        <a4j:commandButton bypassUpdates="true" action="#{confirmacionBean.Confirmar}"/>
                    </f:facet>
                    
                    
                </h:panelGrid>
            </h:form>
        </rich:panel>
					
					</td>
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