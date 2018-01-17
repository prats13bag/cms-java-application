import java.io.*;
import java.lang.Object.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class AddEmail extends JFrame implements ActionListener 
{
private JButton cmd_add, cmd_new,cmd_back;
private JPanel panel1,panel2,panel3,TitlePanel,MainPanel,panel8,panelA,panelB;  
private JTextField Entry_id,Person_Name,EmailID;
FileWriter Addfile;
String st;		
int index1=0,index2=0;	//index1="@", index2="."

	public AddEmail(String fname)
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
		EmailID =new JTextField(20);
	
		Entry_id.setEnabled(false);
		
		panel1.setLayout(new FlowLayout(FlowLayout.LEFT,6,1));
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT,11,1));
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
		panelB.add(new JLabel("Email Entry Form"));
		TitlePanel.add(panelA,BorderLayout.NORTH);
		TitlePanel.add(panelB,BorderLayout.CENTER);
		panel1.add(new JLabel("Entry ID:"));    	
		panel1.add(Entry_id);	
		panel2.add(new JLabel("Name:"));
		panel2.add(Person_Name);
		panel3.add(new JLabel("Email ID:"));
		panel3.add(EmailID);
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
			EmailID.setText("");
			cmd_new.setEnabled(false);
		    cmd_add.setEnabled(true);
		}
 		if(s.equals("Add Record"))
		{
			
			String lastString = null;
			try {
			File f=new File("EmailFile");	
			BufferedReader br=new BufferedReader( new 									FileReader(f));
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
				File f2=new File("EmailFile");	
				Addfile = new FileWriter(f2,true);
				String str = id+"*"+Person_Name.getText()+"*"+EmailID.getText()+"\n";
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
	  if( (Person_Name.getText().trim().length()==0) || (EmailID.getText().trim().length()==0))
	  {
		  displayMesg("Please Fill Each Entry");
		  return false;
	  
	  }
	  else							
	  {
			index1=EmailID.getText().indexOf("@");
			index2=EmailID.getText().trim().indexOf(".");
			if (index1 > 0 && index2 > 0)
			{
				displayMesg("Record Saved");
				return true;
			}	
			else
		    {
				displayMesg("Enter A Valid EMail");
				return false;  
			}
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

