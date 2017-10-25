import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 30;
	private long targetTime = 1000 / FPS;
	
	private TileMap tileMap;
	private Player player;

	// image
	private BufferedImage image;
	private Graphics2D g;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		addKeyListener(this);
	}

	public void run() {
		init();

		long start;
		long elapsed; // urdTime
		long wait;

		// game loop
		while (running) {

			start = System.nanoTime();

			update();
			render(); // render
			draw(); // draw

			elapsed = (System.nanoTime() - start) / 1000000;
			wait = targetTime - elapsed;
			
			if (wait < 0)
				wait = 5;

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		running = true;
		
		tileMap = new TileMap("testmap2.txt", 32);
		tileMap.loadTiles("tileset.gif");
		
		player = new Player(tileMap);
		player.setx(50);
		player.sety(50);
	}

	///////////////////////////////////////////////////////////////

	
	private void update() {
		tileMap.update();
		player.update();

	}

	private void render() {// render
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		tileMap.draw(g);
		player.draw(g);

	}

	private void draw() {
		// draw
		// draw Graphic
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}

	
	public void keyPressed(KeyEvent key) {
		
		int code = key.getKeyCode();
		if(code == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if(code == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if(code == KeyEvent.VK_X) {
			player.setJumping(true);
		}
		
		
	}


	public void keyReleased(KeyEvent key) {
		
		int code = key.getKeyCode();
		
		if(code == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if(code == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		
	}


	public void keyTyped(KeyEvent key) {
		// TODO Auto-generated method stub
		
	}
}
