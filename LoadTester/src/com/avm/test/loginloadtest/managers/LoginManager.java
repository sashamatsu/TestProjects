package com.avm.test.loginloadtest.managers;



import com.avm.test.loginloadtest.model.User;
import com.avm.test.loginloadtest.webservices.LoginSvc;

import android.content.Context;
import android.os.Handler;
import android.os.Message;



public class LoginManager {

	private static Handler activityHandler = null;
	private LoginThread loginThread = null;

	public LoginManager(Handler handler) {
		activityHandler = handler;
	}// end constructor

	public void setHandler(Handler handler) {
		activityHandler = handler;
	}// end setHandler

	public void login(String userName, String password, Context context,int numberOfThreads) {
		loginThread = new LoginThread(userName, password, context);
		loginThread.start();
	}// end startDataDownload
	
	private void sendErrorMessageToActivity(int message) {
		activityHandler.sendEmptyMessage(message);
	}// end sendMessageToActivity

	private void sendSuccessToActivity() {
		Message msg=new Message();
		msg.what=ManagerMessages.VALID_USER;
		msg.arg1 	
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
						sendErrorMessageToActivity(ManagerMessages.ERROR);
					}// end if nothing pulled
					else {
						if (mUser.getUserID() == -1) {
							sendErrorMessageToActivity(ManagerMessages.INVALID_USER);
						}// end if failed to authenticate
						else {
								
						}// end else success
					}// end else
				} catch (Exception e) {
					sendErrorMessageToActivity(ManagerMessages.BAD_SERVICE_DATA);
				} 
			}// end if network available

			else {
				sendErrorMessageToActivity(ManagerMessages.NOSIGNAL);
			}// end else

		}// end run
		
		public void cancel() {

		}// end cancel

	}// end DataDownloadThread
}// end class