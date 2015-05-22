//**************************************************
// A-number : A01872123
// Problem : HW9
// Description : A game called "Lunar Lockout"
//**************************************************

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;



public class SlipperyMaze extends JPanel implements ActionListener, ItemListener, KeyListener
{

	private static final int WIDTH = 600;
	private static final int HEIGHT = 400;
	
	private JButton retryB;
	private JButton PlayerB, B1, B2, B3, B4, B5;
	private JButton upB, downB, leftB, rightB;
	private JButton nextB, restartB;
	private JComboBox levelCB;
	private JLabel Level1, Level2, Level3, DestinationL, MessageBox;
	private JTextField moveTF;

	private String[] levelName = {"Level 1", "Level 2", "Level 3"};
	private String currentLevel;
	
	private int countMove = 0;
	private int playerP;  //save player position
	private int B1P, B2P, B3P, B4P, B5P;    //save obstacles position
	private int destinationP;   //store destination position
	private int upP, downP, leftP, rightP, moveAfter, moveObject, winP;  //save movement position
	private int upCheck, downCheck, leftCheck, rightCheck;
	private int [][] array = new int[5][5];
	
	ImagePanel BG = new ImagePanel(new ImageIcon("image/BG1.jpg").getImage());
	
	public SlipperyMaze()
	{
		setLayout(null);

    	//Create the button
    	retryB = new JButton(new ImageIcon("image/retry.jpg"));
    	retryB.setSize(140,43);
    	retryB.setLocation(410,47);
    	retryB.addActionListener(this);
    	
    	//Create a combo box for select level
    	levelCB = new JComboBox(levelName);
    	levelCB.setMaximumRowCount(3);
    	levelCB.setSize(120,30);
    	levelCB.setLocation(412,170);
    	levelCB.addItemListener(this);
    	
    	//Create a text field to count how many move
    	moveTF = new JTextField(2);
    	moveTF.setSize(20,20);
    	moveTF.setLocation(470,245);
    	moveTF.setEditable(false);
		
    	//Set the player, obstacles, and destination
    	PlayerB = new JButton(new ImageIcon("image/player.jpg"));
    	PlayerB.setSize(60,60);
    	PlayerB.setLocation(700,700);
    	PlayerB.addActionListener(this);
    	
    	B1 = new JButton(new ImageIcon("image/obstacles.jpg"));
    	B1.setSize(60,60);
    	B1.setLocation(700,700);
    	B1.addActionListener(this);
    	
    	B2 = new JButton(new ImageIcon("image/obstacles.jpg"));
    	B2.setSize(60,60);
    	B2.setLocation(700,700);
    	B2.addActionListener(this);
    	
    	B3 = new JButton(new ImageIcon("image/obstacles.jpg"));
    	B3.setSize(60,60);
    	B3.setLocation(700,700);
    	B3.addActionListener(this);
    	
    	B4 = new JButton(new ImageIcon("image/obstacles.jpg"));
    	B4.setSize(60,60);
    	B4.setLocation(700,700);
    	B4.addActionListener(this);
    	
    	B5 = new JButton(new ImageIcon("image/obstacles.jpg"));
    	B5.setSize(60,60);
    	B5.setLocation(700,700);
    	B5.addActionListener(this);
    	
    	DestinationL = new JLabel(new ImageIcon("image/destination.jpg"));
    	DestinationL.setSize(60,60);
    	DestinationL.setLocation(700,700);
    	
    	upB = new JButton(new ImageIcon("image/dot.jpg"));
    	upB.setSize(60,60);
    	upB.setLocation(700,700);
    	upB.addActionListener(this);
    	
    	downB = new JButton(new ImageIcon("image/dot.jpg"));
    	downB.setSize(60,60);
    	downB.setLocation(700,700);
    	downB.addActionListener(this);
    	
    	leftB = new JButton(new ImageIcon("image/dot.jpg"));
    	leftB.setSize(60,60);
    	leftB.setLocation(700,700);
    	leftB.addActionListener(this);
    	
    	rightB = new JButton(new ImageIcon("image/dot.jpg"));
    	rightB.setSize(60,60);
    	rightB.setLocation(700,700);
    	rightB.addActionListener(this);
    	
    	nextB = new JButton("Next Level");
    	nextB.setSize(100,30);
    	nextB.setLocation(700,700);
    	nextB.addActionListener(this);
    	
    	restartB = new JButton("Restart Level");
    	restartB.setSize(150,30);
    	restartB.setLocation(700,700);
    	restartB.addActionListener(this);
    	
    	MessageBox = new JLabel(new ImageIcon("image/winBox.jpg"));
    	MessageBox.setSize(300,300);
    	MessageBox.setLocation(700,700);
    	
    	Level1 = new JLabel(new ImageIcon("image/L1.jpg"));
    	Level1.setSize(100,30);
    	Level1.setLocation(700,700);
    	
    	Level2 = new JLabel(new ImageIcon("image/L2.jpg"));
    	Level2.setSize(100,30);
    	Level2.setLocation(700,700);
    	
    	Level3 = new JLabel(new ImageIcon("image/L3.jpg"));
    	Level3.setSize(100,30);
    	Level3.setLocation(700,700);
    	
    	Level1();
    	
    	add(nextB);
		add(restartB);
		add(MessageBox);
    	add(retryB);
    	add(levelCB);
    	add(moveTF);
    	add(Level1);
    	add(Level2);
    	add(Level3);
    	add(PlayerB);
		add(B1);
		add(B2);
		add(B3);
		add(B4);
		add(B5);	
		add(upB);
		add(downB);
		add(leftB);
		add(rightB);
		add(DestinationL);
    	add(BG);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
	}
	
	
	//Game Level 1 setting
	public void Level1()
	{
		currentLevel = "Level 1";
		
		for (int i =0; i< 5; i++)
			for(int j = 0; j<5 ; j++)
				array[i][j] = 0;

		countMove = 0;
		moveTF.setText(String.format("%d", countMove));
		
		upB.setLocation(700,700);
		downB.setLocation(700,700);
		leftB.setLocation(700,700);
		rightB.setLocation(700,700);
		Level1.setLocation(380,358);
		Level2.setLocation(700,700);
		Level3.setLocation(700,700);
		
		playerP = 2;
		array[0][2]= 99; // save player in this position
		PlayerB.setLocation(Movement.findX(playerP),Movement.findY(playerP));

		destinationP = 22;
		winP = 22;
		DestinationL.setLocation(Movement.findX(destinationP),Movement.findY(destinationP));

		B1P = 41;
		array[4][1]= 1;
		B1.setLocation(Movement.findX(B1P),Movement.findY(B1P));
		
		B2P = 42;
		array[4][2]= 2;
		B2.setLocation(Movement.findX(B2P),Movement.findY(B2P));
		
		B3P = 43;
		array[4][3]= 3;
		B3.setLocation(Movement.findX(B3P),Movement.findY(B3P));
		
		B4P = -1;
		B4.setLocation(Movement.findX(B4P),Movement.findY(B4P));
		
		B5P = -1;
		B5.setLocation(Movement.findX(B5P),Movement.findY(B5P));
		
		nextB.setLocation(700,700);
		restartB.setLocation(700,700);
		MessageBox.setLocation(700,700);
	}
	
	
	//Game Level 2 setting
	public void Level2()
	{
		currentLevel = "Level 2";
		
		for (int i =0; i< 5; i++)
			for(int j = 0; j<5 ; j++)
				array[i][j] = 0;
		
		countMove = 0;
		moveTF.setText(String.format("%d", countMove));
		
		upB.setLocation(700,700);
		downB.setLocation(700,700);
		leftB.setLocation(700,700);
		rightB.setLocation(700,700);
		Level1.setLocation(700,700);
		Level2.setLocation(380,358);
		Level3.setLocation(700,700);
		
		playerP = 14;
		array[1][4]= 99; // save player in this position
		PlayerB.setLocation(Movement.findX(playerP),Movement.findY(playerP));

		destinationP = 22;
		winP = 22;
		DestinationL.setLocation(Movement.findX(destinationP),Movement.findY(destinationP));

		B1P = 10;
		array[1][0]= 1;
		B1.setLocation(Movement.findX(B1P),Movement.findY(B1P));
		
		B2P = 12;
		array[1][2]= 2;
		B2.setLocation(Movement.findX(B2P),Movement.findY(B2P));
		
		B3P = 40;
		array[4][0]= 3;
		B3.setLocation(Movement.findX(B3P),Movement.findY(B3P));
		
		B4P = 43;
		array[4][3]= 4;
		B4.setLocation(Movement.findX(B4P),Movement.findY(B4P));
		
		B5P = -1;
		B5.setLocation(Movement.findX(B5P),Movement.findY(B5P));
		
		nextB.setLocation(700,700);
		restartB.setLocation(700,700);
		MessageBox.setLocation(700,700);
	}
	
	
	//Game Level 3 setting
	public void Level3()
	{
		currentLevel = "Level 3";
		
		for (int i =0; i< 5; i++)
			for(int j = 0; j<5 ; j++)
				array[i][j] = 0;
		
		countMove = 0;
		moveTF.setText(String.format("%d", countMove));
		
		upB.setLocation(700,700);
		downB.setLocation(700,700);
		leftB.setLocation(700,700);
		rightB.setLocation(700,700);
		Level1.setLocation(700,700);
		Level2.setLocation(700,700);
		Level3.setLocation(380,358);
		
		playerP = 14;
		array[1][4]= 99; // save player in this position
		PlayerB.setLocation(Movement.findX(playerP),Movement.findY(playerP));

		destinationP = 22;
		winP = 22;
		DestinationL.setLocation(Movement.findX(destinationP),Movement.findY(destinationP));

		B1P = 0;
		array[0][0]= 1;
		B1.setLocation(Movement.findX(B1P),Movement.findY(B1P));
		
		B2P = 2;
		array[0][2]= 2;
		B2.setLocation(Movement.findX(B2P),Movement.findY(B2P));
		
		B3P = 4;
		array[0][4]= 3;
		B3.setLocation(Movement.findX(B3P),Movement.findY(B3P));
		
		B4P = 40;
		array[4][0]= 4;
		B4.setLocation(Movement.findX(B4P),Movement.findY(B4P));
		
		B5P = 44;
		array[4][4]= 5;
		B5.setLocation(Movement.findX(B5P),Movement.findY(B5P));
		
		nextB.setLocation(700,700);
		restartB.setLocation(700,700);
		MessageBox.setLocation(700,700);
	}
	

