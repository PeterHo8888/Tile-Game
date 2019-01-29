import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile
{
	private static int id = 0;
	private int b_id;
	
	private BufferedImage image = new BufferedImage(Screen.chunkWidth, Screen.chunkHeight,
			BufferedImage.TYPE_INT_RGB);
	
	/*
	 * Apply the passed in image chunk to the object
	 * If the id = 0, then it's the top and we should
	 * put in a blank square instead
	 */
	public Tile(BufferedImage image)
	{
		this.b_id = id++;
		if (this.b_id != 0) {
			this.image = image;
		} else {
			Graphics gr = this.image.createGraphics();
			gr.setColor(Color.CYAN);
			gr.fillRect(0, 0, Screen.chunkWidth, Screen.chunkHeight);
			gr.dispose();
		}
	}
	
	public void draw(Graphics g, int x, int y)
	{
		g.drawImage(image, x * image.getWidth(), y * image.getHeight(), null);
	}
	
	public int getId()
	{
		return this.b_id;
	}
}
