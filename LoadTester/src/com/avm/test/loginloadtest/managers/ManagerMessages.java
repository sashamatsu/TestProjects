package com.avm.test.loginloadtest.managers;



public class ManagerMessages {
	
	public  static final int DONE=1;
	public  static final int ERROR=0;
	public 	static final int NOSIGNAL=2;
	public static final int NOALERTS=4;
	public static final int PROGRESS=5;
	public static final int INVALID_USER=6;
	public static final int VALID_USER=7;
	public static final int VALID_CHANGED_USER=8;
	
	//ERROR MESSAGES
	public static final int UNREACHABLE_SERVICE=9;
	public static final int BAD_SERVICE_DATA=10;
	
	//INIT DATA MESSAGES
	public static final int HIERARCHY_COMPLETE=11;
	public static final int GENERAL_QUESTIONS_COMPLETE=12;
	public static final int PRODUCT_QUESTIONS_COMPLETE=14;
	public static final int ALERTS_COMPLETE=15;
	public static final int INCOMPLETE_HIERARCHY_DATA=16;
	
	//REFRESH MANAGER MESSAGES
	public static final int	REFRESH_HIERARCHY_START=100;
	public static final int REFRESH_HIERARCHY_STOP=105;
	public static final int	REFRESH_GEN_QUESTIONS_START=110;
	public static final int REFRESH_GEN_QUESTIONS_STOP=115; 
	public static final int	REFRESH_PRODUCT_QUESTSIONS_START=120;
	public static final int REFRESH_PRODUCT_QUESTIONS_STOP=125;
	public static final int	REFRESH_ALERTS_START=130;
	public static final int	REFRESH_ALERTS_STOP=135;
	
   //LOCATION MANAGER MESSAGES
	public static final int LOCATION_ACQUIRED=20;
	public static final int LOCATION_PROVIDER_DISABLED=22;
	
}//end class