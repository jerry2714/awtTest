import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


class DoubleBufCanvas extends Canvas
{
	BufferedImage img;
	Graphics2D g2d;
	
	public void paintCanvas(Graphics g){}
	
	
	public void update(Graphics g)
	{
		if(img == null){
			img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		}
		Graphics2D canvas = img.createGraphics();
		canvas.clearRect(0, 0, getWidth(), getHeight());
		paintCanvas(canvas);
		//g.drawImage(img, 0, 0, this);
		paint(g);
	}
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, this);
	}
}
class Ghost extends DoubleBufCanvas implements Runnable
{
	BufferedImage img;
	int fps = 30;
	int ox, oy, x, y;
	public Ghost()
	{
		try {
            img = ImageIO.read(new File("ghost.png"));
        }
        catch (Exception ex) {
            System.out.println("No ghost.png!!");
        }
		// setSize(50, 50);
		ox = oy = x = y = 0;
		//System.out.println(""+isDoubleBuffered());
	}
	public void paintCanvas(Graphics g)
	{
		g.drawImage(img, x, y, 50, 50, this);
	} 
	public void run()
	{
		for(int i = 0; i < 500; i++)
		{
			x = ox + 1;
			y = oy;
			
			ox = x;
			oy = y;
			repaint();
			try
			{
				Thread.sleep(1000/fps);
			}
			catch(Exception e)
			{
				System.exit(0);
			}
		}
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
		Ghost ghost = new Ghost();
		
		Thread t1 = new Thread(ghost);
		frm.setSize(800, 600);
		frm.setLocation(100, 100);
		
		
		frm.add(panel);
		
		panel.setLayout(new BorderLayout());
		
		panel.add(ghost);
		
		frm.setBackground(new Color(0, 0, 0));
		frm.addWindowListener(new WinLis());
		frm.setVisible(true);
		t1.start();
		
	}
	public void paint(Graphics g)
	{
		Rectangle rect = g.getClipBounds();
		System.out.println(rect.width + " " + rect.height);
		System.out.println(rect.x + " " + rect.y);
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