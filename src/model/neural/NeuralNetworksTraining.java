package model.neural;

import java.util.ArrayList;

import utility.CustomUtilitites;

public class NeuralNetworksTraining {
	

	NeuralNetwork network;
	private TrainingSuccessfulCallback callback;
	public int incrementBy = 1, decrementBy = -1;
	
	public NeuralNetworksTraining(NeuralNetwork network){
		this.network  = network;
	}
	
	public double[] convertBooleansToDoubles(boolean[] inputBooleans){
		double[] inputDoubles = new double[inputBooleans.length];
		for (int i = 0; i < inputBooleans.length; i++) {
			if(inputBooleans[i] == true){
				inputDoubles[i] = 1;
			} else {
				inputDoubles[i] = 0;
			}
		}
		return inputDoubles;
	}
	
	public void trainNetwork(boolean[] inputBooleans, int expectedOutputIndex){
		//convert to double of 0 and 1
		
		double[] inputDoubles = convertBooleansToDoubles(inputBooleans);
		
		double[] actualOutputArray = network.outputForInputValue(inputDoubles);
		int maxIndex = CustomUtilitites.getLargestIndex(actualOutputArray);
		if(maxIndex == expectedOutputIndex){
			callback.noNeedToTrain(expectedOutputIndex);
			return;
		}
		ArrayList<Neuron> lastLayer  = network.otherNeurons.get(network.otherNeurons.size() - 1);
		Neuron correctNeuron = lastLayer.get(expectedOutputIndex);
		Neuron wrongNeuron = lastLayer.get(maxIndex);
		ArrayList<NeuronConnection> correctConnections = correctNeuron.connectionsFromOtherNeurons;
		ArrayList<NeuronConnection> wrongcoConnections = wrongNeuron.connectionsFromOtherNeurons;
		
		for (int i = 0; i < inputBooleans.length; i++) {
			if(inputBooleans[i] == true){
				correctConnections.get(i).weight += incrementBy;
				wrongcoConnections.get(i).weight += decrementBy;
			} 
		}
		
		
		network.getMetadata().numberOfTrainings ++;
		callback.successful(expectedOutputIndex, maxIndex);
		
	}
	
	public void setOnSuccessful(TrainingSuccessfulCallback callback){
		this.callback = callback;
	}

}
