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

