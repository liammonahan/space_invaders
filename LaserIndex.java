import java.util.ArrayList;

public class LaserIndex extends ArrayList<Laser>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 626509505491371432L;
	int i = 0;
	  
	public LaserIndex()
	{
		super();
	}

	public void add(int x, int y, boolean direction)
	{
		add(new Laser(x, y, direction));
	}
	
	public void move()
	{
		if(Timing.laserCanMove())
		{
			for(Laser laser : this)
			{
				laser.move();
			}
		}
	}
	
	public void hit()
	{
		for(Laser laser : this)
		{
			laser.hit();
		}
	}
}
