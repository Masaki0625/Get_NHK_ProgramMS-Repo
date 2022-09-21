package com.src.java.nhk.program.GetNHKProgramMS.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseEntity {
	
	@JsonProperty("list")
	public Object list;
	
	@JsonProperty("nowonair_list")
	public Object nowonair_list;
}
