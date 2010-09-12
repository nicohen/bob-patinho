package com.self_managment.web.util;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class JSFUtil {
    public static void addErrorMessages(List<String> messages) {
	for (String message : messages) {
	    addErrorMessage(message);
	}
    }

    public static void addErrorMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
		msg, msg);
	FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
	FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
		msg, msg);
	FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }
}
