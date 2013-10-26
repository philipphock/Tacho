package de.uniulm.drivingsimulator.speedmeter.ui;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		tachoWidget= new ChangableTachoWidget();
		
		this.getContentPane().add(tachoWidget);
		
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
}
