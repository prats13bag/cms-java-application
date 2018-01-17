import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

@SuppressWarnings("all")
public class SearchAddress extends JFrame implements ActionListener
{

	JTable table;
	Object data[][];
	Object dataArray[][];
	final String[] colHeads = {"Entry ID","Name"," Address","City","State","Country","Pincode"};
    JPanel panel1,panel3;
	int count =0;
	String st;
	JButton cmd_back;
	

public SearchAddress(String st1,String str) 
{
	super(st1);
	st =str;
	getContentPane().setLayout(new BorderLayout());
	panel1=new JPanel();
	panel3=new JPanel();

	cmd_back=new JButton("Back");
	cmd_back.addActionListener(this);
	panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
	panel3.setLayout(new FlowLayout(FlowLayout.CENTER));
	fillTable();
	panel1.add(new JLabel("Addresses Matched"));
	panel3.add(cmd_back);
	getContentPane().add(panel1,BorderLayout.NORTH);
	getContentPane().add(panel3,BorderLayout.SOUTH);
	addWindowListener(new MyWindowAdapter());
}

public void fillTable()
{
		int count =0;
		String str;

		try
		{
			BufferedReader BR=new BufferedReader( new FileReader("AddressFile"));
			while((str = BR.readLine()) != null)
			{
				count++;
			}
			BR.close();

		}
		catch(Exception e) {}

	
		Object Id;
		Object name;
		
		data=new Object[count][7];
		
		int i=0;
		int j=0;
		int m;
		
		try
		{
			BufferedReader br=new BufferedReader( new FileReader("AddressFile"));

			while((str = br.readLine()) != null)
			{

				StringTokenizer Str= new StringTokenizer(str, "*");
				int n=Str.countTokens();
				Id=Str.nextElement();
				name=Str.nextElement();
				String strnameLow=name.toString().toLowerCase();
				String strnameUpp=name.toString().toUpperCase();					if(strnameLow.startsWith(st.toLowerCase()) || strnameUpp.startsWith(st.toUpperCase()))
					{
						data[i][0]=Id;
						data[i][1]=name;
						for(j=2;j<n;j++)
						{	
							data[i][j]=Str.nextElement();
						}							
					i=i+1;
					}
			}
			br.close(); 	
		}
		catch(IOException io) 
		{
			System.out.println("error"+io);
		}
	//System.arraycopy(data,0,dataArray,0,i);
	dataArray= new Object[i][7];
	for(int a=0;a<i;a++)
	{
		for(int b=0;b<7;b++)
		{
			dataArray[a][b]=data[a][b];
		}
	}
	table = new JTable(dataArray,colHeads);
	int V = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	int H = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	JScrollPane ScrollBar = new JScrollPane(table,V,H);
	getContentPane().add(ScrollBar,BorderLayout.CENTER);
}

public void actionPerformed(ActionEvent ae)
{
	if(ae.getSource() == cmd_back)
	{
		setVisible(false);
		SearchAddr addform=new SearchAddr("Search Address Form");
        addform.setSize(300,300);
        addform.setResizable(false);
        addform.setVisible(true);
        dispose();
	}
}

/* public static void main(String args[])
	{
        SearchAddress AF = new SearchAddress();
  		AF.setSize(600,300);
 		AF.setVisible(true);
	}
*/	

 class MyWindowAdapter extends WindowAdapter
  {
    public void windowClosing(WindowEvent we)
    {
      	setVisible(false);
		SearchAddr addform=new SearchAddr("Search Address Form");
        addform.setSize(300,300);
        addform.setResizable(false);
        addform.setVisible(true);
        dispose();
    }

  }
}
