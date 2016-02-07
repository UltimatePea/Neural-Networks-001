package ui;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import model.neural.NeuralNetwork;
import model.neural.Neuron;



public class NeuralNetworkLayerPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4672388277815609228L;
	
	public ArrayList<ArrayList<Neuron>> layers;
	private NeuralNetwork neuralNetwork;

	public NeuralNetworkLayerPanel(NeuralNetwork neuralNetwork){
		setNeuralNetwork(neuralNetwork);
	}
		
	
	public void setNeuralNetwork(NeuralNetwork neuralNetwork){
		//retrive metadata of neural network
		
		removeAll();
				layers =  neuralNetwork.otherNeurons;
				int numOfLayers = layers.size();
				int numOfNeuronsPerLayer = layers.get(0).size();
				
				//seting grid layout
				GridLayout gridLayout = new GridLayout(numOfLayers, numOfNeuronsPerLayer);
				gridLayout.setHgap(5);
				gridLayout.setVgap(5);
				setLayout(gridLayout);
				
				
				//filling in data
				for (int i = 0; i < numOfLayers; i++) {
					for (int j = 0; j < numOfNeuronsPerLayer; j++) {
						Neuron neuron = layers.get(i).get(j);
						SingleNeuronCanvas canvas = new SingleNeuronCanvas(neuron);
						add(canvas);
					}
				}
			this.neuralNetwork = neuralNetwork;
	}
	
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
		Component[] canvases = getComponents();
		for (Component component : canvases) {
			component.repaint();
		}
	}
	
	
	
	
}
