package com.avm.test.loginloadtest.model;



import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;



public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String mUserName = "";
	private String mPwd = "";
	private int mUserID = -1;
	private String mToken="";
	
	public User(int userID,String token){
		this.mUserID=userID;
		this.mToken=token;
	}//end constructor
	
    public User(JSONObject jsonObj,String userName,String pwd) throws JSONException{
	   if(jsonObj!=null){
			try {
				mUserID=jsonObj.getInt("UserId");
				if(mUserID>0){
			    this.mToken=jsonObj.getString("AuthenticationTicket");
			    this.mPwd=pwd;
			    this.mUserName=userName;
				}//end if valid userID
			} catch (JSONException e) {
				throw e;
			}//catch
			
		}//end if valid user
   }//end constructor
	

	public boolean isValidUser(){
		if(mUserID>0){
			return true;
		}//end if valid userID
		else{
			return false;
		}//end else
	}//end isValidUser
	
	

	public void setUserName(String userName){
		this.mUserName=userName;
	}//end setUserName
	

	public String getUserName() {
		return mUserName;
	}

	public void setPwd(String pwd){
		this.mPwd=pwd;
	}//end setPwd
	
	public String getPwd() {
		return mPwd;
	}

	public int getUserID() {
		return mUserID;
	}

	public void setToken(String token){
		this.mToken=token;
	}//end setToken
	
	public String getToken(){
		return this.mToken;
	}//end getToken
	
	
	
}// end login
