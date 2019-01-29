
public class Mouse {
	
	public Mouse()
	{
		
	}
	
	/*
	 * 
	 * 0  1  2  3 
	 * 4  5  6  7
	 * 8  9  10 11
	 * 12 13 14 15
	 * 
	 */
	
	int hor, vert;
	
	// tile 0 was removed
	int removed = 0;
	
	public void do_action(int x, int y)
	{
		// figure out what tile the user clicked with
		
		hor = (int) ((float) x / 720.0 * (float) Screen.cols);
		vert = (int) ((float) y / 720.0 * (float) Screen.rows);
		
		move();
	}
	
	public void move()
	{
		// ignore if blank tile clicked
		if (Screen.tiles.get(hor + vert * Screen.cols).getId() == removed) {
			System.out.println("Blank at id " + Screen.tiles.get(hor + vert * Screen.cols).getId());
			return;
		}
		// check left, right, up, down
		int up = -1, left = -1, down = -1, right = -1;
		try {
			// y's can't be 0 for right movement
			if (vert != 0) {
				up = Screen.tiles.get(hor + (vert - 1) * Screen.cols).getId();
			}
			// x's can't be 0 for left movement
			if (hor != 0) {
				left = Screen.tiles.get(hor - 1 + vert * Screen.cols).getId();
			}
			// y's can't be cols - 1 for down movement
			if (vert != Screen.cols - 1) {
				down = Screen.tiles.get(hor + (vert + 1) * Screen.cols).getId();
			}
			if (hor != Screen.cols - 1) {
				right = Screen.tiles.get(hor + 1 + vert * Screen.cols).getId();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// determine which way to move a piece
		// by comparing a side with the id of the removed block
		if (up == removed) {
			moveUp();
		} else if (left == removed) {
			moveLeft();
		} else if (right == removed) {
			moveRight();
		} else if (down == removed) {
			moveDown();
		}
	}
	
	/*
	 * These 4 functions essentially swap the two pieces in the ArrayList
	 */
	public void moveUp()
	{
		Tile tile = Screen.tiles.get(hor + vert * Screen.cols);
		Tile blank = Screen.tiles.get(hor + (vert - 1) * Screen.cols);
		Screen.tiles.set(hor + (vert - 1) * Screen.cols, tile);
		Screen.tiles.set(hor + vert * Screen.cols, blank);
	}
	
	public void moveLeft()
	{
		Tile tile = Screen.tiles.get(hor + vert * Screen.cols);
		Tile blank = Screen.tiles.get(hor - 1 + vert * Screen.cols);
		Screen.tiles.set(hor - 1 + vert * Screen.cols, tile);
		Screen.tiles.set(hor + vert * Screen.cols, blank);
	}
	
	public void moveRight()
	{
		Tile tile = Screen.tiles.get(hor + vert * Screen.cols);
		Tile blank = Screen.tiles.get(hor + 1 + vert * Screen.cols);
		Screen.tiles.set(hor + 1 + vert * Screen.cols, tile);
		Screen.tiles.set(hor + vert * Screen.cols, blank);
	}
	
	public void moveDown()
	{
		Tile tile = Screen.tiles.get(hor + vert * Screen.cols);
		Tile blank = Screen.tiles.get(hor + (vert + 1) * Screen.cols);
		Screen.tiles.set(hor + (vert + 1) * Screen.cols, tile);
		Screen.tiles.set(hor + vert * Screen.cols, blank);
	}
	
}
