package de.uniulm.drivingsimulator.speedmeter.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class TexturedTachoWidged extends AbstractTachoWidget{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -400294609997588417L;
	private Image background;
	private Image needle;
	
	private Point needle_mid = new Point(397, 449);
	
	
	
	public TexturedTachoWidged(){
		setSize(800, 600);
		setVisible(true);

		background = (new ImageIcon("./img/background_2.png")).getImage();
		needle = (new ImageIcon("./img/needle.png")).getImage();
	}
	
	

	@Override
	protected void drawBackground(Graphics2D g) {
		g.drawImage(background, 0,0,null);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.GREEN);
		g.drawOval(394, 446, 6, 6);
		
	}

	@Override
	protected void drawBackgroundWithNeedleStartRotation(Graphics2D g) {
		//XXX: not in use
		g.rotate(Math.PI);
		Color c = new  Color((int)(getSpeed()%255),(int)(255-(getSpeed()%255)),0);
		g.setColor(c);
		g.setStroke(new BasicStroke(8));
		g.drawArc(-716/2-4, -711/2, 717, 716, 0,-1*(int)( Math.toDegrees(step1Speed)*getSpeed()));//-1*(int)( Math.toDegrees(step1Speed)*getSpeed())
		g.rotate(-Math.PI);

//		g.rotate(Math.PI);
//		g.drawArc(0, 0, 500, 500, 0, -getTachoMaxArc());
//		double rotation=0;
//		for (int i = 0;i<speedStepCount;i++){	
//			g.rotate(step1Speed*speedSteps);
//			rotation+=step1Speed*speedSteps;	
//		}
//		g.rotate(-rotation);
//		g.rotate(-Math.PI);
	}

	@Override
	protected void drawNeedle(Graphics2D g) {
		g.drawImage(needle, -236, -19, 255, 37, null);
	}

	@Override
	protected void drawOnNeedleSpeedRotation(Graphics2D g) {
		g.setColor(Color.GREEN);
		//Neddle anchor should be @ approx 400/155
		//g.fillOval(-stepRadius-155, 0, 12, 12);
	}

	@Override
	protected void onResize() {
	}

	@Override
	protected Point getNeedleLocation() {
		return needle_mid;
	}

	@Override
	protected int getNeedleRadius() {
		return 600;
	}

	@Override
	protected double getNeedleStartRotation() {
		return -0.15;
	}

	@Override
	protected int getTachoMaxSpeed() {
		return 200;
	}

	@Override
	protected int getTachoMaxArc() {
		return 180;
	}

	@Override
	protected void drawFrom00Center(Graphics2D g) {
//		g.setColor(Color.BLACK);
//		for (int i = 0; i < speedStepCount; i++) {
//			int x = (int) (stringDistanceToCenter
//					* Math.cos(i * step1Speed * speedSteps - Math.toRadians(187)));
//			int y = (int) (stringDistanceToCenter
//					* Math.sin(i * step1Speed * speedSteps - Math.toRadians(187)));
//			g.drawString((i * speedSteps) + "", x-5, y-5);
//		}
//		
//		g.setColor(Color.RED);
//		g.fillArc(-5,-5,10,10,0, 360);
//		
//		g.setColor(Color.BLACK);
//		g.drawString((int)getSpeed()+"", -10.0f, 50.0f);
	}

}
