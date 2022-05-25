import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class kirby{
	
	//add location attributes
	public int x, y; //position of the bird
	private int speedX = 5;
	private int speedY = 5;
	private int score = 0;
	private Image img; 	
	private AffineTransform tx;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getScore() {
		return score;
	}
	public int getSpeedX() {
		return speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setY(int newY) {
		y = newY;
	}
	public void setX(int newX) {
		x = newX;
	}
	public void setScore(int newScore) {
		score = newScore;
	}
	public void setSpeedX(int newSpeedX) {
		speedX = newSpeedX;
	}
	public void setSpeedY(int newSpeedY) {
		speedY = newSpeedY;
	}
	
	
	
	
	public kirby(int x, int y) {
		img = getImage("kirbyK.png"); //load the image for Tree
		this.x = x;
		this.y = y;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 				//initialize the location of the image
					 				//use your variables
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		x += speedX;
		y += speedY;
		
		if(x > 830) {
			speedX *= -1;
	
		}
		if (y >= 850) {
			speedY *= -1;
		}
		if(x <= 0) {
			speedX *= -1;
		}
		if(y <= 170) {
			speedY *= -1;
		}
		
		update();
		 
		//text section (scores, etc.)
		 //560 bottom
		//call update to update the actual picture location
	}
	
	//update the picture variable location
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.3, .3);
		
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(.3, .3);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = kirby.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}