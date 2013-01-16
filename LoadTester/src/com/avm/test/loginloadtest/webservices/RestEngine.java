package com.avm.test.loginloadtest.webservices;



import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONObject;

import com.avm.test.loginloadtest.Consts;
import com.avm.test.loginloadtest.http.HttpUtils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;



public class RestEngine {
	private final static String TAG = RestEngine.class.getSimpleName();
	
	public static final String ACTION_UPLOAD_PROGRESS = "RE_ACTION_UPLOAD_PROGRESS";
	public static final String ACTION_FILE_START= "RE_ACTION_FILE_START";
	public static final String ACTION_FILE_COMPLETE = "RE_ACTION_FILE_COMPLETE";	
	
	
	private static LocalBroadcastManager mLBM = null;
	
	private static TimerTask heartbeatTask = null;
	private static Timer heartbeatTimer = null;
	private static long totalBytesTransferred = 0;
	
	public static String callAuthService(String serviceURL, String userName,
			String pwd) throws Exception {

		Boolean ssl = false;
		URL url = null;
		InputStream in = null;
		HttpURLConnection urlConnection = null;
		StringBuffer buff = new StringBuffer("");
		String response = "";

		try {

			if (serviceURL.contains("https:")) {
				ssl = true;
			}// end if ssl

			url = new URL(serviceURL);

			if (ssl) {
				urlConnection = (HttpsURLConnection) url.openConnection();
			}// end if call secure
			else {
				urlConnection = (HttpURLConnection) url.openConnection();
			}// end else call non secure

			// Connection Params
			urlConnection.setRequestProperty("Authorization",
					HttpUtils.getAuthHeader(userName, pwd));
			urlConnection.setConnectTimeout(Consts.TIMEOUT_CONNECTION);
			
			// Opens the stream to the server
			in = urlConnection.getInputStream();

			// start listening to the stream
			Scanner inStream = new Scanner(in);

			// process the stream and store it in StringBuilder
			while (inStream.hasNextLine()) {
				buff.append(inStream.nextLine());
			}// end while

			response = buff.toString();

		}
		catch(Exception ex){
			throw ex;
		}//end catch

		finally {
			try {
				if(in!=null){
				in.close();
				}//end if not null
				urlConnection.disconnect();
				buff = null;
			} catch (Exception e) {

			}// end catch
		}// end finally
			// response, need to be parsed
		return response;

	}// end callSecureService

	

	public static String callService(String serviceURL, String token)
			throws Exception {
			
			Boolean ssl = false;
			URL url = null;
			InputStream in = null;
			HttpURLConnection urlConnection = null;
			StringBuffer buff = new StringBuffer("");
			String response = "";

			if (token == null || token.length() < 1) {
				throw new Exception("Token Not Autherized");
			}
            
			try {

				if (serviceURL.contains("https:")) {
					ssl = true;
				}// end if ssl

				url = new URL(serviceURL);

				if (ssl) {
					urlConnection = (HttpsURLConnection) url.openConnection();
				}// end if call secure
				else {
					urlConnection = (HttpURLConnection) url.openConnection();
				}// end else call non secure

				// Connection Params
				urlConnection.setRequestProperty("Authorization", HttpUtils.getTokenHeader(token));
				urlConnection.setConnectTimeout(Consts.TIMEOUT_CONNECTION);
                
    			urlConnection.setRequestMethod("GET");
    			urlConnection.setDoOutput(false);
				
				// Opens the stream to the server
				in = urlConnection.getInputStream();

				// start listening to the stream
				Scanner inStream = new Scanner(in);

				// process the stream and store it in StringBuilder
				while (inStream.hasNextLine()) {
					buff.append(inStream.nextLine());
				}// end while

				response = buff.toString();

			} catch (FileNotFoundException e) {
				if (Consts.LOG_ERR) Log.e("FileNotFoundException", e.toString());
				throw new Exception("NotAutherizedException", e);
			} catch (ConnectTimeoutException e) {
				if (Consts.LOG_ERR) Log.e("ConnectTimeoutException", e.toString());
				throw new Exception("ServiceUnreachableException", e);
			} catch (IOException e) {
				if (Consts.LOG_ERR) Log.e("IO exception", e.toString());
				// New Session timeout behavior on 4.1+ phones - java.io.IOException: No authentication challenges found
				// We should get a response, but we don't.
				if (e.getMessage().equals("No authentication challenges found")) {
					// Session Timeout
					throw new Exception("NotAutherizedException", e);
				} else {
					throw new Exception("ServiceUnreachableException", e);
				}				
			} catch (Exception e) {
				if (Consts.LOG_ERR) Log.e("Exception", e.toString());
				throw new Exception("GeneralServiceException", e);
			}// end exception

			finally {
				try {
					if(in!=null){
					in.close();
					}//end if not null
					urlConnection.disconnect();
					buff = null;
				} catch (Exception e) {

				}// end catch
			}// end finally
				// response, need to be parsed
			return response;
			
	}// end callService
	

	
	// Read the HttpResponse. If there was an error on the server, throw an
	// exception.
	private static String readResponse(HttpResponse response)
			throws Exception {
		BufferedInputStream bufStream = null;
		ByteArrayBuffer byteArrayBuf = null;
		String retVal = null;

		try {
			StatusLine sl = response.getStatusLine();

			if (sl.getStatusCode() == 500) {
				throw new Exception(sl.getReasonPhrase());
			} else {
				// Read the response
				bufStream = new BufferedInputStream(response.getEntity()
						.getContent());
				byteArrayBuf = new ByteArrayBuffer(20);

				// Assemble the response
				int current = 0;
				while ((current = bufStream.read()) != -1) {
					byteArrayBuf.append((byte) current);
				}
				retVal = new String(byteArrayBuf.toByteArray());
			}
		} catch (IOException e) {
			if (Consts.LOG_ERR) Log.e("IO exception", e.toString());
			throw new Exception(
					"Failed while parsing response", e);
		} finally {
			// Close the stream
			if (bufStream != null) {
				try {
					bufStream.close();
				} catch (Exception ignore) {
					if (Consts.LOG_ERR) Log.e(TAG, "Couldn't close the response stream buffer ",
							ignore);
				}
			}
		}
		return retVal;
	}	
}// end class
