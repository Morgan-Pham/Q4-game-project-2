import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Trail {
	private int x;
	private int y;
	private int width;
	private int height;
	private Image img; 	
	private AffineTransform tx;
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getW() {
		return width;
	}
	public int getH() {
		return height;
	}
	public void setW(int newW) {
		y = newW;
	}
	public void setH(int newH) {
		y = newH;
	}
	public void setY(int newY) {
		y = newY;
	}
	public void setX(int newX) {
		x = newX;
	}

	public Trail(int x, int y) {
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
				
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		g.fillRect(x, y, 50, 50);
	}
	
	//update the picture variable location
}

