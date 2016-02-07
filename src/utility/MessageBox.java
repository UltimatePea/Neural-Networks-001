package utility;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MessageBox {
	
	public static JPanel panel;
	public static JLabel label ;
	
	public static JPanel getJPanel(){
		if (panel == null) {
		
			panel = new JPanel();
			label = new JLabel("Message Box : Ready");
			panel.add(label);
			panel.setPreferredSize(new Dimension(400, 40));
		}
		return panel;
	}
	
	public static void info(String info){
		label.setText(info);
		System.out.println("Info: " + info);
	}
	
	public static void warning(String warning){
		label.setText(warning);
		System.out.println("Warning: " + warning);
	}

}
