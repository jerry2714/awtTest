import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

class Ghost extends Component
{
	Image image;
	
	public Ghost()
	{
        try {
            image = ImageIO.read(new File("example.bmp"));
        }
        catch (Exception ex) {
            System.out.println("No example.jpg!!");
        }
		
	}
}

public class GraphicsDemo08	 extends Canvas {
    
     static Ghost ghost = new Ghost();
	 static GraphicsDemo08 canvas = new GraphicsDemo08();
    public static void main(String[] args) {
        Frame frame = new Frame("AWTDemo");
        frame.addWindowListener(new AdapterDemo());
        frame.setSize(520, 415);
        canvas.addMouseMotionListener(new MouseLis());
        
        
		frame.setLayout(null);
		frame.add(canvas);
        //canvas.setBounds(50, 50, ghost.getWidth(), ghost.getHeight());
        canvas.setBounds(50, 50, 50, 50);
		canvas.setBackground(new Color(255, 255, 0));
		frame.add(ghost);
        frame.setVisible(true);
    }
     
    public void paint(Graphics g) {
         
        //g.drawImage(ghost.image, 0, 0, ghost.getWidth(), ghost.getHeight(), null);
        g.drawImage(ghost.image, 0, 0, 50, 50, this);
		System.out.println(ghost.getWidth()+" " + ghost.getHeight());
    }

	static class MouseLis extends MouseMotionAdapter
	{
		public void mouseMoved(MouseEvent e)
		{
			// if(canvas.contains(e.getX(), e.getY()))
				// System.out.println("Y");
		}
	}
}
 
class AdapterDemo extends WindowAdapter {
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
}