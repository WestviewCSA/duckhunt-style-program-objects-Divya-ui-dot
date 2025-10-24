import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

// The Duck class represents a picture of a duck that can be drawn on the screen.
public class greenGoblin2 {
    // Instance variables (data that belongs to each Duck object)
    private Image img;               // Stores the picture of the duck
    private Image normal; //normal look
    private Image dead;
    
    
    private AffineTransform tx;      // Used to move (translate) and resize (scale) the image

    // Variables to control the size (scale) of the duck image
    private double scaleX;           
    private double scaleY;           

    // Variables to control the location (x and y position) of the duck
    private double x;                
    private double y;        
    
    //variables for speed
    private int vx;
    private int vy;
    
    //debugging variable
    public boolean debugging = false;

   
    // Constructor: runs when you make a new Duck object
    public greenGoblin2() {
        normal = getImage("/imgs/green goblin alive GIF.gif"); // Load the image file
        dead = getImage("/imgs/explosion.png");
        
        //img will point to current state object for image
        img = normal;
        
        
        
        
        
        tx = AffineTransform.getTranslateInstance(0, 0); // Start with image at (0,0)
        
        // Default values
        scaleX = 2.0;
        scaleY = 2.0;
        x = 50;
        y = 400;
        
        //init vx and vy variables to non zero value
        vx = 5;
        vy = 2;
        init(x, y); // Set up the starting location and size
    }
    
    //2nd constructor to initialize location and scale!
    public greenGoblin2(int x, int y, int scaleX, int scaleY) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	init(x,y);
    }
    
    //2nd constructor to initialize location and scale!
    public greenGoblin2(int x, int y, int scaleX, int scaleY, int vx, int vy) {
    	this();
    	this.x 		= x;
    	this.y 		= y;
    	this.scaleX = scaleX;
    	this.scaleY = scaleY;
    	this.vx 	= vx; 
    	this.vy 	= vy;
    	init(x,y);
    }
    
    public void setVelocityVariables(int vx, int vy) {
    	this.vx = vx;
    	this.vy = vy;
    }
    
    
    // Changes the picture to a new image file
    public void changePicture(String imageFileName) {
        img = getImage("/imgs/"+imageFileName);
        init(x, y); // keep same location when changing image
    }
    
    //update any variables for the object such as x, y, vx, vy
    public void update() {
    	x += vx;
    	//bounce off right side
    	if(x >= 685 || x <= 0) {
    		vx *= -1;
    	}
    	y += vy;
   	    if(y <= 0) {
    		vy *= -1;
    		
    	}
    	if(vx == 0 && vy < 10) { 
    		if(y >= 750) {
    			vy = -(int)(Math.random()*8+3);
    			vx = -(int)(Math.random()*8+3);
    			img = normal;
    			//50% o the time, vx is negative
    			if(Math.random()<0.5) {
    				vx *= -1;
    			}
    		}
    	}
    	//regular behavior - regular bouncing from the bottom
    	if(y >= 768 && vx != 0) vy *= -1;
    }
    
    
    
    // Draws the duck on the screen
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;   // Graphics2D lets us draw images
        g2.drawImage(img, tx, null);      // Actually draw the duck image
        update();
        init(x,y);
        
      //create a green hitbox for debugging
        if(debugging) {
        g.setColor(Color.magenta);
        g.drawRect((int)x , (int) y, 100, 100);
        }
    }
    
    
    
    // Setup method : places the duck at (a, b) and scales it
    private void init(double a, double b) {
        tx.setToTranslation(a, b);        // Move the image to position (a, b)
        tx.scale(scaleX, scaleY);         // Resize the image using the scale variables
    }

    // Loads an image from the given file path
    private Image getImage(String path) {
        Image tempImage = null;
        try {
            URL imageURL = greenGoblin2.class.getResource(path);
            tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempImage;
    }
    // NEW: Method to set scale
    public void setScale(double sx, double sy) {
        scaleX = sx;
        scaleY = sy;
        init(x, y);  // Keep current location
    }

    // NEW: Method to set location
    public void setLocation(double newX, double newY) {
        x = newX;
        y = newY;
        init(x, y);  // Keep current scale
    }

    //collision and collision logic
    public boolean checkCollision(int mX, int mY) {
    	//represent the mouse as a rectangle
    	Rectangle mouse = new Rectangle(mX, mY, 50, 50);
    	
    	//represent this object as a Rectangle
    	Rectangle thisObject = new Rectangle((int) x, (int) y, 100, 100);
    	
    	//use built-in method for Rectangle to check if they intersect!
    	//aka Collision
    	if(mouse.intersects(thisObject)) {
    		
    		//logic if colliding
    		vx = 0; //turn off vx to fall from the sy
    		vy = 9; //fall y -gravity
    		
    		//change sprite to the alternate skin
    		img = dead;//dead sprite
    		
    		
    		return true;
    	}
    	else {
    		
    		//logic if not colliding
    		
    		return false;
    		
    	}
    	
    }
    
    
    
    
    
    
}
