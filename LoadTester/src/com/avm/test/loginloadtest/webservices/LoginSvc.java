package com.avm.test.loginloadtest.webservices;



import org.json.JSONException;
import org.json.JSONObject;

import com.avm.test.loginloadtest.Consts;
import com.avm.test.loginloadtest.model.User;

import android.util.Log;



public class LoginSvc {

	private final static String TAG = LoginSvc.class.getSimpleName();
  
	public static User login(String userName,String pwd) throws Exception,JSONException{
		JSONObject jsonObj=null;
		String response=null;
		User user=null;
		
		response=RestEngine.callAuthService(Consts.SREST_LOGIN_URL, userName, pwd);
		try{
			jsonObj=new JSONObject(response);
			user=new User(jsonObj, userName, pwd);
		}//end try
		catch(JSONException e){
			throw e;
		}//end catch json exception
		catch (Exception e) {
		throw e;
		}//end catch
		
		
		return user;
	}//end login
	
}//end class