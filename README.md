#Neural Network 001

##A simple investigation into Java Programing and Artificial Neural Network.

###About
The network uses **two** layers neurons. The first layer contains 10000 neuron inputs,* (100 px width by 100 px height single color image { double[][] } )*. The second layer contains 10 neurons output, *(strenths for number 0 ~ 9 ).* 

###Install
The app is compiled using JVM 8, with the main method in src.ui.Start. Using eclipse to import project and run the project is the recommended approach of running the program. See the file saving and loading for more information.

###GUI Interface
On the top left corner locates the drawing panel. Top box indicates the state of the application and the messages being sent. On the top right corner locates the training box, indicating the training information and the output information. On the bottom locates the network indication box, indicating the weights attached to each neuron connections. Black indicates negative weights while white indicates positive. The idea is gained via Neural Networks for Machine Learning, Lecture 1, by University of Torento, on Coursera.

###Saving and Loading
This application uses a serialization-based object streaming method for storing data. Upon launching, the app searches for file "NeuralNetwork.data" in the running directory. If the file is not found, a new network is created and saved to this file. Use "Save As.." to retain a copy (no overwritten warnings displayed) and use "Open.." to open that copy. Note that "Save" will always save the current neural network to file "NeuralNetwork.data" no matter what the opened file is. Upon application closing, "Save" will be invoked automatically.  

###Inefficiencies and Defects
This code is written by a novice programmer who wants to implement the simple linear neural network algorithms. There are a few potential improvements that could be added:
The actual training image is not saved for later training. 
File saving and loading functions could be improved.

###Forking and Updates
Every one is welcomed use and investigate the code. Pull requests are welcomed. It is also recommended to leave a message to the software author on GitHub. 
