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

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class GasDataReader extends Thread {
	
    // ***************************
    // Public Constants
    // ***************************
    public final static byte STATE_NOT_STARTED = 0;
    public final static byte STATE_RUNNING = 1;
    public final static byte STATE_DONE = 2;
    public final static byte STATE_PAUSED = 3;
	
    // ***************************
    // State
    // ***************************
    private boolean wait;
    private boolean run;
    
	public static final String LOG_TAG = "Gaz-datareader";
		
    byte myStatus = STATE_NOT_STARTED;

    /* Interface */
    IntDummy myInt = new IntDummy();
    
    public Handler HandlerOfCaller; 
    // Thread constructor
    GasDataReader(Handler oHandler) 
    {
        HandlerOfCaller = oHandler;
    }
    
    
    // This method is called when the thread runs
    public void run() {
        while (true) {
            // Do work
        	myStatus = STATE_RUNNING;
        	
            try {
            	float value_ppm = myInt.getData();
            	GasData gd = new GasData( value_ppm );
            	
            	// send data to the process
                Message oMessage = HandlerOfCaller.obtainMessage();               
                // oMessage.obj = gd;
                Bundle b = new Bundle();
                b.putFloat("ppm", gd.getValuePpm() );
                oMessage.setData(b);
                HandlerOfCaller.sendMessage(oMessage);

            	Log.v(LOG_TAG, "Thread running... Status: " + myStatus + gd.print());          
            	Thread.sleep(200);
				
            	
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            // Check if should wait
            synchronized (this) {
                if (wait) {
                    try {
                    	myStatus = STATE_PAUSED;
                        wait();
                    } catch (Exception e) {
                    }
                }
            }

            // Do work
            
        }
    }
    
    // Get the running status of the threaded process
    public byte GetStatus()
    {
      return myStatus;
    }
    
    
    
    /**
     * Pause
     */
    public void pauseReader() {
        this.wait = true;
        synchronized(this) {
            this.notify();
        }
    }
 
    /**
     * Resume 
     */
    public void resumeReader() {
        this.wait = false;
        synchronized(this) {
            this.notify();
        }
    }

}