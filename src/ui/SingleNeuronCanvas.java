package ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import model.neural.Neuron;
import model.neural.NeuronConnection;

public class SingleNeuronCanvas extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5415842803239182702L;
	
	private static final int ASPECT_RATIO = 1;
	Neuron neuron;
	int gridWidth;
	
	public SingleNeuronCanvas(Neuron neuron){
		int size = neuron.connectionsFromOtherNeurons.size();
		int width = (int) Math.sqrt(size);
		setPreferredSize(new Dimension(width* ASPECT_RATIO, width * ASPECT_RATIO));
		gridWidth = width;
		this.neuron = neuron;
	}
	
	public void paint(Graphics g){
		ArrayList<NeuronConnection> connections = neuron.connectionsFromOtherNeurons;
		for (int i = 0; i < gridWidth; i++) {
			for (int j = 0; j < gridWidth; j++) {
				int weight = (int) connections.get(i * gridWidth + j).weight;
				int r = colorFunction(weight);
				int green = colorFunction(weight);
				int b = colorFunction(weight);
				g.setColor(new Color(r, green, b));
				g.fillRect(i * ASPECT_RATIO, j * ASPECT_RATIO, ASPECT_RATIO, ASPECT_RATIO);
			}
		}
	}
	
	private int colorFunction(int weight){
		return (int) (255 /( 1 + Math.pow(2.71828182846, - 0.05 * weight)));
	}

}
