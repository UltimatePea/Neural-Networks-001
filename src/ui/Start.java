package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import utility.MessageBox;
import model.neural.NeuralNetwork;
import model.neural.NeuralNetworkFactory;

public class Start {
	

	private NeuralNetwork network;
	private NeuralNetworkLayerPanel networkLayerPanel;
	private DrawingCanvas canvas;
	private TrainingPanel trainingPanel = new TrainingPanel();
	private JFrame mainFrame;

	public Start(){
		canvas = setUpDrawingCanvas();
		mainFrame = setUpMenus(canvas);
		network = NeuralNetworkFactory.getNeuralNetwork();
		networkLayerPanel = setUpNeuralNetworkDisplay(network);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(networkLayerPanel, BorderLayout.SOUTH);
		mainFrame.add(canvas, BorderLayout.CENTER);
		mainFrame.add(trainingPanel, BorderLayout.EAST);
		mainFrame.add(MessageBox.getJPanel(), BorderLayout.NORTH);
		mainFrame.pack();
		mainFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				NeuralNetworkFactory.saveNeuralNetwork(network);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	
	
	private DrawingCanvas setUpDrawingCanvas(){
//		JFrame frame = new JFrame("Drawing Canvas");
		DrawingCanvas canvas = new DrawingCanvas();
		canvas.setPreferredSize(canvas.preferredDimension);
//		frame.add(canvas);
//		frame.setSize(500, 500);
//		frame.setResizable(false);
//		frame.setVisible(true);
		return canvas;
	}
	
	private JFrame setUpMenus(DrawingCanvas canvas) {
		//create frame
		JFrame frame = new JFrame("Menus");
		frame.setLayout(new GridLayout(1, 3));
		
		//create menu
		
		//create menu bar
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu neuralNetworksMenu = new JMenu("Neural Networks");
		JMenu learningMenu = new JMenu("Learning");
		bar.add(fileMenu);
		bar.add(editMenu);
		bar.add(neuralNetworksMenu);
		bar.add(learningMenu);
		
		//create menu items
		//---File---
		JMenuItem menuButtonFileNew = new JMenuItem("New..");
		JMenuItem menuButtonFileOpen = new JMenuItem("Open..");
		JMenuItem menuButtonFileSave = new JMenuItem("Save");
		JMenuItem menuButtonFileSaveAs = new JMenuItem("Save As..");
		fileMenu.add(menuButtonFileNew);
		fileMenu.add(menuButtonFileOpen);
		fileMenu.add(menuButtonFileSave);
		fileMenu.add(menuButtonFileSaveAs);
		//---Edit---
		JMenuItem menuButtonEditClearCanvas = new JMenuItem("Clear Canvas");
		editMenu.add(menuButtonEditClearCanvas);
		//---Neural Networks
		JMenuItem menuButtonNeuralNetworksLearn = new JMenuItem("Learn");
		JMenuItem menuButtonNeuralNetworksRecognize = new JMenuItem("Recognize");
		neuralNetworksMenu.add(menuButtonNeuralNetworksLearn);
		neuralNetworksMenu.add(menuButtonNeuralNetworksRecognize);
		//---Learning ---
		JMenuItem menuButtonIncrementValueBy1 = new JMenuItem("Increase expected value by 1");
		JMenuItem menuButtonDecrementValueBy1 = new JMenuItem("Decrease expected value by 1");
		learningMenu.add(menuButtonIncrementValueBy1);
		learningMenu.add(menuButtonDecrementValueBy1);
		
		//set up accelerator
		int shortcutMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		int shiftShortcutMask = InputEvent.SHIFT_DOWN_MASK;
		menuButtonFileNew.setAccelerator(KeyStroke.getKeyStroke('N', shortcutMask));
		menuButtonFileOpen.setAccelerator(KeyStroke.getKeyStroke('O', shortcutMask));
		menuButtonFileSave.setAccelerator(KeyStroke.getKeyStroke('S', shortcutMask));
		menuButtonFileSaveAs.setAccelerator(KeyStroke.getKeyStroke('S', shortcutMask + shiftShortcutMask));
		menuButtonEditClearCanvas.setAccelerator(KeyStroke.getKeyStroke('R', shortcutMask));
		menuButtonNeuralNetworksLearn.setAccelerator(KeyStroke.getKeyStroke('W', shortcutMask));
		menuButtonNeuralNetworksRecognize.setAccelerator(KeyStroke.getKeyStroke('E', shortcutMask));
		menuButtonIncrementValueBy1.setAccelerator(KeyStroke.getKeyStroke('D', shortcutMask));
		menuButtonDecrementValueBy1.setAccelerator(KeyStroke.getKeyStroke('C', shortcutMask));
		//create pane
		
		JButton clearCanvasPaneButton = new JButton("Clear Canvas");
		frame.add(clearCanvasPaneButton);
		
		//setting up listeners
		
		//----file----
		menuButtonFileNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				network = NeuralNetworkFactory.createEmptyNeuralNetwork(10000, 1, 10);
				
				networkLayerPanel.setNeuralNetwork(network);
			}
		});
		menuButtonFileOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog("Please enter the name of the network file");
				if(fileName == null) return;
				network = NeuralNetworkFactory.createNeuralNetworkFromStroredData(fileName);
				
				networkLayerPanel.setNeuralNetwork(network);
			}
		});
		menuButtonFileSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				NeuralNetworkFactory.saveNeuralNetwork(network);
			}
		});
		menuButtonFileSaveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = JOptionPane.showInputDialog("Please enter the name of the file including  extension");
				if(fileName == null) return;
				NeuralNetworkFactory.saveNeuralNetwork(network, fileName);
			}
		});
		//-----edit-----
		ActionListener clearCanvasActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				canvas.clear();
				
			}
		};
		menuButtonEditClearCanvas.addActionListener(clearCanvasActionListener);
		//-----neural-networks-----
		menuButtonNeuralNetworksLearn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NeuralNetworksLearn(canvas, networkLayerPanel, trainingPanel, network);
			}
		});
		menuButtonNeuralNetworksRecognize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new NeuralNetworksRecognize(canvas, networkLayerPanel, trainingPanel,network);
				
			}
		});
		//-----learning-----
		menuButtonIncrementValueBy1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				trainingPanel.setExpected((trainingPanel.getExpected() + 1) % 10);
			}
		});
		menuButtonDecrementValueBy1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int t = trainingPanel.getExpected() - 1;
				trainingPanel.setExpected((t>=0?t:t+9) % 10);
			}
		});
		
		
		
		
		
		//set frame to be visible
		frame.setJMenuBar(bar);
		
		return frame;
	}

	private NeuralNetworkLayerPanel setUpNeuralNetworkDisplay(NeuralNetwork network){
		NeuralNetworkLayerPanel panel = new NeuralNetworkLayerPanel(network);
		return panel;
	}
	
	public static void main(String[] args){
		System.setProperty("apple.laf.useScreenMenuBar", "true");
		new Start();
	}

}