 	public void actionPerformed(ActionEvent e) 
	{
		JButton b = (JButton) e.getSource();
		if (b == retryB|| b == restartB)
		{
			selectLevel(currentLevel);	
		}
		
		if (b == PlayerB)
		{
			moveObject = 99;
			Direction(playerP, array);	
		}
		
		if (b == B1)
		{
			moveObject = 1;
			Direction(B1P, array);
		}
		if (b == B2)
		{
			moveObject = 2;
			Direction(B2P, array);	
		}
		if (b == B3)
		{
			moveObject = 3;
			Direction(B3P, array);		
		}
		if (b == B4)
		{
			moveObject = 4;
			Direction(B4P, array);	
		}
		if (b == B5)
		{
			moveObject = 5;
			Direction(B5P, array);	
		}
		
		if (b == upB)
		{
			cancelDot();
			cancelKey();
			moveAfter = upP;
			Move();
		}
		if (b == downB)
		{
			cancelDot();
			cancelKey();
			moveAfter = downP;
			Move();
		}
		if (b == leftB)
		{
			cancelDot();
			cancelKey();
			moveAfter = leftP;
			Move();
		}
		if (b == rightB)
		{
			cancelDot();
			cancelKey();
			moveAfter = rightP;
			Move();
		}
		if (b == nextB)
		{
			System.out.println(currentLevel);
			if (currentLevel == "Level 1")
				Level2();
			else if (currentLevel == "Level 2")
				Level3();
			else if (currentLevel == "Level 3")
			{
				JOptionPane.showMessageDialog(null, 
						"This is the last level!","Warning",
						JOptionPane.INFORMATION_MESSAGE);
				Level1();
			}
		}
		
	}
	
