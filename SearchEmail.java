import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class SearchEmail extends JFrame implements ActionListener
{
	JTextField NameField;
	private JPanel panel1,panel2,panel3,panel4; 
	JButton cmd_search,cmd_back;
	JTable table;
	Object data[][];
	final String[] colHeads = {"Entry ID" , "Name" , "Email ID"};
	String str;
	JPanel panel5;
	public SearchEmail(String fname)
	{
		super(fname);
		getContentPane().setLayout(new BorderLayout());
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
	
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));	
		panel5.setLayout(new FlowLayout(FlowLayout.CENTER));	
		panel4.setLayout(new BorderLayout());

		NameField =new JTextField(15);
	
			
		panel1.add(new JLabel("B. R. Enterprises"));
		panel2.add(new JLabel("Email Details Form"));
		panel3.add(new JLabel("Name:"));
		panel3.add(NameField);
		cmd_search=new JButton("Search");
		cmd_search.addActionListener(this);
		cmd_back=new JButton("Back");
		cmd_back.addActionListener(this);
	
		panel5.add(cmd_search);   
		panel5.add(cmd_back); 
		panel4.add(panel1,BorderLayout.NORTH);
		panel4.add(panel2,BorderLayout.CENTER);
		panel4.add(panel3,BorderLayout.SOUTH);
		getContentPane().add(panel4,BorderLayout.NORTH);
		getContentPane().add(panel5,BorderLayout.SOUTH);
		addWindowListener(new MyWindowAdapter());
	}

	
  	 
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == cmd_search)
		{	
			
			str = NameField.getText();
			SearchEmailId SA = new SearchEmailId("Email Search Form",str);
  			SA.setLocation(this.getX(),this.getY());
			SA.setSize(600,300);
			SA.setResizable(false);
 			SA.setVisible(true);
 			setVisible(false);
		}

		if(ae.getSource() == cmd_back)
		{
			setVisible(false);
			SearchForm sform=new SearchForm("Search Contact Form");
      			sform.setSize(300,300);
	      		sform.setResizable(false);
      			sform.setVisible(true);
	  		dispose();
		}

	}

/*public static void main(String args[])
	{
        SearchAddr AF = new SearchAddr();
  		AF.setSize(300,300);
 		AF.setVisible(true);
	}
*/

class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      	setVisible(false);
			SearchForm sform=new SearchForm("Search Contact Form");
      		sform.setSize(300,300);
      		sform.setResizable(false);
      		sform.setVisible(true);
	    dispose();
    }

  }
}
