package com.src.java.nhk.program.GetNHKProgramMS.ResponseContent;

public class ResponseDetails {

	private String status;
	
	private String message;
	
	private String time;
	
	private Object successapiresponse;
	
	public ResponseDetails() {
		this.setStatus(new String());
		this.setMessage(new String());
		this.setTime(new String());
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getTime() {
		return time;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public Object getSuccessAPIResponse() {
		return successapiresponse;
	}
	
	public void setSuccessAPIResponse(Object successapiresponse) {
		this.successapiresponse = successapiresponse;
	}
}