	public void itemStateChanged(ItemEvent e) 
	{
		if(e.getSource() == levelCB)
		{
			currentLevel = levelName[levelCB.getSelectedIndex()];
			selectLevel(currentLevel);		
		}
		
	}
	
	
	//change the level
	public void selectLevel(String s)
	{
		if(s == "Level 1")
			Level1();
		else if(s == "Level 2")
			Level2();
		else if(s == "Level 3")
			Level3();
		
		repaint();
		validate();
	}
	
	
	//Show the direction to move
 	public void Direction(int p, int [][]a)
	{
 		upCheck = -1;
 		downCheck = -1;
 		leftCheck = -1;
 		rightCheck = -1;
		
 		cancelDot();
 		cancelKey();
 		
		//check the movement
 		upCheck = Movement.checkUp(p,a);
 		downCheck = Movement.checkDown(p,a);
 		leftCheck = Movement.checkLeft(p,a);
 		rightCheck = Movement.checkRight(p,a);
		
		if (upCheck >= 0)
		{
			upP= upCheck;
			upB.setLocation(Movement.findX(upP),Movement.findY(upP));
		}
		
		if (downCheck >= 0)
		{
			downP= downCheck;
			downB.setLocation(Movement.findX(downP),Movement.findY(downP));
		}
		
		if (leftCheck >= 0)
		{
			leftP= leftCheck;
			leftB.setLocation(Movement.findX(leftP),Movement.findY(leftP));
		}
		
		if (rightCheck >= 0)
		{
			rightP= rightCheck;
			rightB.setLocation(Movement.findX(rightP),Movement.findY(rightP));
		}
		
		PlayerB.addKeyListener(this);
		B1.addKeyListener(this);
		B2.addKeyListener(this);
		B3.addKeyListener(this);
		B4.addKeyListener(this);
		B5.addKeyListener(this);
		
		repaint();
		validate();
	}
	
