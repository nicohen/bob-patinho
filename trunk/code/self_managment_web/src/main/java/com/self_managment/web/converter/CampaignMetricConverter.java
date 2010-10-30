package com.self_managment.web.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

import com.self_managment.model.entity.CampaignMetric;

public class CampaignMetricConverter implements Converter {

    @SuppressWarnings("unchecked")
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component,
	    String string) {

	List<SelectItem> selectItems = retrieveSelectItems(component);
	CampaignMetric selected = null;
	for (SelectItem item : selectItems) {
	    selected = (CampaignMetric) item.getValue();
	    if (string.equals(selected.getMetric().getCode()))
		return selected;
	}
	return null;

    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	if (arg2 == null)
	    return null;
	return ((CampaignMetric) arg2).getMetric().getCode();
    }

    @SuppressWarnings("unchecked")
    private List<SelectItem> retrieveSelectItems(UIComponent uiComponent) {
	List<SelectItem> items = null;
	if (!uiComponent.getChildren().isEmpty()
		&& uiComponent.getChildren().get(0) instanceof UISelectItems) {
	    items = (List<SelectItem>) ((UISelectItems) uiComponent
		    .getChildren().get(0)).getValue();
	}
	return items;
    }

}
