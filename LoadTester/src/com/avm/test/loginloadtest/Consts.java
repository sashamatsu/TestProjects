package com.avm.test.loginloadtest;

import java.io.File;

import android.os.Environment;

public class Consts {

	public static final String APPNAME="StoreFlix";
	public static final String LOGTAG=APPNAME.toUpperCase();
	
	public static boolean LOG_ERR = true;
	public static boolean LOG_INFO=true;
	public static boolean LOG_DEBUG=true;
	public static boolean LOG_VERB=true;
	
	public static final int GOOD_SIGNAL_MIN=-100;
	
	public static final File ROOT_FOLDER=new File(Environment.getExternalStorageDirectory() + "/"+APPNAME);
	public static final File MEIDA_FOLDER=new File(ROOT_FOLDER+"/media");
	public static final File DEBUG_FOLDER=new File(ROOT_FOLDER+"/debug");
	
	// SUPPORT
	public static final String EMAIL_SUPPORT = "support@storeflix.com";
	public static final String CALL_SUPPORT = "888-810-2226";
	public static String APP_UPDATE_URL = "http://www.storeflix.com/apps/StoreFlix-debug.apk";
	
	
	public static final int ACTION_TAKE_PIC = 0;
	public static final int ACTION_TAKE_VID = 1;
	public static final int ACTION_ATTACHMENTS=4;
	public static final int ACTION_UPLOAD_STATUS=5;
	public static final int ACTION_SLIDES=6;
	public static final int ACTION_ALERTS=7;
	public static final int ACTION_BOARDROOM = 8;
	
	//ActivityResults
	public static final int RESULT_SLIDES_DONE=8;
	public static final int RESULT_LOGOUT=9;
	

	// DEFAULT CONFIG
	public static final int UPLOAD_ATTEMPT_MAX = 5;
	public static final int UPLOAD_SVC_POLL_INTERVAL = 30;
	public static final int MAX_VIDEO_DURATION_MS = 30000;
	
	
	//Boardroom
	public static String APP_LANDER_URL="https://www.storeflix.com/applander.aspx";
	
	//JSON
	public static String RESTSERVER="www.storeflix.com";
	//public static final String RESTSERVER="192.168.66.78:60500"; //Jamies dynamic ip
	
	//LOGIN
	private static final String LOGIN_URL_PATH = "/mobileapi/user/token";
	public static String SREST_LOGIN_URL="https://"+RESTSERVER+LOGIN_URL_PATH;
	public static String REST_LOGIN_URL="http://"+RESTSERVER+LOGIN_URL_PATH;
	
	// UPLOAD LOCATION
    // public static final String UPLOAD_URL = "http://demo.storeflix.com/uploadfile_v3.aspx";
	private static final String UPLOAD_URL_PATH = "/mobileapi/packet/uploadMedia/";
	public static String UPLOAD_URL = "http://" + RESTSERVER + UPLOAD_URL_PATH;
	
	//INIT DATA
	public static final String INIT_DATA_URL_PATH="/mobileapi/user/initdata";
	public static String SREST_INIT_DATA_URL="https://"+RESTSERVER+INIT_DATA_URL_PATH;
	public static String REST_INIT_DATA_URL="http://"+RESTSERVER+INIT_DATA_URL_PATH;
	
	//QUESTION DATA
	public static final String GENERAL_QUESTIONS_PATH="/mobileapi/questions/general/";
	public static String SREST_GENERAL_QUESTIONS_URL="https://"+RESTSERVER+GENERAL_QUESTIONS_PATH;
	public static String REST_GENERAL_QUESTIONS_URL="http://"+RESTSERVER+GENERAL_QUESTIONS_PATH;

	public static final String PRODUCT_QUESTIONS_PATH="/mobileapi/questions/product/";
	public static String SREST_PRODUCT_QUESTIONS_URL="https://"+RESTSERVER+PRODUCT_QUESTIONS_PATH;
	public static String REST_PRODUCT_QUESTIONS_URL="http://"+RESTSERVER+PRODUCT_QUESTIONS_PATH;
	
