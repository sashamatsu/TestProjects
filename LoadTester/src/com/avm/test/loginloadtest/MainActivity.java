package com.avm.test.loginloadtest;

import com.avm.test.loginloadtest.managers.LoginManager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText tbxNumberOfThreads;
	private TextView lblThreadCount;
	private ProgressBar progressBar;
	
	private LoginManager mLoginManager=null;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        tbxNumberOfThreads=(EditText)findViewById(R.id.tbxNumberOfThreads);
        lblThreadCount=(TextView)findViewById(R.id.lblThreadCount);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        
        if(mLoginManager!=null){
        	mLoginManager.setHandler(mLoginHandler);
        }//end if not null
        else{
        	mLoginManager=new LoginManager(mLoginHandler);
        }//end else
        
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
    	mLoginManager.login("empireadmin", "g00fba11", this, numberOfThreads);
    	
    }//end runLoatTest
    
    
    
    private final Handler mLoginHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {

			case ManagerMessages.HIERARCHY_COMPLETE:
				break;	
			}// end switch
		}// end handleMessage
	};
    
}//end calss
