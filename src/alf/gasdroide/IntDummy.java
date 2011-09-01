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