	//Move the object
	public void Move()
	{
		int x,y;
		boolean moveCheck = false;
		
		if (moveObject == 99)
		{
			PlayerB.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(playerP);
			y = Movement.findIndexY(playerP);
			array[x][y] = 0;
			
			playerP = moveAfter;
			x = Movement.findIndexX(playerP);
			y = Movement.findIndexY(playerP);
			array[x][y] = 99;
			moveCheck = true;
		}
		if (moveObject == 1)
		{
			B1.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(B1P);
			y = Movement.findIndexY(B1P);
			array[x][y] = 0;
			
			B1P = moveAfter;
			x = Movement.findIndexX(B1P);
			y = Movement.findIndexY(B1P);
			array[x][y] = 1;
			moveCheck = true;
		}
		if (moveObject == 2)
		{
			B2.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(B2P);
			y = Movement.findIndexY(B2P);
			array[x][y] = 0;
			
			B2P = moveAfter;
			x = Movement.findIndexX(B2P);
			y = Movement.findIndexY(B2P);
			array[x][y] = 2;
			moveCheck = true;
		}
		if (moveObject == 3)
		{
			B3.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(B3P);
			y = Movement.findIndexY(B3P);
			array[x][y] = 0;
			
			B3P = moveAfter;
			x = Movement.findIndexX(B3P);
			y = Movement.findIndexY(B3P);
			array[x][y] = 3;
			moveCheck = true;
		}
		if (moveObject == 4)
		{
			B4.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(B4P);
			y = Movement.findIndexY(B4P);
			array[x][y] = 0;
			
			B4P = moveAfter;
			x = Movement.findIndexX(B4P);
			y = Movement.findIndexY(B4P);
			array[x][y] = 4;
			moveCheck = true;
		}
		if (moveObject == 5)
		{
			B5.setLocation(Movement.findX(moveAfter),Movement.findY(moveAfter));
			x = Movement.findIndexX(B5P);
			y = Movement.findIndexY(B5P);
			array[x][y] = 0;
			
			B5P = moveAfter;
			x = Movement.findIndexX(B5P);
			y = Movement.findIndexY(B5P);
			array[x][y] = 5;
			moveCheck = true;
		}
		
		if(moveCheck)
		{
			countMove ++;
			moveTF.setText(String.format("%d", countMove));
		}
		
		if(Movement.checkWin(playerP, winP))
			win();
		else	
			keepMove();
	}
	
	//Keep show the direction
	public void keepMove()
	{
		if(moveObject == 99)
		{
			Direction(playerP, array);
		}
		if (moveObject == 1)
		{
			Direction(B1P, array);
		}
		if (moveObject == 2)
		{
			Direction(B2P, array);	
		}
		if (moveObject == 3)
		{
			Direction(B3P, array);	
		}
		if (moveObject == 4)
		{
			Direction(B4P, array);		
		}
		if (moveObject == 5)
		{
			Direction(B5P, array);	
		}
	}
	
	//check win
	public void win()
	{
		if(Movement.checkWin(playerP, winP))
		
		{
			nextB.setLocation(155,280);
			restartB.setLocation(130,220);
			MessageBox.setLocation(54,67);
			PlayerB.setLocation(700,700);
			B1.setLocation(700,700);
			B2.setLocation(700,700);
			B3.setLocation(700,700);
			B4.setLocation(700,700);
			B5.setLocation(700,700);
			cancelDot();
			repaint();
			validate();
		}
	}
	
	//cancel all direction dot
	public void cancelDot()
	{
		upB.setLocation(1000,1000);
		downB.setLocation(1000,1000);
		leftB.setLocation(1000,1000);
		rightB.setLocation(1000,1000);
	}
	
	//cancel the key listener
	public void cancelKey()
	{
    	PlayerB.removeKeyListener(this);
    	B1.removeKeyListener(this);
    	B2.removeKeyListener(this);
    	B3.removeKeyListener(this);
    	B4.removeKeyListener(this);
    	B5.removeKeyListener(this);
	}
		
	public void keyPressed(KeyEvent ke) 
	{
		if (ke.getKeyCode() == KeyEvent.VK_UP) 
		{
			if(upCheck >=0)
			{
				cancelDot();
				cancelKey();
				moveAfter = upP;
				Move();
			}
		}
		else if (ke.getKeyCode() == KeyEvent.VK_DOWN) 
		{
			if(downCheck >=0)
			{
				cancelDot();
				cancelKey();
				moveAfter = downP;
				Move();
			}
		} 
		else if (ke.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(leftCheck >=0)
			{
				cancelDot();
				cancelKey();
				moveAfter = leftP;
				Move();
			}
		}
		else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(rightCheck >=0)
			{
				cancelDot();
				cancelKey();
				moveAfter = rightP;
				Move();
			}
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}


	public static void main(String[] args) 
	{
		JFrame window = new JFrame("Lunar Lockout");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new SlipperyMaze());
		
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
}



//import the image
class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}
