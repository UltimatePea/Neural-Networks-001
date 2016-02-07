package model.neural;

import java.io.Serializable;

public class NeuralNetworkMetadata implements Serializable {
	
	public int numberOfTrainings;
	
	public NeuralNetworkMetadata(){
		numberOfTrainings = 0;
	}

}
