package nanoop073.shapedemo;

import java.awt.Color;

import gchen344.shapes.Circle;
import gchen344.shapes.Rectangle;
import processing.core.PApplet;


public class DrawingSurface extends PApplet {

	private PhysicsShape shape, shape2;
	private Rectangle wallU, wallR, wallD, wallL;
	private PhysicsShape holding;
	
	//Rectangle(c.getX()-w/2,c.getY()-w/2,w,w)
	public DrawingSurface() {
		shape =  new PhysicsShape(new Rectangle(400,400,50,50));
		shape2 = new PhysicsShape (new Circle(50,300,300));
		wallU = new Rectangle (0,0,800,0);
		wallL = new Rectangle (-100,0,100,600);
		wallR = new Rectangle (800,0,100,600);
		wallD = new Rectangle (0,600,800,0);
		holding = null;
	}
	
	public void settings() {
		setSize(800,600);
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {

	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() {
		// change
		shape.collideWithBorder(wallU,wallL,wallR,wallD);
		
		
		shape.act(shape2);
		
		// draw 
		background(255);
		shape.draw(this);
		shape2.draw(this);
		wallU.draw(this);
		wallD.draw(this);
		wallL.draw(this);
		wallR.draw(this);
		
		push();
		
		textSize(16);
		fill(0);
		
		text("Left Mouse Button moves one rectangle. \nClick and drag and release to throw.",10,50);
		pop();
		
	}
	
	public void mousePressed() {
		if (shape.isPointInside(mouseX, mouseY)) {
			holding = shape;
		
			//shape.accelerate(1, 0);
		}
		if (shape2.isPointInside(mouseX, mouseY)) {
			holding = shape2;
		
			//shape.accelerate(1, 0);
		}
	}
	
	public void mouseReleased () {
		if (holding != null && holding == shape) {
			shape.accelerate(mouseX-pmouseX, mouseY-pmouseY );
			holding = null;
		}
		if (holding != null && holding == shape2) {
			shape2.accelerate(mouseX-pmouseX, mouseY-pmouseY );
			holding = null;
		}
	}
	
}


