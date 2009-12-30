import java.awt.geom.Rectangle2D;

class Laser extends Rectangle2D.Float {
	public boolean directionUp;

	public Laser(int x, int y, boolean direction) {
		super(x, y, 5, 10);
		directionUp = direction;
	}

	public void move() {
		if(directionUp) {
			super.y -= 15;
		}
		else {
			super.y += 15;
		}
	}
	
	public void hit() {
		for(int i = 0; i < Ship.ships_col; i++) {
      for(int j = 0; j < Ship.ships_row; j++) {
        if(directionUp) {
          if((Math.abs(Ship.ships[i][j].coordinates.getX() + 20 - super.x) < 15 && Math.abs(Ship.ships[i][j].coordinates.getY() + 20 - super.y) < 10)) {
	          super.x = -600; //moves offscreen because it cannot be deleted correctly
	          super.y = -600;
	          Ship.ships[i][j].remove(i, j);
	          Main.score += 120;
          }
        }
        else {
          if((Math.abs(Main.turret_x + 25 - super.x) < 25 && Math.abs(Main.turret_y + 25 - super.y) < 25)) {
	          super.x = -600; //moves offscreen because it cannot be deleted correctly
	          super.y = -600;
	          Main.lives--;  //you got hit!
	          checkIfAlive();
          }
        }
      }
    }
	}
	
	public void checkIfAlive() {
		if(Main.life <= 0) {
			System.exit(0); //oh no!  Game over!!!
		}
	}
}
