import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

class GameDraw extends Canvas implements Runnable
{
	private static BufferedImage img = null;//ø�ϥ�
	private static Graphics2D g2d;//img��Graphic context
	private static LinkedList<GameObject> list = new LinkedList<GameObject>();//��ø�s��ø�Ϧ�C
	private static Iterator<GameObject> listItr;//list��Iterator
	public void init()//��l�ơA�}�l�ϥΫe�����I�s
	{
		img = new BufferedImage(getWidth(),getHeight(), BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		g2d.clearRect(0, 0, getWidth(), getHeight());
		list.clear();
	}
	
	public void paintCanvas(Graphics g){}//���Ҧ��l���O��g2d�i��ާ@(ø��)
	
	public void addToList(GameObject go)//�N�@��GameObject�[�Jø�Ϧ�C
	{
		list.add(go);
	}
	public void listSort()//�Nlist���������ھ��u���v�i��Ƨ�
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
	private int priority;//ø���u���v(�̤j���̫�ø�s�A�ҥH�\�b�̤W��)
	BufferedImage img;	//������ثe������ϧ�
	int ox, oy, x, y;	//�W�@���y�СB�s�y��
	
	public void setPriority(int a){priority = a;}
	
	
	// public void show1(){System.out.println(priority);}
	
	public int compareTo(GameObject go)//�Nø���u���v�]���ƧǨ̾�
	{
		return this.priority - go.priority;
	}
}

// class Ghost extends GameObject