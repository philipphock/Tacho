package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class BasicTachoWidget extends AbstractTachoWidget{


	private static final long serialVersionUID = 1L;
	
	/**
	 * needed for rendering, the steps shown at the speedmeter
	 */
	private final int speedSteps = 20;
	
	/**
	 * needed for rendering, the amount of steps to render 
	 */
	private final int speedStepCount;
	
	/**
	 * needed for rendering, the height of the step | 
	 */
	private final int stepRadius=200;
	
	/**
	 * needed for rendering, the distance to draw the speed-strings from the center
	 */
	private int stringDistanceToCenter=215;
	
	
	/**
	 * where the middle is
	 */
	private Point middle;
		
	public BasicTachoWidget() {
		speedStepCount = getTachoMaxSpeed()/speedSteps +1;
	}
	
	/**
	 * neede for rendering, draws a step |
	 * @param g the graphics to draw to
	 */
	protected void drawStep(Graphics2D g){
		g.drawLine(stepRadius,0,stepRadius+10,0);
	}

	
	//not implemented
	@Override
	protected void drawBackground(Graphics2D g) {
		

		
	}

	@Override
	protected void drawBackgroundWithNeedleStartRotation(Graphics2D g) {
		g.rotate(Math.PI);											//fix rotation
		g.drawArc(-200, -200, 400, 400, 0, -getTachoMaxArc());		//draw the arc
		double rotation=0;
		
		//draw the steps
		for (int i = 0;i<speedStepCount;i++){						
			drawStep(g);	
			g.rotate(step1Speed*speedSteps);
			rotation+=step1Speed*speedSteps;	
		}
		g.rotate(-rotation);
		g.rotate(-Math.PI);
	}

	@Override
	protected void drawNeedle(Graphics2D g) {
		g.setColor(Color.RED);
		g.drawLine(0, 0, -200, 0);
	}

	@Override
	protected void drawOnNeedleSpeedRotation(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillOval(-stepRadius-4, -3, 6, 6);
		
	}
	
	/**
	 * draw speed strings
	 */
	@Override
	protected void drawFrom00Center(Graphics2D g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < speedStepCount; i++) {
			int x = (int) (stringDistanceToCenter
					* Math.cos(i * step1Speed * speedSteps - Math.toRadians(187)));
			int y = (int) (stringDistanceToCenter
					* Math.sin(i * step1Speed * speedSteps - Math.toRadians(187)));
			g.drawString((i * speedSteps) + "", x-5, y-5);
		}
		
		g.setColor(Color.RED);
		g.fillArc(-5,-5,10,10,0, 360);
		
		g.setColor(Color.BLACK);
		g.drawString((int)getSpeed()+"", -10.0f, 50.0f);
		
	}

	@Override
	protected void onResize() {
		middle = getAbsolute(0.5f, 0.5f);
		middle.y=300;
		
	}

	@Override
	protected Point getNeedleLocation() {

		return middle;
	}

	@Override
	protected int getNeedleRadius() {
		return 400;
	}

	@Override
	protected double getNeedleStartRotation() {
		return -0.1;
	}

	@Override
	protected int getTachoMaxSpeed() {
		return 250;
	}

	@Override
	protected int getTachoMaxArc() {
		return 190;
	}

	

	
	
}

