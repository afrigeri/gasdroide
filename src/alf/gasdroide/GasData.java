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
	
	public String print(){
		return "Value: "+value_ppm+" ppm @"+this.time_millis;
	}
}


