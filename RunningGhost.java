import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class RunningGhost extends Frame
{
	public RunningGhost(){}
	public RunningGhost(String str){super(str);}
	static Ghost ghost1 = new Ghost("src\\images\\Ghost1.png");
	static Ghost ghost2 = new Ghost("src\\images\\Ghost2.png");
	public static void main(String args[])
	{
		RunningGhost frm = new RunningGhost("Running Ghost");
		Panel panel = new Panel();
		GameDraw gd = new GameDraw();
		
		
		frm.setSize(800, 600);
		frm.setLocation(100, 100);
		panel.setLayout(new BorderLayout());
		frm.setBackground(new Color(255, 255, 0));
		frm.addWindowListener(new WinLis());
		
		frm.add(panel);
		panel.add(gd);
		
		
		frm.setVisible(true);
		frm.addKeyListener(new KeyLis());
		frm.requestFocus();
		
		//panel.setBackground(new Color(0, 0, 0));
		gd.init();
		gd.addToList(ghost1);
		gd.addToList(ghost2);
		ghost2.setPosition(700, 50);
		ghost1.setDirection(GameObject.Direction.RIGHT);
		ghost2.setDirection(GameObject.Direction.LEFT);
		
		Thread t1 = new Thread(gd);
		t1.start();
	}
	
	
	static class WinLis extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			Frame frm = (Frame)e.getSource();
			frm.dispose();
			System.exit(0);
		}
	}
	static class KeyLis extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int k = e.getKeyCode();
			switch(k)
			{
				case e.VK_UP:
					ghost1.setDirection(GameObject.Direction.UP);
					break;
				case KeyEvent.VK_DOWN:
					ghost1.setDirection(GameObject.Direction.DOWN);
					break;
				case KeyEvent.VK_LEFT:
					ghost1.setDirection(GameObject.Direction.LEFT);
					break;
				case KeyEvent.VK_RIGHT:
					ghost1.setDirection(GameObject.Direction.RIGHT);
					break;
			}
		}
		public void keyReleased(KeyEvent e)
		{
		}
	}
}