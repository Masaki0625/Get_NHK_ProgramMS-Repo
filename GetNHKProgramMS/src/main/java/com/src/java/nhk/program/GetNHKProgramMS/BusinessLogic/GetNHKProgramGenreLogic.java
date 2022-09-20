package com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;

import javax.enterprise.context.RequestScoped;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestBodyEntity;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.RequestEntity;
import com.src.java.nhk.program.GetNHKProgramMS.Entity.ResponseEntity;
import com.src.java.nhk.program.GetNHKProgramMS.ResponseContent.ResponseCommon;
import com.src.java.nhk.program.GetNHKProgramMS.ResponseContent.ResponseDetails;
import com.src.java.nhk.program.GetNHKProgramMS.Util.StringUtil;

@RequestScoped
public class GetNHKProgramGenreLogic {
	
	StringUtil stringUtil = new StringUtil();
	
	RequestEntity requestEntity = new RequestEntity();
	
	RequestBodyEntity requestBodyEntity = new RequestBodyEntity();
	
	ResponseDetails responseDetails = new ResponseDetails();
	
	ResponseEntity responseEntity = new ResponseEntity();
	
	private static Logger log;
	
	//コンストラクタ
	public GetNHKProgramGenreLogic() {
		log = LogManager.getLogger(GetNHKProgramGenreLogic.this);
	}

	public Response ServiceResponse(ResponseCommon responseCommon) {
		responseDetails.setStatus(responseCommon.getResponseStatus());
		responseDetails.setMessage(responseCommon.getResponseMessage());
		responseDetails.setTime(LocalDateTime.now().toString());
		return Response.status(responseCommon.getStatus()).entity(responseDetails).build();
	}
	
	public Response Service(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		ResponseCommon responseCommon = ResponseCommon.S200;
		
		Response response = CheckRequestBody(requestEntity, requestBodyEntity);
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			log.info("Failed Execute CheckRequestBody Method");
			return response;
		}else {
			log.info("Success Execute CheckRequestBody Method");
		}
		
		response = GetNHKGenre(requestEntity, requestBodyEntity);
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			log.info("Failed Execute GetNHKGenre Method");
			return response;
		}else {
			log.info("Success Execute GetNHKGenre Method");
		}
		
		responseDetails.setStatus(responseCommon.getResponseStatus());
		responseDetails.setMessage(responseCommon.getResponseMessage());
		responseDetails.setTime(LocalDateTime.now().toString());
		responseDetails.setSuccessAPIResponse(responseEntity.list);
		return Response.status(responseCommon.getStatus()).entity(responseDetails).build();
	}
	
	/**
	 * 
	 * @param requestEntity：入力オブジェクトの格納用
	 * @param requestBodyEntity：入力オブジェクトの格納用
	 * @return
	 * 実行成功：200 OK
	 * 実行失敗：400 BAD_REQUEST
	 * 
	 * 説明
	 * 入力パラメータのnullチェック
	 */
	private Response CheckRequestBody(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		long startTime = System.currentTimeMillis();
		
		if(stringUtil.isNullorEmpty(requestBodyEntity.getarea()) ||
		   stringUtil.isNullorEmpty(requestBodyEntity.getdate()) ||
		   stringUtil.isNullorEmpty(requestBodyEntity.getservice()) ||
		   stringUtil.isNullorEmpty(requestBodyEntity.getgenre()) ||
		   stringUtil.isNullorEmpty(requestBodyEntity.getapikey())) {
			return ServiceResponse(ResponseCommon.E400);
		}else {
			log.info("Parameters are Normal");
		}
		
		long endTime = System.currentTimeMillis();
		log.info("Processing Time: " + (endTime - startTime) + " ms");
		return Response.status(Response.Status.OK.getStatusCode()).entity(responseDetails).build();
	}
	
	/**
	 * 
	 * @param requestEntity：入力オブジェクトの格納用
	 * @param requestBodyEntity：入力オブジェクトの格納用
	 * @return
	 * 実行成功：200 OK
	 * 実行失敗：500 INTERNA+_SERVER_ERROR
	 * 
	 *  説明
	 *  NHKの番組ジャンル取得APIへ接続し入力されたJSONパラメータ値をリクエストURLの各値へ代入し、リクエスト。
	 *  レスポンスオブジェクトでJSONレスポンスの「list」を全数取得し、リクエスト元へ返却。
	 */
	private Response GetNHKGenre(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		long startTime = System.currentTimeMillis();
		
		try {
			URL connectUrl = new URL("https://api.nhk.or.jp/v2/pg/genre/" + requestBodyEntity.getarea() + "/" + requestBodyEntity.getservice() + "/" + requestBodyEntity.getgenre() + "/" + requestBodyEntity.getdate() + ".json?key=" + requestBodyEntity.getapikey());
			
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connectUrl.openConnection();
			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			
			/**
			 * public interface TrustManager
			 * 
			 * JSSE信頼マネージャの基本インタフェースです。
			 * 
			 * TrustManagerは、信頼を判断するときに私用される信頼データを管理し、ピアが提出した資格を受け入れるかどうかを判断します。
			 * TrustManagerは、TrustManagerFactoryを使用するか、またはTrustManagerのいずれかのサブクラスを実装することによって作成されます。
			 */
			TrustManager[] trustManager = { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                	
                }
                
                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                	
                }
			} 
			};
			
			SSLContext sslcontext = SSLContext.getInstance("SSL");
            sslcontext.init(null, trustManager, null);
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            
            httpsURLConnection.setSSLSocketFactory(sslcontext.getSocketFactory());
            httpsURLConnection.connect();
            
            PrintStream printStream = new PrintStream(httpsURLConnection.getOutputStream());
    		printStream.close();
    		
    		 BufferedReader reader = new BufferedReader(new InputStreamReader(
    				 httpsURLConnection.getInputStream(), "utf8"));
             String line;
             StringBuilder sb = new StringBuilder();
             while ((line = reader.readLine()) != null) {
            	 sb.append(line);
             }
             ObjectMapper mapper = new ObjectMapper(); 
             
             //NHKプログラム取得APIのレスポンスパラメータ値「"list"」を取得し、Objectクラスへ格納
             try {
            	 responseEntity = mapper.readValue(sb.toString(), ResponseEntity.class);
               } catch (JsonProcessingException jsonProcessingException) {
                 log.error(jsonProcessingException.getStackTrace());
                 return ServiceResponse(ResponseCommon.E500);
               }
             
             //BufferedReaderをクローズ
             reader.close();
             //コネクション切断
             httpsURLConnection.disconnect();
		}catch(Exception exception) {
			log.error(exception.getStackTrace());
			return ServiceResponse(ResponseCommon.E500);
		}
		
		long endTime = System.currentTimeMillis();
		log.info("Processing Time: " + (endTime - startTime) + " ms");
		return Response.status(Response.Status.OK.getStatusCode()).entity(responseDetails).build();
	}
}
