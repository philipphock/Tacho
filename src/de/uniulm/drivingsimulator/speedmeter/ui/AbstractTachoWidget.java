package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

public abstract class AbstractTachoWidget extends JComponent{

	private static final long serialVersionUID = 4405549361010059300L;
	
	private volatile double speed = 60;
	
	protected abstract void drawBackground(Graphics2D g);
	protected abstract void drawBackgroundWithNeedleStartRotation(Graphics2D g);
	protected abstract void drawNeedle(Graphics2D g);
	protected abstract void drawOnNeedleSpeedRotation(Graphics2D g);
	protected abstract void onResize();
	
	protected abstract Point getNeedleLocation();
	protected abstract int getNeedleRadius();
	protected abstract double getNeedleStartRotation();
	protected abstract int getTachoMaxSpeed();
	protected abstract int getTachoMaxArc();
	protected abstract void drawFrom00Center(Graphics2D g);
	
	public void updateSpeed(double speed){
		this.speed = speed;
		repaint();
	}
	
	
	/**
	 * in radians, the amount of rotation of one speedstep
	 */
	protected double step1Speed = Math.toRadians((double)getTachoMaxArc()/getTachoMaxSpeed());

	protected double getSpeed(){
		return speed;
	}
	
	@Override
	public void paint(Graphics g) {
		onResize();
		double speed = this.speed;
		float rotation=0; 
		Graphics2D g2d = (Graphics2D)g;
		
	
		drawBackground(g2d);

		
		//needle
		g2d.translate(getNeedleLocation().x,getNeedleLocation().y);	//translate begin middle
		drawFrom00Center(g2d);
		g2d.rotate(getNeedleStartRotation()); 						//rotate begin needle 
		rotation+=getNeedleStartRotation();
		drawBackgroundWithNeedleStartRotation(g2d);
		
		
	
		
		g2d.rotate(step1Speed*speed);								//rotate begin speed
		rotation+=step1Speed*speed;
		
		drawNeedle(g2d);
		drawOnNeedleSpeedRotation(g2d);
		
		
		g2d.rotate(-rotation); 										// rotate end,rotate end
		g2d.translate(-getNeedleLocation().x,-getNeedleLocation().y); ////translate end
		rotation=0;
		

		
		super.paint(g2d);
	}

	protected Point getAbsolute(float relativeX, float relativeY){
		
		return new Point((int)(relativeX*getWidth()),(int)(relativeY*getHeight())); 
	}
	
	
}

