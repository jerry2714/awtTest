import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

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
		//int j = 0;
		/*for(int i = 0; i < 5000; i++)
		{
			if(j == 10)
			{
				x = ox+1;
				y = oy;
			
				ox = x;
				oy = y;
				j = 0;
			}
			else
				j++;
			
		}*/
	}
}

public class RunningGhost extends Frame
{
	public RunningGhost(){}
	public RunningGhost(String str){super(str);}
	public static void main(String args[])
	{
		RunningGhost frm = new RunningGhost("Running Ghost");
		Panel panel = new Panel();
		GameDraw gd = new GameDraw();
		Ghost ghost = new Ghost("src\\images\\Ghost.png");
		
		
		frm.setSize(800, 600);
		frm.setLocation(100, 100);
		panel.setLayout(new BorderLayout());
		frm.setBackground(new Color(255, 255, 0));
		frm.addWindowListener(new WinLis());
		
		frm.add(panel);
		panel.add(gd);
		
		
		frm.setVisible(true);
		
		//panel.setBackground(new Color(0, 0, 0));
		gd.init();
		gd.addToList(ghost);
		
		Thread t1 = new Thread(gd);
		t1.start();
	}
	public void paint(Graphics g)
	{
		Rectangle rect = g.getClipBounds();
		// System.out.println(rect.width + " " + rect.height);
		// System.out.println(rect.x + " " + rect.y);
	}
	
	static class WinLis extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Frame frm = (Frame)e.getSource();
			frm.dispose();
		}
	}
}