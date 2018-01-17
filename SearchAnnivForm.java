import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class SearchAnnivForm extends JFrame implements ActionListener
{
  private static String [] Months={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
  private static String [] Col_Heading={"Entry ID","Name","Day","Month","Year"};
  private JComboBox cho_Mon;
  private JTable tt;
  private JButton cmd_Search,cmd_Cancel;
  
  JPanel panel2;
  Object data[][];

  public SearchAnnivForm(String fname)
  {
    super(fname);
    Vector v=new Vector();
    getContentPane().setLayout(new BorderLayout());
    for(int i=0;i<12;i++)
		v.addElement(Months[i]);

	//Add values in month combobox.
    cho_Mon=new JComboBox(v);
    
    cmd_Search=new JButton("Search");
    cmd_Cancel=new JButton("Back");
  
    JPanel panel1=new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.add(cho_Mon);

    panel2=new JPanel();
    panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
    JPanel panel3=new JPanel();
    panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
    panel3.add(cmd_Search);
    panel3.add(cmd_Cancel);

    getContentPane().add(panel1,BorderLayout.NORTH);        
    getContentPane().add(panel2,BorderLayout.CENTER);
    getContentPane().add(panel3,BorderLayout.SOUTH);

    cmd_Search.addActionListener(this);
    cmd_Cancel.addActionListener(this);
    addWindowListener(new MyWindowAdapter());

  }


  public void actionPerformed(ActionEvent ae)
  {
	  //If user clicks on Search button.
	  if(ae.getSource()==cmd_Search)
	  {

		  //Gets the total count for records in the Annivers file.		  		  
	  	  int count=getCount();
	  	  
	  	  try
	  	  {
		  	  
		      BufferedReader br=new BufferedReader(new FileReader("Annivers"));
		  	  String str=new String();
		  	  int i=0;
		  	  boolean flag=false;
		  	  StringTokenizer t;
		  	  data=new Object[count][5];
		  	  flag=false;
		  	  while((str=br.readLine())!=null)
		  	  {
			      StringTokenizer token=new StringTokenizer(str,"*");
			      t=new StringTokenizer(str,"*");
			      String eid=t.nextToken();
			      String name=t.nextToken();
			      String dd=t.nextToken();
			      String mm=t.nextToken();
			      String yy=t.nextToken();
	
			      //If current record matches selected entry then add it to the Object array.
			  	  if(mm.equals((String)cho_Mon.getSelectedItem()))
			  	  {
				  	  
				  	  flag=true;
				  	  for(int j=0;j<5;j++)
				  	  {
					  	  data[i][j]=token.nextElement();
			          }
			          i++;
		          }
	          }
	          br.close();
	          if(flag==false)
	          {
		          displayMesg("No record found");
	          }
		  else
		  {
		          panel2.removeAll();
		          tt=new JTable(data,Col_Heading);
	        	  int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		          int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		          JScrollPane sc=new JScrollPane(tt);
			  panel2.add(sc);
			  pack();
		          repaint();
		   }
	     	          
          }  catch(FileNotFoundException fnfe)
             {

	         }
	         catch(IOException ioe)
	         {
		         displayMesg("I/O error");
	         }
      }
      else if(ae.getSource()==cmd_Cancel)
      {

          SearchForm sform=new SearchForm("Search Contact Form");
          sform.setSize(250,250);
          sform.setResizable(false);
          sform.setVisible(true);
          dispose();
      }
    
    }

  //Returns the total count for total records in the Annivers file.
  int getCount()
  { int count=0;
    try
    {
      BufferedReader br=new BufferedReader(new FileReader("Annivers"));
      
      String str;
      while((str=br.readLine())!=null)
      {
	      StringTokenizer token=new StringTokenizer(str,"*");
	      String eid=token.nextToken();
	      String n=token.nextToken();
	      String d=token.nextToken();
	      String m=token.nextToken();
	      if(m.equals((String)cho_Mon.getSelectedItem()))
	      {
		      count++;
		      
	      }
  
	  }
      br.close();	      

    }  catch(FileNotFoundException fnfe)
       {
	       displayMesg("Annivers file not found");
	       setVisible(false);
	       dispose();
       }
       catch(IOException ioe)
       {
	       displayMesg("I/O error");
       }
    return count;
  }

  //Inner class to implement windowClosing() method.
  class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
  	      
          SearchForm sform=new SearchForm("Search Contact Form");
          sform.setSize(250,250);
          sform.setResizable(false);
          sform.setVisible(true);
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