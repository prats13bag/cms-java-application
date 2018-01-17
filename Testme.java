import javax.swing.*;
import java.io.*;
import java.awt.*;

@SuppressWarnings("all")
public class Testme extends JFrame implements Runnable
{
private JLabel label;
Thread t;
JPanel panel=new JPanel();
public Testme()
{
  super();
  t=new Thread(this);
 
  panel.setLayout(new FlowLayout());
  label=new JLabel("BLINKING TEXT");
  panel.add(label);
  getContentPane().setLayout(new FlowLayout());
  getContentPane().add(panel);
  t.start();
}

public void run()
{
	doit();
	System.out.println("I am in Test Mode");
	try
	{
		Thread.sleep(1000);
	}  catch(InterruptedException ie){}
	
}
public void doit()
{
	label.setVisible(!label.isVisible());
	panel.add(label);
	repaint();	
}

public static void main(String args[])
{
  Testme t=new Testme();
  t.setSize(200,200);
  t.setVisible(true);

}

}
