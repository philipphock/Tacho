package de.uniulm.drivingsimulator.speedmeter.main;

import de.uniulm.drivingsimulator.speedmeter.net.TCPClient;
import de.uniulm.drivingsimulator.speedmeter.ui.TachoView;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TCPClient c = new TCPClient();
		
		TachoView t = new TachoView();
		c.addObserver(t.getTachoObserver());
	}

}
