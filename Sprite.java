import java.awt.Image;
import java.awt.Graphics;

public class Sprite {
  private Image picture;
  private CoordinateSystem coordinates;

  // Constructor
  public Sprite(int x, int y, Image pic) {
    picture = pic;
    coordinates = new CoordinateSystem(x, y, pic);
  }

  public void moveForward(int distance) {
    coordinates.shift(distance, 0);
  }

  public void moveSideways() {
    coordinates.shift(0, 900);
  }

  public void draw(Graphics g) {
    coordinates.drawImage(g, picture);
  }
}
