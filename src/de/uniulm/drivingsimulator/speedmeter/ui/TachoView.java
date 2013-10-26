package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class TachoView extends JFrame{
	private static final long serialVersionUID = 3481634193479144614L;

	private final ChangableTachoWidget tachoWidget;
	
	public Observer getTachoObserver(){
		return tachoWidget;
	}

	public TachoView() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tachoWidget= new ChangableTachoWidget();
		
		this.getContentPane().add(tachoWidget);
		
		setLocationRelativeTo(null);
		setVisible(true);
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {	
				switch(e.getKeyCode()){
				case KeyEvent.VK_UP:
					tachoWidget.updateSpeed(tachoWidget.getSpeed()+5);
					break;
					
				case KeyEvent.VK_DOWN:
					tachoWidget.updateSpeed(tachoWidget.getSpeed()-5);
					break;
					 
				}
			}
		});
	}
	
}
