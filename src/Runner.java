
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
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
import java.lang.reflect.Array;
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
	Background bg = new Background();
	Background2 bg2 = new Background2();
	Music ms = new Music("digdug.wav", true);
	Music co = new Music("coin.wav", false);
	Music yell = new Music("scream.wav", false);
	ArrayList<Bitcoin> bList = new ArrayList<Bitcoin>();
	ArrayList<Doge> dList = new ArrayList<Doge>();
	ArrayList<Ethereum> eList = new ArrayList<Ethereum>();
	ArrayList<kirby> kList = new ArrayList<kirby>();
	ArrayList<Rectangle> arr = new ArrayList<Rectangle>();
	private int score = 0;
	private int time = 60;
	private int totalCount = 0;
	private int coinCount = 0;
	Counter timeCounter = new Counter();
	public void paint(Graphics g) {
		
		//BACKGROUND
		bg.paint(g);
		bg2.paint(g);
		
		//CREATES A BLACK TRAIL BEHIND THE MINER
		for(int i = 0; i < arr.size(); i++) {
	    	g.fillRect((int)arr.get(i).getX(), (int)arr.get(i).getY(), 50, 50);
	    }
		if(m.getY() > 220) {
			if(m.getSpeed() > 0 || m.getSpeed() < 0) {
				arr.add(new Rectangle(m.getX()+10, m.getY(), 50, 50));
			}
			if(m.getSped() > 0 || m.getSped() < 0) {
				arr.add(new Rectangle(m.getX()+10, m.getY(), 50, 50));
			}
		}
		
		//PAINTS ALL OF THE COIN OBJECTS AND COUNTS THE SCORE
		for(Bitcoin b: bList) {
			b.paint(g);
			Rectangle rm = new Rectangle(m.getX()+5, m.getY(), 85, 60);
		    Rectangle rd = new Rectangle(b.getX(), (int) b.getY(), 60, 60);
		    if(rm.intersects(rd)==true) {
		    	co.play();
		    	b.setX(1000);
			    score+=100;
			    coinCount--;
		    }
		}
		for(Doge d: dList) {
			d.paint(g);
			Rectangle rm = new Rectangle(m.getX()+5, m.getY(), 85, 60);
			Rectangle rd = new Rectangle(d.getX(), (int) d.getY(), 60, 60);
			if(rm.intersects(rd)==true) {
				co.play();
				d.setX(1000);
			    score+=20;
			    coinCount--;
			}
		}
		for(Ethereum e: eList) {
			e.paint(g);
			Rectangle rm = new Rectangle(m.getX()+5, m.getY(), 85, 60);
			Rectangle re = new Rectangle(e.getX(), (int) e.getY(), 60, 60); 
			if(rm.intersects(re)==true) {
				co.play();
				e.setX(1000);
				score+=70;
				coinCount--;
			}
		}
		for(kirby k: kList) {
			k.paint(g);
			Rectangle rm = new Rectangle(m.getX()+5, m.getY(), 85, 60);
			Rectangle re = new Rectangle(k.getX(), (int) k.getY(), 60, 60); 
			if(rm.intersects(re)==true) {
				yell.play();
				score-=200;
				k.setY((int)(Math.random()*500)+250);
				k.setX((int)(Math.random()*800)+1);
			}
			if(time == 0) {
				k.setSpeedX(0);
				k.setSpeedY(0);
			}
			if(coinCount == 0) {
				k.setSpeedX(0);
				k.setSpeedY(0);
			}
		}
		//MINER MAP COLLISION
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
		
		//ALL OF THE TEXT/STRINGS FOR THE PROJECT
		Font f = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(f);
		g.setColor(Color.yellow);
		g.drawString("Crypto Miner", 290, 50);
		g.drawString(score+"", 750, 50);
		
		//WINNING SCENE
		if(coinCount == 0) {
			m.setSpeed(0);
			m.setSped(0);
			Font y = new Font("Times New Roman", Font.BOLD, 30);
			g.setFont(y);
			g.setColor(Color.yellow);
			g.drawString("YOU WIN, SEE YOUR SCORE", 400, 130);
			g.drawString("CLOSE GAME TO PLAY AGAIN", 400, 180);
		}
		
		//TIMER FOR GAME
		Font e = new Font("Times New Roman", Font.BOLD, 50);
		g.setFont(e);
		g.drawString(String.valueOf(time), 45, 50);
		if(timeCounter.getY()>=40 && time != 0) {
			timeCounter.setY(0);
			time--;
		}
		Font y = new Font("Times New Roman", Font.BOLD, 30);
		g.setFont(y);
		g.setColor(Color.yellow);
		timeCounter.setY(timeCounter.getY()+1);
		
		//LOSING SCENE
		if(time <= 0) {
			m.setSpeed(0);
			m.setSped(0);
			g.setColor(Color.red);
			g.drawString("GAME OVER, SEE YOUR SCORE", 210, 130);
			g.drawString("CLOSE GAME TO RESTART", 210, 160);
		}
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
		ms.play();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		//CREATION OF THE COIN OBJECTS TO BE PAINTED
		for(int i = 0; i<11; i++) {
			Bitcoin temp = new Bitcoin((int)(Math.random()*800)+1, (int)(Math.random()*600)+250);
			bList.add(temp);
			totalCount++;
		}
		for(int i = 0; i<11; i++) {
			Doge temp = new Doge((int)(Math.random()*800)+1, (int)(Math.random()*600)+250);
			dList.add(temp);
			totalCount++;
		}
		for(int i = 0; i<11; i++) {
			Ethereum temp = new Ethereum((int)(Math.random()*800)+1, (int)(Math.random()*600)+250);
			eList.add(temp);
			totalCount++;
		}
		for(int i = 0; i<3; i++) {
			kirby temp = new kirby((int)(Math.random()*800)+1, (int)(Math.random()*500)+250);
			kList.add(temp);
		}
		coinCount += totalCount;
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
		
		//MINER MOVEMENT CONTROLS
		//MINER IMAGE CHANGES DEPENDING IF HE IS UNDEGROUND OR NOT
		//AND IF HE IS GOING RIGHT OR LEFT
		if(arg0.getKeyCode() == 39) { //right
			m.setSpeed(+5);
		}
		
		if(arg0.getKeyCode() == 37) { //left
			m.setSpeed(-5);
		}
		
		if(arg0.getKeyCode() == 40) {  //down
			if(m.getY() >= 150) {
				m.setSped(+5);
			}
		}
		if(arg0.getKeyCode() == 38) {   //up
			m.setSped(-5);
			if(m.getY() >= 220) { //if underground while going up
			}
		}
		if(m.getY() >= 170) {
			if(arg0.getKeyCode() == 39 || arg0.getKeyCode() == 40) {  //going right 
				m.changePicture("minerdigR.png");
				if(m.getY() >= 220) {
				}
			}
		}
		if(m.getY() >= 170) {
			if(arg0.getKeyCode() == 37) {    //underground and left
				m.changePicture("minerdigL.png");
				if(m.getY() >= 220) {
				}
			}
		}
		if(m.getY() <= 160) {
			m.changePicture("mineridle4.png");  //when above ground
		}//bring home
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		//MINER MOVEMENT SPEED
		if(arg0.getKeyCode() == 39) {
			m.setSpeed(0);
		}
		if(arg0.getKeyCode() == 37) {
			m.setSpeed(0);
		}
		if(arg0.getKeyCode() == 38) {
			m.setSped(0);
		}
		if(arg0.getKeyCode() == 40) {
			m.setSped(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
