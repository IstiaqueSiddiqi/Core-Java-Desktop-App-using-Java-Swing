package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteMember extends JFrame implements ActionListener
{
	private JTextField id_txt,name_txt,phno_txt,address_txt;
	private JLabel id_lbl,name_lbl,phno_lbl,address_lbl;
	private JButton delete_btn,search_btn;
	private ArrayList<Member> list;
	private JPanel panel, panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	private Member member;
	private MemberFileHandler memberfilehandler;
	private boolean found = false;
	
	//public Constructor
	public DeleteMember()
	{
		//setting the title of the window
		this.setTitle("Member Deletion");
		 
		//Reading From File
		try
		{
			memberfilehandler = new MemberFileHandler();
			list=memberfilehandler.readFromMember();
		}
		catch(Exception ex)
		{
			list = new ArrayList<Member>();
		}	 
		 
		//creating object of JTextField
		id_txt=new JTextField("",20);
		name_txt=new JTextField("",20);
		phno_txt=new JTextField("",20);
		address_txt=new JTextField("",20);
		 
		//creating object of JLabel  
		id_lbl=new JLabel("Member ID");
		name_lbl=new JLabel("NAME");
		phno_lbl=new JLabel("PHONE_NO");
		address_lbl=new JLabel("Enter ADDRESS");
		  
		//creating object of JButton  
		delete_btn=new JButton("DELETE & EXIT");		  
		search_btn=new JButton("Search");
		 
		//creating object of JPanel
		panel=new JPanel();
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		panel6=new JPanel();
		panel7=new JPanel();
		panel8=new JPanel();		
		  
		//adding components to JPanel 
		panel1.add(id_lbl);
		panel1.add(id_txt);
		panel2.add(search_btn);
		panel3.add(name_lbl);
		panel3.add(name_txt);		 
		panel4.add(phno_lbl);
		panel4.add(phno_txt);
		panel5.add(address_lbl);
		panel5.add(address_txt);
		panel8.add(delete_btn);
		
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel4);
		panel.add(panel5);
		panel.add(panel6);
		panel.add(panel7);
		panel.add(panel8);
				  
		//Disabling All Components
		name_txt.setEditable(false);
		phno_txt.setEditable(false);
		address_txt.setEditable(false);
		delete_btn.setEnabled(false);		  
		  
		//adding components to JFrame  
		add(panel);				
		  
		//setting size of the window
		setSize(320,350);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		 
		//making the window visible
		setVisible(true);
		
		//resizing the window - disabled
		setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//registering components of the window
		search_btn.addActionListener(this);
		delete_btn.addActionListener(this);
	  }
	 
	 public void actionPerformed(ActionEvent e)
	 {		 
		 //action performed when clicked on search button
		 if(e.getSource()==search_btn)
		 {
			 int mid; 
			 if(!Validation.isValidNumber(id_txt.getText().trim()))
			 {
				 JOptionPane.showMessageDialog(null, "ID not found..!!", "Member ID Status",JOptionPane.ERROR_MESSAGE);					 
			 }
			 else
			 {		
				 int id=Integer.parseInt(id_txt.getText());
				 for(int i =0 ; i < list.size(); i++)
				 {
					 member = list.get(i);
					 mid=member.getId();
					 if(id==mid)
					 {
						 found = true;
						 String name=member.getName();
						 long ph_no = member.getPhno();
						 String address = member.getAddress();
						 id_txt.setEditable(false);
							 
						 //Enabling delete button				      
						 delete_btn.setEnabled(true);			 
							
						 name_txt.setText(name);
						 phno_txt.setText(""+ph_no);
						 address_txt.setText(address);
						 break;							
					 }
				 }
						
				 if(!found)
				 {
					 JOptionPane.showMessageDialog(null, "ID not found..!!", "Member ID Status",JOptionPane.ERROR_MESSAGE);						 
				 }					
			 }
			 found = false;
		 }
		 
		 //action performed when clicked on delete button		 
		 if(e.getSource()==delete_btn)
		 {
			 int id=Integer.parseInt(id_txt.getText());
			 for(int i =0 ; i < list.size(); i++)
			 {   
				 int mid; 
				 member = list.get(i);
				 mid=member.getId();
				 if(id==mid)
				 {
					 found = true;
					 String name=member.getName();
					 long ph_no = member.getPhno();
					 String address = member.getAddress();
					 id_txt.setEditable(false);
						 
					 //Enabling TEXTFIELDS				      
					 delete_btn.setEnabled(true);			 
						
					 name_txt.setText(name);
					 phno_txt.setText(""+ph_no);
					 address_txt.setText(address);
					 list.remove(i);
					 break;						
				 }
			 }
			 
			 //writing to file
			 try
			 {
				 memberfilehandler = new MemberFileHandler();
				 memberfilehandler.writeonmember(list);
			 }
			 catch(Exception ex)
			 {
				 JOptionPane.showMessageDialog(null, "Write to MemberDB file error..!!","Error",JOptionPane.ERROR_MESSAGE);
				 dispose();	
			 }						 
			 name_txt.setText("");
			 phno_txt.setText("");
			 address_txt.setText("");
			
			 JOptionPane.showMessageDialog(null, "Sucessfully Deleted", "Delete Member",JOptionPane.INFORMATION_MESSAGE);
			 dispose();			 
		 }				 
	 }
}


