import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("all")
public class PersonDetailForm extends JFrame implements ActionListener
{

  private JTextField txt_Name,txt_City,txt_State, txt_Country,txt_Pincode;
  private JComboBox cho_Entryid;
  private JTextArea txt_Address;
  private JButton cmd_Addnew,cmd_Update,cmd_Delete,cmd_Close;
  private boolean operation;
  public PersonDetailForm(String fname)
  {
    super(fname);
    operation=false;
    cho_Entryid=new JComboBox();
    txt_Name=new JTextField(25);
    txt_Address=new JTextArea(5,25);
    txt_City=new JTextField(15);
    txt_State=new JTextField(15);
    txt_Country=new JTextField(15);
    txt_Pincode=new JTextField(10);
    //cmd_Addnew=new JButton("Add New");
    cmd_Update=new JButton("Update");
    cmd_Delete=new JButton("Delete");
    cmd_Close=new JButton("Close");
    
	//getEntryID();
    getContentPane().setLayout(new BorderLayout());
    JPanel panel1=new JPanel();
    panel1.setLayout(new GridLayout(4,4,10,5));
    panel1.add(new JLabel("Entry id : "));
    panel1.add(cho_Entryid);
    panel1.add(new JLabel("Person Name : "));
    panel1.add(txt_Name);
    panel1.add(new JLabel("Address : "));
    panel1.add(txt_Address);
    panel1.add(new JLabel("City : "));
    panel1.add(txt_City);
    panel1.add(new JLabel("State : "));
    panel1.add(txt_State);
    panel1.add(new JLabel("Country : "));
    panel1.add(txt_Country);
    panel1.add(new JLabel("Pin Code : "));
    panel1.add(txt_Pincode);


    JPanel panel2=new JPanel();
    panel2.setLayout(new FlowLayout());
    //panel2.add(cmd_Addnew);
    panel2.add(cmd_Update);
    panel2.add(cmd_Delete);
    panel2.add(cmd_Close);

    getContentPane().add(new JLabel("Personal Details"),BorderLayout.NORTH);
    getContentPane().add(panel1,BorderLayout.CENTER);
    getContentPane().add(panel2,BorderLayout.SOUTH);
    addWindowListener(new MyWindowAdapter());
    //cmd_Addnew.addActionListener(this);
    cmd_Update.addActionListener(this);
    cmd_Delete.addActionListener(this);
    cmd_Close.addActionListener(this);

  }

/*  public void getEntryID()
  {
    try
    {

      FileInputStream fis=new FileInputStream("address.dat");
      ObjectInputStream ois=new ObjectInputStream(fis);

    }  catch(){}



  }*/
  

  public void actionPerformed(ActionEvent ae)
  {
    if(ae.getSource()==cmd_Update)
    { 
/*      txt_Entryid.setText(getNewID());
      txt_Name.setText("");
      txt_Address.setText("");
      txt_City.setText("");
      txt_State.setText("");
      txt_Country.setText("");
      txt_Pincode.setText("");*/

      

    }
    else if(ae.getSource()==cmd_Delete)
    {



    }
    else if(ae.getSource()==cmd_Close)
    {



    }

  }

/*  String getNewID()
  {
    try
    {
      //FileInputStream fis=new FileInputStream("Address.dat");

    }

  }*/

  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      dispose();
    }

  }


}