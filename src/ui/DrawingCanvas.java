package ui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utility.MessageBox;

public class DrawingCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9175954806946064902L;
	boolean[][] pointsDrawn = new boolean[100][100];
	int lastX, lastY;
	boolean isMouseDown = false;
	public static final int ASPECT_RATIO = 1;
	public Dimension preferredDimension = new Dimension(ASPECT_RATIO * 100, ASPECT_RATIO * 100);
	public DrawingCanvas(){
		super();
		
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				isMouseDown = false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				isMouseDown = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				fillXY25boxes(e.getX() / ASPECT_RATIO, e.getY() / ASPECT_RATIO);
				repaint();
			}
		});
	}
	/*
	public void fillLineFromLastXY(int newX, int newY){
		for (int i = newX; i  == newX ; i = (i + 1 )%(Math.abs(newX - lastX))) {
			for (int j = newY; j  ==  newY ; j = (j+1)%(Math.abs(newY - lastY))) {
				fillXY9boxes(i, j);
			}
		}
	}
	
	*/
	
	public void fillXY25boxes(int x, int y){
		for (int i = -3; i < 4; i++) {
			for (int j = -3; j < 4; j++) {
				try{
				pointsDrawn[x+i][y+j] = true;
				} catch(Exception exception){}
			}
		}
		
	}
	
	public void clear(){
		for (int i = 0; i < pointsDrawn.length; i++) {
			for (int j = 0; j < pointsDrawn[i].length; j++) {
				pointsDrawn[i][j] = false;
			}
		}
		repaint();
	}
	
	
	@Override
	public void paint(Graphics g){
		if(getWidth() < pointsDrawn.length || getHeight() < pointsDrawn[0].length){
			MessageBox.info("Neural network not showing completely");
		}
		
		//paint border
		g.drawRect(0, 0, preferredDimension.width, preferredDimension.height);
		//paint connections
		for (int i = 0; i < pointsDrawn.length; i++) {
			for (int j = 0; j < pointsDrawn[i].length; j++) {
				if(pointsDrawn[i][j]){
					g.fillRect(i * ASPECT_RATIO,j * ASPECT_RATIO, ASPECT_RATIO, ASPECT_RATIO);
				}
			}
		}
	}
	
	
}
