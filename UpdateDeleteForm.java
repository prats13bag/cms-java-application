import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("all")
public class UpdateDeleteForm extends JFrame implements ActionListener
{
  private JButton cmd_UpdDelAdd,cmd_UpdDelPh,cmd_UpdDelEmail,cmd_UpdDelBth,cmd_UpdDelAnn,cmd_UpdDelRem,cmd_UpdDelBack;

  public UpdateDeleteForm(String fname)
  {
    super(fname);
    cmd_UpdDelAdd=new JButton("Update/Delete Address");
    cmd_UpdDelPh=new JButton("Update/Delete Phone No.");
    cmd_UpdDelEmail=new JButton("Update/Delete Email");
    cmd_UpdDelBth=new JButton("Update/Delete Birthday");
    cmd_UpdDelAnn=new JButton("Update/Delete Anniversary");
    cmd_UpdDelRem=new JButton("Update/Delete Reminder");
    cmd_UpdDelBack=new JButton("Back");

    JPanel panel1=new JPanel();
    JPanel panelA=new JPanel();		
	JPanel panelB=new JPanel();
	JPanel panel2=new JPanel();
	panel2.setLayout(new BorderLayout());
    panel1.setLayout(new GridLayout(7,1,10,5));
    panel1.add(cmd_UpdDelAdd);
    panel1.add(cmd_UpdDelPh);
    panel1.add(cmd_UpdDelEmail);
    panel1.add(cmd_UpdDelBth);
    panel1.add(cmd_UpdDelAnn);
    panel1.add(cmd_UpdDelRem);
    panel1.add(cmd_UpdDelBack);

    panelA.setLayout(new FlowLayout(FlowLayout.CENTER));
	panelB.setLayout(new FlowLayout(FlowLayout.CENTER));
	panelA.add(new JLabel("B. R. Enterprises"));
	panelB.add(new JLabel("Menu For Updating/Deleting Corresponding Details"));
	panel2.add(panelA,BorderLayout.NORTH);
	panel2.add(panelB,BorderLayout.SOUTH);
		
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(panel2,BorderLayout.NORTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
    cmd_UpdDelAdd.addActionListener(this);
    cmd_UpdDelPh.addActionListener(this);
    cmd_UpdDelEmail.addActionListener(this);
    cmd_UpdDelBth.addActionListener(this);
    cmd_UpdDelAnn.addActionListener(this);
    cmd_UpdDelRem.addActionListener(this);
    cmd_UpdDelBack.addActionListener(this);
    addWindowListener(new MyWindowAdapter());


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

  public void actionPerformed(ActionEvent ae)
  {
	if(ae.getSource()==cmd_UpdDelAdd)
	{
		
		UpdationForm addform=new UpdationForm("Address Update/Delete Form");
        addform.setSize(300,400);
        addform.setResizable(false);
        addform.setVisible(true);
		setVisible(false);
	}
	else if(ae.getSource()==cmd_UpdDelPh)
	{
		
		UpdatePhone phform=new UpdatePhone("Phone No. Update/Delete Form");
        phform.setSize(300,400);
        phform.setResizable(false);
        phform.setVisible(true);		
		setVisible(false);
		
	}
	else if(ae.getSource()==cmd_UpdDelEmail)
	{
		setVisible(false);
		UpdateEmail mailform=new UpdateEmail("Email Update/Delete Form");
        mailform.setSize(300,400);
        mailform.setResizable(false);
        mailform.setVisible(true);		
		
	}
    else if(ae.getSource()==cmd_UpdDelBth)
    {
	    
	    
	    UpdDelBirthdayForm udbform=new UpdDelBirthdayForm("Update Delete Birthday Form");
	    udbform.setSize(450,300);
	    udbform.setResizable(false);
	    udbform.setVisible(true);
		setVisible(false);
    }
    else if(ae.getSource()==cmd_UpdDelAnn)
    {
	    setVisible(false);
	    UpdDelAnnivForm udaform=new UpdDelAnnivForm("Update Delete Anniversary Form");
	    udaform.setSize(450,300);
	    udaform.setResizable(false);
	    udaform.setVisible(true);	    
	    
    }
    else if(ae.getSource()==cmd_UpdDelRem)
    {
	  
	    UpdDelRemForm udrform=new UpdDelRemForm("Update Delete Reminder Form");
	    udrform.setSize(450,300);
	    udrform.setResizable(false);
	    udrform.setVisible(true);
	    setVisible(false);
    }
    else if(ae.getSource()==cmd_UpdDelBack)
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


