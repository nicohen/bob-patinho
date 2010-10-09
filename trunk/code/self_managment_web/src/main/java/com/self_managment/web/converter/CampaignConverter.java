package com.self_managment.web.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

import com.self_managment.model.entity.Campaign;
import com.self_managment.service.CampaignService;

public class CampaignConverter implements Converter {

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
	    String string) {
	ApplicationContext ctx = FacesContextUtils
		.getWebApplicationContext(facesContext);

	CampaignService campaignService = (CampaignService) ctx
		.getBean("campaignService");

	try {
	    List<Campaign> l = campaignService.findAllByProperty("id", Integer
		    .parseInt(string.split(" - ")[0]));
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
