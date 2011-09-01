package alf.gasdroide;

import java.util.Random;

public class IntDummy extends AdInterface {	
	
	float my_data_ppm = 1;
	
	// the constructor
    public IntDummy() {   
    	this.GET_DATA_MSG = "damme_li_dati";
    	this.ACK_MSG = "apposto!";     	
    }
    
    @Override
    float getData(){
    	Random myValue = new Random();
    	my_data_ppm = my_data_ppm + myValue.nextFloat();    	
    	return my_data_ppm;
    }
}