package alf.gasdroide;

import java.util.Random;

import android.os.SystemClock;
import android.util.Log;

public class DataReader extends Thread {

   
    public void run() {
            while (true) {
                    
            	    // Dummy reader
                    float sample =new Random().nextInt(10);                   
                    SystemClock.sleep(1000);                    
                    Log.d("Test"," added "+sample);
                    
            }
    }
}