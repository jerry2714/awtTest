import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class DrawTest extends Frame
{
	public DrawTest(){}
	public DrawTest(String str){super(str);}

	public static void main(String args[])
	{
		DrawTest frm = new DrawTest("Running Ghost");
		
		Game game = new Game();
		
		
		frm.setSize(800, 600);
		frm.setLocation(100, 100);
		
		frm.setBackground(new Color(255, 255, 0));
		frm.addWindowListener(new WinLis());
		
		frm.add(game);
		
		frm.setVisible(true);
		game.init();
		game.gameStart();
		

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
}