import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.*;

interface Initialize	//���F��ø�ϰϤj�p�b�{���}�l�ɸ�����@�P(�@�ɳ·СA�ثe�Q����n�N�I����k)
{
	public void init();
	public void setFrameSize(int width, int height);
}
class Game extends Panel implements Initialize
{
	GameLoop gl = new GameLoop();
	Ghost ghost1 = new Ghost("src\\images\\Ghost1.png");
	Ghost ghost2 = new Ghost("src\\images\\Ghost2.png");
	Background bg = new Background();
	int frameWidth, frameHeight;
	Timer timer = new Timer();
	Game()
	{
		this.setLayout(new BorderLayout());
		this.add(gl.getCanvas());
	}
	public void setFrameSize(int width, int height)
	{
		frameWidth = width;
		frameHeight = height;
		gl.setFrameSize(width, height);
	}
	public void init()
	{
		setFrameSize(getWidth(), getHeight());
		gl.init();
		gl.addToList(ghost1);
		gl.addToList(ghost2);
		gl.addToList(bg);
	}
	public void gameStart()
	{
		// if(gl.getCanvas().isVisible())
		// {
			// System.out.println("true");
			// System.out.println(gl.getCanvas().getCanvasWidth());
		// }
		ghost1.setDirection(GameObject.Direction.RIGHT);
		ghost2.setDirection(GameObject.Direction.RIGHT);
		bg.init();
		ghost2.setPosition(0, 200);
		timer.schedule(gl, 0, 10);
	}
	
	class KeyLis extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			int k = e.getKeyCode();
			switch(k)
			{
				case KeyEvent.VK_UP:
					ghost1.setDirection(GameObject.Direction.UP);
					gl.getCanvas().draw(bg);
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
class GameLoop extends TimerTask implements Initialize //
{
	GameCanvas canvas;
	LinkedList<GameObject> list = new LinkedList<GameObject>();//�s��Ҧ��C�������󪺦�C
	Iterator<GameObject> listItr;//list��Iterator
	GameLoop()
	{
		canvas = new GameCanvas();
		list.clear();
	}
	public void setFrameSize(int width, int height)
	{
		canvas.setFrameSize(width, height);
	}
	public void init()
	{
		canvas.init();
	}
	public GameCanvas getCanvas()
	{
		return canvas;
	}
	public void addToList(GameObject go)//�N�@��GameObject�[�J��C
	{
		list.add(go);
	}
	public void listSort()//�Nlist���������ھ��u���v�i��Ƨ�
	{
		Collections.sort(list);
	} 
	public void run()
	{
		listSort();
		listItr = list.iterator();
		while(listItr.hasNext())
		{
			canvas.draw(listItr.next());
		}
		canvas.update(canvas.getGraphics());
	}
}
class GameCanvas extends Canvas implements Initialize
{
	private static BufferedImage img = null;//ø�ϥ�
	private static Graphics2D g2d;//img��Graphic context
	public static int frameWidth, frameHeight;
	
	public void setFrameSize(int width, int height)
	{
		frameWidth = width;
		frameHeight = height;
	}
	public void init()//��l�ơA�}�l�ϥΫe�����I�s
	{
		img = new BufferedImage(frameWidth, frameHeight, BufferedImage.TYPE_INT_ARGB);
		g2d = img.createGraphics();
		g2d.clearRect(0, 0, frameWidth, frameHeight);
	}
	public int getCanvasWidth(){return getWidth();}
	public int getCanvasHeight(){return getHeight();}
	public void paintCanvas(Graphics g){}//���Ҧ��l���O��g2d�i��ާ@(ø��)
	
	
	public void update(Graphics g)
	{	
		if(img == null)
			init();
		//System.out.println("update");
		paint(g);
	}
	public void paint(Graphics g)
	{
		//setBackground(new Color(255, 0, 0));
		//System.out.println("paint");
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
	}
	
	public void draw(GameObject go)
	{
		go.paintCanvas(g2d);
		//System.out.println("draw");
	}
}

class GameObject extends GameCanvas implements Comparable<GameObject>
{
	private int priority;//ø���u���v(�̤j���̫�ø�s�A�ҥH�\�b�̤W��)
	BufferedImage img;	//������ثe������ϧ�
	int ox, oy, x, y;	//�W�@���y�СB�s�y��
	int width, height;	//���e
	public static enum Direction{CENTER, RIGHT, LEFT, UP, DOWN}//���󲾰ʤ�V�ACENTER�N����
	Direction direction = Direction.CENTER;//���󲾰ʤ�V
	public void setPriority(int a){priority = a;}
	public void setPosition(int x, int y){this.x = x; this.y = y;}
	public void setDirection(Direction d){direction = d;}
	
	// public void show1(){System.out.println(priority);}
	
	public int compareTo(GameObject go)//�Nø���u���v�]���ƧǨ̾�
	{
		return this.priority - go.priority;
	}
}
