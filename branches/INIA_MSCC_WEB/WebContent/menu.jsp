<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<f:subview id="menu">
<f:loadBundle basename="com.bean.text" var="text" />
<h:form id="menuForm">
	<h:panelGrid columns="1">
		<h:panelGrid columns="2" footerClass="colorNegro">
			<h:panelGroup>
				<h:commandLink rendered="#{menuBean.opcion == 'inicio'}" styleClass="opcionElegida" action="#{menuBean.inicio}" value="#{text.menu_start}"/>
				<h:commandLink rendered="#{menuBean.opcion != 'inicio'}" styleClass="opcionNormal" action="#{menuBean.inicio}" value="#{text.menu_start}"/>
				<h:panelGrid rendered="#{loginBean.logged}">
					<h:outputText styleClass="textoPlano" value="#{loginName.nombre}"/>
				</h:panelGrid>
			</h:panelGroup>
		</h:panelGrid>
		<h:graphicImage url="../theme/imagenes/otraQbox.gif"/>
		<h:panelGrid columns="3" footerClass="colorNegro">
			<h:panelGroup>
				<h:commandLink rendered="#{menuBean.opcion == 'modelo'}" styleClass="opcionElegida" action="#{menuBean.modelo}" value="#{text.menu_catalog}"/>
				<h:commandLink rendered="#{menuBean.opcion != 'modelo'}" styleClass="opcionNormal" action="#{menuBean.modelo}" value="#{text.menu_catalog}"/>
			</h:panelGroup>
			<h:panelGroup>
				<h:commandLink  rendered="#{menuBean.opcion == 'listado'}" styleClass="opcionElegida" action="#{menuBean.listado}" value="#{text.menu_list}"/>
				<h:commandLink rendered="#{menuBean.opcion != 'listado'}" styleClass="opcionNormal" action="#{menuBean.listado}" value="#{text.menu_list}"/>
			</h:panelGroup>
			<h:panelGroup>
				<h:commandLink rendered="#{menuBean.opcion == 'objetos'}" styleClass="opcionElegida" action="#{menuBean.objetos}" value="#{text.menu_objects}"/>
				<h:commandLink rendered="#{menuBean.opcion != 'objetos'}" styleClass="opcionNormal" action="#{menuBean.objetos}" value="#{text.menu_objects}"/>
			</h:panelGroup>	
		</h:panelGrid>
		<h:outputText value=""/>
	</h:panelGrid>
</h:form>
</f:subview>
