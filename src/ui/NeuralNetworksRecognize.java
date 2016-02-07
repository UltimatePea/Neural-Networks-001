package ui;

import utility.CustomUtilitites;
import model.neural.NeuralNetwork;

public class NeuralNetworksRecognize extends NeuralNetworksGUIComminication {

	public NeuralNetworksRecognize(DrawingCanvas canvas,
			NeuralNetworkLayerPanel layerPanel, TrainingPanel trainingPanel, NeuralNetwork network) {
		super(canvas, layerPanel, trainingPanel, network);
		// TODO Auto-generated constructor stub
		trainingPanel.setProduced(CustomUtilitites.getLargestIndex(network.outputForInputValue(training.convertBooleansToDoubles(currentDrawing))));
	}

}
