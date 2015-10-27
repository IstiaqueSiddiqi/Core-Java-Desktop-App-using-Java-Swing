package retailBilling;

import java.awt.BorderLayout;
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

public class ChangePassword extends JFrame implements ActionListener
{
	private JPasswordField oldPassword_pass, newPassword_pass, reEnterPassword_pass;
	private JLabel oldPassword_lbl, newPassword_lbl, reEnterPassword_lbl, title_lbl;
	private JButton resetPassword_btn, cancel_btn;
	private JPanel panel1, panel2, panel3;
	private AdminFileHandler adminFileHandler;
	private Admin admin;
	
	public ChangePassword()
	{
		//creating objects of JPasswordField
		oldPassword_pass = new JPasswordField("",15);
		newPassword_pass = new JPasswordField("",15);
		reEnterPassword_pass = new JPasswordField("",15);
		
		//creating objects of JLabel
		oldPassword_lbl = new JLabel("Old Password");
		newPassword_lbl = new JLabel("New Password");
		reEnterPassword_lbl = new JLabel("Re-Enter Password");
		title_lbl = new JLabel("RESET PASSWORD");
		
		//creating objects of JButton
		resetPassword_btn = new JButton("RESET PASSWORD");
		cancel_btn = new JButton("CANCEL");
		
		//creating object of JPanel
		panel1 =  new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();		
		
		//adding components to the panels
		panel1.add(oldPassword_lbl);
		panel1.add(oldPassword_pass);
		panel1.add(newPassword_lbl);
		panel1.add(newPassword_pass);
		panel1.add(reEnterPassword_lbl);
		panel1.add(reEnterPassword_pass);		
		panel2.add(resetPassword_btn);
		panel2.add(cancel_btn);		
		panel3.add(title_lbl);
		
		//adding panels to JFrame
		add(panel1, BorderLayout.CENTER);
		add(panel2, BorderLayout.SOUTH);
		add(panel3, BorderLayout.NORTH);		
		
		//setting size of the window
		this.setSize(320, 250);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//title of the window
		this.setTitle("Reset Password");
		
		//making the window visible
		this.setVisible(true);
		
		//resizing the window - disabled
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//registering components
		resetPassword_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
	}
	
	//implementing actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		//action performed when cancel_btn is clicked
		if(e.getSource() == cancel_btn)
		{
			dispose();
		}
		
		//action performed when resetPassword_btn is clicked
		if(e.getSource() == resetPassword_btn)
		{
			//reading password file
			try
			{
				//creating object of AdminFileHandler
				adminFileHandler =  new AdminFileHandler();
				admin = adminFileHandler.readFromFile();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(null, "Admin file read error.", "Error", JOptionPane.ERROR_MESSAGE);
			}			
			
			//retrieving original password
			String password = admin.getPassword();
			
			//changing to character array
			char originalPassword[] = password.toCharArray();
			
			//getting content from JPasswordField
			char oldPassword[] = oldPassword_pass.getPassword(); 
			char newPassword[] = newPassword_pass.getPassword();
			char reEnterPassword[] = reEnterPassword_pass.getPassword();
			String newPass = new String(newPassword);
			admin = new Admin(newPass);
			
			if(Arrays.equals(oldPassword, originalPassword))
			{
				if(Arrays.equals(newPassword, reEnterPassword))
				{
					//write new password to file
					try
					{
						adminFileHandler.writeToFile(admin);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "File write error..!! Password not changed","Error", JOptionPane.ERROR_MESSAGE);
					}
					
					//success message
					JOptionPane.showMessageDialog(null, "Password changed successfully.","Password Reset Status", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "New Password Mismatch","Error",JOptionPane.ERROR_MESSAGE);
				}					
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Invalid Password","Error",JOptionPane.ERROR_MESSAGE);
			}
			
			//erasing the content of password fields
			oldPassword_pass.setText("");
			newPassword_pass.setText("");
			reEnterPassword_pass.setText("");
		}
	}	
}
