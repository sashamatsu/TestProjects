package com.avm.test.loginloadtest.managers;



import com.avm.test.loginloadtest.Consts;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;


public class SignalManager extends PhoneStateListener {
	public static final String TAG = SignalManager.class.getSimpleName();
	TelephonyManager Tel=null;
	
	
	
	public static boolean isNetworkAvailable(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    
	    if(activeNetworkInfo!=null){
	    	if(activeNetworkInfo.isConnected()){
	    		return true;
	    	}//end if connected
	    	else{
	    		return false;
	    	}//end not connected
	    }//end if has network coverage
	    else{
	    		return false;
	    }//end else
	}//end class
	
	public static boolean isNetworkConnected(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    
	    if(activeNetworkInfo!=null){
	    		return activeNetworkInfo.isConnected();
	    }//end if has network coverage
	    else{
	    		return false;
	    }//end else
	}//end class
	
	public static boolean isNetworkConnecting(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    
	    if(activeNetworkInfo!=null){
	    		return activeNetworkInfo.isConnectedOrConnecting();
	    }//end if has network coverage
	    else{
	    		return false;
	    }//end else
	}//end class
	
	public static boolean isVerizon3g(Context context) {
		boolean retVal = false;
		
	    ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    TelephonyManager telephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

	    if (activeNetworkInfo.getType() == ConnectivityManager.TYPE_MOBILE && 
	    	!(activeNetworkInfo.getSubtype() == 13) &&
	    	isVerizon(telephonyMgr)) {
	    	if (Consts.LOG_DEBUG) Log.d(TAG,"On Verizon non-LTE");
	    	retVal = true;
	    }
	    return retVal;
	}
	
	// Check to see if the network operator name has verizon in it.  
	private static boolean isVerizon(TelephonyManager mgr) {
		boolean retVal = false;
		
		// See http://en.wikipedia.org/wiki/Mobile_Network_Code for MCC+MNC codes as returned by
		// mgr.getNetworkOperator()
		String networkOpNameLower = mgr.getNetworkOperatorName().toLowerCase();
		if (networkOpNameLower.contains("verizon")) {
			retVal = true;
		}
		
		return retVal;
	}
	
	public static boolean isPhoneAvailable(Context context) {
		boolean retVal = false;
		
		TelephonyManager telephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
		
		if (telephonyMgr.getPhoneType() == TelephonyManager.PHONE_TYPE_NONE) {
			retVal = false;
		} else {
			retVal = true;
		}
		
		return retVal;
	}
	
	
	@Override
    public void onSignalStrengthsChanged(SignalStrength signalStrength)
    {
       super.onSignalStrengthsChanged(signalStrength);
    }//end onSignalStrengthsChanged

	
	
}//end class
