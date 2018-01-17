import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class AddAnnivForm extends JFrame implements ActionListener
{
  private static String [] Months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

  private static String[] Days={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};


  private JComboBox cho_Period,cho_Month,cho_Day;
  private JTextField txt_Entryid,txt_Name,txt_Year;
  private JButton cmd_Add,cmd_Cancel,cmd_New;

  public AddAnnivForm(String fname)
  {
    super(fname);
    cho_Period=new JComboBox();
    txt_Entryid=new JTextField(5);
    txt_Name=new JTextField(10);
    txt_Year=new JTextField(5);
    txt_Entryid.setEnabled(false);

    cmd_Add=new JButton("Save");
    cmd_Cancel=new JButton("Back");
    cmd_New=new JButton("New");
    Vector v=new Vector();
    Vector v2=new Vector();
    for(int i=0;i<31;i++)
    {
        v.addElement(Days[i]);
    }
    
	//Add values in day combobox.
    cho_Day=new JComboBox(v);    

    for(int i=0;i<12;i++)
    {
        v2.addElement(Months[i]);
    }

    //Add values in month combobox.
    cho_Month=new JComboBox(v2);

    JPanel panel=new JPanel();
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JPanel panel4=new JPanel();
    JPanel panel5=new JPanel();
    JPanel panel6=new JPanel();
    JPanel tpanel=new JPanel();
    
    panel1.setLayout(new FlowLayout());
    panel.setLayout(new FlowLayout());
    panel.add(new JLabel("B. R. Enterprises"));
    panel1.add(new JLabel("Anniversary Entry Form"));
    tpanel.setLayout(new BorderLayout());
    tpanel.add(panel,BorderLayout.NORTH);
    tpanel.add(panel1,BorderLayout.SOUTH);
    
    
    panel2.setLayout(new GridLayout(2,2,0,5));
    panel2.add(new JLabel("Entry ID : "));
    panel2.add(txt_Entryid);
    panel2.add(new JLabel("Name : "));
    panel2.add(txt_Name);
    
    panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel3.add(new JLabel("Enter Date of Ann. : "));
    panel3.add(new JLabel("Day"));
    panel3.add(cho_Day);
    panel3.add(new JLabel("Month"));
    panel3.add(cho_Month);
    panel3.add(new JLabel("Year"));
    panel3.add(txt_Year);
    
    panel4.setLayout(new FlowLayout());
    panel4.add(cmd_New);
    panel4.add(cmd_Add);
    panel4.add(cmd_Cancel);
    
    JPanel mpanel=new JPanel();
    mpanel.setLayout(new GridLayout(4,1,15,5));
    mpanel.add(tpanel);
    mpanel.add(panel2);
    mpanel.add(panel3);
    mpanel.add(panel4);
    
    getContentPane().setLayout(new FlowLayout());
    getContentPane().add(mpanel);

    txt_Entryid.setEnabled(false);
    cmd_New.setEnabled(false);

    cmd_Add.addActionListener(this);
    cmd_Cancel.addActionListener(this);
    cmd_New.addActionListener(this);
    addWindowListener(new MyWindowAdapter());

  }

  public void actionPerformed(ActionEvent ae)
  {
	  //If user clicks on Add button.
	  if(ae.getSource()==cmd_Add)
	  {
		  //Check all values entered.
		  if(checkValues()==true)
		  {
			  //Get unique entry id for new record.
			  int eid=getCount();
			  txt_Entryid.setText(" "+eid);
			  try
			  {
				  //Open Annivers file to append a new entry.
				  BufferedWriter br=new BufferedWriter(new FileWriter("Annivers",true));
				  String str=new String();
				  str=txt_Entryid.getText().trim()+"*"+txt_Name.getText().trim()+"*"+(String)cho_Day.getSelectedItem()+"*"+(String)cho_Month.getSelectedItem()+"*"+txt_Year.getText().trim()+"\n";
				  br.write(str);
				  br.close();
				  cmd_Add.setEnabled(false);
				  cmd_New.setEnabled(true);
				  
			  }  catch(FileNotFoundException fnfe)
			     {
				     displayMesg("Annivers file not found");
			     }
			     catch(IOException ioe)
			     {

				     displayMesg("I/O error");
			     }
	      }

	  }
	  
	  //If user clicks on New button.
	  else if(ae.getSource()==cmd_New)
	  {
		  txt_Entryid.setText("");
		  txt_Name.setText("");
		  txt_Year.setText("");
		  cho_Day.setSelectedIndex(0);
		  cho_Month.setSelectedIndex(0);
		  cmd_New.setEnabled(false);
		  cmd_Add.setEnabled(true);
	  }
	  
  	  //If user clicks on Back button.
      else if(ae.getSource()==cmd_Cancel)
      {

	      AddForm addform=new AddForm("Add Contact Form");
      	  addform.setSize(300,250);
      	  addform.setResizable(false);
      	  addform.setVisible(true);
		  dispose();	      
      }
  }

  //Checks if all the textfields are provided with values.
  public boolean checkValues()
  {
	  	  
	  if((txt_Name.getText().trim().length()==0) || (txt_Year.getText().trim().length()==0))
	  {
		  displayMesg("Please Fill Each Entry");
		  return false;
	  }
	  if (txt_Year.getText().length()>4 || txt_Year.getText().length()<4)
	  {
		  displayMesg("Year Size Should Be 4");
		  return false;
	  }
	  if((txt_Year.getText().trim().length()!=0))               
	  {
		try
	  	{
	  		Long.parseLong(txt_Year.getText().trim());
			displayMesg("Record Saved");
			return true;
	  	}	  
	  	catch (Exception e)
	  	{
			displayMesg("Check Year Entry");
			return false;
	  	}  
		
	  }
	  else
	  {
		  displayMesg("Record Saved");
		  return true;
	  }


  }
  
  //Inner class to implement windowClosing() method.
  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
   	      
          AddForm addform=new AddForm("Add Contact Form");
          addform.setSize(250,250);
          addform.setResizable(false);
          addform.setVisible(true);
          dispose();
    }

  }
  
  //getCount() method returns a new Entryid for the new record.
  int getCount()
  { 
	int count=0;
    try
    {
      BufferedReader br=new BufferedReader(new FileReader("Annivers"));
      
      String str;
      //Traverse to the last record in the file and reads the Entryid of the last record into
      //the variable count and assigns new Entryid by incrementing count by 1.
      while((str=br.readLine())!=null)
      {
	      StringTokenizer token=new StringTokenizer(str,"*");
	      String eid=token.nextToken();
	      count=Integer.parseInt(eid);
      }
	  count++;
      br.close();	      

    }  catch(FileNotFoundException fnfe)
       {
	 	   count++;
       }
       catch(IOException ioe)
       {
	       displayMesg("I/O error");
       }
    return count;
  }
  
  public void displayMesg(String m)
  {
	  MyDialog dialog=new MyDialog(this,m,true);
	  dialog.setLocation(this.getX(),this.getY());
	  dialog.setSize(300,100);
	  dialog.setVisible(true);  
	  
  }

}