import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JPanel implements ActionListener, MouseListener, KeyListener {
	
	//frame size
	private int screenWidth = 768, screenHeight = 768;
	private String title = "Goblin Hunt";
	//count
    private int count = 3;
    private int hitCount = 0;
	
	/**
	 * Declare and instantiate (create) your objects here
	 */
	public Duck duckObject = new Duck();
	private background myBackground = new background();
	private bush myBush = new bush();
	private tree myTree = new tree();
	private greenGoblin gG = new greenGoblin();
	private greenGoblin2 gG2 = new greenGoblin2();
	private ammo myAmmo = new ammo();
	private gameOver myGameOver = new gameOver();
	private hitCount myHitCount = new hitCount();
	public void paint(Graphics pen) {
		
		//this line of code is to force redraw the entire frame
		super.paintComponent(pen);
		
		//background should be draw before the objects
		//or based on how you want to layer
		myBackground.paint(pen);
		myTree.paint(pen);
		//call paint for the object
		//for objects, you call methods on them using the dot operator
		//methods use always involve parenthesis
		duckObject.paint(pen);
		myBush.paint(pen);
		gG.paint(pen);
		//gG2.paint(pen);
		myAmmo.paint(pen);
		myHitCount.paint(pen);
	}
	
	
	@Override
	public void mouseClicked(MouseEvent mouse) {
	    // Runs when the mouse is clicked (pressed and released quickly).
	    // Example: You could use this to open a menu or select an object.
	}

	@Override
	public void mouseEntered(MouseEvent mouse) {
	    // Runs when the mouse enters the area of a component (like a button).
	    // Example: You could highlight the button when the mouse hovers over it.
	}

	@Override
	public void mouseExited(MouseEvent mouse) {
	    // Runs when the mouse leaves the area of a component.
	    // Example: You could remove the highlight when the mouse moves away.
	}
	
	@Override
	public void mousePressed(MouseEvent mouse) {
	    // Runs when a mouse button is pressed down.
	    // Example: You could start dragging an object here.
		System.out.println(mouse.getX()+":"+mouse.getY());
		gG.checkCollision(mouse.getX(), mouse.getY());
		//gG2.checkCollision(mouse.getX(), mouse.getY());
		if(gG.checkCollision(mouse.getX(), mouse.getY()) == false) {
			count --;
			if(count == 2) {
				myAmmo.changePicture("twoShot.png");
			}
			else if(count == 1) {
				myAmmo.changePicture("oneShot.png");
			}
			else {
				myHitCount.changePicture("gameOver.png");
				myHitCount.setLocation(0.0, 0.0);
				myHitCount.setScale(1, 1);
			}
		}
		
		if(gG.checkCollision(mouse.getX(), mouse.getY()) == true){
			hitCount ++;
			if(hitCount == 1) {
				myHitCount.changePicture("oneHit.png");
				duckObject.changePicture("spidermanYay.gif");
			}
			else if(hitCount == 2) {
				myHitCount.changePicture("twoHit.png");
				duckObject.changePicture("spidermanYay2.gif");
			}
			else if(hitCount == 3) {
				myHitCount.changePicture("threeHit.png");
				duckObject.changePicture("spidermanYay.gif");
			}
			else if(hitCount == 4) {
				myHitCount.changePicture("fourHit.png");
				duckObject.changePicture("spidermanYay2.gif");
			}
			else if(hitCount == 5) {
				myHitCount.changePicture("fiveHit.png");
				duckObject.changePicture("spidermanYay.gif");
			}
			else if(hitCount == 6) {
				myHitCount.changePicture("sixHit.png");
				duckObject.changePicture("spidermanYay2.gif");
			}
			else if(hitCount == 7) {
				myHitCount.changePicture("sevenHit.png");
				duckObject.changePicture("spidermanYay.gif");
			}
			else if(hitCount == 8) {
				myHitCount.changePicture("eightHit.png");
				duckObject.changePicture("spidermanYay2.gif");
			}
			else if(hitCount == 9) {
				myHitCount.changePicture("nineHit.png");
				duckObject.changePicture("spidermanYay.gif");
			}
			else {
				myHitCount.changePicture("tenHit.png");
				duckObject.changePicture("spidermanYay2.gif");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent mouse) {
	    // Runs when a mouse button is released.
	    // Example: You could stop dragging the object or drop it in place.
		
	}



	/*
	 * This method runs automatically when a key is pressed down
	 */
	public void keyPressed(KeyEvent key) {
		
		System.out.println("from keyPressed method:"+key.getKeyCode());
		
	}

	/*
	 * This method runs when a keyboard key is released from a pressed state
	 * aka when you stopped pressing it
	 */
	public void keyReleased(KeyEvent key) {
		
	}

	/**
	 * Runs when a keyboard key is pressed then released
	 */
	public void keyTyped(KeyEvent key) {
		myAmmo.changePicture("threeShot.png");
		myAmmo.setLocation(5.0, 630);
		myAmmo.setScale(1.75, 1.75);
		
	}
	
	
	/**
	 * The Timer animation calls this method below which calls for a repaint of the JFrame.
	 * Allows for our animation since any changes to states/variables will be reflected
	 * on the screen if those variables are being used for any drawing on the screen.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	/*
	 * Main method to create a Frame (the GUI that you see)
	 */
	public static void main(String[] arg) {
		new Frame();
	}
	
	
	
	public Frame() {
		JFrame f = new JFrame(title);
		f.setSize(new Dimension(screenWidth, screenHeight));
		f.setBackground(Color.blue);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//Cursor icon code
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cursor.png");
		Cursor a = toolkit.createCustomCursor(image, new Point(this.getX(),this.getY()), "");
		this.setCursor(a);
		
	}

}
