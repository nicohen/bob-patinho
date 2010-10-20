package com.self_managment.web.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class JSFUtil {
    public static void addErrorMessages(List<String> messages) {
	for (String message : messages) {
	    addErrorMessage(message);
	}
    }

    public static Object getBean(String name) {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(FacesContext.getCurrentInstance());

	return ctx.getBean(name);
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

    public static String getRequestParameter(String key) {
	return FacesContext.getCurrentInstance().getExternalContext()
		.getRequestParameterMap().get(key);
    }

    public static List<SelectItem> getSelectItems(List<?> entities) {
	List<SelectItem> items = new ArrayList<SelectItem>();
	for (Object x : entities) {
	    items.add(new SelectItem(x, x.toString()));
	}
	return items;
    }
}
