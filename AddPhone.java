import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.security.Permission.*;

@SuppressWarnings("all")
public class AddPhone extends JFrame implements ActionListener 
{
private JButton cmd_add, cmd_new,cmd_back;
private JPanel panel1,panel2,panel3,TitlePanel,MainPanel,panel8,panelA,panelB;  
private JTextField Entry_id,Person_Name,PhoneNo;
FileWriter Addfile;
String st;

	public AddPhone(String fname)
  	{
   	    super(fname);
		getContentPane().setLayout(new BorderLayout());
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panelA=new JPanel();
		panelB=new JPanel();
		TitlePanel = new JPanel();
		MainPanel=new JPanel();
		panel8 = new JPanel();

		Entry_id =new JTextField(4);
		Person_Name =new JTextField(15);
		PhoneNo =new JTextField(20);
	
		Entry_id.setEnabled(false);
		
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT,11,1));
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT,16,1));
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT,4,1));
		panelA.setLayout(new FlowLayout(FlowLayout.CENTER));
		panelB.setLayout(new FlowLayout(FlowLayout.CENTER));
		TitlePanel.setLayout(new BorderLayout());
		MainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel8.setLayout(new FlowLayout(FlowLayout.LEFT,4,1));
		cmd_add=new JButton("Add Record");
		cmd_new=new JButton("New Record");
		cmd_back=new JButton("Back");
		cmd_add.addActionListener(this);
		cmd_new.addActionListener(this);
		cmd_back.addActionListener(this);
		

		panelA.add(new JLabel("B. R. Enterprises"));
		panelB.add(new JLabel("Phone No. Entry Form"));
		TitlePanel.add(panelA,BorderLayout.NORTH);
		TitlePanel.add(panelB,BorderLayout.CENTER);
		panel1.add(new JLabel("Entry ID:"));    	
		panel1.add(Entry_id);	
		panel2.add(new JLabel("Name:"));
		panel2.add(Person_Name);
		panel3.add(new JLabel("Phone No.:"));
		panel3.add(PhoneNo);
		panel8.add(cmd_new);
		panel8.add(cmd_add);
		panel8.add(cmd_back);
		MainPanel.add(panel1);
		MainPanel.add(panel2);
		MainPanel.add(panel3);
		
		cmd_new.setEnabled(false);
		
		getContentPane().add(TitlePanel,BorderLayout.NORTH);
		getContentPane().add(MainPanel,BorderLayout.CENTER);
  		getContentPane().add(panel8,BorderLayout.SOUTH);
  		addWindowListener(new MyWindowAdapter());
	}
	

public void actionPerformed(ActionEvent ae)
	{
		String s= ae.getActionCommand();
		int id =1;
		if(s.equalsIgnoreCase("New Record"))
		{
			Entry_id.setText("");
			Person_Name.setText("");			
			PhoneNo.setText("");
			cmd_new.setEnabled(false);
		    cmd_add.setEnabled(true);
		}
 		if(s.equals("Add Record"))
		{
			
			String lastString = null;
			try {
				BufferedReader br=new BufferedReader( new FileReader("PhoneFile"));
				while((st = br.readLine()) != null)
					{
						
						lastString = st;
										 
					}
				if(lastString != null)
					{
						StringTokenizer st= new StringTokenizer(lastString, "*");
						id=Integer.parseInt(st.nextToken()) + 1;
					}
			    
				br.close(); 			
			}
			catch(IOException ioe)
				 { }
			if(checkValues()==true)
		  	{
				Entry_id.setText(" " + id);	

			try {
                        	Addfile = new FileWriter("PhoneFile",true);
                                String str = id+"*"+Person_Name.getText()+"*"+PhoneNo.getText()+"\n";
				Addfile.write(str);
                                Addfile.close();
				cmd_add.setEnabled(false);
				cmd_new.setEnabled(true);
			}
			catch(IOException ioe)
				 { System.out.println(ioe.toString()); }
			}		
		}     
			
		if(s.equals("Back"))
			{
				setVisible(false);
				AddForm addform=new AddForm("Add Contact Form");
      			addform.setSize(300,250);
      			addform.setResizable(false);
      			addform.setVisible(true);
	  			dispose();
			}
	}

/*public static void main(String args[])
	{
  		AddAddress AF = new AddAddress();
  		AF.setSize(300,300);
 		AF.setVisible(true);
	} 

*/

public boolean checkValues()
  {
	  
	  if((PhoneNo.getText().trim().length()!=0))               
	  {
	  	try
	  	{
	  		Long.parseLong(PhoneNo.getText().trim());
	  	}	  
	  	catch (Exception e)
	  	{
			displayMesg("Check Phone No. Entry");
			return false;
	  	}  	  
  	  }
	  if( (Person_Name.getText().trim().length()==0) || (PhoneNo.getText().trim().length()==0))
	  {
		  displayMesg("Please Fill Each Entry");
		  return false;
	  }

	  else 
	  {
	  
		  displayMesg("Record Saved");
		  return true;
	  }
  }

  public void displayMesg(String m)
  {
	  MyDialog dialog=new MyDialog(this,m,true);
	  dialog.setSize(300,100);
	  dialog.setVisible(true);  
	  
  }

 class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
		setVisible(false);
		AddForm addform=new AddForm("Add Contact Form");
    	addform.setSize(300,250);
      	addform.setResizable(false);
      	addform.setVisible(true);
	  	dispose();
    }

  }
  
}

