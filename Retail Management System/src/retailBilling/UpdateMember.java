package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UpdateMember extends JFrame implements ActionListener
{
	private JTextField id_txt,name_txt,phno_txt,address_txt;
	  private JLabel id_lbl,name_lbl,phno_lbl,address_lbl;
	  private JButton update_btn,search_btn;
	  private ArrayList<Member> list;
	  private JPanel panel, panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	  private Member member;
	  private MemberFileHandler memberfilehandler;	  
	  private boolean found = false;
	  
	  //public constructor
	  public UpdateMember()
	  {
		 //setting the title of the window
		  this.setTitle("Update Member");
		 
		//Reading From File
		 try
		 {
			 list=(ArrayList<Member>)memberfilehandler.readFromMember();			 
		 }
		 catch(Exception ex)
		 {
			 list=new ArrayList<Member>();
		 }		 	
		 
		 //creating the objects of JTextFields
		 id_txt=new JTextField("",20);
		 name_txt=new JTextField("",20);
		 phno_txt=new JTextField("",20);
		 address_txt=new JTextField("",20);
		 
		 //creating the objects of JLabel		  
		 id_lbl=new JLabel("Member ID");
		 name_lbl=new JLabel("NAME");
		 phno_lbl=new JLabel("PHONE_NO");
		 address_lbl=new JLabel("Enter ADDRESS");
		  
		 //creating objects of JButton 
		 update_btn=new JButton("UPDATE & EXIT");		
		 search_btn=new JButton("Search");
		 
		 //creating objects of JPanel
		 panel=new JPanel();
		 panel1=new JPanel();
		 panel2=new JPanel();
		 panel3=new JPanel();
		 panel4=new JPanel();
		 panel5=new JPanel();
		 panel6=new JPanel();
		 panel7=new JPanel();
		 panel8=new JPanel();  
		 
		 //adding components to panel
		 panel1.add(id_lbl);
		 panel1.add(id_txt);
		 panel2.add(search_btn);
		 panel3.add(name_lbl);
		 panel3.add(name_txt);		 
		 panel4.add(phno_lbl);
		 panel4.add(phno_txt);
		 panel5.add(address_lbl);
		 panel5.add(address_txt);
		 panel8.add(update_btn);		 
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
		 update_btn.setEnabled(false);
		 	  
		 //adding panel to JFrame
		 add(panel);
		
		 //registering window components
		 search_btn.addActionListener(this);
		 update_btn.addActionListener(this);	 
		  
		 //setting the size of the window
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
		  
		  //checking the size of the list
		  if(list.size() == 0)
		  {
			  JOptionPane.showMessageDialog(null, "No Member Record found","Error",JOptionPane.ERROR_MESSAGE);
			  dispose();
		  }
	  }
	 
	  //implementing the actionPerformed method
	 public void actionPerformed(ActionEvent e)
	 {
		 //action performed when clicked on search button
		 if(e.getSource()==search_btn)
		 {
			int mid; 
			if(!Validation.isValidNumber(id_txt.getText().trim()))
			{
				JOptionPane.showMessageDialog(null, "Invalid ID", "Member ID status",JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				int id=Integer.parseInt(id_txt.getText());
				Iterator<Member> itr = list.iterator();					
					
				while(itr.hasNext())
				{
					member = itr.next();
					mid=member.getId();
					if(id==mid)
					{
						found = true;
						String name=member.getName();
						long ph_no = member.getPhno();
						String address = member.getAddress();							 
							 
						//Enabling TEXTFIELDS
						name_txt.setEditable(true);
						phno_txt.setEditable(true);
						address_txt.setEditable(true);
						update_btn.setEnabled(true);			 
							
						//setting the retrieved data
						name_txt.setText(name);
						phno_txt.setText(""+ph_no);
						address_txt.setText(address);							
						break;							
					}
				}
						
				if(!found)
				{
					JOptionPane.showMessageDialog(null, "ID not found..!!", "Member Updation",JOptionPane.ERROR_MESSAGE);
					name_txt.setText("");
				    phno_txt.setText("");
				    address_txt.setText("");
				}					
			}
			found = false;
		 }
		 
		 //action performed when clicked on update button
		 if(e.getSource()==update_btn)
		 {
			if( name_txt.getText().isEmpty() || phno_txt.getText().isEmpty()||address_txt.getText().isEmpty())
			{
				//message - No fields can be left empty
				JOptionPane.showMessageDialog(null, "No field can be left blank..!!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{			
				if(!Validation.isValidName(name_txt.getText()))
				{
					//message - Product not valid
					JOptionPane.showMessageDialog(null, "Invalid Name", "Error", JOptionPane.ERROR_MESSAGE);
				}					
				else if(!Validation.isValidNumber(phno_txt.getText()) || phno_txt.getText().trim().length() != 10)
				{
					//message - Price is not valid
					JOptionPane.showMessageDialog(null, "Invalid ph no", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!Validation.isValidName(address_txt.getText()))
				{
					//message - Price is not valid
					JOptionPane.showMessageDialog(null, "Invalid Address", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					String newName=name_txt.getText().toUpperCase();
			        long newPhno=Long.parseLong(phno_txt.getText());
			        String newAdd=address_txt.getText().toUpperCase();
			           
			        //searching the memberId to edit
			        int id=Integer.parseInt(id_txt.getText());
			        Iterator<Member> itr = list.iterator();
					int mid;						
					while(itr.hasNext())
					{
						member = itr.next();
						mid=member.getId();
						if(id==mid)
						{				       
							//setting the new value
					        member.setAddress(newAdd);
					        member.setName(newName);
					        member.setPhno(newPhno);

					        //writing the updated member to file
							try
							{
								memberfilehandler.writeonmember(list);	
							}
							catch(Exception ex)
							{
								JOptionPane.showMessageDialog(null, "Write to Member file error", "Error", JOptionPane.ERROR_MESSAGE);
								dispose();
							}						 
			
							JOptionPane.showMessageDialog(null, "Sucessfully Updated", "Member Updation",JOptionPane.INFORMATION_MESSAGE);			          
							dispose();
						}
					}
				}
			 }
		}			 
	 }
}


