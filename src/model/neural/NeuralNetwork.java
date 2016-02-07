package model.neural;
import java.io.Serializable;
import java.util.ArrayList;



public class NeuralNetwork implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 197582012579552867L;

	private NeuralNetworkMetadata metadata;
	
	public double defaultWeightMin = -100, defaultWeightMax = 100;

	public double[] cacheResult;
	
	

	public ArrayList<Neuron> inputNeurons = new ArrayList<Neuron>();
		public ArrayList<ArrayList<Neuron> >	otherNeurons = new ArrayList<ArrayList<Neuron> >();
			
	public NeuralNetworkMetadata getMetadata(){
		if(metadata == null){
			metadata = new NeuralNetworkMetadata();
		}
		return metadata;
	}
	
	public NeuralNetwork(int numOfInputNeurons, int numOfTransmittingNeurons, int numberOfLayers){
		//set up neurons list
		for(int i = 0; i < numOfInputNeurons; i ++){
			inputNeurons.add(new Neuron(Neuron.NeuronType.Input));
		}
		for(int i = 0; i < numberOfLayers; i ++){
			ArrayList<Neuron> layer  = new ArrayList<Neuron>();
			otherNeurons.add(layer);
			for(int j = 0; j< numOfTransmittingNeurons; j ++){
				Neuron neuron = new Neuron(Neuron.NeuronType.Default);
				layer.add(neuron);
			}
		}
		
		
		connectLayerToLayer(inputNeurons, otherNeurons.get(0));
		//set up connections
		for(int i = 1; i < numberOfLayers; i++){
			ArrayList<Neuron> fromLayer = otherNeurons.get(i - 1);
			ArrayList<Neuron> toLayer = otherNeurons.get(i);
			connectLayerToLayer(fromLayer, toLayer);
		}
		
	}
	
	private void connectLayerToLayer(ArrayList<Neuron> fromLayer, ArrayList<Neuron> toLayer){
		for(int i = 0; i < fromLayer.size(); i ++){
			for(int j = 0; j < toLayer.size(); j ++){
				Neuron fromNeuron = fromLayer.get(i);
				Neuron toNeuron = toLayer.get(j);
				connectNeuronToNeuron(fromNeuron, toNeuron);
			}
		}
	}
	
	//connect two neurons together using neuron connection
	private void connectNeuronToNeuron(Neuron fromNeuron, Neuron toNeuron){
		NeuronConnection connection = new NeuronConnection(fromNeuron, toNeuron, Math.random()*(defaultWeightMax - defaultWeightMin)+ defaultWeightMin);
		fromNeuron.connectionsToOtherNeurons.add(connection);
		toNeuron.connectionsFromOtherNeurons.add(connection);
	}
	
	
	public void prepareNeuronsForSpark(double[] value){
		for (int i = 0; i < inputNeurons.size(); i ++) {
			Neuron inputNeuron = inputNeurons.get(i);
			double valueToSet = value[i];
			inputNeuron.setValue(valueToSet);
		}
	}
	
	public void spark(){
		ArrayList<Neuron> lastLayer = otherNeurons.get(otherNeurons.size() - 1);
		cacheResult = new double[lastLayer.size()];
		for (int i = 0; i < lastLayer.size(); i++) {
			cacheResult[i] = lastLayer.get(i).getValue();
		}
	}
	
	public double[] getSparkedResult(){
		return cacheResult;
	}
	
	public double[] outputForInputValue(double[] value){
		prepareNeuronsForSpark(value);
		spark();
		return getSparkedResult();
	}
	
}
