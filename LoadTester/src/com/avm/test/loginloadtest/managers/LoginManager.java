package com.avm.test.loginloadtest.managers;



import java.util.HashMap;

import com.avm.test.loginloadtest.model.User;
import com.avm.test.loginloadtest.webservices.LoginSvc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;



public class LoginManager {

	private static Handler activityHandler = null;
	private LoginThread loginThread = null;
	private LoginThread[] testThreads=null;

	public LoginManager(Handler handler) {
		activityHandler = handler;
	}// end constructor

	public HashMap prepareManager(int numberOfThreads,String userName,String password,Context context){
		
		 HashMap<Long,Integer> threadMap = new HashMap<Long,Integer>();
		
		if(numberOfThreads <= 0){
			numberOfThreads=1;
		}//end if not valid
		
		testThreads=new LoginThread[numberOfThreads];
		for(int i =0;i<testThreads.length;i++ ){
			testThreads[i]=new LoginThread(userName,password,context);
			threadMap.put(testThreads[i].getId(), i);
		}//end for threads
		
		return threadMap;
	}//end prepareManager
	
	public void reset(){
		if(testThreads!=null){
			for(int i=0;i<testThreads.length;i++){
				testThreads[i].cancel();
			}//end for
		}//end if testThreads
	}//end reset
	
	public void setHandler(Handler handler) {
		activityHandler = handler;
	}// end setHandler

	public void runLoginTest() {
		if(testThreads!=null){
		for(int i =0;i<testThreads.length;i++ ){
			testThreads[i].start();
		}//end for threads
		}//end not null
	}// end startDataDownload
	
	private void sendErrorMessageToActivity(int message) {
		activityHandler.sendEmptyMessage(message);
	}// end sendMessageToActivity

	private void sendSuccessToActivity(long threadID) {
		Message msg=new Message();
		msg.what=ManagerMessages.VALID_USER;
		Bundle b = new Bundle();
	    b.putLong("threadID", threadID);
	    msg.setData(b);
	    
		activityHandler.sendMessage(msg);
	}// end sendMessageToActivity

	private void sendValidChangedUserToActivity() {
		activityHandler.sendEmptyMessage(ManagerMessages.VALID_CHANGED_USER);
	}// end sendValidChangedUserToActivity
	
	private class LoginThread extends Thread {

		private User mUser = null;
		private String mUserName = "";
		private String mPwd = "";
		private Context mContext = null;

		public LoginThread(String userName, String password, Context context) {
			mUserName = userName;
			mPwd = password;
			mContext = context;
		}// end constructor

		public void run() {

			if (SignalManager.isNetworkAvailable(mContext)) {
				try {
					mUser = LoginSvc.login(mUserName, mPwd);

					if (mUser == null) {
						//sendErrorMessageToActivity(ManagerMessages.ERROR);
						sendSuccessToActivity(this.getId());	
					}// end if nothing pulled
					else {
						if (mUser.getUserID() == -1) {
							//sendErrorMessageToActivity(ManagerMessages.INVALID_USER);
							sendSuccessToActivity(this.getId());	
						}// end if failed to authenticate
						else {
						   sendSuccessToActivity(this.getId());	
						}// end else success
					}// end else
				} catch (Exception e) {
					//sendErrorMessageToActivity(ManagerMessages.BAD_SERVICE_DATA);
					sendSuccessToActivity(this.getId());	
				} 
			}// end if network available

			else {
				//sendErrorMessageToActivity(ManagerMessages.NOSIGNAL);
				sendSuccessToActivity(this.getId());	
			}// end else

		}// end run
		
		public void cancel() {
			interrupt();
		}// end cancel

	}// end DataDownloadThread
}// end class