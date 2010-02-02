<rich:panelMenu styleClass="menu" style="width:200px"
												mode="server" iconExpandedGroup="disc"
												iconCollapsedGroup="disc" iconExpandedTopGroup="chevronUp"
												iconGroupTopPosition="right"
												iconCollapsedTopGroup="chevronDown">
												<rich:panelMenuGroup label="Seguridad"
													styleClass="textoMenu">
													<rich:panelMenuItem action='irLogin'>
														<h:outputLabel styleClass="textoMenuSecundario"
															rendered="#{loginBean.logged}"
															value="#{text.login_userName} : #{loginBean.loginName}" />
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoMenuSecundario"
															rendered="#{loginBean.logged}"
															action="#{loginBean.logout}" value="#{text.login_logout}"
															image="../Recursos/Imagenes/Iconos/door_out.png" />
														<a4j:commandButton
															style="font-size: 10pt; color: #2d77c2;"
															styleClass="textoMenuSecundario"
															rendered="#{!loginBean.logged}"
															action="#{loginBean.login}" value="#{text.login_login}"
															image="../Recursos/Imagenes/Iconos/door_in.png" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Datos de su cuenta"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG004.jsp" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Dar de baja su cuenta"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG005.jsp" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Cambiar su contraseña"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG007.jsp" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Item 2.5" rendered="#{panelMenu.updateCurrent}"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG007.jsp" />
													</rich:panelMenuItem>
												</rich:panelMenuGroup>
												<rich:panelMenuGroup label="Adminstracion"
													styleClass="textoMenu">
													<rich:panelMenuItem label="Precentacion"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/Precentacion.jsp" />
													</rich:panelMenuItem>
													<rich:panelMenuItem label="Modificar datos personales"
														styleClass="textoMenuSecundario"
														action="#{panelMenu.updateCurrent}">
														<f:param name="current" value="/Servicios/SEG/SEG004.jsp" />
													</rich:panelMenuItem>
												</rich:panelMenuGroup>
											</rich:panelMenu>