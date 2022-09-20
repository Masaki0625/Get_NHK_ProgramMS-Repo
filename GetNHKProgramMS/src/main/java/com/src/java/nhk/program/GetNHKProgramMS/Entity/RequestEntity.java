package com.src.java.nhk.program.GetNHKProgramMS.Entity;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestEntity {

	private String area;
	
	private String service;
	
	private String date;
	
	private String apikey;
	
	private String genre;
	
	public String getarea() {
		return area;
	}
	
	public void setarea(String area) {
		this.area = area;
	}
	
	public String getservice() {
		return service;
	}
	
	public void setservice(String service) {
		this.service = service;
	}
	
	public String getdate() {
		return date;
	}
	
	public void setdate(String date) {
		this.date = date;
	}
	
	public String getapikey() {
		return apikey;
	}
	
	public void setapikey(String apikey) {
		this.apikey = apikey;
	}
	
	public String getgenre() {
		return genre;
	}
	
	public void setgenre(String genre) {
		this.genre = genre;
	}
}
