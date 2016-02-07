package model.neural;
import java.io.Serializable;


public class NeuronConnection implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4513854261400288107L;
	public Neuron fromNeuron, toNeuron;
	public double weight;
	
	public NeuronConnection(Neuron fromNeuron, Neuron toNeuron, double weight)
	{
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
		this.weight = weight;
	}
	
	public double getComputedValue(){
		
		return weight * fromNeuron.getValue();
	}
	
}
