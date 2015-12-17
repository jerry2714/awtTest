import java.awt.*;
import java.awt.event.*;

public class keytest extends Frame implements KeyListener
{
	static keytest frm = new keytest();
	static TextArea txa = new TextArea("", 4, 19, TextArea.SCROLLBARS_NONE);
	static TextField txf = new TextField(18);
	public static void main(String args[])
	{
		
		frm.setSize(200, 150);
		frm.setTitle("keytest");
		//frm.setLayout(new FlowLayout(FlowLayout.CENTER));
		frm.setLayout(null);
		//txf.addKeyListener(frm);
		//new ActLis();
		
		txa.setSize(100, 100);
		txa.setLocation(50, 50);
		txa.setEditable(false);
		
		//frm.add(txf);
		//frm.add(txa);
		frm.addKeyListener(frm);
		frm.setVisible(true);
		frm.requestFocus();
	}
	static class ActLis implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("t");
		}
	}
	 public void keyPressed(KeyEvent e)
	{
		//txa.setText("");
		//txa.append("keyPressed()\n");
		System.out.println("keyPress");
	}
	public void keyReleased(KeyEvent e)
	{
		//txa.append("keyReleased()\n");
		System.out.println("keyReleased");
	}
	public void keyTyped(KeyEvent e)
	{
		//txa.append("ketTyped()\n");
		System.out.println("keyTyped");
	} 
}