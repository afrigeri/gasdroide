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

public class AdInterface {

	public String GET_DATA_MSG;
	public String ACK_MSG;
	
	// the Bicycle class has one constructor
    public AdInterface() {
        this.GET_DATA_MSG = "";
        this.ACK_MSG = "";     
    }
    
    float requestData(String msg){		
		// open the inteface and send "msg"	
    	return 3.14f;
	}

	float getData(){
		float data_read = requestData(GET_DATA_MSG);
		return 6.18f; 
	}
	
	
		
	
	
}

