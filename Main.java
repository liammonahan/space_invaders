import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Main extends JApplet implements KeyListener, ActionListener {
	static LaserIndex lasers = new LaserIndex();
	private PaintSurface canvas;
	public static int i = 75, turret_x = 20, turret_y = 360, score = 0, life = 3;
	private Image pic2= new ImageIcon("health.jpg").getImage(); 
  private Image pic3= new ImageIcon("health2.jpg").getImage();
  
  private Sprite sprite = new Sprite(350, 250, new ImageIcon("win.jpg").getImage());
  private Sprite sprite2 = new Sprite(84, 275, pic2);
  private Sprite sprite3 = new Sprite(84, 217, pic2);
  private Sprite sprite4 = new Sprite(84, 160, pic2);
  private Sprite sprite5 = new Sprite(85, 225, pic3);
  private Sprite sprite6 = new Sprite(40, 160, new ImageIcon("health3.jpg").getImage());
  private Sprite sprite7 = new Sprite(350, 250, new ImageIcon("Loss.jpg").getImage());
  
  private int f = 0;
  public static int lives = 4;

	public void init() {
		Container c = getContentPane();
		c.setBackground(Color.BLACK);
		addKeyListener(this);
		Ship.createFleet(); //mobilize the troops, dark lord!
		canvas = new PaintSurface();
		this.add(canvas, BorderLayout.CENTER);

		Timer clock = new Timer(25, this); // fires every 25 milliseconds
		clock.start();
	}

  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      if (turret_x > 0) {
    		turret_x -= 30;
    	}
    }
    else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      if (turret_x < 460) { //460 controls how far to the right the paddle goes
    		turret_x += 30; //move twenty pixels at a time
    	}
    }
    else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    	if(Timing.turretCanShoot()) {
    		lasers.add(turret_x + 25, turret_y - 8, true); //25 is half of the total width of the turret(50px)
    		Timing.turretCanShoot(false);
    	}
    }
  }

  public void keyReleased(KeyEvent e) {}

  public void keyTyped(KeyEvent e) {}
	
	class PaintSurface extends JComponent {
	  public PaintSurface() {}

    public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Shape turret = new Rectangle2D.Float(turret_x, turret_y, 50, 16);

			Ship.draw(g2);
			sprite6.draw(g);
      sprite5.draw(g);
      sprite2.draw(g);
      sprite3.draw(g);
      sprite4.draw(g);
      
      if (lives == 3) {
        sprite4.moveSideways();
      }
      
      if (lives == 2) {
        sprite3.moveSideways();
      }
      
      if(lives == 1) {
       sprite2.moveSideways();
      }
      
      if (lives == 0) {
        Ship.massRemove();
        sprite7.draw(g);
        for (Laser laser : lasers) {
          g2.setColor(Color.BLACK);
          g2.fill(laser);
        }  
      }
      else {
        for (Laser laser : lasers) {
          g2.setColor(Color.GREEN);
          g2.fill(laser);
        }
      }

      for (Laser laser : lasers) {
        if (f == 30){
	        g2.setColor(Color.BLACK);
	        g2.fill(laser);
        }
        else {
          g2.setColor(Color.YELLOW);
         	g2.fill(laser);
        }
      }

      g2.setColor(Color.WHITE);
      g2.fill(turret);
      g2.drawString("Score: " + score, 250, 20);
      
      if(f == 30) {
        sprite.draw(g);
        sprite6.draw(g);
        sprite5.draw(g);
      }
		}
	}

	// Called automatically when the timer fires
	public void actionPerformed(ActionEvent e) {
		Ship.move();
		if(Timing.shipCanShoot()) {
			Ship.shoot();
		}
		
		if(score == 4320) { //see if all ships have been shot
      f = 30;
		}
		
		lasers.move();
		lasers.hit();
		Timing.turretCounter();
		repaint();
	}
}