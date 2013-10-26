package de.uniulm.drivingsimulator.speedmeter.ui;

import java.util.Observable;
import java.util.Observer;


public class ChangableTachoWidget extends TexturedTachoWidged implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
		Double speed = (Double) arg;
		updateSpeed(speed);
	}

	
	


	

}
