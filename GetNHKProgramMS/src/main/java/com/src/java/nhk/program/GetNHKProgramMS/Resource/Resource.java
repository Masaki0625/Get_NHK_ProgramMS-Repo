package com.src.java.nhk.program.GetNHKProgramMS.Resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic.GetNHKProgramLogic;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestBodyEntity;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestEntity;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("")
public class Resource {
	
	GetNHKProgramLogic getNHKProgramLogic = new GetNHKProgramLogic();
	
	@POST
	@Path("/nhkprogram")
	public Response GetNHKProgram(@BeanParam RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		return getNHKProgramLogic.Service(requestEntity, requestBodyEntity);
	}

}
