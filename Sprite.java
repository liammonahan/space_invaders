// Represents a foot, used for displaying walking creatures.

import java.awt.Image;
import java.awt.Graphics;

public class Sprite
{
  private Image picture;
  private CoordinateSystem coordinates;

  // Constructor
  public Sprite(int x, int y, Image pic)
  {
    picture = pic;
    coordinates = new CoordinateSystem(x, y, pic);
  }

  // Moves this foot forward by distance pixels
  // (or backward if distance < 0).
  public void moveForward(int distance)
  {
    coordinates.shift(distance, 0);
  }

  // Moves this foot sideways by distance pixels
  // (to the right if distance  > 0 or to the left
  // if distance < 0).
  public void moveSideways()
  {
    coordinates.shift(0, 900);
  }

  // Turns this foot (clockwise for degrees > 0).

  // Draws this foot in the appropriate coordinate system.
  public void draw(Graphics g)
  {
    coordinates.drawImage(g, picture);
  }
}

