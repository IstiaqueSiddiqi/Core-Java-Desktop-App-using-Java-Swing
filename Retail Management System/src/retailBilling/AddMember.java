package retailBilling;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

public class AddMember  extends JFrame implements ActionListener
{
	private JTextField id_txt,name_txt,phno_txt,address_txt;
	private JLabel id_lbl,name_lbl,phno_lbl,address_lbl;
	private JButton add_btn,save_btn;
	private ArrayList<Member> list=null;
	private JPanel panel1,panel2,panel3,panel4,panel5,panel6,panel7,panel8;
	private int count, originalListSize, presentListSize;
	private String id;
	private LastMemberId ref;
	private MemberFileHandler memberFileHandler;
  
	//public constructor
	public AddMember()
	{
		//setting the title of the window
		this.setTitle("Add Member");
		
		//reading from file	 
		try
		{
			memberFileHandler =new MemberFileHandler();
			list = memberFileHandler.readFromMember();
		}
		catch(Exception ex)
		{
			list = new ArrayList<Member>();
		}
		
		//getting no of members
		originalListSize = list.size();
	 
		//crating object of LastMemberId class
		ref = new LastMemberId();
		
		//getting the value of last memberId
		count = ref.getLastMemberId();
		
		//auto id increment
		id = ""+ ++count;
		
		//creating objects of JTextField
		id_txt=new JTextField(id,20);
		id_txt.setEditable(false);
		name_txt=new JTextField("",20);	  
		phno_txt=new JTextField("",20);
		address_txt=new JTextField("",20);
	 
		//creating object of JLabel
		id_lbl=new JLabel("Member ID");
		name_lbl=new JLabel("Enter NAME");
		phno_lbl=new JLabel("Enter PHONE_NO");
		address_lbl=new JLabel("Enter ADDRESS");
	  
		//creating object of JButton
		add_btn=new JButton("ADD");
		save_btn=new JButton("SAVE & EXIT");
	  
		//creating object of JPanel
		panel1=new JPanel();
		panel2=new JPanel();
		panel3=new JPanel();
		panel4=new JPanel();
		panel5=new JPanel();
		panel6=new JPanel();
		panel7=new JPanel();
		panel8=new JPanel();
	  
		setLayout(new FlowLayout());
	  
		panel1.add(id_lbl);
		panel1.add(id_txt);
		panel2.add(name_lbl);
		panel2.add(name_txt);	 
		panel4.add(phno_lbl);
		panel4.add(phno_txt);
		panel5.add(address_lbl);
		panel5.add(address_txt);
		panel8.add(add_btn);
		panel8.add(save_btn);
	  
	  
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		add(panel7);
		add(panel8);
		
		
		
		//setting the size of the window
		setSize(400,250);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//making the window visible
		setVisible(true);
	  
		//resizing the window - disabled
		setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
		
		//registering the window components
		add_btn.addActionListener(this);
		save_btn.addActionListener(this);
		
		//focus cursor to name_txt field		
		name_txt.grabFocus();
	}
	
	//implementing the actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		//action performed when clicked on add button
		if(e.getSource()==add_btn)
		{
			//validation of input
			if(name_txt.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No field can be left blank..!!", "Error", JOptionPane.ERROR_MESSAGE);
				name_txt.grabFocus();
			}
			else if(phno_txt.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "No field can be left blank..!!", "Error", JOptionPane.ERROR_MESSAGE);
				phno_txt.grabFocus();
			}
			else if( address_txt.getText().isEmpty())
			{
				//message - No fields can be left empty
				JOptionPane.showMessageDialog(null, "No field can be left blank..!!", "Error", JOptionPane.ERROR_MESSAGE);
				address_txt.grabFocus();
			}
			else
			{
				if(!Validation.isValidName(name_txt.getText()))
				{
					//message - Invalid name
					JOptionPane.showMessageDialog(null, "Invalid Name", "Error", JOptionPane.ERROR_MESSAGE);
					name_txt.grabFocus();
				}
				
				else if(!(Validation.isValidNumber(phno_txt.getText().trim()) && phno_txt.getText().trim().length()==10))
				{
					//message - Invalid phone number
					JOptionPane.showMessageDialog(null, "Invalid Phone number", "Error", JOptionPane.ERROR_MESSAGE);
					phno_txt.grabFocus();
				}
				else if(!Validation.isValidName(address_txt.getText()))
				{
					//message - invalid address
					JOptionPane.showMessageDialog(null, "Invalid Address", "Error", JOptionPane.ERROR_MESSAGE);
					address_txt.grabFocus();
				}
				else
				{
					//getting data from text fields
					String name=name_txt.getText().toUpperCase();
					long phno=Long.parseLong(phno_txt.getText());
					String add=address_txt.getText().toUpperCase();
					
					//adding data to list
					list.add(new Member(count,name,add,phno));
					JOptionPane.showMessageDialog(null, "Sucessfully Added", "Add Member Status",JOptionPane.INFORMATION_MESSAGE);
					
					//setting the text field null
					name_txt.setText("");
					phno_txt.setText("");
					address_txt.setText("");
					
					//setting the id - auto incrementing
					id_txt.setText(""+ ++count);
				}		 
			}
		}
		
		//action performed when clicked on save button
		if(e.getSource()==save_btn)
		{
			//MemberFileHandler ref=new  MemberFileHandler();
			try
			{
				memberFileHandler = new MemberFileHandler();
				memberFileHandler.writeonmember(list); 
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Write to Member file error", "Error", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
			
			//getting current no of members
			presentListSize = list.size();
			
			if(originalListSize == presentListSize)
			{
				JOptionPane.showMessageDialog(null, "No member is added", "Add Member Status",JOptionPane.INFORMATION_MESSAGE);		 
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Sucessfully Saved", "Add Member Status",JOptionPane.INFORMATION_MESSAGE);		 
			}
			
			dispose();
		} 
	}	
}

