package model.neural;
import java.io.Serializable;
import java.util.ArrayList;


public class Neuron implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -184628042181043079L;

	public static enum NeuronType{
		Input, Default
	}
	
	public ArrayList<NeuronConnection> 
		connectionsFromOtherNeurons = new ArrayList<NeuronConnection>(), 
		connectionsToOtherNeurons = new ArrayList<NeuronConnection>();
	private double value = 0;//only has effect if type is input
	private NeuronType neuronType;
	
	public Neuron(NeuronType type){
		neuronType = type;
	}
	
	public void setValue(double value){
		if(this.neuronType != NeuronType.Input) throw new IllegalArgumentException("You can only set values on neurons of type input");
		this.value = value;
	}
	/**
	 * Subclasses can override this method to provide detailed functionalities
	 * By default, the implementation does a linear summation.
	 * @return the value of linear summation of all neuron connections
	 */
	public double getValue(){
		double valueToReturn;
		switch (neuronType) {
		case Input:

			valueToReturn = value;
			break;
		default:
			double temp = 0;
			for (int i = 0; i < connectionsFromOtherNeurons.size(); i++) {
				temp += connectionsFromOtherNeurons.get(i).getComputedValue();
			}
			valueToReturn = temp;
			break;
		}
		return valueToReturn;
	}
}
