import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class SearchForm extends JFrame implements ActionListener
{

  private JButton cmd_SearchBday,cmd_SearchPno,cmd_SearchEmail,cmd_SearchAdd,cmd_SearchAnn,cmd_SearchRem,cmd_Back;

  public SearchForm(String fname)
  {
    super(fname);
    cmd_SearchBday=new JButton("Search Birthday");
    cmd_SearchPno=new JButton("Search Phone No.");
    cmd_SearchEmail=new JButton("Search Email");
    cmd_SearchAdd=new JButton("Search Address");
    cmd_SearchAnn=new JButton("Search Anniversary");
    cmd_SearchRem=new JButton("Search Reminder");
    cmd_Back=new JButton("Back");

    getContentPane().setLayout(new BorderLayout());
    JPanel panel1=new JPanel();
    JPanel panelA=new JPanel();		
	JPanel panelB=new JPanel();
	JPanel panel2=new JPanel();
	panel2.setLayout(new BorderLayout());
    panel1.setLayout(new GridLayout(7,1,10,5));
    panel1.add(cmd_SearchAdd);
    panel1.add(cmd_SearchEmail);
    panel1.add(cmd_SearchPno);
    panel1.add(cmd_SearchBday);
    panel1.add(cmd_SearchAnn);
    panel1.add(cmd_SearchRem);
    panel1.add(cmd_Back);
    
    panelA.setLayout(new FlowLayout(FlowLayout.CENTER));
	panelB.setLayout(new FlowLayout(FlowLayout.CENTER));
	panelA.add(new JLabel("B. R. Enterprises"));
	panelB.add(new JLabel("Menu For Searching Corresponding Details"));
	panel2.add(panelA,BorderLayout.NORTH);
	panel2.add(panelB,BorderLayout.CENTER);

    cmd_SearchAdd.addActionListener(this);
    cmd_SearchEmail.addActionListener(this);
    cmd_SearchPno.addActionListener(this);
    cmd_SearchBday.addActionListener(this);
    cmd_SearchAnn.addActionListener(this);
    cmd_SearchRem.addActionListener(this);
    cmd_Back.addActionListener(this);

    
    getContentPane().add(panel2,BorderLayout.NORTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
    addWindowListener(new MyWindowAdapter());

  }


  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cmd_SearchAdd)
    {
        SearchAddr addform=new SearchAddr("Search Address Form");
        addform.setSize(300,300);
        addform.setResizable(false);
        addform.setVisible(true);
        setVisible(false);

    }
    else if(ae.getSource()==cmd_SearchEmail)
    {
        SearchEmail mailform=new SearchEmail("Search Email Form");
        mailform.setLocation(this.getX(),this.getY());
        mailform.setSize(300,300);
        mailform.setResizable(false);
        mailform.setVisible(true);
		setVisible(false);

    }
    else if(ae.getSource()==cmd_SearchPno)
    {
        SearchPhone phform=new SearchPhone("Search Phone No. Form");
        phform.setLocation(this.getX(),this.getY());
        phform.setSize(300,300);
        phform.setResizable(false);
        phform.setVisible(true);
        setVisible(false);


    }
    else if(ae.getSource()==cmd_SearchBday)
    {
        SearchBirthdayForm sbform=new SearchBirthdayForm("Search Birthday Form");
        sbform.setLocation(this.getX(),this.getY());
        sbform.setSize(300,350);
        sbform.setResizable(false);
        sbform.setVisible(true);
        setVisible(false);
    }
    else if(ae.getSource()==cmd_SearchAnn)
    {
	    SearchAnnivForm saform=new SearchAnnivForm("Search Anniversary Form");
	    saform.setLocation(this.getX(),this.getY());
        saform.setSize(300,350);
        saform.setResizable(false);
        saform.setVisible(true);
        setVisible(false);
    }
    else if(ae.getSource()==cmd_SearchRem)
    {
	    SearchRemindForm srform=new SearchRemindForm("Search Reminder Form");
	    srform.setLocation(this.getX(),this.getY());
        srform.setSize(300,350);
        srform.setResizable(true);
        srform.setVisible(true);
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