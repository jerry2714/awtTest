import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

class GameDraw extends Canvas implements Runnable
{
	private static BufferedImage img = null;//繪圖用
	private static Graphics2D g2d;//img的Graphic context
	private static LinkedList<GameObject> list = new LinkedList<GameObject>();//待繪製的繪圖串列
	private static Iterator<GameObject> listItr;//list的Iterator
	public void init()//初始化，開始使用前必須呼叫
	{
		img = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		g2d.clearRect(0, 0, getWidth(), getHeight());
		list.clear();
	}
	
	public void paintCanvas(Graphics g){}//讓所有子類別對g2d進行操作(繪圖)
	
	public void addToList(GameObject go)//將一個GameObject加入繪圖串列
	{
		list.add(go);
	}
	public void listSort()//將list中的元素根據優先權進行排序
	{
		Collections.sort(list);
	}
	public void update(Graphics g)
	{	
		
		if(img == null)
		{
			//System.out.println("img == null");
			img = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);
			g2d = img.createGraphics();
			g2d.clearRect(0, 0, getWidth(), getHeight());
		}
		paint(g);
	}
	public void paint(Graphics g)
	{
		//setBackground(new Color(255, 0, 0));
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void run()
	{
		GameObject go;
		System.out.println("start");
		//init();
		
		while(true)
		{
			listItr = list.iterator();
			
			try
			{
				Thread.sleep(1000);
				
				while(listItr.hasNext())
				{
					System.out.println("1");
					go = listItr.next();
					go.paintCanvas(g2d);
				}
				repaint();
			}
			catch(InterruptedException e)
			{
				System.out.println("GameDraw Thread error");
				System.exit(0);
			}
		}
	}
}

class GameObject extends GameDraw implements Comparable<GameObject>
{
	private int priority;//繪圖優先權(最大的最後繪製，所以蓋在最上面)
	BufferedImage img;	//此物件目前的完整圖形
	int ox, oy, x, y;	//上一次座標、新座標
	
	public void setPriority(int a){priority = a;}
	
	
	// public void show1(){System.out.println(priority);}
	
	public int compareTo(GameObject go)//將繪圖優先權設為排序依據
	{
		return this.priority - go.priority;
	}
}

// class Ghost extends GameObject