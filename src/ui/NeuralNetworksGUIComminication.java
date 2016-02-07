package ui;

import utility.MessageBox;
import model.neural.NeuralNetwork;
import model.neural.NeuralNetworksTraining;
import model.neural.TrainingSuccessfulCallback;

public abstract class NeuralNetworksGUIComminication {
	
	protected NeuralNetwork network;
	protected boolean[] currentDrawing;
	protected int expected;
	protected NeuralNetworksTraining training;
	
	public NeuralNetworksGUIComminication(DrawingCanvas canvas, NeuralNetworkLayerPanel layerPanel, TrainingPanel trainingPanel, NeuralNetwork network){
		this.network = network;
		boolean[][] pointsDrawn = canvas.pointsDrawn;
		currentDrawing = new boolean[pointsDrawn.length * pointsDrawn[0].length];
		for (int i = 0; i < pointsDrawn.length; i++) {
			for (int j = 0; j < pointsDrawn[i].length; j++) {
				currentDrawing[i * pointsDrawn.length + j] = pointsDrawn[i][j];
			}
		}
		expected = trainingPanel.getExpected();
		training = new NeuralNetworksTraining(network);
		training.setOnSuccessful(new TrainingSuccessfulCallback() {
			
			@Override
			public void successful(int expected, int produced) {
				layerPanel.repaint();
				trainingPanel.setExpected(expected);
				trainingPanel.setProduced(produced);
				MessageBox.info("Training Successful. Total " + network.getMetadata().numberOfTrainings + " trains.");
			}
			
			@Override
			public void noNeedToTrain(int expected) {
				trainingPanel.setProduced(expected);
				MessageBox.info("No need to train. Total " + network.getMetadata().numberOfTrainings + " trains.");
				
			}
		});
	}

}
