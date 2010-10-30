package com.self_managment.web.util;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.springframework.security.BadCredentialsException;
import org.springframework.security.ui.AbstractProcessingFilter;

import com.sun.faces.util.MessageUtils;


public class LoginErrorPhaseListener implements PhaseListener {
    private static final long serialVersionUID = 1L;

    @Override
    public void beforePhase(final PhaseEvent arg0) {
	Exception e = (Exception) FacesContext
		.getCurrentInstance()
		.getExternalContext()
		.getSessionMap()
		.get(
			AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY);

	if (e instanceof BadCredentialsException) {
	    FacesContext
		    .getCurrentInstance()
		    .getExternalContext()
		    .getSessionMap()
		    .put(
			    AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY,
			    null);
	    JSFUtil.addErrorMessage(MessageUtils.getExceptionMessageString("error.badCredentials", new Object[]{}));
	}
    }

    @Override
    public void afterPhase(final PhaseEvent arg0) {
    }

    @Override
    public PhaseId getPhaseId() {
	return PhaseId.RENDER_RESPONSE;
    }

}