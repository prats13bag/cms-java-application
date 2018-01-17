import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
  
  @SuppressWarnings("all")
  class MyDialog extends JDialog implements ActionListener
  {
	  JLabel lbl_mesg;
	  JButton cmd_OK;
	  public MyDialog(JFrame frame,String mesg,boolean modal)
	  {
		  super(frame,mesg,modal);
		  lbl_mesg=new JLabel(mesg);
		  cmd_OK=new JButton("OK"); 
		  JPanel panel1=new JPanel();
		  panel1.setLayout(new FlowLayout());
		  panel1.add(lbl_mesg);
		  JPanel panel2=new JPanel();
		  panel2.setLayout(new FlowLayout());
		  panel2.add(cmd_OK);
		  getContentPane().setLayout(new BorderLayout()); 
		  getContentPane().add(panel1,BorderLayout.CENTER);
		  getContentPane().add(panel2,BorderLayout.SOUTH);
		  cmd_OK.addActionListener(this);
	  }
	  public void actionPerformed(ActionEvent ae)
	  {
		  dispose();
		   
	  }
	  
  }
