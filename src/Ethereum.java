import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Ethereum{
	
	private int x;
	private int y;
	private Image img; 	
	private AffineTransform tx;
	public int getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setY(int newY) {
		y = newY;
	}
	public void setX(int newX) {
		x = newX;
	}
	
	public Ethereum(int x , int y) {
		img = getImage("ethereum.png"); //load the image for Tree
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
		tx.scale(.15, .15);
		
	}

	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.15, 0.15);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Ethereum.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

}
