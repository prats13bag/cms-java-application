import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class UpdationForm extends JFrame implements ItemListener, ActionListener
{
private JButton cmd_update,cmd_delete,cmd_back;
private JPanel panel,panel1,panel2,panel3,panel4,Apanel,Apanel1,Apanel2,Apanel3,Apanel4,Apanel5,Apanel6,Apanel7;  
private JTextField Entry_id,Person_Name,address,city,state,country,pincode;
Vector v=new Vector();
JComboBox comboBox;
int confirm=0;

	public UpdationForm(String fname)
  	{
	  	super(fname);
   		getContentPane().setLayout(new BorderLayout());
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel=new JPanel();
		v=getList();
		comboBox = new JComboBox();
		comboBox.addItem("Select name");
		Iterator itr=v.iterator();
			int j=1;
			while(itr.hasNext())
			{
				comboBox.insertItemAt(itr.next(),j);
				j++;
			} 
		
		
		Apanel=new JPanel();
		Apanel1=new JPanel();
		Apanel2=new JPanel();
		Apanel3=new JPanel();
		Apanel4=new JPanel();
		Apanel5=new JPanel();
		Apanel6=new JPanel();
		Apanel7=new JPanel();
		
		Entry_id =new JTextField(4);
		Person_Name =new JTextField(15);
		address =new JTextField(25);
		city =new JTextField(15);
		state =new JTextField(10);
		country=new JTextField(10);
		pincode =new JTextField(10);
	

		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel4.setLayout(new BorderLayout());
		
		Apanel1.setLayout(new FlowLayout(FlowLayout.LEFT,6,1));
		Apanel2.setLayout(new FlowLayout(FlowLayout.LEFT,11,1));
		Apanel3.setLayout(new FlowLayout(FlowLayout.LEFT,4,1));
		Apanel4.setLayout(new FlowLayout(FlowLayout.LEFT,17,1));
		Apanel5.setLayout(new FlowLayout(FlowLayout.LEFT,12,1));
		Apanel6.setLayout(new FlowLayout(FlowLayout.LEFT,5,1));
		Apanel7.setLayout(new FlowLayout(FlowLayout.LEFT,4,1));
		Apanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		cmd_update=new JButton("Update");
		cmd_delete=new JButton("Delete");
		cmd_back=new JButton("Back");
		cmd_update.addActionListener(this);
		cmd_back.addActionListener(this);
		cmd_delete.addActionListener(this); 
		comboBox.addItemListener(this); 
		
		panel1.add(new JLabel("B. R. Enterprises"));
		panel2.add(new JLabel(" Address Updation/Deletion Form"));
		panel3.add(comboBox);  
		panel3.add(cmd_update);   
		panel3.add(cmd_delete);   
		panel.add(cmd_back); 
		panel4.add(panel1,BorderLayout.NORTH);
		panel4.add(panel2,BorderLayout.CENTER);
		panel4.add(panel3,BorderLayout.SOUTH);
		
		Apanel1.add(new JLabel("Entry ID:"));    	
		Apanel1.add(Entry_id);	
		Apanel2.add(new JLabel("Name:"));
		Apanel2.add(Person_Name);
		Apanel3.add(new JLabel("Address:"));
		Apanel3.add(address);
		Apanel4.add(new JLabel("City:"));
		Apanel4.add(city);
		Apanel5.add(new JLabel("State:"));    
		Apanel5.add(state);
		Apanel6.add(new JLabel("Country:"));    
		Apanel6.add(country);
		Apanel7.add(new JLabel("Pincode:"));    
		Apanel7.add(pincode);

		Apanel.add(Apanel1);
		Apanel.add(Apanel2);
		Apanel.add(Apanel3);
		Apanel.add(Apanel4);
		Apanel.add(Apanel5);
		Apanel.add(Apanel6);
		Apanel.add(Apanel7);
		
		
		getContentPane().add(panel4,BorderLayout.NORTH);
		getContentPane().add(Apanel,BorderLayout.CENTER);
		getContentPane().add(panel,BorderLayout.SOUTH);
		addWindowListener(new MyWindowAdapter());
  	}

  	public Vector getList()
  		{
    		Vector c=new Vector();    
    		String str;
    		try
    		{
      			BufferedReader br=new BufferedReader(new FileReader("AddressFile"));
				while((str=br.readLine())!=null)
      			{
					StringTokenizer token=new StringTokenizer(str,"*");
					String s1=token.nextToken();
					String s2=token.nextToken();
					c.addElement(s2);
      			}
      			br.close();
    		}  catch(Exception e) {System.out.println("Error : "+e.toString());}
    		return c;
	    }
	
	public void itemStateChanged(ItemEvent ie)
  	{
	  	
 			String str,st;
 			int n, i =1;
 			
    		
			n = comboBox.getSelectedIndex();
			if(n >= 1)
	    	{
			try
    		{
	    	BufferedReader br=new BufferedReader(new FileReader("AddressFile"));	
	    	
			while( i < n)
	  	 		{
		  	 		st = br.readLine();
		  	  		i=i+1;
				}   	
			str=br.readLine();
      		     		  			
      				StringTokenizer token=new StringTokenizer(str,"*");
					String s1=token.nextToken();
					String s2=token.nextToken();
					String s3=token.nextToken();
					String s4=token.nextToken();
					String s5=token.nextToken();
					String s6=token.nextToken();
					String s7=token.nextToken();
					Entry_id.setText(s1);
					Person_Name.setText(s2);
					address.setText(s3);
					city.setText(s4);
					state.setText(s5);
					country.setText(s6);
					pincode.setText(s7);
					
      		br.close();
			}
			catch(Exception e)
				 {System.out.println(" Null Error : "+e.toString());} 
		    
			}
	}
	public void RefreshComboBox()
	{
  			v.removeAllElements();
			v=getList();
			comboBox.removeAllItems();
			comboBox.addItem("Select Entry");
			Iterator itr=v.iterator();
			int j=1;
			while(itr.hasNext())
			{
				comboBox.insertItemAt(itr.next(),j);
				j++;
			} 
	}		
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource() == cmd_delete)
		{
			int n, i=1;
			String str;
    		n = comboBox.getSelectedIndex();
    		if ( n >= 1)
      		{
	      	if(checkValues()==true)
		  	{
	      	try
    		{
///////////////////////////
	confirm=JOptionPane.showConfirmDialog(this,"Do You Want To delete This Record","Delete Confirm Dialog",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    if (confirm==0)
    {
      			
				BufferedReader br=new BufferedReader(new FileReader("AddressFile"));
      			FileWriter temp = new FileWriter("TempFile",true);
      		while( i < n)
	  	 		{
		  	 		str=br.readLine();
		  	 		temp.write(str+ "\n");
		  	 		i = i+1;
				}
			str=br.readLine();
			while((str=br.readLine())!=null)
      		{
	      		temp.write(str+"\n");
			}
			br.close();
			temp.close();
			File fladdr=new File("AddressFile");
			boolean blctr=fladdr.delete();
			boolean bl=new File("TempFile").renameTo(new File("AddressFile"));
			boolean blctr1=new File("TempFile").delete();
			RefreshComboBox();
								
			Entry_id.setText("");
			Person_Name.setText("");			
			address.setText("");			
			city.setText("");			
			state.setText("");			
			country.setText("");			
			pincode.setText("");
    			setVisible(true);
			/*UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
     			udform.setLocation(this.getX(),this.getY());
     			udform.setSize(300,300);
     			udform.setResizable(false);
     			udform.setVisible(true);
	  		dispose();*/
			repaint();    	
			displayMesg("Record Deleted");
	}    		
	else if(confirm ==1) 
	{
		displayMesg("Record Is Not deleted");
	}
//////////////////////////////////////////////
    		}
			catch(Exception e)
				{System.out.println("Error : "+e.toString());} 				
			}
			}
		}
		
		if(ae.getSource() == cmd_update)
		{
		
			int n, i=1;
			String str;
    		n = comboBox.getSelectedIndex();
    		if( n >=1 )	
	  	 	{
		  		if(checkValues()==true)
		  		{
    				try
    				{
      					BufferedReader br=new BufferedReader(new FileReader("AddressFile"));
      					FileWriter temp = new FileWriter("TempFile",true);
      				while( i < n)
	  	 			{
		  	 			str=br.readLine();
		  	 			temp.write(str+ "\n");
		  	 			i = i+1;
					}
					str=br.readLine();
					str = Entry_id.getText()+"*"+Person_Name.getText()+"*"+address.getText()+"*"+city.getText()+"*"+state.getText()+"*"+ country.getText()+"* "+pincode.getText();
					temp.write(str+"\n");
					while((str=br.readLine())!=null)
      				{
	      				temp.write(str+"\n");
					}
					br.close();
					temp.close();
					File fladdr=new File("AddressFile");
					boolean blctr=fladdr.delete();
					boolean bl=new File("TempFile").renameTo(new File("AddressFile"));
					RefreshComboBox();
					displayMesg("Record Updated");
					}		
					catch(Exception e) 
  		    			{System.out.println("Error : "+e.toString());} 		
		    	}	
			}
  		}
  		if(ae.getSource()==cmd_back)
		{
			setVisible(false);
			UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
     			udform.setSize(300,300);
     			udform.setResizable(false);
     			udform.setVisible(true);
	  		dispose();
		}	
 	     
	}		
