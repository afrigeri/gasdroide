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
    
	public static final String LOG_TAG = "Gaz-dr3";
	
    byte myStatus = STATE_NOT_STARTED;

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
            Log.v(LOG_TAG, "Thread running... Status: " + myStatus);
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