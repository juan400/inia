<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j" %>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>

<style>
    .pic {
        margin-bottom: -4px;
        margin-right: 2px;
    }
    </style>
<f:subview id="menu">
<f:loadBundle basename="com.bean.text" var="text" />
<h:form id="menuForm">

        <rich:toolBar >

            <rich:dropDownMenu id="ddmPrincipal">
                <f:facet name="label"> 
                    <h:panelGroup>
                        <h:graphicImage value="/images/icons/copy.gif" styleClass="pic"/>
                        <h:outputText value="ADM"/>
                    </h:panelGroup>
                </f:facet>
                <rich:menuItem submitMode="ajax" value="New"
                    action="#{ddmenu.doNew}" icon="/images/icons/create_doc.gif">
                </rich:menuItem>
                <rich:menuItem submitMode="ajax" value="Open"
                    action="#{ddmenu.doOpen}" icon="/images/icons/open.gif" />
                <rich:menuGroup value="Save As...">
                    <rich:menuItem submitMode="ajax" value="Save" 
                        action="#{ddmenu.doSave}" icon="/images/icons/save.gif" />
                    <rich:menuItem submitMode="ajax" value="Save All"
                        action="#{ddmenu.doSaveAll}">
                        <f:facet name="icon">
                            <h:graphicImage value="/images/icons/save_all.gif" />
                        </f:facet>
                    </rich:menuItem>
                </rich:menuGroup>
                <rich:menuItem submitMode="ajax" value="Close"
                    action="#{ddmenu.doClose}" />
                <rich:menuSeparator id="menuSeparator11" />
                <rich:menuItem submitMode="ajax" value="Exit"
                    action="#{ddmenu.doExit}" />

            </rich:dropDownMenu>

            <rich:dropDownMenu>

                <f:facet name="label">
                    <h:panelGrid cellpadding="0" cellspacing="0" columns="2"
                        style="vertical-align:middle">
                        <h:outputText value="Links" />
                    </h:panelGrid>
                </f:facet>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='http://labs.jboss.com/jbossrichfaces/'">
                    <h:outputLink value="http://labs.jboss.com/jbossrichfaces/">
                        <h:outputText value="RichFaces Home Page"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

                <rich:menuItem submitMode="none"
                    onclick="document.location.href='http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261'">
                    <h:outputLink
                        value="http://jboss.com/index.html?module=bb&amp;op=viewforum&amp;f=261">
                        <h:outputText value="RichFaces Forum"></h:outputText>
                    </h:outputLink>
                </rich:menuItem>

            </rich:dropDownMenu>
        </rich:toolBar>
    </h:form>
    <rich:spacer width="1" height="5"/>
    <br />
    <a4j:outputPanel ajaxRendered="true">
        <h:outputText value="Current Selection: "></h:outputText>
        <h:outputText style="font-weight:bold" value="#{maestroBean.islogged}"></h:outputText>
    </a4j:outputPanel>
    <br />
    <rich:spacer width="1" height="25" />
</f:subview>