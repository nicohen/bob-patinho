package com.self_managment.web.controller;

import java.io.IOException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("loginBean")
@Scope("request")
public class LoginWebController {

    private String username = "";
    private String password = "";
    private boolean rememberMe = false;
    private boolean loggedIn = false;

    public String doLogin() throws IOException, ServletException {
	FacesContext jsf = FacesContext.getCurrentInstance();
	jsf.getExternalContext().dispatch("/j_spring_security_check");
	jsf.responseComplete();
	return null;

    }

    public String getUsername() {
	return this.username;
    }

    public void setUsername(final String username) {
	this.username = username;
    }

    public String getPassword() {
	return this.password;
    }

    public void setPassword(final String password) {
	this.password = password;
    }

    public boolean isRememberMe() {
	return this.rememberMe;
    }

    public void setRememberMe(final boolean rememberMe) {
	this.rememberMe = rememberMe;
    }

    public boolean isLoggedIn() {
	return this.loggedIn;
    }

    public void setLoggedIn(final boolean loggedIn) {
	this.loggedIn = loggedIn;
    }
}
