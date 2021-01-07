import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * Author: Sanjesh 
 * Date: November 2017
 * Description: This class is used to set the number of steps and get the number of steps. This class is also used to move the picture by
 * changing x and y position. 
 * Also this class has a self testing main method.
 *
 */

public class DroidImage extends ImagePicture{

	private int steps;	// Private data for steps taken
	private int stepSize = 8; // Size of one step

	public DroidImage(ImageIcon img, int x, int y) { // constructor 
		super(img, x, y); // calling super constructor 
		this.steps = 0; // setting steps to 0
		repaint();
	}


	public void setSteps (int steps) { // method to set steps
		this.steps = steps; // setting steps
	}


	public int getSteps() { // method to get steps
		return this.steps; // returning steps
	}



	public void move (int steps) { // method to move
		int x = this.getxPos() + steps*this.stepSize; // creating int and getting x position and adding steps
		this.setxPos(x); // setting steps with new int value
		this.steps = this.steps + steps; // adding to steps

	}

	public void movey (int steps) { // method to move
		int x = this.getyPos() + steps*this.stepSize; // creating int and getting y position and adding steps
		this.setyPos(x); // setting steps with new int value
		this.steps = this.steps + steps; // adding to steps
	}

	public int totalSteps () { // method for total steps taken by droid
		return this.getSteps(); // returning Droid's steps
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) { // self testing main method
		// TODO Auto-generated method stub
		TextPicture text;
		JFrame f = new JFrame (); // creating frame

		text = new TextPicture ("Self Testing Main Method", 0, 25); // creating object
		text.setColor(Color.ORANGE); // setting color
		text.setBounds(0, 0, 100, 30); // setting bounds

		DroidImage d [] = new DroidImage [2]; // creating DroidImage array
		d[0] = new DroidImage(new ImageIcon("Droid2.png"), 100, 30); // creating DroidImage
		d[1] =  new DroidImage(new ImageIcon("Droid2.png"), 150, 175);


		//DroidImage p = new DroidImage(new ImageIcon("minion.png"), 0, 0); // creating DroidImage object with correct inputs for constructor
		f.setSize (400,400); // setting size of frame
		f.add(d[0]); // adding DroidImage to the frame
		f.setVisible(true); // setting visible to true
		f.add(d[1]); // add DroidImage to frame
		f.setVisible(true);// setting visible to true
		f.add(text); // adding TextPicture
		f.setVisible(true); // setting Visible true

		JOptionPane.showMessageDialog(null, "Wait"); // used to slow down the computer to see it complete the task before and after dialog box 
		int i =0; // int for do while loop
		do { // do while loop
			d[0].move(1); // calling method to move number of steps
			d[1].move(1);
			try { // to delay
				Thread.sleep(50); // 50 milli seconds delay
			}
			catch(InterruptedException e) {
				System.out.println(e.getMessage()); // error message
			}
			i++; // adding one to loop
		}
		while(i<10); // condition for do while loop 

		JOptionPane.showMessageDialog(null, "Wait"); // used to slow down the computer to see it complete the task before and after dialog box 
		int k =0; // int for do while loop
		do { // do while loop
			d[0].movey(1) ; // calling method to move number of steps
			d[1].movey(1);
			try { // to delay
				Thread.sleep(50); // 50 milli seconds delay
			}
			catch(InterruptedException e) {
				System.out.println(e.getMessage()); // error message
			}
			k++; // adding one to loop
		}
		while(k<10); // condition for do while loop 

		d[0].move(1); // Move DroidImage 1 by 1 step

		System.out.println(d[0].totalSteps()); // testing totalSteps method
		System.out.println(d[1].totalSteps()); // testing totalSteps method




	}

}
