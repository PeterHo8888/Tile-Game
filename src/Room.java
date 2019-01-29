import java.awt.*;

public class Room
{
	
	public Room()
	{
		define();
	}
	
	public void define()
	{
		
	}
	
	public void draw(Graphics g)
	{
		int count = 0;
		for (int y = 0; y < Screen.rows; y++) {
			for (int x = 0; x < Screen.cols; x++) {
				Screen.tiles.get(count).draw(g, x, y);
				g.drawRect(x * Screen.chunkWidth, y * Screen.chunkHeight, Screen.chunkWidth,
						Screen.chunkHeight);
				++count;
			}
		}
	}
	
}