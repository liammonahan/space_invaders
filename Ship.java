import java.awt.Image;
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Ship {
	private static Image pic = new ImageIcon("ship.png").getImage();
	public CoordinateSystem coordinates;
	static int ships_row = 6, ships_col = 6;
	public static Ship[][] ships = new Ship[6][6];
	private static String direction = "right";

	// Constructor
	public Ship(int x, int y) {
		coordinates = new CoordinateSystem(x, y, pic);
	}

	public static void massRemove() {
		for(int i = 0; i < ships_row; i++) {
			for(int j = 0; j < ships_col; j++) {
				ships[i][j].remove(i, j);
			}
		}
	}

	public void remove(int i, int j) {
		ships[i][j] = new Ship(1500, 1500);
	}

	// Moves this ships side to side
	public static void moveArray(int shift) {
		for (int i = 0; i < ships_row; i++) {
			for (int j = 0; j < ships_col; j++) {
				ships[i][j].coordinates.shift(shift, 0);
			}
		}
	}

	public static void shoot() {
		int a = randomInt(0, ships_row - 1);
		int b = randomInt(0, ships_col - 1);
		Main.lasers.add(ships[a][b].coordinates.getX() + 20, 
		    ships[a][b].coordinates.getY() + 20, false);
	}

	public static int randomInt(int low, int high) {
		int result = (int) (Math.random() * (high - low + 1)) + low;
		return result;
	}

	public static void move() {
		if (Timing.shipCanMove()) {
			if (direction.equals("right")) {
				moveArray(20);
				direction = "left";
			} else {
				moveArray(-20);
				direction = "right";
			}
		}
	}

	public static void createFleet() {
		for (int i = 0; i < ships_row; i++) {
			for (int j = 0; j < ships_col; j++) {
				ships[i][j] = new Ship(i * 60 + 150, j * 50 + 50);
			}
		}
	}

	// Draws this ship in the appropriate coordinate system.
	public static void draw(Graphics g) {
		for (int i = 0; i < ships_row; i++) {
			for (int j = 0; j < ships_col; j++) {
				ships[i][j].coordinates.drawImage(g, pic);
			}
		}
	}
}
