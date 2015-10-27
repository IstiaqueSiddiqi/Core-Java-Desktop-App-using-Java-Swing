package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MemberListTable extends JFrame
{
	private JTable member_table;
	private ArrayList<Member> memberList;
	private Object[][] obj;
	private Member member;
	private MemberFileHandler memberFileHandler;
	private JScrollPane jsp;
	
	//public constructor
	public MemberListTable() 
	{
		//setting the title of the window
		this.setTitle("Member List Table");
		
		//defining the columns of the table
		String columHead[]={"Sl_no","Member_Id","Name","Phone Number","Address"};
		
		//reading the MemberDB
		try
		{
		memberList=(ArrayList<Member>)memberFileHandler.readFromMember();
		
		}
		catch(Exception ex)
		{
			memberList=new ArrayList<Member>();
			
		}
		
		obj=new Object[memberList.size()][5];
		
		if(memberList.size()==0)
		{			
			//Message if no member record is found
			JOptionPane.showMessageDialog(null, "No Member Record Found","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{			
			int index=0;
			Iterator<Member> itr=memberList.iterator();
			while(itr.hasNext())
			{	
				member=itr.next();
				obj[index][0] = index+1;
				obj[index][1] = member.getId();
				obj[index][2] = member.getName();
				obj[index][3] = member.getPhno();
				obj[index++][4] = member.getAddress();
			}
			member_table=new JTable(obj,columHead);
			member_table.setEnabled(false);
			jsp=new JScrollPane(member_table);
			
			//setting the size of the table
			member_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			member_table.getColumnModel().getColumn(0).setPreferredWidth(75);
			member_table.getColumnModel().getColumn(1).setPreferredWidth(100);
			member_table.getColumnModel().getColumn(2).setPreferredWidth(300);
			member_table.getColumnModel().getColumn(3).setPreferredWidth(100);
			member_table.getColumnModel().getColumn(4).setPreferredWidth(400);
			
			//adding component to JFrame
			add(jsp);
			
			//making the window visible
			this.setVisible(true);
			
			//setting the size of the window
			this.setSize(1000,500);
			
			// setting window in center of screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
			
			//resizing the window
			this.setResizable(false);
			
			//default close operation
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}
}
