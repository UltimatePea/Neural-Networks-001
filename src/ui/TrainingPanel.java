package ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TrainingPanel extends JPanel {

	JTextField expectedValueTextField;
	JLabel producedValueLabel;
	public TrainingPanel(){
		this.setLayout(new GridLayout(2, 2));
		this.add(new JLabel("Expected: "));
		expectedValueTextField = new JTextField("0");
		this.add(expectedValueTextField);
		this.add(new JLabel("Produced: "));
		producedValueLabel = new JLabel("[Produced Value]");
		this.add(producedValueLabel);
		
	}
	
	public int getExpected(){
		return Integer.parseInt(expectedValueTextField.getText());
	}
	
	public void setExpected(int expected){
		expectedValueTextField.setText(String.valueOf(expected));
	}
	
	public void setProduced(int produced){
		producedValueLabel.setText(String.valueOf(produced));
	}
	
}
