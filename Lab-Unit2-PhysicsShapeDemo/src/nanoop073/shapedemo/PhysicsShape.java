package nanoop073.shapedemo;
import gchen344.shapes.Circle;
import gchen344.shapes.Rectangle;
import gchen344.shapes.Shape;
import processing.core.PApplet;

public class PhysicsShape {

	private Shape s;
	
	private double vx, vy;
	
	public PhysicsShape(Shape s) {
		this.s = s;
		vx = 0;
		vy = 0;
	}
	
	
	
	public void draw(PApplet surface) {
		s.draw(surface);
	}
	
	public void collideWithBorder(Shape wallU,Shape wallL,Shape wallR, Shape wallD) {
		if (wallU.isTouching(s)||wallL.isTouching(s)||wallR.isTouching(s)||wallD.isTouching(s)) {
			// UNDO THE MOVE
			s.move(s.getX()-vx, s.getY()-vy);
			
			// REACT TO THE COLLISION
			
		}	
			
			if (760<s.getX()) {
				vx=0;
				vy=0;
				s.move(750, s.getY());
			}
			
			if (0>s.getX()) {
				vx=0;
				vy=0;
				s.move(0, s.getY());
			}
			
			if (0>s.getY()) {
				vx=0;
				vy=0;
				s.move(s.getX(), 5);
			}
			
			if (570<s.getY()) {
				vx=0;
				vy=0;
				s.move(s.getX(), 550);
			}
		
	}
	
	
	
	public Shape hitBoxMe() {
		if (s instanceof Circle) {
			Circle c = (Circle)s;
			double w = c.getRadius();  // Whoops, this library returns the radius as the circle's width
			return new Rectangle(c.getX()-w/2,c.getY()-w/2,w,w);
		}
		
		return s;
	}
	
	
	public void act (PhysicsShape wall) {
		s.move(s.getX()+vx, s.getY()+vy);
		System.out.println(s.toString());
		if (wall.hitBoxMe().isTouching(hitBoxMe())) {
			// UNDO THE MOVE
			s.move(s.getX()-vx, s.getY()-vy);
			
			// REACT TO THE COLLISION
			
			vx = -vx*0.7; // Bounce, lose some energy
			vy = -vy*0.7; 
			// To have better bounces/reactions, split up the x and y axes
			
		}
		// APPLY WORLD FORCES
		vx *=0.99;
		vy *=0.99;
	}
	
	public void accelerate(double ax,double ay) {
		vx+= ax;
		vy+= ay;
	}
	
	public boolean isPointInside (double x , double y) {
		return s.isPointInside(x, y);
	}
	
	public double getX() {
		return s.getX();
	}
	
	public double getY() {
		return s.getY();
	}
	
}