	//ALERT DATA
	public static final String ALERTS_PATH="/mobileapi/user/alerts/";
	public static String SREST_ALERTS_URL="https://"+RESTSERVER+ALERTS_PATH;
	public static String REST_ALERTS_URL="http://"+RESTSERVER+ALERTS_PATH;

	
	//PACKET DATA
	public static final String CREATE_PACKET_PATH="/mobileapi/packet/create";
	public static String SREST_CREATE_PACKET_URL="https://"+RESTSERVER+CREATE_PACKET_PATH;
	public static String REST_CREATE_PACKET_URL="http://"+RESTSERVER+CREATE_PACKET_PATH;
	
	//VERSION INFO
	public static final String VERSION_INFO_PATH="/mobileapi/user/versionInfo";
	public static String SREST_VERSION_INFO="https://"+RESTSERVER+VERSION_INFO_PATH;
	public static String REST_VERSION_INFO="http://"+RESTSERVER+VERSION_INFO_PATH;
	
	
	// EXTRAS
	public static final String EXTRA_PCT_PROGRESS = "EXTRA_PCT_PROGRESS";
	public static final String EXTRA_FILE_NAME = "EXTRA_FILE_NAME";
	public static final String EXTRA_FILE_SIZE = "EXTRA_FILE_SIZE";
	public static final String EXTRA_BYTES_XFRD = "EXTRA_BYTES_XFRD";
	public static final String EXTRA_ATTEMPT_COUNT = "EXTRA_ATTEMPT_COUNT";
	public static final String EXTRA_TRANSFER_FILE = "EXTRA_TRANSFER_FILE";	

	//TIMEOUTS
	
	// Set the timeout in milliseconds until a connection is established.
	// For reasons unexplained the timeout seems to take double the set milliseconds
	public static final int TIMEOUT_CONNECTION = 5000;
			
	// in milliseconds which is the timeout for waiting for data.
	public static final int TIMEOUT_SOCKET = 20000;
	
	public static void SetRestServerURL(String url)
	{
		RESTSERVER = url;
		
		SREST_LOGIN_URL="https://"+RESTSERVER+LOGIN_URL_PATH;
		REST_LOGIN_URL="http://"+RESTSERVER+LOGIN_URL_PATH;
		
		UPLOAD_URL = "http://" + RESTSERVER + UPLOAD_URL_PATH;
		
		SREST_INIT_DATA_URL="https://"+RESTSERVER+INIT_DATA_URL_PATH;
		REST_INIT_DATA_URL="http://"+RESTSERVER+INIT_DATA_URL_PATH;
		
		SREST_GENERAL_QUESTIONS_URL="https://"+RESTSERVER+GENERAL_QUESTIONS_PATH;
		REST_GENERAL_QUESTIONS_URL="http://"+RESTSERVER+GENERAL_QUESTIONS_PATH;
		
		SREST_PRODUCT_QUESTIONS_URL="https://"+RESTSERVER+PRODUCT_QUESTIONS_PATH;
		REST_PRODUCT_QUESTIONS_URL="http://"+RESTSERVER+PRODUCT_QUESTIONS_PATH;
		
		SREST_ALERTS_URL="https://"+RESTSERVER+ALERTS_PATH;
		REST_ALERTS_URL="http://"+RESTSERVER+ALERTS_PATH;
		
		SREST_CREATE_PACKET_URL="https://"+RESTSERVER+CREATE_PACKET_PATH;
		REST_CREATE_PACKET_URL="http://"+RESTSERVER+CREATE_PACKET_PATH;
		
		SREST_VERSION_INFO="https://"+RESTSERVER+VERSION_INFO_PATH;
		REST_VERSION_INFO="http://"+RESTSERVER+VERSION_INFO_PATH;
		
		APP_UPDATE_URL = "http://" + RESTSERVER + "/apps/StoreFlix-debug.apk";
		APP_LANDER_URL="https://" + RESTSERVER + "/applander.aspx";
	}
}//end class