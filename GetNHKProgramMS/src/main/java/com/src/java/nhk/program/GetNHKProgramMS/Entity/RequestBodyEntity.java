package com.src.java.nhk.program.GetNHKProgramMS.Entity;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.HeaderParam;

@RequestScoped
public class RequestBodyEntity {
	
	@HeaderParam("area")
	private String area;
	
	@HeaderParam("service")
	private String service;
	
	@HeaderParam("date")
	private String date;
	
	@HeaderParam("apikey")
	private String apikey;
	
	@HeaderParam("genre")
	private String genre;
	
	@HeaderParam("id")
	private String id;
	
	public void setarea(String area) {
		this.area = area;
	}
	
	public String getarea() {
		return this.area;
	}
	
	public void setservice(String service) {
		this.service = service;
	}
	
	public String getservice() {
		return this.service;
	}
	
	public void setdate(String date) {
		this.date = date;
	}
	
	public String getdate() {
		return this.date;
	}
	
	public void setapikey(String apikey) {
		this.apikey = apikey;
	}
	
	public String getapikey() {
		return this.apikey;
	}

	public void setgenre(String genre) {
		this.genre = genre;
	}
	
	public String getgenre() {
		return this.genre;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	
	public String getid() {
		return this.id;
	}
}
