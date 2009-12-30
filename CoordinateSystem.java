import java.awt.*;
import java.awt.geom.AffineTransform;

public class CoordinateSystem {
  private Image picture;
  private AffineTransform coordTransform;
  int xCord, yCord;

   //  0 degrees points east
  public CoordinateSystem(int x, int y, Image pic) {
    picture = pic;
    coordTransform = new AffineTransform();
    
    int w = picture.getWidth(null) / 2;
    int h = picture.getHeight(null) / 2;
    xCord = x - w;
    yCord = y - h;
    coordTransform.translate(x - w, y - h);
  }

  public void shift(double dx, double dy) {
    coordTransform.translate(dx, dy);
    xCord += dx;
    yCord += dy;
  }
  
  public int getX() {
    return xCord;
  }
  
  public int getY() {
    return yCord;
  }

  public void drawImage(Graphics g, Image picture) {
    ((Graphics2D)g).drawImage(picture, coordTransform, null);
  }
}
