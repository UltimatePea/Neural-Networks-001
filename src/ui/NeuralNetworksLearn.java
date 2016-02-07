package ui;

import model.neural.NeuralNetwork;

public class NeuralNetworksLearn extends NeuralNetworksGUIComminication {

	public NeuralNetworksLearn(DrawingCanvas canvas,
			NeuralNetworkLayerPanel layerPanel, TrainingPanel trainingPanel, NeuralNetwork network) {
		super(canvas, layerPanel, trainingPanel, network);
		training.trainNetwork(currentDrawing, expected);
	}

}
