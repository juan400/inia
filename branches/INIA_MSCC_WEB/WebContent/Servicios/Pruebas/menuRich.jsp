<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<f:view>
<div class="logo"></div>
	<h:form id="globalForm">
		<div class="top"> 
			<rich:toolBar styleClass="toolbar" itemSeparator="grid">
	            <rich:toolBarGroup>
                  <h:outputLabel value="#{text.menu_goal}  ::" />
	              <h:commandButton id="listGoals" action="#{menuBean.listado}" image="/images/icons/page.png"/>
	              <h:outputLabel value="#{text.menu_list}" for="listGoals" />         
	           	</rich:toolBarGroup>
	           	
	           	<rich:toolBarGroup>
	              <h:outputLabel value="#{text.menu_catalog} ::" />
	              <h:commandButton id="listQuality" action="#{menuBean.modelo}" image="/images/icons/table_multiple.png"/>
	              <h:outputLabel value="#{text.menu_qualityList}" for="listQuality" />         
            	</rich:toolBarGroup>	

                <rich:toolBarGroup>
              		<h:outputLabel value="#{text.menu_objects} ::" />
              		<h:commandButton id="listObjects" action="#{menuBean.objetos}" image="/images/icons/database.png"/>
              		<h:outputLabel value="#{text.menu_objectList}" for="listObjects" />         
            	</rich:toolBarGroup>
           
	            <rich:toolBarGroup location="right"  itemSeparator="grid">
	              <h:outputLabel  rendered="#{loginBean.logged}" value="#{text.login_userName} : #{loginBean.nombre}"/>
	              <h:commandButton rendered="#{loginBean.logged}" action="#{loginBean.logout}" label="#{text.login_logout}" image="/images/icons/door_out.png"/> 
	              <h:commandButton rendered="#{!loginBean.logged}" action="#{loginBean.login}" label="#{text.login_login}" image="/images/icons/door_in.png"/>
	            </rich:toolBarGroup>
       		</rich:toolBar>
       </div>
		<h:panelGroup rendered="#{objetivoBean.init}"/>
		<h:panelGrid headerClass="tituloPantalla">
			<f:facet name="header">
				<h:outputText value="#{text.newGoal_title}" />
			</f:facet>
			<h:panelGroup rendered="#{!objetivoBean.logged}">
				<h:outputText styleClass="mensajeError" value="#{text.login_notLogged}"/>
			</h:panelGroup>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>