package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JComponent;

/**
 * Provides basic functionalities and predefined methods to implement an analogue speedmeter 
 * This widget fully implements a linear mapping between speed and needle in the paint method
 * @author phil
 *
 */
public abstract class AbstractTachoWidget extends JComponent{

	private static final long serialVersionUID = 4405549361010059300L;
	
	/**
	 * the current speed the widget renders
	 */
	private volatile double speed = 60;
	
	/**
	 * called at first in the painting order to draw background
	 * @param g
	 */
	protected abstract void drawBackground(Graphics2D g);
	
	/**
	 * Called to paint with the rotation the needle has when it's at 0 speed.<br>
	 * This allows for example to draw an Arc from the beginning to the end of the speed-scale  
	 * @param g
	 */
	protected abstract void drawBackgroundWithNeedleStartRotation(Graphics2D g);
	
	/**
	 * Called to draw the needle.<br>
	 * The needle center is at the center-right, and points to the center-left<br>
	 * Rotation handles the baseclass 
	 */
	protected abstract void drawNeedle(Graphics2D g);
	
	/**
	 * Called with the rotation of the current needle rotation
	 * @param g
	 */
	protected abstract void drawOnNeedleSpeedRotation(Graphics2D g);
	
	/**
	 * called on paint
	 */
	protected abstract void onResize();
	
	/**
	 * 
	 * @return the point where the needle should be painted 
	 */
	protected abstract Point getNeedleLocation();
	
	/**
	 * 
	 * @return the radius in pixel of the needle
	 */
	protected abstract int getNeedleRadius();
	
	/**
	 * 
	 * @return the initial rotation (in radians) where the needle is at 0 speed
	 */
	protected abstract double getNeedleStartRotation();
	
	/**
	 * 
	 * @return the maximum of the scale
	 */
	protected abstract int getTachoMaxSpeed();
	
	/**
	 * the opening angle between 0 and max speed
	 * @return
	 */
	protected abstract int getTachoMaxArc();
	
	/**
	 * called with a translation to the needle center
	 * @param g
	 */
	protected abstract void drawFrom00Center(Graphics2D g);
	
	/**
	 * sets a new speed and repaints
	 * @param speed the speed to draw
	 */
	public void updateSpeed(double speed){
		this.speed = speed;
		repaint();
	}
	
	
	/**
	 * in radians, the amount of rotation of one speedstep
	 */
	protected double step1Speed = Math.toRadians((double)getTachoMaxArc()/getTachoMaxSpeed());

	/**
	 * 
	 * @return the current speed
	 */
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

	/**
	 * 
	 * @param relativeX x-pos in percent
	 * @param relativeY y-pos in percent
	 * @return the position translated to pixel coordinates
	 */
	protected Point getAbsolute(float relativeX, float relativeY){
		
		return new Point((int)(relativeX*getWidth()),(int)(relativeY*getHeight())); 
	}
	
	
}

