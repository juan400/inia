  <!doctype html public "-//w3c//dtd html 4.0 transitional//en">
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<!-- RichFaces tag library declaration -->
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<html>
<head>
<title>Login Page</title> 
</head>
<body>
<f:view>
<center>

<h:panelGrid width="380px" columns="2" border="0">

<rich:panel header="Login" style="background-color: #ebf3fd;">



<h:panelGroup>
    <form action="j_security_check"   method="POST">
        <h:panelGrid>
            <h:inputText id="j_username" required="true" value="" size="12"/>
            <h:inputSecret id="j_password" required="true" size="12"/>
        </h:panelGrid>
        <h:commandButton type="submit" value="login"/>
    </form>
</h:panelGroup>






</rich:panel>
</h:panelGrid>
</center>
</f:view> 
</body>
</html>