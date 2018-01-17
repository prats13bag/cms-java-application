import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.File;

@SuppressWarnings("all")
public class UpdDelAnnivForm extends JFrame implements ActionListener, ItemListener
{
	private JTextField txt_Entryid,txt_Name,txt_Year;
	private JComboBox cho_Day,cho_Month,cho_Contact;
  	private JButton cmd_Update,cmd_Delete,cmd_Cancel;
  	Vector v=new Vector();
  	Vector v2=new Vector();
  	Vector c=new Vector();
  
  	private static String [] Months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

	private static String[] Days={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	int confirm=0;
  	public UpdDelAnnivForm(String fname)
  	{
    	super(fname);
    	txt_Entryid=new JTextField(5);
    	txt_Name=new JTextField(15);
    	txt_Year=new JTextField(5);
    	cmd_Update=new JButton("Update");
    	cmd_Delete=new JButton("Delete");
    	cmd_Cancel=new JButton("Back");

    	for(int i=0;i<12;i++)
    	{
			v.addElement(Months[i]);
		}
		
		//Add values in month combobox.
    	cho_Month=new JComboBox(v);

    	for(int i=0;i<31;i++)
    	{
			v2.addElement(Days[i]);
		}
		
		//Add values in day combobox.
		cho_Day=new JComboBox(v2);
    	
		//Gets the Entryid+Name entries in vector c.
		c=getList();

		//Initialize cho_Contact combobox with vector c.
		cho_Contact=new JComboBox(c);
	        		
	        //If vector c has atleast 1 entry.
		if(c.size()>0)
		{
			cho_Contact.setSelectedIndex(0);
			try
			{
				BufferedReader br=new BufferedReader(new FileReader("Annivers"));
				String str=new String();
				str=br.readLine();
				StringTokenizer token=new StringTokenizer(str,"*");
				txt_Entryid.setText(token.nextToken());
				txt_Name.setText(token.nextToken());
				cho_Day.setSelectedItem(token.nextToken());
				cho_Month.setSelectedItem(token.nextToken());
				txt_Year.setText(token.nextToken());
				br.close();
				
			}  catch(FileNotFoundException fnfe)
			   {
				   displayMesg("Annivers file not found");
				   
			   }
			   catch(IOException ioe)
			   {
				   displayMesg("I/O error");
				   
			   }
		}
		else
		{
			
			cho_Contact.setEnabled(true);
			cmd_Update.setEnabled(false);
			cmd_Delete.setEnabled(false);
			cho_Day.setEnabled(false);
			cho_Month.setEnabled(false);
			txt_Entryid.setEnabled(false);
			txt_Name.setEnabled(false);
			txt_Year.setEnabled(false);
			displayMesg("There is No record found");
		}
		
		JPanel mainpanel=new JPanel();
		JPanel panel1=new JPanel();
		JPanel panel2=new JPanel();
		JPanel panel3=new JPanel();
		JPanel panel4=new JPanel();
		JPanel panel5=new JPanel();
		JPanel panel6=new JPanel();
		
		getContentPane().setLayout(new BorderLayout());
		
		panel1.setLayout(new BorderLayout());
		panel2.setLayout(new FlowLayout());
		panel2.add(new JLabel("B. R. Enterprises"));
		panel3.setLayout(new FlowLayout());
		panel3.add(new JLabel("Update Delete Anniversary Form"));
		panel1.add(panel2,BorderLayout.NORTH);
		panel1.add(panel3,BorderLayout.CENTER);
		panel4.setLayout(new GridLayout(3,2));
		panel4.add(new JLabel("Select Contact"));
		panel4.add(cho_Contact);
		panel4.add(new JLabel("Entry ID : "));
		panel4.add(txt_Entryid);
		panel4.add(new JLabel("Name : "));
		panel4.add(txt_Name);
		panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel5.add(new JLabel("Select Date of Anniversary : "));
		panel5.add(new JLabel("Day "));
		panel5.add(cho_Day);
		panel5.add(new JLabel("Month "));
		panel5.add(cho_Month);
		panel5.add(new JLabel("Year "));
		panel5.add(txt_Year);
		panel6.setLayout(new FlowLayout());
		panel6.add(cmd_Update);
		panel6.add(cmd_Delete);
		panel6.add(cmd_Cancel);
		mainpanel.setLayout(new GridLayout(3,1,5,5));
		mainpanel.add(panel1);
		mainpanel.add(panel4);
		mainpanel.add(panel5);
		getContentPane().add(mainpanel,BorderLayout.CENTER);
		getContentPane().add(panel6,BorderLayout.SOUTH);
		pack();
		txt_Entryid.setEnabled(false);
		txt_Name.setEnabled(false);

    		cmd_Update.addActionListener(this);
	    	cmd_Delete.addActionListener(this);
	    	cmd_Cancel.addActionListener(this);
	    	cho_Contact.addItemListener(this);
	    	addWindowListener(new MyWindowAdapter());
		

	}

  	public void actionPerformed(ActionEvent ae)
  	{
	  	//If user clicks on Update button.
    	if(ae.getSource()==cmd_Update)
    	{
			try
			{
	    		BufferedReader br=new BufferedReader(new FileReader("Annivers"));
	    		BufferedWriter bw=new BufferedWriter(new FileWriter("tempfile"));
	    		String str=new String();
	    		while((str=br.readLine())!=null)
	    		{
		    		StringTokenizer token=new StringTokenizer(str,"*");
		    		String eid=txt_Entryid.getText();
		    		String name=txt_Name.getText();
		    		String e=token.nextToken();
		    		String n=token.nextToken();

		    		//If file entry matches with the cho_Contact selected entry then
		    		//write record to new record to the tempfile.
		    		if(eid.equals(e) && name.equals(n))
		    		{
			    		String s=txt_Entryid.getText().trim()+"*"+txt_Name.getText().trim()+"*"+(String)cho_Day.getSelectedItem()+"*"+(String)cho_Month.getSelectedItem()+"*"+txt_Year.getText().trim();
		    			bw.write(s+"\n");
		    		}
		    		//If file entry does not matches with the cho_Contact selected entry then
		    		//write the record read from the Annivers file.
		    		else
		    		{
			    		bw.write(str+"\n");
		    		}
	    		}
	    		br.close();
	    		bw.close();
				if(checkValues()==true)
				{
    				updateFile();
				}
    		    
    		}  catch(FileNotFoundException fnfe)
    		   {
	    		   displayMesg("Annivers file not found");
	    		   dispose();
	    		   return;
	    		   
	    	   }
	    	   catch(IOException ioe)
	    	   {

		    	   displayMesg("I/O error");
		    	   return;
		    	   
	    	   }

	    }
	    
	    //If user clicks on Delete button.
    	else if(ae.getSource()==cmd_Delete)
    	{

			try
			{
	    		BufferedReader br=new BufferedReader(new FileReader("Annivers"));
	    		BufferedWriter bw=new BufferedWriter(new FileWriter("tempfile"));
	    		String str=null;
	    		while((str=br.readLine())!=null)
	    		{
		    		
		    		StringTokenizer token=new StringTokenizer(str,"*");
		    		String eid=txt_Entryid.getText();
		    		String name=txt_Name.getText();
		    		String e=token.nextToken();
		    		String n=token.nextToken();

		    		//If file entry does not matches with the cho_Contact selected entry then
		    		//write record to new record to the tempfile.
		    		if((eid.equals(e) && name.equals(n))==false)
		    		{
			    		bw.write(str+"\n");
		    		}
	    		}
	    		br.close();
	    		bw.close();
				confirm=JOptionPane.showConfirmDialog(this,"Do You Want To delete This Record","Delete Confirm Dialog",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    		    if (confirm==0)
    		    {
					updateFile();
		    		cho_Contact.removeItem(cho_Contact.getSelectedItem());//Remove the deleted item from cho_Contact combobox.
					displayMesg("Record deleted");
				}    		
				else if(confirm ==1) 
				{
					displayMesg("Record Is Not deleted");
				}
   		    	
    		}  catch(FileNotFoundException fnfe)
    		   {

	    		   displayMesg("Annivers file not found");
	    	   }
	    	   catch(IOException ioe)
	    	   {

		    	   displayMesg("I/O error");
	    	   }
    	
	    	//Remove the deleted item from cho_Contact combobox.
    		//cho_Contact.removeItem(cho_Contact.getSelectedItem());//aalok

	    }
	    
	    //If user clicks on Back button.
	    else if(ae.getSource()==cmd_Cancel)
	    {
		
		    UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
      	    udform.setSize(200,300);
      		udform.setResizable(false);
      		udform.setVisible(true);
			dispose();		    		    
	    }

  	}

  	public void updateFile()
  	{
    		File file2=new File("tempfile");
		    File file=new File("Annivers");
		    file.delete();
		    file2.renameTo(file);
	  	  	
  	}
  	
  	//Obtain the record of item selected from the cho_Contact combobox.
  	public void itemStateChanged(ItemEvent ie)
  	{
    	if(ie.getSource()==cho_Contact)
    	{
	    	String item=(String)cho_Contact.getSelectedItem();
      		
		try
			{
				BufferedReader br=new BufferedReader(new FileReader("Annivers"));
				String str=new String();
				
				while((str=br.readLine())!=null)
				{
					StringTokenizer token=new StringTokenizer(str,"*");
					
					String eid=token.nextToken();
					String name=token.nextToken();

					if(item.equals(name))
					{
						String dd=token.nextToken();
						String mm=token.nextToken();
						String yy=token.nextToken();
						txt_Entryid.setText(eid);
						txt_Name.setText(name);
						txt_Year.setText(yy);
						cho_Day.setSelectedItem(dd);
						cho_Month.setSelectedItem(mm);
						repaint();						
						break;
					}
				
				
				}
				br.close();	

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
	//////////////////////////////
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
	/////////////////////////////
	  else
	  {
		  displayMesg("Record Saved");
		  return true;
	  }

  }
  	//Returns the vector of Entryid+name values.
  	public Vector getList()
  	{
    	Vector n=new Vector();    
    	
    	String str;
    	try
    	{
      		BufferedReader br=new BufferedReader(new FileReader("Annivers"));
	
    		while((str=br.readLine())!=null)
      		{
				StringTokenizer token=new StringTokenizer(str,"*");
				String s=token.nextToken();
				String s2=token.nextToken();
				n.addElement(s2);
      		}
			br.close();
			
    	}  catch(FileNotFoundException fnfe)
    	   {
	    	   displayMesg("Annivers file not found");

	    	   
	       }
	       catch(IOException ioe)
	       {

		       displayMesg("I/O error");
		       
	       }
    	return n;
  	}

  	//Inner class to implement windowClosing() method.
	class MyWindowAdapter extends WindowAdapter
  	{
    	public void windowClosing(WindowEvent we)
    	{
		    
		    UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
      	    udform.setSize(200,300);
      		udform.setResizable(false);
      		udform.setVisible(true);
      		dispose();
    	}

  	}
  	
  public void displayMesg(String m)
  {
	  MyDialog dialog=new MyDialog(this,m,true);
	  dialog.setLocation(this.getX(),this.getY());
	  dialog.setSize(300,100);
	  dialog.setVisible(true);  
	  
  }

}