import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.Timer;

import java.awt.*;

/**
 * @author Akif Imtiaz
 * @date Oct. 2017
 * Program description: This is the main UI galaxy of the game. This class uses the die class to race the
 * two droids, using the DroidImage class, to the finish line. 
 * 
 * OPEN THE "Opening.java" CLASS TO START UP PROGRAM
 */
public class GalaxyGame extends JFrame implements ActionListener {

	// Declaring private JLabel, JText, JButton, ImagePicture and TextPicture GUI variables
	private JLabel lblTitle;   
	private JTextArea score, score1;
	private JButton btnRoll, btnRestart, btnExit;  
	private ImagePicture lblBackground, r2d2, cp30, turn1, turn2;
	private TextPicture t, droidTurn;
	private int turn; // Declare private integer variable for alternating turns

	// Declaring private variables for the droids position and steps
	private int xPos, xPos1, yPos, yPos1, steps, steps1; 
	private Timer timer;   // animation timer
	private Die dice[] = new Die [2];   // dice for rolling the number of steps for each droid
	private DroidImage d [] = new DroidImage[4]; // Declaring droids array
	Container frame;  // Declaring the frame

	// Code to get dimensions of the screen size and place the frame accordingly
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int)screenSize.getHeight();

	public GalaxyGame() {
		// TODO Auto-generated constructor stub
		
		// Set up GUI
		setLayout(null); 
		frame = getContentPane (); 
		setResizable(false); // Make sure window cannot be resized

		// Create texts in GUI of game using TextPicture class
		t = new TextPicture ("THE GALAXY GAME", 130, 450); // Title 
		t.setFont(new Font("Times New Roman", Font.BOLD,45)); // Set font
		t.setColor(Color.GREEN); // Set colour
		t.setBounds(50, -400 ,600, 550); // Set location of the text
		frame.add(t); // Add to JFrame
		
		droidTurn = new TextPicture ("Droid turn:", 130, 450); // Text 
		droidTurn.setFont(new Font("Times New Roman", Font.BOLD, 22));
		droidTurn.setBounds(695, -70, 600, 550);
		droidTurn.setColor(Color.BLACK);
		frame.add(droidTurn);

		// Creating GUI elements
		lblBackground = new ImagePicture (new ImageIcon ("background2.png")); // Create background of game image
		btnRoll = new JButton ("Roll die!"); // Button text
		btnRoll.setFont(new Font("Times New Roman", Font.PLAIN, 16)); // Set font
		btnRestart = new JButton ("Restart"); 
		btnRestart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		btnExit = new JButton ("Exit"); 
		btnExit.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		score = new JTextArea (); // Create area for score information
		score.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		score1 = new JTextArea ();
		score1.setFont(new Font("Times New Roman", Font.PLAIN, 14));

		// Create droids using the DroidImage class and give them pictures
		d [0] = new DroidImage ((new ImageIcon ("r2d2.gif")), 0,0);
		d [1] = new DroidImage ((new ImageIcon ("test1.png")), 0, 0);
		d [2] = new DroidImage ((new ImageIcon ("r2d2.gif")), 0, 0);
		d [3] = new DroidImage ((new ImageIcon ("c3p0.gif")), 0, 0);

		// Setting the bounds of each GUI element
		btnRoll.setBounds(813, 200, 150, 50);
		btnRestart.setBounds (813, 250, 150, 50);
		btnExit.setBounds(813, 300, 150, 50);
		lblBackground.setBounds(0, -50, 810, 750);
		d[0].setBounds(20, 50, 800, 400);
		d[1].setBounds(20, 200, 800, 800);
		d[2].setBounds(840, 400, 800, 400);
		d[3].setBounds(840, 400, 800, 800);
		score.setBounds(813, 30, 150, 30);
		score1.setBounds(813, 60, 150, 30);

		// Dice gif 
		ImagePicture diceGif; // New image varibale
		diceGif = new ImagePicture (new ImageIcon ("dice.gif")); // Create gif
		diceGif.setBounds(800, 90, 800, 400);
		frame.add(diceGif);

		// Add all the GUI elements to the frame
		frame.add(score1);
		frame.add(score);
		frame.add(btnRestart);
		frame.add(btnRoll);
		frame.add(btnExit);
		frame.add(d[0]);
		frame.add(d[1]);
		frame.add(d[2]);
		frame.add(d[3]);
		frame.add(lblBackground);

		// Set original alternating turn picture visibility to false
		d[2].setVisible(false);
		d[3].setVisible(false);
		
		score.setEditable(false); // Make sure user cannot edit output text

		// Make buttons clickable
		btnRoll.addActionListener(this);
		btnRestart.addActionListener (this); 
		btnExit.addActionListener (this); 

		// Set the location of the frame in the middle of the screen
		setLocation (width/2 - 440, height/2 - 380); 
		setSize (980, 700);     // Set frame size
		setVisible (true);      // Show frame

		Random r = new Random(); // Randomize which droid goes first 
		int Low = 0;
		int High = 2;
		int turn = r.nextInt(High-Low) + Low; // Variable for the droid's turn
		if (turn == 0) {
			d[3].setVisible(true);
		}
		else {
			d[2].setVisible(true);
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GalaxyGame(); // Start new Galaxy Game
	}

	// Method for buttons pressed
	public void actionPerformed(ActionEvent e) 
	{ 
		if (e.getSource () == btnRoll) // If the user clicks roll die
		{
			// Get positions of each droid
			xPos = d[0].getxPos();
			yPos = d[0].getyPos();
			xPos1 = d[1].getxPos();
			yPos1 = d[1].getyPos();

			// Roll the two die 
			for (int i = 0; i < dice.length; i++) {
				dice[i] = new Die();
				dice[i].rollDie();
			}
			// Get sum of the two different die for each droid
			int sum = dice[0].getValue() + dice[1].getValue();

			if (turn%2 == 0) { // Switch turns between the two droids
				d[2].setVisible(false);
				d[3].setVisible(true);
				if (xPos > 595) { // If the first droid reaches x location of 600, if he wins
					// Get number of steps it took
					steps = d[0].getSteps();
					steps1 = d[1].getSteps();
					// Display message
					JOptionPane.showMessageDialog(null, "R2D2 Won! \nNumber of steps: \n"
							+ "R2D2: " + steps + "\n"
							+ "C3P0: " + steps1);
					btnRoll.setEnabled(false);
				}
				else { // Before any of the droids reach an x location of 600
					if (yPos > 140) { // If statement to move horizontally 
						if (sum == 2 || sum == 3 || sum == 12) { // If the sum of the die are 2, 3, or 12
							d[0].move(0); // Move 0
							score.setText("R2D2 rolled a " + sum + ".\nHe moved 0!");
						}
						else if (sum == 4){ // If the sum of the two dice is 4
							d[0].move(sum); // move 4
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 5){ // If the sum of the two dice is 5
							d[0].move(sum); // Move 5
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 6){ // If the sum of the two dice is 6
							d[0].move(sum); // Move 6
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 7){ // If the sum of the two dice is 7
							d[0].move(sum); // Move 7
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 8){ // If the sum of the two dice is 8
							d[0].move(sum); // Move 8
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 9){ // If the sum of the die is 9
							d[0].move(sum); // move 9
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 10){ // if the sum of the die is 10
							d[0].move(sum); // move 10
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 11){ // if the sum of the die is 11
							d[0].move(sum); // move 11
							score.setText("R2D2 rolled a " + sum);
						}
					}
					else if (xPos > 410) { // If statement to move droid vertically
						if (sum == 2 || sum == 3 || sum == 12) {
							d[0].movey(0); // Same thing except move vertically
							score.setText("R2D2 rolled a " + sum + ".\nHe moved 0!");
						}
						else if (sum == 4){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 5){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 6){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 7){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 8){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 9){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 10){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 11){
							d[0].movey(sum);
							score.setText("R2D2 rolled a " + sum);
						}
					}
					else { // If statement to initially move droid
						if (sum == 2 || sum == 3 || sum == 12) { // Same thing
							d[0].move(0);
							score.setText("R2D2 rolled a " + sum + ".\nHe moved 0!");
						}
						else if (sum == 4){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 5){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 6){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 7){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 8){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 9){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 10){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
						else if (sum == 11){
							d[0].move(sum);
							score.setText("R2D2 rolled a " + sum);
						}
					}
				}
			}
			else  {
				d[2].setVisible(true);
				d[3].setVisible(false);
				for (int i = 0; i < dice.length; i++) {
					dice[i] = new Die();
					dice[i].rollDie();
				}
				int sum2 = dice [0].getValue() + dice[1].getValue();

				if (xPos1 > 595) { // If the second droid reaches an x location of 600, if he wins
					// Get steps
					steps = d[0].totalSteps();
					steps1 = d[1].totalSteps();
					JOptionPane.showMessageDialog(null, "C3P0 Won! \nNumber of steps: \n"
							+ "R2D2: " + steps + "\n"
							+ "C3P0: " + steps1);
					btnRoll.setEnabled(false);
				}
				else { // Same thing except for the second droid

					if (yPos1 < -160) {
						if (sum2 == 2 || sum2 == 3 || sum2 == 12) {
							d[1].move(0);
							score1.setText("C3P0 rolled a " + sum2 + ".\nHe moved 0!");

						}
						else if (sum2 == 4){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);

						}
						else if (sum2 == 5){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);

						}
						else if (sum2 == 6){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);

						}
						else if (sum2 == 7){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);

						}
						else if (sum2 == 8){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);

						}
						else if (sum2 == 9){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 10){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 11){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
					}
					else if (xPos1 > 410) {
						if (sum2 == 2 || sum2 == 3 || sum2 == 12) {
							d[1].movey(0);
							score1.setText("C3P0 rolled a " + sum2 + ".\nHe moved 0!");
						}
						else if (sum2 == 4){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 5){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 6){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 7){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 8){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 9){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 10){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
						else if (sum2 == 11){
							sum2 = sum2 * (-1);
							d[1].movey(sum2);
							score1.setText("C3P0 rolled a " + Math.abs(sum2));
						}
					}
					else {
						if (sum2 == 2 || sum2 == 3 || sum2 == 12) {
							d[1].move(0);
							score1.setText("C3P0 rolled a " + sum2 + ".\nHe moved 0!");
						}
						else if (sum2 == 4){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 5){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 6){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 7){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 8){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 9){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 10){
							d[1].move(sum2);
							score1.setText("C3P0 rolled a " + sum2);
						}
						else if (sum2 == 11){
							d[1].move(sum2);
							score1.setText("CP30 rolled a " + sum2);
						}
					}
				}
			}
			turn++; // Switch turns
		}

		// If user clicks exit button
		if (e.getSource () == btnExit)
		{ 
			System.exit(0); // Close program
		}
		// If user clicks restart button
		else if (e.getSource() == btnRestart)
		{ 
			// Set droids locations back to start and set number of steps to 0
			btnRoll.setEnabled(true);
			d[0].setxPos(0);
			d[0].setyPos(0);
			d[1].setxPos(0);
			d[1].setyPos(0);
			d[0].setSteps(0);
			d[1].setSteps(0);
			score.setText(" ");
			score1.setText(" ");
		}
	}
}
