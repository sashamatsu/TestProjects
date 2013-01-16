package com.avm.test.loginloadtest;

import java.util.HashMap;

import com.avm.test.loginloadtest.managers.LoginManager;
import com.avm.test.loginloadtest.managers.ManagerMessages;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText tbxNumberOfThreads;
	private TextView lblThreadCount;
	private ProgressBar progressBar;
	private Button btnTest;
	private HashMap threadMap;
	private Context mContext;
	
	private LoginManager mLoginManager=null;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        tbxNumberOfThreads=(EditText)findViewById(R.id.tbxNumberOfThreads);
        lblThreadCount=(TextView)findViewById(R.id.lblThreadCount);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        btnTest=(Button)findViewById(R.id.button1);
        
        if(mLoginManager!=null){
        	mLoginManager.setHandler(mLoginHandler);
        }//end if not null
        else{
        	mLoginManager=new LoginManager(mLoginHandler);
        }//end else
        
        if(mContext==null){
        	mContext=this;
        }//end if 
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void runLoadTest(View v){
    	int numberOfThreads=Integer.parseInt(tbxNumberOfThreads.getText().toString());
    	progressBar.setMax(numberOfThreads);
    	progressBar.setProgress(numberOfThreads);
    	lblThreadCount.setText("Thread Count: "+ numberOfThreads);
    	threadMap=mLoginManager.prepareManager(numberOfThreads,"empireadmin", "g00fba11", this);
        btnTest.setEnabled(false);
        mLoginManager.runLoginTest();
    }//end runLoatTest
    
    
    
    private final Handler mLoginHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case ManagerMessages.VALID_USER:
				Bundle data=msg.getData();
				long threadID=data.getLong("threadID");
				threadMap.remove(threadID);
				int threadCount=threadMap.size();
				if(threadCount==0){
					btnTest.setEnabled(true);
				}//end if done
				progressBar.setProgress(threadCount);
				lblThreadCount.setText("Thread Count: "+ threadCount);
				break;
			case ManagerMessages.ERROR:
			    Toast.makeText(mContext, "Error occured!", Toast.LENGTH_SHORT).show();
			    mLoginManager.reset();
				btnTest.setEnabled(true);
			    break;
			case ManagerMessages.INVALID_USER:
				Toast.makeText(mContext, "Invalid User", Toast.LENGTH_SHORT).show();
				mLoginManager.reset();
				btnTest.setEnabled(true);
				break;
			case ManagerMessages.BAD_SERVICE_DATA:
				Toast.makeText(mContext, "Bad Data", Toast.LENGTH_SHORT).show();
				mLoginManager.reset();
				btnTest.setEnabled(true);
				break;
			case ManagerMessages.NOSIGNAL:
				Toast.makeText(mContext, "No Signal Available", Toast.LENGTH_SHORT).show();
				mLoginManager.reset();
				btnTest.setEnabled(true);
				break;
			}// end switch
			
		}// end handleMessage
	};
    
}//end class
