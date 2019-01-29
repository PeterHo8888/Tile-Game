
public class Scrambler {

	public Scrambler() {
		
	}

	public void scramble(int n) {

		moveUp(n);
		moveDown(n);
		moveRight(n);
		moveLeft(n);

	}

	public void moveUp(int n) {
		try {
			Tile tile = Screen.tiles.get(n);
			Tile blank = Screen.tiles.get(n - 4);
			if (blank.getId() == 0) {
				Screen.tiles.set(n - 4, tile);
				Screen.tiles.set(n, blank);
			}
		} catch (Exception e) {

		}
	}

	public void moveDown(int n) {
		try {
			Tile tile = Screen.tiles.get(n);
			Tile blank = Screen.tiles.get(n + 4);
			if (blank.getId() == 0) {
				Screen.tiles.set(n + 4, tile);
				Screen.tiles.set(n, blank);
			}
		} catch (Exception e) {

		}
	}

	public void moveLeft(int n) {
		try {
			Tile tile = Screen.tiles.get(n);
			Tile blank = Screen.tiles.get(n - 1);
			if (blank.getId() == 0) {
				Screen.tiles.set(n - 1, tile);
				Screen.tiles.set(n, blank);
			}
		} catch (Exception e) {

		}
	}

	public void moveRight(int n) {
		try {
			Tile tile = Screen.tiles.get(n);
			Tile blank = Screen.tiles.get(n + 1);
			if (blank.getId() == 0) {
				Screen.tiles.set(n + 1, tile);
				Screen.tiles.set(n, blank);
			}
		} catch (Exception e) {

		}
	}
}