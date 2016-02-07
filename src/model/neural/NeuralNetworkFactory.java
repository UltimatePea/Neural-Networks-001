package model.neural;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class NeuralNetworkFactory {
	
	public static final String FILENAME = "NeuralNetwork.data";
	
	public static NeuralNetwork createEmptyNeuralNetwork(){
		return createEmptyNeuralNetwork(10000, 1, 10);
	}
	
	public static NeuralNetwork createEmptyNeuralNetwork(int numOfInputs, int numOfLayers, int numOfNeuronsPerLayer){
		return createEmptyNeuralNetwork(numOfInputs, numOfLayers, numOfNeuronsPerLayer, true);
	}
	
	public static NeuralNetwork createEmptyNeuralNetwork(int numOfInputs, int numOfLayers, int numOfNeuronsPerLayer, boolean save){
		NeuralNetwork network = new NeuralNetwork(numOfInputs,  numOfNeuronsPerLayer, numOfLayers);
		if(save){
			saveNeuralNetwork(network);
		}
		return network;
	}
	
	public static void saveNeuralNetwork(NeuralNetwork network){
		saveNeuralNetwork(network, FILENAME);
	}
	
	public static void saveNeuralNetwork(NeuralNetwork network, String fileName){
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(network);
			oout.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static NeuralNetwork createNeuralNetworkFromStroredData(){
		return createNeuralNetworkFromStroredData(FILENAME);
	}
		
	public static NeuralNetwork createNeuralNetworkFromStroredData(String fileName){
		NeuralNetwork network = null;
		try {
			FileInputStream fin = new FileInputStream(fileName);
			ObjectInputStream oin = new ObjectInputStream(fin);
			Object obj = oin.readObject();
			if (obj instanceof NeuralNetwork) {
				network = (NeuralNetwork) obj;
			}
			oin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return network;
		
	}
	public static NeuralNetwork getNeuralNetwork(){
		File file = new File(FILENAME);
		if(file.exists()){
			return createNeuralNetworkFromStroredData();
		} else {
			return createEmptyNeuralNetwork();
		}
	}
	

}
