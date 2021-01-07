import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Author: Akif Imtiaz
 * Date: October 2017
 * Description: Displays the word "My Title" on a JFrame and perform various changes to it
 */

public class TextPicture extends Picture {

	// Private data for Text and Font
	private String myTitleText;    
	private Font myFont;
	
	//default Constructor
	public TextPicture() {
		super();
		this.myFont = new Font("Monospaced", Font.PLAIN, 12);
		this.myTitleText = "";
	}
	
	// Constructor to for specific location and title
	public TextPicture(String title, int x, int y) {
		super();
		setxPos(x);
		setyPos(y);
		this.myFont = new Font("Monospaced", Font.PLAIN, 12);
		this.myTitleText = title;
		repaint();
	}
	
	// setter for font
	public void setFont(Font f) {
		this.myFont = f;
	}

	// paint method
	public void paint (Graphics g) {
		g.setColor (this.getColor());
		g.setFont(this.myFont);
		g.drawString(this.myTitleText, getxPos(), getyPos());
	}
	
	public static void main(String[] args) {
		// self testing main method;            
		JFrame f = new JFrame("Part C");
		
		String title = "My title";
		
		TextPicture p = new TextPicture(title, 10, 20);
		
		p.setFont(new Font("Serif", Font.ITALIC, 24));
		
		f.setSize(400,350);  // size for graphics
		f.add(p);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(Color.BLUE);
		
		JOptionPane.showMessageDialog(null,"Wait");
		p.setColor(50,50,120);  
		
		JOptionPane.showMessageDialog(null,"Wait");

		p.setxPos(100);
		p.setyPos(100);
		
		p.repaint();  // repaint picture
	}
}
