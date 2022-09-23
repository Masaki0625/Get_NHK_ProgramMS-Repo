package com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.ws.rs.core.Response;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestBodyEntity;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestEntity;
import com.src.java.nhk.program.GetNHKProgramMS.ResponseContent.ResponseDetails;

public class GetNHKProgramInfoLogicTest {
	
	@Mock
	URL url;
	
	@Mock
	HttpsURLConnection httpsURLConnection;
	
	@InjectMocks
	GetNHKProgramInfoLogic target = new GetNHKProgramInfoLogic();
	
	@Test
	public void testService_OK() throws IOException {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(url.openConnection()).thenReturn(httpsURLConnection);
		
		RequestEntity requestEntity = new RequestEntity();
		RequestBodyEntity requestBodyEntity = new RequestBodyEntity();
		
		requestBodyEntity.setarea("011");
		requestBodyEntity.setservice("e4");
		requestBodyEntity.setdate("2022-09-21");
		requestBodyEntity.setapikey("d1BPMkcyWHlNT3JNb3JibjJxMkoyRFNpY2VBUTBUSHI=");
		
		Response response = target.Service(requestEntity, requestBodyEntity);
		ResponseDetails responseDetails = (ResponseDetails) response.getEntity();
		assertEquals(responseDetails.getStatus(), "S200");
		assertEquals(responseDetails.getMessage(), "OK");
	}

}
