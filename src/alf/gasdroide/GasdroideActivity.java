//
// Copyright 2010,2011 Alessandro Frigeri
//
// This file is part of GasDroide.
//
// GasDroide is free software: you can redistribute it and/or modify it
// under the terms of the GNU General Public License as published by the
// Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// GasDroide is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
// See the GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License along
// with GasDroide. If not, see http://www.gnu.org/licenses/.
//
//
//

package alf.gasdroide;

import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GasdroideActivity extends Activity {
	
	public static final String LOG_TAG = "Gasdroide";
	TextView if_status;
	
	// receives messages from the thread
	final Handler messageHandler = new Handler(){
		
		public void handleMessage(Message oMessage) 
	    {
	      TextView txtOutput = (TextView)findViewById(R.id.textView2);
	      float myval = oMessage.getData().getFloat("ppm");
	      txtOutput.setText( myval + " ppm" ) ;
		
		  //switch (oMessage.what)
	      //  {
		  //default:
		  //	break;
	      //  }
	    }
		
	}; 
	
	// The data reader
	GasDataReader MyReader = new GasDataReader(messageHandler);
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if_status=(TextView)findViewById(R.id.textView1);
    }

    public void showStatus(View v)
	{
		if_status.setText(new Date().toString());
	}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu);
   	    MenuInflater mInflater = getMenuInflater();
   	    mInflater.inflate(R.menu.menu, menu);
        // return true to get the menu displayed
   	    return true;
    }
    
    public void startAcquisition(View v)
    {
    	Button startstopButton = (Button) findViewById(R.id.button2);
    	TextView data_lbl = (TextView)findViewById(R.id.textView2);
    	
        // Start the worker thread
    	if (MyReader.GetStatus() == MyReader.STATE_NOT_STARTED){ // 0
    		startstopButton.setText("Stop Acquisition");
            MyReader.start();    	    	    
    	}
    	    		
        if (MyReader.GetStatus() == MyReader.STATE_RUNNING){ // 1
        	startstopButton.setText("Start Acquisition");
        	MyReader.pauseReader();        	
            }      
        
        if (MyReader.GetStatus() == MyReader.STATE_PAUSED){ // 1
        	MyReader.resumeReader();
        	startstopButton.setText("Stop Acquisition");
            }  
        
        Log.v(LOG_TAG, "++Thread Status: "+MyReader.GetStatus() );
        
    }
    
    
    @Override
    protected void onStart()
    {
            super.onStart();
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle menu item selection
        switch (item.getItemId()) {
        case R.id.preferences:
        	startActivity(new Intent(this, EditPreferences.class));
            return true;
        case R.id.show_settings:
        	startActivity(new Intent(this, ShowSettingsActivity.class));            
            return true;
        case R.id.quit:
            //showHelp();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
  	
}
