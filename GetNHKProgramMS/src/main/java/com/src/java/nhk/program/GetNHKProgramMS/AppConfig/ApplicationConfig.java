package com.src.java.nhk.program.GetNHKProgramMS.AppConfig;

import java.util.Set;
import java.util.HashSet;
import javax.ws.rs.ApplicationPath;

import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class ApplicationConfig extends Application{
	
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(com.src.java.nhk.program.GetNHKProgramMS.Resource.Resource.class);
		return classes;
	}
}
