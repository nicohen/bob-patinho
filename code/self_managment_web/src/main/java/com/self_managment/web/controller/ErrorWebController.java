package com.self_managment.web.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("errorBean")
@Scope("request")
public class ErrorWebController {

    public String getStackTrace() {

	// Get the current JSF context
	FacesContext context = FacesContext.getCurrentInstance();
	Map<String, Object> requestMap = context.getExternalContext()
		.getRequestMap();

	// Fetch the exception
	Throwable ex = (Throwable) requestMap
		.get("javax.servlet.error.exception");

	// Create a writer for keeping the stacktrace of the exception
	StringWriter writer = new StringWriter();
	PrintWriter pw = new PrintWriter(writer);

	// Fill the stack trace into the write
	fillStackTrace(ex, pw);

	return writer.toString();
    }

    private void fillStackTrace(Throwable ex, PrintWriter pw) {
	if (null == ex)
	    return;

	ex.printStackTrace(pw);

	// The first time fillStackTrace is called it will always be a
	// ServletException
	if (ex instanceof ServletException) {
	    Throwable cause = ((ServletException) ex).getRootCause();

	    if (null != cause) {
		pw.println("Root Cause:");
		fillStackTrace(cause, pw);
	    } else {
		// Embedded cause
		if (null != cause) {
		    pw.println("Cause:");
		    fillStackTrace(cause, pw);
		}
	    }
	}
    }
}
