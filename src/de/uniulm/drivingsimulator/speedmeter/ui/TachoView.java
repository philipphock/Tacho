package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observer;

import javax.swing.JFrame;

import de.uniulm.opends.connectivity.opends_xml_interface_subscription.protocol.OpenDSValue;
import de.uniulm.opends.connectivity.opends_xml_interface_subscription.protocol.SubscriptionListener;

public class TachoView extends JFrame implements SubscriptionListener{
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

	@Override
	public void eventReceived(OpenDSValue<?> value) {
		switch (value.getType()) {
		case RPM:
			
			break;
		case SPEED:
			@SuppressWarnings("unchecked")
			OpenDSValue<Double> v = (OpenDSValue<Double>) value;
			tachoWidget.updateSpeed(v.getValue());
		default:
			break;
		}
	}
	
}
