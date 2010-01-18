<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<f:view>
	<link href="/Recursos/css/inia2010-01-17.css" rel="stylesheet"
		type="text/css">

	<rich:panel id="panel">
		<f:facet name="header">
			<h:panelGroup style="tituloPantalla">
				<h:outputText value="#{state.stateTitle}" />
				<a4j:commandLink action="switch" value="#{state.link}"
					immediate="true" reRender="panel" />
			</h:panelGroup>
		</f:facet>

		<rich:message for="action" />

		<h:panelGrid columns="3">
			<h:outputText value="username" />
			<h:inputText value="#{state.bean.name}" id="name" required="true">
				<f:validateLength minimum="3" maximum="12" />
			</h:inputText>
			<rich:message for="name" showSummary="false"
				styleClass="mensajeError" />
			<h:outputText value="password" />

			<h:inputSecret value="#{state.bean.password}" id="password"
				required="true">
				<f:validateLength minimum="5" maximum="12" />
			</h:inputSecret>
			<rich:message for="password" styleClass="mensajeError" />
			<h:outputText value="confirm" rendered="#{state.showConfirm}" />

			<h:inputSecret value="#{state.bean.confirmPassword}"
				rendered="#{state.showConfirm}" id="confirm" required="true">
				<f:validateLength minimum="5" maximum="12" />
			</h:inputSecret>
			<rich:message for="confirm" styleClass="mensajeError" />
		</h:panelGrid>
		<a4j:commandButton actionListener="#{state.action.listener}"
			action="#{state.action.ok}" value="#{state.okBtn}" id="action" />
	</rich:panel>
</f:view>