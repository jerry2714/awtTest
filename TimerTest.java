import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;
class GameLoop extends TimerTask
{
	int a = 0;
	public void run()
	{
		System.out.println(a);
		a++;
	}
}
public class TimerTest
{
	public static void main(String args[])
	{
		Timer t = new Timer();
		GameLoop g = new GameLoop();
		t.schedule(g, 0, 1000);
	}
}