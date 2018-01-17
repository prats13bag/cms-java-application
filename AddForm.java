import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("all")
public class AddForm extends JFrame implements ActionListener
{
	private JButton  cmd_Reminder, cmd_Birthday, cmd_Anniversary, cmd_Address, cmd_Phone, cmd_Email, cmd_Back;			
  
	public AddForm(String fname)
  	{
		super(fname);
		getContentPane().setLayout(new BorderLayout());
    	JPanel panel4=new JPanel();
    	JPanel panel5=new JPanel();
    	JPanel panel6=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panelA=new JPanel();		
		JPanel panelB=new JPanel();
		JPanel panel1=new JPanel();
		JPanel MainPanel=new JPanel(new BorderLayout());
		
		panel1.setLayout(new GridLayout(3,2,2,2));
		/*panel4.setLayout(new BorderLayout());
		panel5.setLayout(new BorderLayout());
		panel6.setLayout(new BorderLayout());*/
		panel2.setLayout(new BorderLayout());
		panelA.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelB.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		cmd_Address=new JButton("Add Address");    
		cmd_Phone=new JButton("Add Phone");
		cmd_Email=new JButton("Add Email Address");
		cmd_Birthday=new JButton("Add Birthday");
		cmd_Anniversary=new JButton("Add Anniversary");
		cmd_Reminder=new JButton("Add Reminder");
		cmd_Back=new JButton("Back");
    
				
		panelA.add(new JLabel("B. R. Enterprises"));
		panelB.add(new JLabel("Menu For Adding Corresponding Details"));
		panel2.add(panelA,BorderLayout.NORTH);
		panel2.add(panelB,BorderLayout.SOUTH);
		
		panel1.add(cmd_Address);
		panel1.add(cmd_Phone);
		panel1.add(cmd_Email);
		panel1.add(cmd_Birthday);
		panel1.add(cmd_Anniversary);
		panel1.add(cmd_Reminder);		
		
		MainPanel.add(panel1,BorderLayout.NORTH);
		MainPanel.add(cmd_Back,BorderLayout.SOUTH);
		
		getContentPane().add(panel2,BorderLayout.NORTH);
		getContentPane().add(MainPanel,BorderLayout.CENTER);

		cmd_Address.addActionListener(this);
		cmd_Phone.addActionListener(this);
		cmd_Email.addActionListener(this);
		cmd_Birthday.addActionListener(this);
		cmd_Anniversary.addActionListener(this);
		cmd_Reminder.addActionListener(this);
		cmd_Back.addActionListener(this);
		addWindowListener(new MyWindowAdapter());
	}

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cmd_Address)
    {
        AddAddress addform=new AddAddress("Address Entry Form");
        addform.setSize(300,400);
        addform.setResizable(false);
        addform.setVisible(true);
		setVisible(false);
    }
    else if(ae.getSource()==cmd_Phone)
    {
        AddPhone phform=new AddPhone("Phone Entry Form");
        phform.setSize(300,300);
        phform.setResizable(false);
        phform.setVisible(true);
	    setVisible(false);

    }
    else if(ae.getSource()==cmd_Email)
    {
        AddEmail mailform=new AddEmail("Email Entry Form");
        mailform.setSize(300,300);
        mailform.setResizable(false);
        mailform.setVisible(true);
		setVisible(false);
    }
    else if(ae.getSource()==cmd_Birthday)
    {
        AddBirthdayForm abdform=new AddBirthdayForm("Birthday Entry Form");
        abdform.setLocation(this.getX(),this.getY());
        abdform.setSize(650,350);
        abdform.setResizable(false);
        abdform.setVisible(true);
        setVisible(false);
    }
    else if(ae.getSource()==cmd_Anniversary)
    {
	    AddAnnivForm annform=new AddAnnivForm("Anniversary Entry Form");
	    annform.setSize(650,350);
	    annform.setResizable(false);
	    annform.setVisible(true);
	    setVisible(false);
    }
    else if(ae.getSource()==cmd_Reminder)
    {
	    AddRemindForm remform=new AddRemindForm("Reminder Entry Form");
	    remform.setSize(650,350);
	    remform.setResizable(false);
	    remform.setVisible(true);
	    setVisible(false);
    }
    else if(ae.getSource()==cmd_Back)
    {
	    setVisible(false);
	    MainForm mform=new MainForm("Main Form");
	    mform.setSize(300,300);
        mform.setResizable(false);
        mform.setVisible(true);
        dispose();
    }

  }

  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
		setVisible(false);
	    MainForm mform=new MainForm("Main Form");
	    mform.setSize(300,300);
        mform.setResizable(false);
        mform.setVisible(true);      
	    dispose();
    }

  }

}






