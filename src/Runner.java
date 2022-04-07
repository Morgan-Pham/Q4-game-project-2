
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

	/*	get an attack that destroys the closest square to the miner
	 * 	get collision and jumping
	 */

public class Runner extends JPanel implements ActionListener, MouseListener, KeyListener {
	Miner m = new Miner(100, 150);
	Background b = new Background();
	private int score = 0;
	private boolean test = true;
	
	public void paint(Graphics g){
		b.paint(g);
		m.paint(g);
		if(m.getY() <= 150) {
			m.setY(150);
		}
		if(m.getX() <= 0) {
			m.setX(0);
		}
		if(m.getX() >= 790) {
			m.setX(790);
		}
		if(m.getY() >= 875) {
			m.setY(875);
		}
		
		Font f = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(f);
		g.setColor(Color.yellow);
		g.drawString("Bitcoin Miner", 290, 50);
		g.drawString(score+"", 800, 50);
		Font e = new Font("Times New Roman", Font.BOLD, 20);
		g.setFont(e);
	}
	
	public static void main(String[] arg) {
		Runner r = new Runner();
	}
	public Runner() {
		JFrame f = new JFrame("Bitcoin Miner");
		f.setSize(new Dimension(900, 1000));
		f.setBackground(Color.white);
		f.add(this);
		f.setResizable(false);
		f.setLayout(new GridLayout(1,2));
		f.addMouseListener(this);
		f.addKeyListener(this);
		Timer t = new Timer(16, this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		//e 
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getKeyCode());
		if(arg0.getKeyCode() == 39) { //right
			m.setX(m.getX()+10);
		}
		
		if(arg0.getKeyCode() == 37) { //left
			m.setX(m.getX()-10);
		}
		
		if(arg0.getKeyCode() == 40) {
			if(m.getY() >= 150) {
				m.setY(m.getY()+10);
			}
		}
		if(arg0.getKeyCode() == 38) {
			m.setY(m.getY()-10);
		}
		if(m.getY() >= 155) {
			if(arg0.getKeyCode() == 39 || arg0.getKeyCode() == 40) {
				m.changePicture("minerdigR.png");
			}
		}
		if(m.getY() >= 155) {
			if(arg0.getKeyCode() == 37) {
				m.changePicture("minerdigL.png");
			}
		}
		if(m.getY() <= 160) {
			m.changePicture("miner.png");
		}//does this work pleasey
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
