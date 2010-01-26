<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>

<head>
<title>INIA - Prueba menu Mauricio.</title>
</head>

<f:subview id="menu">
	<f:loadBundle basename="com.bean.text" var="text" />
	<h:form id="menuForm">

		<style>
.cols {
	vertical-align: Top;
	width: 50%;
}
</style>
		<h:panelGrid columns="2" columnClasses="cols" width="100%">
			<rich:panelMenu styleClass="menu" style="width:200px" mode="ajax"
				iconExpandedGroup="disc" iconCollapsedGroup="disc"
				iconExpandedTopGroup="chevronUp" iconGroupTopPosition="right"
				iconCollapsedTopGroup="chevronDown">
				<rich:panelMenuGroup label="Adminstracion">
					<rich:panelMenuItem label="Item 1.1"
						action="#{panelMenu.updateCurrent}">
						<f:param name="current" value="prueba 1" />
					</rich:panelMenuItem>
					<rich:panelMenuItem label="Item 1.2"
						action="#{panelMenu.updateCurrent}">
						<f:param name="current" value="Prueba 2" />
					</rich:panelMenuItem>
				</rich:panelMenuGroup>
			</rich:panelMenu>
			<rich:panel bodyClass="rich-laguna-panel-no-header">
				<a4j:outputPanel ajaxRendered="true"> 
					<h:outputText value="#{panelMenu.current} selected" id="current" />
					
				</a4j:outputPanel>
			</rich:panel>
		</h:panelGrid>
	</h:form>
</f:subview>