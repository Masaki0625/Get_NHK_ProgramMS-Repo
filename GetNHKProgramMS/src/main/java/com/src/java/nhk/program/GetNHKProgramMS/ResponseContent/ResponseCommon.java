package com.src.java.nhk.program.GetNHKProgramMS.ResponseContent;

import javax.ws.rs.core.Response;

public enum ResponseCommon {
	
	S200(Response.Status.OK.getStatusCode(), "200", "OK"),
	E304(Response.Status.NOT_MODIFIED.getStatusCode(), "304", "NOT_MODIFIED"),
	E400(Response.Status.BAD_REQUEST.getStatusCode(), "400", "BAD_REQUEST"),
	E401(Response.Status.UNAUTHORIZED.getStatusCode(), "401", "UNAUTHORIZED"),
	E403(Response.Status.FORBIDDEN.getStatusCode(), "403", "FORBIDDEN"),
	E404(Response.Status.NOT_FOUND.getStatusCode(), "404", "NOT_FOUND"),
	E500(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "500", "INTERNAL_SERVER_ERROR"),
	E503(Response.Status.SERVICE_UNAVAILABLE.getStatusCode(), "503", "SERVICE_UNAVAILABLE");
	
	private int Status;
	
	private String ResponseStatus;
	
	private String ResponseMessage;
	
	private ResponseCommon(int Status, String ResponseStatus, String ResponseMessage) {
		this.Status = Status;
		this.ResponseStatus = ResponseStatus;
		this.ResponseMessage = ResponseMessage;
	}
	
	public int getStatus() {
		return Status;
	}
	
	public String getResponseStatus() {
		return ResponseStatus;
	}
	
	public String getResponseMessage() {
		return ResponseMessage;
	}

}
