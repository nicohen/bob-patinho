package com.self_managment.web.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component("menuBean")
@Scope("request")
public class MenuWebController {
	
	private String username = "";
	private String role = "";
	private String loginDescription = "";
	public static final String ACCOUNT_MANAGER = "AccountManager";
	public static final String SUPERVISOR = "Supervisor";
	public static final String AGENT = "Agent";
	
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof UserDetails) {
			username = ((UserDetails) obj).getUsername();
		}
		return username;
	}

	public String getRole() {
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (obj instanceof UserDetails) {
			GrantedAuthority[] authorities = ((UserDetails) obj).getAuthorities();
			for(int i=0; i<authorities.length; i++) {
				if(authorities[i].equals(ACCOUNT_MANAGER)) {
					return "Jefe de cuentas";
				} else if(authorities[i].equals(SUPERVISOR)) {
					return "Supervisor";
				} else if(authorities[i].equals(AGENT)) {
					return "Agente";
				}
			}
		}
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setLoginDescription(String loginDescription) {
		this.loginDescription = loginDescription;
	}

	public String getLoginDescription() {
		if (!"".equals(username)) {
			return "Usuario: " + username + " | Rol: " + role + " | ";
		} else {
			return "";
		}
	}
	
}