package com.src.java.nhk.program.GetNHKProgramMS.BusinessLogic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.Base64;

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
public class GetNHKProgramInfoLogic {
	
	//StringUtilクラスをインスタンス化
	StringUtil stringUtil = new StringUtil();
	
	//ResponseDetailsクラスをインスタンス化
	ResponseDetails responseDetails = new ResponseDetails();
	
	//ResponseEntityクラスをインスタンス化
	ResponseEntity responseEntity = new ResponseEntity();
	
	private static Logger log;
	
	//コンストラクタ
	public GetNHKProgramInfoLogic() {
		log = LogManager.getLogger(GetNHKProgramInfoLogic.this);
	}
	
	public Response ServiceResponse(ResponseCommon responseCommon) {
		responseDetails.setStatus(responseCommon.getResponseStatus());
		responseDetails.setMessage(responseCommon.getResponseMessage());
		responseDetails.setTime(LocalDateTime.now().toString());
		return Response.status(responseCommon.getStatus()).entity(responseDetails).build();
	}
	
	public Response Service(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity) {
		ResponseCommon responseCommon = ResponseCommon.S200;
		
		StringBuilder apiKey = new StringBuilder();
		
		Response response = CheckRequestBody(requestEntity, requestBodyEntity);
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			log.info("Failed Execute CheckRequestBody Method");
			return response;
		}else {
			log.info("Success Execute CheckRequestBody Method");
		}
		
		response = APIKeyDecoder(requestEntity, requestBodyEntity, apiKey);
		
		response = GetNHKInfo(requestEntity, requestBodyEntity, apiKey);
		if(response.getStatus() != Response.Status.OK.getStatusCode()) {
			log.info("Failed Execute GetNHKInfo Method");
			return response;
		}else {
			log.info("Success Execute GetNHKInfo Method");
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
		   stringUtil.isNullorEmpty(requestBodyEntity.getid()) ||
		   stringUtil.isNullorEmpty(requestBodyEntity.getservice()) ||
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
	 * @param apiKey：リクエストで受け付けたAPIKey
	 * @return
	 * 実行成功：200 OK
	 * 
	 * 説明
	 * エンコードされたAPIKeyのデコード
	 */
	private Response APIKeyDecoder(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity, StringBuilder apiKey) {
		long startTime = System.currentTimeMillis();
		
		String decodeAPIKey = new String(Base64.getDecoder().decode(requestBodyEntity.getapikey()));
		
		apiKey.append(decodeAPIKey);
		
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
	 * 実行失敗：
	 * 304 NOT_MODIFIED
	 * 400 BAD_REQUEST
	 * 401 UNAUTHORIZED
	 * 403 FORBIDDEN
	 * 404 NOT_FOUND
	 * 500 INTERNA+_SERVER_ERROR
	 * 503 SERVICE_UNAVAILABLE
	 * 
	 *  説明
	 *  NHKの番組ジャンル取得APIへ接続し入力されたJSONパラメータ値をリクエストURLの各値へ代入し、リクエスト。
	 *  レスポンスオブジェクトでJSONレスポンスの「list」を全数取得し、リクエスト元へ返却。
	 */
	private Response GetNHKInfo(RequestEntity requestEntity, RequestBodyEntity requestBodyEntity, StringBuilder apiKey) {
		long startTime = System.currentTimeMillis();
		
		try {
			URL connectUrl = new URL("https://api.nhk.or.jp/v2/pg/info/" + requestBodyEntity.getarea() + "/" + requestBodyEntity.getservice() + "/" + requestBodyEntity.getid() + ".json?key=" + apiKey.toString());
			
			HttpsURLConnection httpsURLConnection = (HttpsURLConnection) connectUrl.openConnection();
			httpsURLConnection.setRequestMethod("GET");
			httpsURLConnection.setDoInput(true);
			httpsURLConnection.setDoOutput(true);
			httpsURLConnection.setConnectTimeout(12000);
			
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
    		
    		//ベンダー提供APIレスポンスエラーハンドリング
    		if(httpsURLConnection.getResponseCode() == 304) {
    			/**
    			 * 304 Not Modified アクセスされたリソースが更新されていない場合に返されます。
    			 * ログレベル：info
    			 */
    			log.info("NHK HTTP Status Code 304 Not Modified: Accessed resource not updated");
    			return ServiceResponse(ResponseCommon.E304);
    		}else if(httpsURLConnection.getResponseCode() == 400) {
    			/**
    			 * 400 Bad Request パラメータがAPIで期待されたものと一致しない場合に返されます。
    			 * ログレベル：info
    			 */
    			log.info("NHK HTTP Status Code 400 Bad Request: Parameters do not match what is expected by the API");
    			return ServiceResponse(ResponseCommon.E400);
    		}else if(httpsURLConnection.getResponseCode() == 401) {
    			/**
    			 * 401 Unauthorized 許可されていないアクセスであった場合に返されます。
    			 * ログレベル：info
    			 */
    			log.info("NHK HTTP Status Code 401 Unauthorized: was unauthorized access");
    			return ServiceResponse(ResponseCommon.E401);
    		}else if(httpsURLConnection.getResponseCode() == 403) {
    			/**
    			 * 403 Forbidden リソースへのアクセスを許されていないか、利用制限を超えている場合に適用されます。
    			 * ログレベル：info
    			 */
    			log.info("NHK HTTP Status Code 403 Forbidden: Not allowed to access resource or usage limit exceeded");
    			return ServiceResponse(ResponseCommon.E403);
    		}else if(httpsURLConnection.getResponseCode() == 404) {
    			/**
    			 * 404 Not Found 指定されたリソースが見つからない場合に返されます。
    			 * ログレベル：info
    			 */
    			log.info("NHK HTTP Status Code 404 Not Found: Specified resource not found");
    			return ServiceResponse(ResponseCommon.E404);
    		}else if(httpsURLConnection.getResponseCode() == 500) {
    			/**
    			 * 500 Internal Server Error 内部的な問題によってデータを返すことができない場合に返されます。
    			 * ログレベル：fatal
    			 * 
    			 * 影響度：致命的
    			 * 対処法：なし
    			 */
    			log.fatal(requestBodyEntity.getapikey() + ": " + "NHK HTTP Status Code 500 Internal Server Error: Data cannot be returned due to an internal problem");
    			return ServiceResponse(ResponseCommon.E500);
    		}else if(httpsURLConnection.getResponseCode() == 503) {
    			/**
    			 * 503 Service Unavailable 内部的な問題によってデータを返すことができない場合に返されます。
    			 * ログレベル：fatal
    			 * 
    			 * 影響度：致命的
    			 * 対処法：なし
    			 */
    			log.fatal(requestBodyEntity.getapikey() + ": " + "NHK HTTP Status Code 503 Service Unavailable: Data cannot be returned due to an internal problem");
    			return ServiceResponse(ResponseCommon.E503);
    		}
    		
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
                 log.error(jsonProcessingException);
                 return ServiceResponse(ResponseCommon.E500);
               }
             
             //BufferedReaderをクローズ
             reader.close();
             //コネクション切断
             httpsURLConnection.disconnect();
		}catch(Exception exception) {
			log.error(exception);
			return ServiceResponse(ResponseCommon.E500);
		}
		
		long endTime = System.currentTimeMillis();
		log.info("Processing Time: " + (endTime - startTime) + " ms");
		return Response.status(Response.Status.OK.getStatusCode()).entity(responseDetails).build();
	}
}
