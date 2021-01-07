import java.applet.Applet; // imports
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel; 
/**
 * Authors: Akif, Sanjesh, and Faraz
 * Date: Nov 2017
 * Description: This is the opening screen for the game. This class has an exit button, start button to go to the actual game and button with instruction
 */
public class Opening extends JFrame implements ActionListener{

	// Declaring GUI elements
	private TextPicture p, choose; // text picture for welcome 
	private Font font; 
	GalaxyGame image;
	private JButton btn, btninstruction, btnexit, r2d2, c3p0;
	private JFrame f;

	public Opening () {
		super ("Welcome");
		// Akif
		setLayout(null);
		ImageIcon img = new ImageIcon("background.gif"); // creating image for background
		f = new JFrame (); // creating JFrame
		JLabel back = new JLabel (img); // creating JLabel
		//back.setSize(600, 600); // setting size for background
		back.setBounds(0, 0, 800, 800); // setting bounds for the background 
		setResizable(false);

		// Faraz
		String title = "WELCOME TO GALAXY GAME!"; // title for TextPicture
		p = new TextPicture(title,60, 60); // creating TextPicture
		p.setColor(Color.MAGENTA); // setting color
		p.setBounds(0, 0, 600, 200); // setting bounds for TextPicture
		p.setFont(new Font("TimesRoman", Font.PLAIN, 30)); // font for TextPicture

		String title1 = "Choose your droid!"; // title for TextPicture
		choose = new TextPicture(title1,190, 90); // creating TextPicture
		choose.setColor(Color.MAGENTA); // setting color
		choose.setBounds(0, 0, 600, 200); // setting bounds for TextPicture
		choose.setFont(new Font("TimesRoman", Font.PLAIN, 20)); // font for TextPicture

		// Declare droid buttons (Akif)
		r2d2 = new JButton ("R2D2");
		r2d2.setBounds(140, 250, 80, 30);
		r2d2.addActionListener(this);
		c3p0 = new JButton ("C3P0");
		c3p0.setBounds(320, 250, 80, 30);
		c3p0.addActionListener(this);

		// Sanjesh
		btninstruction = new JButton ("Instructions"); // creating button
		btninstruction.setBounds(165, 295, 120, 50); // setting bounds
		btninstruction.addActionListener(this); // making this class listen

		btnexit = new JButton ("Exit"); // creating button
		btnexit.setBounds(280, 295, 100, 50); // setting bounds
		btnexit.addActionListener(this); // making this class listen

		// Sanjesh
		DroidImage d [] = new DroidImage[2]; // array of DroidImages

		d[0] = new DroidImage (new ImageIcon("smallerr2d2.gif") , 0,0); // creating DroidImages and setting bounds
		d[0].setBounds(125, 115, 100, 200);
		d[1] = new DroidImage (new ImageIcon("c3p0.gif") , 0,0);
		d[1].setBounds(330, 125, 100, 200);

		f.setSize(580, 420); // setting size of the frame

		// adding buttons, TextPicture, background and DroidImages to the frame
		f.add(p); 
		f.add(btninstruction);
		f.add(btnexit);
		f.add(choose);
		f.add(d[0]);
		f.add(d[1]);
		f.add(c3p0);
		f.add(r2d2);
		f.add(back);

		f.setVisible(true);
	}

	public void actionPerformed (ActionEvent e) // action performed
	{
		//  Faraz
		if (e.getSource () == r2d2)  // if the start buttton is clicked
		{
			dispose(); // close window
			image = new GalaxyGame(); //open second window
			f.setVisible(false); 

		}
		else if (e.getSource()== c3p0) {
			dispose(); // close window
			image = new GalaxyGame(); //open second window
			f.setVisible(false);  
		}
		else if(e.getSource() == btninstruction) { // if the instruction button is clicker

			// display dialog box
			JOptionPane.showMessageDialog(null, "There are 2 droids racing each other. A random droid out of the two will start each time. \n"
					+ "When 'Roll die' is pressed, 2 dice are rolled.\n"
					+ "Then, either C3P0 or R2D2, depending on who's turn it is, \n"
					+ " take the sum of their 2 dice, and move that number of steps ahead. \n"
					+ "If 2, 3, or 12 is rolled, the droid will not move. \n"
					+ "First droid to get to the finish line wins. \n"
					+ "You choose which droid you think will win at the beginning!");
		}

		else if (e.getSource() == btnexit) { // if the exit button is clicked
			System.exit(0); // exit
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Opening o = new Opening (); // running program
		File music = new File ("music.wav"); // Get music file
		PlaySound(music); // Play music file
	}
	static void PlaySound(File Sound){ // Method to play music by Akif
		try {
			Clip audio = AudioSystem.getClip();
			audio.open(AudioSystem.getAudioInputStream(Sound));
			audio.start();

			Thread.sleep(audio.getMicrosecondLength()/1000);
		} catch (Exception e)
		{

		}
	}

}
