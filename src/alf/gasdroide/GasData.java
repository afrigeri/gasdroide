package alf.gasdroide;

// Gas Flux Data

public class GasData {

	public GasData (float value_ppm)
	{
	value_ppm_min = 0;
	value_ppm_max = 1000000;
	this.time_millis = System.currentTimeMillis();	
	this.value_ppm = value_ppm; 
	}
	
	public float getValuePpm () {
		return value_ppm;
	}
	
	long time_millis;

	private float value_ppm;
	
	final float value_ppm_min;
	final float value_ppm_max;
}


