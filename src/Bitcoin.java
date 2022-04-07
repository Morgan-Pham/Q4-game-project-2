import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Bitcoin {
	private int x;
	private double y; 
	private Image img; 	
	private AffineTransform tx;
	public int getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setY(double newY) {
		y = newY;
	}
	public void setX(int newX) {
		x = newX;
	}

	public Bitcoin(int x, int y) {
		img = getImage("bitcoin.png"); 
		this.x = x;
		this.y = y;
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
	
		update();
	}
	
	//update the picture variable location
	private void update() {
		tx.setToTranslation(x, y);
		tx.scale(.05, .05);
		
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.05, 0.05);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Bitcoin.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
