/*<applet code=CMSApplication width=400 height=150></applet>*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.applet.*;

@SuppressWarnings("all")
class LoginForm extends JFrame implements ActionListener
{
  private JTextField txt_UserName;
  private JPasswordField txt_Psswd;
  private JButton cmd_OK, cmd_Cancel,cmd_Exit;
  private JApplet AppObj;
  public LoginForm(String fname, JApplet Tmp)
  {
    super(fname);
    AppObj=Tmp;
    txt_UserName=new JTextField(10);
    txt_Psswd=new JPasswordField(10);
    txt_Psswd.setEchoChar('*');
    cmd_OK=new JButton("Login");
    cmd_Cancel=new JButton("Reset");
    cmd_Exit=new JButton("Exit");
	
    getContentPane().setLayout(new BorderLayout());
    
    JPanel TitlePanel=new JPanel();
    TitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    TitlePanel.add(new JLabel("B. R. Enterprises"));
    
    JPanel panel1=new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.add(new Label("Login ID : "));
    panel1.add(txt_UserName);

    JPanel panel2=new JPanel();
    panel2.setLayout(new FlowLayout());
    panel2.add(new Label("Password : "));
    panel2.add(txt_Psswd);

    JPanel MainPanel=new JPanel();
    MainPanel.setLayout(new BorderLayout());
    MainPanel.add(panel1,BorderLayout.NORTH);
    MainPanel.add(panel2,BorderLayout.CENTER);
    
    JPanel panel3=new JPanel();    
    panel3.setLayout(new FlowLayout());
    panel3.add(cmd_OK);
    panel3.add(cmd_Cancel);
    panel3.add(cmd_Exit);

    getContentPane().add(TitlePanel,BorderLayout.NORTH);
    getContentPane().add(MainPanel,BorderLayout.CENTER);
    getContentPane().add(panel3,BorderLayout.SOUTH);
    
	addWindowListener(new MyWindowAdapter());
    cmd_OK.addActionListener(this);
    cmd_Cancel.addActionListener(this);
    cmd_Exit.addActionListener(this);
  }

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cmd_OK)
    {
      String u=txt_UserName.getText();
      char [] p=txt_Psswd.getPassword();
      String ps=new String(p);
      if(checkLogin(u, ps))
      {
	  MainForm mform=new MainForm("Main Form");
	  mform.setSize(300,300);
      	  mform.setResizable(true);
      	  mform.setVisible(true);
      	  setVisible(false);
	  //displayMesg("Valid Username or Password");
      }
      else
      {
	      displayMesg("Invalid Username or Password");
      }

      
    }
    else if(ae.getSource()==cmd_Exit)
    {
	dispose();
    }
    else if(ae.getSource()==cmd_Cancel)
    {
	txt_UserName.setText("");
	txt_Psswd.setText("");
    }

  }  
  public void displayMesg(String m)
  {
	  MyDialog dialog=new MyDialog(this,m,true);
	  dialog.setSize(300,100);
	  dialog.setVisible(true);  
  }

  @SuppressWarnings("all")
  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      dispose();
      System.exit(0);
    }

  }


  boolean checkLogin(String user, String pwd)
  {
	  boolean flag=false;
	  try
	  {
		  BufferedReader br=new BufferedReader(new FileReader("login"));
		  String str=new String();
		  while((str=br.readLine())!=null)
		  {
			  StringTokenizer token=new StringTokenizer(str,"*");
			  String u=token.nextToken();
			  String p=token.nextToken();
			  if(user.equals(u) && pwd.equals(p))
			  {
				  flag=true;
				  break;
			  }
			  else
			  {
				  flag=false;
				  
			  }
			  
		  }
		  
	  }  catch(FileNotFoundException fnfe)
	     {
		     displayMesg("Cannot open login file");
	     }
		 catch(IOException ioe)
		 {
			 displayMesg("I/O Error");
		 }
		 catch(Exception e)
		 {
			 displayMesg("Error occurred");
		 }
		 
	  
	  return flag;

  }

}

@SuppressWarnings("all")
public class CMSApplication extends JApplet implements ActionListener
{
	private JButton cmd_Login;
	private JLabel Lbl1, Lbl2;
	public void init()
	{
		Lbl1=new JLabel("Welcome to Contact Management System!", JLabel.CENTER);
		cmd_Login=new JButton("Login");
		Font f=new Font("Arial",Font.PLAIN,18);
		getContentPane().setLayout(new BorderLayout());
		JPanel p1=new JPanel();
		p1.setLayout(new FlowLayout());
		Lbl1.setFont(f);
		p1.add(Lbl1);
		JPanel p2=new JPanel();
		p2.setLayout(new FlowLayout());
		p2.add(cmd_Login);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(p1,BorderLayout.NORTH);
		getContentPane().add(p2,BorderLayout.CENTER);

		cmd_Login.addActionListener(this);

		/*LoginForm loginform=new LoginForm("Login Form", this);
		loginform.setSize(300,200);
		loginform.setResizable(true);
		loginform.setVisible(true);*/
	}
	public void destroy()
	{
	}
	public void actionPerformed(ActionEvent ae)
  	{
	    if(ae.getSource()==cmd_Login)
	    {
		LoginForm loginform=new LoginForm("Login Form", this);
		loginform.setSize(300,200);
		loginform.setResizable(true);
		loginform.setVisible(true);
	
	    }
	}
}