package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SearchItem extends JFrame implements ActionListener
{
	private JLabel brandName_lbl;
	private JTextField brandName_txt;
	private JButton search_btn, cancel_btn;
	private JPanel panel1, panel2, panel3;
	
	//public constructor
	public SearchItem()
	{
		//setting the title of the window
		this.setTitle("Search Item");
		
		//creating objects of JLabel
		brandName_lbl = new JLabel("Enter Brand Name");
		
		//creating objects of JTextField
		brandName_txt = new JTextField("",20);
		
		//creating objects of JButton
		search_btn = new JButton("SEARCH");
		cancel_btn = new JButton("CANCEL");
		
		//creating objects of JPanel
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//adding components to panels
		panel1.add(brandName_lbl);
		panel1.add(brandName_txt);
		
		panel2.add(search_btn);
		panel2.add(cancel_btn);
		
		panel3.add(panel1);
		panel3.add(panel2);
		
		//adding component of JFrame
		add(panel3);
		
		//making window visible
		this.setVisible(true);
		
		//setting the size of the window
		this.setSize(350, 250);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//resizing the window - disabled
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//registering the components
		search_btn.addActionListener(this);
		cancel_btn.addActionListener(this);
	}
	
	//implementing actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		//action performed when clicked on search button
		if(e.getSource() == search_btn)
		{
			//checking if the field is empty
			if(brandName_txt.getText().trim().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Field cannot be empty.","Search Status", JOptionPane.ERROR_MESSAGE);
				brandName_txt.grabFocus();
			}
			else
			{
				String brandName = brandName_txt.getText().trim();
				dispose();
				new BrandItemListTable(brandName);
			}
		}
		
		//action performed when clicked on cancel button
		if(e.getSource() == cancel_btn)
		{
			dispose();
		}
	}	
}
