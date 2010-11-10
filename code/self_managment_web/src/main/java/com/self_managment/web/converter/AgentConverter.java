package com.self_managment.web.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Agent;
import com.self_managment.service.AgentService;

public class AgentConverter implements Converter {

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
	    String string) {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(facesContext);

	AgentService agentService = (AgentService) ctx
		.getBean("agentService");

	try {
	    List<Agent> l = agentService.findAllByProperty("name",
		    string.split(", ")[0]);
	    return l.isEmpty() ? null : l.get(0);
	} catch (NumberFormatException e) {
	    return null;
	}
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	if (arg2 == null)
	    return null;
	return arg2.toString();
    }

}
