package com.self_managment.web.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Component("menuBean")
@Scope("session")
public class MenuWebController {
	
	private String username = "";
	private String role = "";
	public static final String ACCOUNT_MANAGER = "AccountManager";
	public static final String SUPERVISOR = "Supervisor";
	public static final String AGENT = "Agent";
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
	    	if (StringUtils.isBlank(username)) {
        		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        		if (obj instanceof UserDetails) {
        			username = ((UserDetails) obj).getUsername();
        		}
	    	}
		return username;
	}

	public String getRole() {
	    	if (StringUtils.isBlank(role)) {
        		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        		if (obj instanceof UserDetails) {
        			GrantedAuthority[] authorities = ((UserDetails) obj).getAuthorities();
        			for(int i=0; i<authorities.length; i++) {
        				if(authorities[i].equals(ACCOUNT_MANAGER)) {
        					role = "Jefe de cuentas";
        					return role;
        				} else if(authorities[i].equals(SUPERVISOR)) {
        					role = "Supervisor";
        					return role;
        				} else if(authorities[i].equals(AGENT)) {
        					role = "Agente";
        					return role;
        				}
        			}
        		}
	    	}
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoginDescription() {
		if (!"".equals(getUsername())) {
			return "Usuario: " + getUsername() + " | Rol: " + getRole() + " | ";
		} else {
			return "";
		}
	}
	
}