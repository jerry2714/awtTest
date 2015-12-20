import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
class Ghost extends GameObject
{
	public Ghost(String str)
	{
		try
		{
            img = ImageIO.read(new File(str));
        }
        catch (Exception ex)
		{
            System.out.println(str);
        }
		// setSize(50, 50);
		ox = oy = x = y = 0;
		//System.out.println(""+isDoubleBuffered());
	}
	public void paintCanvas(Graphics g)
	{
		/*if(img == null)
			System.out.println("ghost missed");*/
		move();
		try
		{
			g.drawImage(img, x, y, 50, 50, this);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	} 
	
	public void move()
	{
		for(int i = 0; i < 10; i++)
		{
			oy = y;
			ox = x;
			switch(direction)
			{
				case CENTER:
					break;
				case UP:
					y--;
					break;
				case DOWN:
					y++;
					break;
				case LEFT:
					x--;
					break;
				case RIGHT:
					x++;
					break;
				default:
			}
		}
	}
}