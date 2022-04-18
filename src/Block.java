
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Block{
	private int x, y, color;//position of the bird 56
	int bX = 400, bY = 700;   //ball starting location
	int bW = 15;     	//ball size
	private Image img;	 
	private AffineTransform tx;
	private int speedX, speedY;
	private int blueVal = (int)(Math.random()*(255-1+1));
	private int greenVal = (int)(Math.random()*(255-1+1));
	private int redVal = (int)(Math.random()*(255-1+1));
    

	public Block(int x, int y, int color) {
    	this.x = x;
    	this.y = y;
    	this.color = color;
    	tx = AffineTransform.getTranslateInstance(x, y);
    	init(x, y);             	//initialize the location of the image
                                	//use your variables
	}
	public void setColor(int newColor) {
    	color = newColor;
	}
	public int getColor() {
    	return color;
	}
	public void setX(int newX) {
    	x = newX;
	}
	public void setY(int newY) {
    	y = newY;
	}
	public void setSpeedX(int newSpeedX) {
    	speedX = newSpeedX;
	}
    
	public int getX() {
    	return x;
	}
	public int getY() {
    	return y;
	}
	public int getSpeedX() {
    	return speedX;
	}
    
	public void changePicture(String newFileName) {
    	img = getImage(newFileName);
    	init(x, y);
   	 
	}
	public void paint(Graphics g) {
    	//these are the 2 lines of code needed draw an image on the screen
    	Graphics2D g2 = (Graphics2D) g;
    	if(color == 1 || color == 0) {
    		 g.setColor(new Color(153,102,0));
    	} 
    	g.fillRect(x,  y, 100, 40);
    	//call update to update the actual picture location
    	update();
    	g2.drawImage(img, tx, null);
    	
	}//update the picture variable location
	private void update() {
    	tx.setToTranslation(x, y);
    	tx.scale(.7, .7);
	}
    
	public boolean collision(int x, int y) {
    	if(x + 50 >= bX && x - 50 <= bX && y + 20 >= bY && y - 20 <= bY) {
        	return true;
    	} else return false;
	}
    
	private void init(double a, double b) {
    	tx.setToTranslation(a, b);
    	tx.scale(.5, .5);
	}

	private Image getImage(String path) {
    	Image tempImage = null;
    	try {
        	URL imageURL = Block.class.getResource(path);
        	tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
    	} catch (Exception e) {
        	e.printStackTrace();
    	}
    	return tempImage;
	}

}