/*public static void main(String args[])
	
	{
  		UpdationForm UF = new UpdationForm();
  		UF.setSize(300,400);
 		UF.setVisible(true);
	} 

*/
 public boolean checkValues()
  {
	  
	   if((pincode.getText().trim().length()!=0))
	  {
	  	try
	  	{
	  		Integer.parseInt(pincode.getText().trim());
	  	}	  
	  	catch (Exception e)
	  	{
			displayMesg("Check Pincode Entry");
			return false;
	  	}  	  
  	  }	  
	  if( (Person_Name.getText().trim().length()==0) || (address.getText().trim().length()==0) || (city.getText().trim().length()==0) || (state.getText().trim().length()==0) || (country.getText().trim().length()==0)|| (pincode.getText().trim().length()==0))
	  {
		  displayMesg("Please Fill Each Entry");
		  return false;
	  
	  }
	  else
	  {
		  return true;
	  }

  }

  public void displayMesg(String m)
  {
	  MyDialog dialog=new MyDialog(this,m,true);
	  dialog.setLocation(this.getX(),this.getY());
	  dialog.setSize(300,100);
	  dialog.setVisible(true);  
	  
  }

 class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      		setVisible(false);
		UpdateDeleteForm udform=new UpdateDeleteForm("Update Delete Form");
     		udform.setSize(300,300);
     		udform.setResizable(false);
     		udform.setVisible(true);
	  	dispose();
    }

  }
}

