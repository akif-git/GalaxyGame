import javax.swing.*;
import java.awt.*;
/**
 * Author: Akif Imtiaz
 * Date: October 2017
 * @Description: Draws a eye image on a JFrame and makes various changes to the image
 */
public class Picture extends JComponent {

	// private data for colour, location and size
	private Color c;
	private int xPos, yPos, width, height;

	// default constructor
	public Picture() {        
		this.c = Color.RED;
		this.xPos = 0;
		this.yPos = 0;
		this.width = 50;
		this.height = 25;
		repaint();
	}

	// constructor for specified location
	public Picture (int x, int y, int w, int h)		
	{
		this.c = Color.RED;
		this.xPos = x;
		this.yPos = y;
		this.width = w;
		this.height = h;
		repaint();
	}
	
	// paint method
	public void paint (Graphics g)  
	{   
		g.setColor(this.c);  // set the new colour
		g.drawRect(this.xPos, this.yPos, this.width, this.height);  // draw shape
		g.drawOval(this.xPos, this.yPos, this.width, this.height);  // draw shape
		g.fillOval(this.xPos + this.width/3, this.yPos, this.width/3, this.height);  // draw shape
	}

	// self testing main method   
	public static void main (String args[]){

		JFrame f = new JFrame("Part A");
		Picture p = new Picture();

		f.setSize(400,350);  // size for graphics
		f.add(p);
		f.setVisible(true);

		JOptionPane.showMessageDialog(null,"Wait"); // Allows the user to click "Ok" before image colour is switched
		p.setColor(Color.BLUE);

		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(50,50,120);  
		JOptionPane.showMessageDialog(null,"Wait");

		Picture p1 = new Picture(200, 50, 100, 50);
		f.remove(p);

		f.add(p1);
		f.setVisible(true);  // repaint frame
		
		JOptionPane.showMessageDialog(null,"Wait");   //changes the position of the eye, and reverts its height and width
		Picture p2 = new Picture(50, 115, 125, 80);
		f.remove(p1);
		f.add(p2);
		f.setVisible(true); // repaint fame

		JOptionPane.showMessageDialog(null,"Wait");
		p1.setxPos(100);

		p1.repaint();  // repaint picture
	}

	// setter and getter methods for color
	public void setColor (Color col)
	{
		this.c = col;
		repaint();
	}

	public void setColor (int r, int g, int b)
	{
		this.c = new Color (r,g,b);
		repaint();
	}

	public Color getColor() {
		return c;
	}

	// setter and getter methods for location
	public int getxPos() {
		return xPos;
	}

	protected void setxPos(int xPos) {
		this.xPos = xPos;
		repaint();
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
		repaint();
	} 
	public void setHeight(int height) {
		this.height = height;
	}
}