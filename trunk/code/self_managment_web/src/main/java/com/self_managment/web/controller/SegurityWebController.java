package com.self_managment.web.controller;

import java.util.Iterator;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.jsf.FacesContextUtils;


import com.self_managment.model.entity.Segurity;
import com.self_managment.model.entity.Supervisor;
import com.self_managment.service.CampaignService;
import com.self_managment.service.SegurityService;



@Component("segurityBean")
@Scope("session")
	public class SegurityWebController {
	private List<Segurity> seguritys;
	  private Segurity segurity = new Segurity();
	  private SegurityService service;
	  
	  
	  public SegurityWebController() {
			ApplicationContext ctx = FacesContextUtils
			.getWebApplicationContext(FacesContext.getCurrentInstance());
			service = (SegurityService) ctx.getBean("SegurityService");
	  }
	
	  
	  
	  
	 
		private String usuario = new String("Juan");
		private String clave;
		
		public void setUsuario( String usuario) {
			this.usuario = usuario;
		}
		
		public String getUsuario() {
			return usuario;
		}
		
		public void setClave( String clave ) {
			this.clave = clave;
		}
		
		public String getClave() {
			return clave;
		}
		
		public List<Segurity> getsegurity() {
			if (seguritys == null)
			    seguritys = service.findAll();
			
			return seguritys;
		    }
		
		public String getValidacion() {
			seguritys = service.findAll();
			Iterator it = seguritys.iterator();
			while (it.hasNext())
			{
			   Segurity segurity = (Segurity)it.next();
			
			   //ya tenemos un objeto usuarios
				if ( usuario.equals( segurity.getUser()) && clave.equals(segurity.getClave()))
				{
					System.out.println("Si");
					return "si";
				}
				else
				{
					

					System.out.println("No");
					return "no";
				}
			}
			
			return "no";
		
		}
	}
