package retailBilling;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComponent;

public class Login extends JFrame implements ActionListener
{
	//declaring the reference variables for window components
	private JTextField userName_txt;
	private JLabel userName_lbl, password_lbl, login_lbl;
	private JPasswordField password_pass;
	private JPanel panel1, panel2, panel3;
	private JButton login_btn, cancel_btn;
	private Admin admin;
	private AdminFileHandler adminFileHandler;
	private String username;
	
	//public constructor
	public Login()
	{
		//setting the title of the window
		this.setTitle("Login");
		
		//creating the objects of JLabel
		userName_lbl = new JLabel("Username");
		password_lbl = new JLabel("Password");
		login_lbl = new JLabel("Login Window");
		
		//creating objects of JTextField
		userName_txt = new JTextField("",15);	
		 
		
		//creating object of JPasswordField
		password_pass = new JPasswordField("",15);
		
		//creating object of JPanel
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//creating object of JButton
		login_btn = new JButton("Login");
		cancel_btn = new JButton("Cancel");
		
		//adding component to panel1
		panel1.add(login_lbl);
		
		//adding component to panel2
		panel2.add(userName_lbl);
		panel2.add(userName_txt);
		panel2.add(password_lbl);
		panel2.add(password_pass);
		
		//adding component to panel3
		panel3.add(login_btn);
		panel3.add(cancel_btn);
		
		//adding panels to JFrame
		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
		
		
		
		//making window visible
		this.setVisible(true);	
		
		
		
		
		//setting size of the window
		this.setSize(300, 200);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//resizing the window - disabled
		this.setResizable(false);
		
		//default close operation - cancel button will close the window
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//reading the password file
		try
		{
			adminFileHandler = new AdminFileHandler();
			admin = adminFileHandler.readFromFile();
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(null, "Admin file read error.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		//component registration
		userName_txt.addActionListener(this);
		login_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
		
		//setting focus to user name text field
		userName_txt.grabFocus();
	}
	
	//implementing actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		
		//action performed when login_btn is clicked
		if(e.getSource() == login_btn)
		{
			//setting user name as "admin"
			username = "admin";
			
			//getting password from password file
			String originalPassword = admin.getPassword();
			
			//changing the original password to array of characters
			char password[] = originalPassword.toCharArray();
			
			//getting content of password field
			char localPassword[] = password_pass.getPassword();
			
			
			//checking user name and password
			if((userName_txt.getText().trim().equals(username)) && Arrays.equals(localPassword, password))
			{
				//main window will be opened
				this.dispose();
			
				new MainWindow();
			}			
			else
			{				
				JOptionPane.showMessageDialog(null, "Invalid Username or Password","Error",JOptionPane.ERROR_MESSAGE);
				password_pass.setText("");
				userName_txt.setText("");
			}			
		}
		
		//action performed when cancel_btn is clicked
		if(e.getSource() == cancel_btn)
		{
			dispose();
		}			
	}
	
	//main method
	public static void main(String args[])
	{
		new Login();
	}
}
