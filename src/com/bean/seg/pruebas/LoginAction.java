package com.bean.seg.pruebas;

import javax.faces.event.ActionEvent;

public class LoginAction
{
    
    private Bean bean; 
    
    public void listener(ActionEvent event) {
        //fetching some data on login
    }
    
    public String ok() {
        return "loggedIn";
    }

    public Bean getBean() {
        return bean;
    }

    public void setBean(Bean bean) {
        this.bean = bean;
    }
}   
