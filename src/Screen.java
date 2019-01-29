import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import java.awt.image.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Screen extends JPanel implements Runnable {

	private Thread thread = new Thread(this);

	private BufferedImage image = null;
	private boolean isFirst = true;
	private Mouse mouse;

	public final static int rows = 4;
	public final static int cols = 4;
	public final static int chunks = rows * cols;

	public static int chunkWidth;
	public static int chunkHeight;

	public static ArrayList<Tile> tiles = new ArrayList<Tile>();
	private static Room room;
	

	public Screen() {
		setBackground(Color.BLACK);
		thread.start();
	}

	/* 
	 * define()
	 * 
	 * Looks in the res/ directory for our game image file
	 * It then computes the widths and heights for each tile,
	 * creates a new Tile object for the image, and puts it in
	 * an ArrayList.
	 * 
	 */
	public void define() {
		try {
			//image = ImageIO.read(new File("res/image.jpg"));
			image = ImageIO.read(getClass().getResource("image.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		chunkWidth = image.getWidth() / cols;
		chunkHeight = image.getHeight() / rows;
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[chunks];
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight, image.getType());

				// draws the image chunk
				Graphics2D gr = imgs[count].createGraphics();
				gr.drawImage(image, 0, 0, chunkWidth, chunkHeight, chunkWidth * y, chunkHeight * x,
						chunkWidth * y + chunkWidth, chunkHeight * x + chunkHeight, null);
				gr.dispose();
				tiles.add(new Tile(imgs[count]));
				++count;
			}
		}

		room = new Room();

		mouse = new Mouse();
	}

	/*
	 * Send coordinates to the mouse handler (Mouse.do_action())
	 * Update the display after action is done
	 * Check if it's a winning move
	 */
	public void setMouseHandler() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				// mouse handler code
				int p_x = me.getX();
				int p_y = me.getY();
				mouse.do_action(p_x, p_y);
				repaint();
				winCheck();
			}
		});
	}

	// Show a win message if the user wins
	public void winCheck() {
		for (int i = 0; i <= 15; i++) {
			if (i != tiles.get(i).getId()) {
				return;
			}
		}
		javax.swing.JOptionPane.showMessageDialog(null, "Congratulations! You won!", "Congratulations", JOptionPane.INFORMATION_MESSAGE);
	}

	public void paintComponent(Graphics g) {
		if (isFirst) {
			define();
			isFirst = false;
		}
		g.clearRect(0, 0, getWidth(), getHeight());
		room.draw(g);
	}

	private boolean canScramble = true;
	
	// runs automatically when thread started
	// game loop
	public void run() {
		while (true) {
			if (canScramble) {
				try {
					Thread.sleep(3000);
				} catch (Exception e) {

				}
				Scrambler scrambler = new Scrambler();

				for (int i = 0; i < 1000; i++) {
					scrambler.scramble(new Random().nextInt(16));
					repaint();
					try {
						Thread.sleep(5);
					} catch (Exception e) {
						
					}
				}
				setMouseHandler();
				canScramble = false;
			}

			try {
				Thread.sleep(2);
			} catch (Exception e) {
				System.out.println("Couldn't sleep!");
			}
		}
	}

}