package com.src.java.nhk.program.GetNHKProgramMS.Resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic.GetNHKProgramGenreLogic;
import com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic.GetNHKProgramInfoLogic;
import com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic.GetNHKProgramLogic;
import com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic.GetNHKProgramNowOnAirLogic;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestBodyEntity;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestEntity;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class Resource {
	
	GetNHKProgramLogic getNHKProgramLogic = new GetNHKProgramLogic();
	
	GetNHKProgramGenreLogic getNHKProgramGenreLogic = new GetNHKProgramGenreLogic();
	
	GetNHKProgramInfoLogic getNHKProgramInfoLogic = new GetNHKProgramInfoLogic();
	
	GetNHKProgramNowOnAirLogic getNHKProgramNowOnAirLogic = new GetNHKProgramNowOnAirLogic();
	
	@POST
	@Path("/nhk/program")
	public Response GetNHKProgram(@BeanParam RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		return getNHKProgramLogic.Service(requestEntity, requestBodyEntity);
	}
	
	@POST
	@Path("/nhk/program/genre")
	public Response GetNHKGenre(@BeanParam RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		return getNHKProgramGenreLogic.Service(requestEntity, requestBodyEntity);
	}
	
	@POST
	@Path("/nhk/program/info")
	public Response GetNHKInfo(@BeanParam RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		return getNHKProgramInfoLogic.Service(requestEntity, requestBodyEntity);
	}
	
	@POST
	@Path("/nhk/program/onair")
	public Response GetNHKNowOnAir(@BeanParam RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		return getNHKProgramNowOnAirLogic.Service(requestEntity, requestBodyEntity);
	}

}
