package de.uniulm.drivingsimulator.speedmeter.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Observable;

public class TCPClient extends Observable implements Runnable{

	private final int PORT = 1338;
	//sprivate final String ADRESS = "134.60.232.96";
	private final String ADRESS = "127.0.0.1";
	private Socket s;
	private BufferedReader br;
	private final Thread listent;
	
	public TCPClient() {
		listent=new Thread(this);
		try {
			s = new Socket(ADRESS,PORT);
			s.getInputStream();
			listent.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true){
			try {
				String speed = br.readLine();
				setChanged();
				notifyObservers(Double.parseDouble(speed));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
