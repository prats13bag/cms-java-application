import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

@SuppressWarnings("all")
public class MainForm extends JFrame implements ActionListener
{
  private JButton cmd_Add,cmd_Birthday,cmd_Anniversary,cmd_Address,cmd_UpdateDelete,cmd_Search,cmd_exit;
  private JLabel mainLabel;
  public MainForm(String fname)
  {
    super(fname);
    getContentPane().setLayout(new BorderLayout());
    JPanel panel1=new JPanel();
    panel1.setLayout(new GridLayout(4,1,10,5));
    JPanel panel=new JPanel();
    panel.setLayout(new FlowLayout());
    mainLabel=new JLabel("B. R. Enterprises");
    panel.add(mainLabel);
    cmd_Add=new JButton("Add Contact");
    cmd_UpdateDelete=new JButton("Update/Delete Contact");
    cmd_Search=new JButton("Search Contact");
    cmd_exit=new JButton("Exit");
    
    panel1.add(cmd_Add);
    panel1.add(cmd_UpdateDelete);
    panel1.add(cmd_Search);
    panel1.add(cmd_exit);

    getContentPane().add(panel,BorderLayout.NORTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
    cmd_Add.addActionListener(this);
    cmd_UpdateDelete.addActionListener(this);
    cmd_Search.addActionListener(this);
    cmd_exit.addActionListener(this);
    addWindowListener(new MyWindowAdapter());
  }

  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      	//setVisible(false);
	    //LoginForm loginform=new LoginForm("Login Form");
    	//loginform.setLocation(200,150);
    	//loginform.setSize(300,150);
    	//loginform.setResizable(false);
    	//loginform.setVisible(true);
	    //dispose();
	System.exit(0);
    }

  }
  
  /*public void run()
  {
	  
	  try
	  {
		  Thread.sleep(1000);
		  
	  }  catch(InterruptedException ie) {}
	  
	  repaint();
  }
  
  public void update(Graphics g)
  {
	  mainLabel.setVisible(!(mainLabel.isVisible()));
	  	  
  }
*/
  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cmd_Add)
    {
      AddForm addform=new AddForm("Add Contact Form");
      addform.setLocation(this.getX(),this.getY());
      addform.setSize(300,250);
      addform.setResizable(false);
      addform.setVisible(true);
	  setVisible(false);
    }
    else if(ae.getSource()==cmd_UpdateDelete)
    {
      UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
      udform.setLocation(this.getX(),this.getY());
      udform.setSize(300,300);
      udform.setResizable(false);
      udform.setVisible(true);
	  setVisible(false);
    }
    else if(ae.getSource()==cmd_Search)
    {
      SearchForm sform=new SearchForm("Search Contact Form");
      sform.setLocation(this.getX(),this.getY());
      sform.setSize(300,300);
      sform.setResizable(false);
      sform.setVisible(true);
	  setVisible(false);	

    }
    else if(ae.getSource()==cmd_exit)
    {
	    /*setVisible(false);
	    LoginForm loginform=new LoginForm("Login Form");
    	loginform.setLocation(250,150);
    	loginform.setSize(300,150);
    	loginform.setResizable(false);
    	loginform.setVisible(true);*/
	    dispose();
  
    }

  }

}

