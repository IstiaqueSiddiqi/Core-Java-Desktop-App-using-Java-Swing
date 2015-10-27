package retailBilling;

import java.awt.BorderLayout;
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

public class AddExistingItem extends JFrame implements ActionListener
{
	private JLabel brandName_lbl, itemName_lbl, quantity_lbl, status_lbl;
	private JTextField brandName_txt, itemName_txt, quantity_txt;
	private JButton search_btn, update_btn, addNewItem_btn;
	private JPanel panel1, panel2, panel3;
	private FileHandler fileHandler;
	private ArrayList<Item> list;
	private Item item;
	private boolean match = false;
	
	public AddExistingItem()
	{
		//set title of the window
		this.setTitle("Add Item");
		
		//creating object of JLabel
		brandName_lbl = new JLabel("Enter Brand Name");
		itemName_lbl = new JLabel("Enter Item Name");
		quantity_lbl = new JLabel("Enter Quantity");
		status_lbl = new JLabel("Search Status :");
		
		//creating object of JFieldText
		brandName_txt = new JTextField("",15);
		itemName_txt = new JTextField("",15);
		quantity_txt = new JTextField("",15);
		quantity_txt.setEditable(false);
		
		//creating object of JButton
		search_btn = new JButton("SEARCH");
		update_btn = new JButton("UPDATE");
		update_btn.setEnabled(false);
		addNewItem_btn = new JButton("ADD NEW ITEM");
		addNewItem_btn.setEnabled(false);
		
		//creating object of panel
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		//adding components to panel
		panel2.add(brandName_lbl);
		panel2.add(brandName_txt);
		panel2.add(itemName_lbl);
		panel2.add(itemName_txt);
		panel2.add(quantity_lbl);
		panel2.add(quantity_txt);
		panel2.add(search_btn);
		panel2.add(status_lbl);		
		panel3.add(update_btn);
		panel3.add(addNewItem_btn);		
		
		//adding panels to JFrame
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);			
		
		//making window visible
		this.setVisible(true);
		
		//resizing the window
		this.setResizable(false);
		
		//setting size of the window
		this.setSize(300, 300);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		
		//registering window components
		search_btn.addActionListener(this);
		update_btn.addActionListener(this);
		addNewItem_btn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == search_btn)
		{
			//setting the addNewItem_btn to false
			addNewItem_btn.setEnabled(false);
		
			try
			{
				fileHandler = new FileHandler();
				list = fileHandler.readFromFile();
			}
			catch(Exception ex)
			{
				list = new ArrayList<Item>();
			}			
			
			//checking if fields are empty
			if((itemName_txt.getText().trim().isEmpty() || brandName_txt.getText().trim().isEmpty()))
			{
				JOptionPane.showMessageDialog(null, "No fields can be left empty..!!","Search Status", JOptionPane.ERROR_MESSAGE);
				brandName_txt.grabFocus();
			}
			else
			{
			//searching 
			Iterator<Item> itr = list.iterator();
			while(itr.hasNext())
			{
				item = itr.next();				
				
				//checking whether the item exists in stock or not
				if(brandName_txt.getText().equalsIgnoreCase(item.getProductName().trim()) && itemName_txt.getText().equalsIgnoreCase(item.getItemName().trim()))
				{
					match = true;
					status_lbl.setText("Item Exists. Enter Quantity and Click on UPDATE");
					
					quantity_txt.setEditable(true);
					update_btn.setEnabled(true);
					search_btn.setEnabled(false);
					
					itemName_txt.setEditable(false);
					brandName_txt.setEditable(false);
					break;
				}
				
			}			
			
			//when no match
			if(match != true)
			{
				status_lbl.setText("Item does not exists. Click on ADD NEW ITEM");
				addNewItem_btn.setEnabled(true);				
			}
			else
			{
				
			}
			}
		}
		
		//action performed when UPDATE button is clicked
		if(e.getSource() == update_btn)
		{
			if(quantity_txt.getText().isEmpty())
			{
				//message - quantity field cannot be empty
				JOptionPane.showMessageDialog(null, "Quantity field cannot be empty","Error",JOptionPane.ERROR_MESSAGE);
			}
			else if(!Validation.isValidNumber(quantity_txt.getText()))
			{
				//message - invalid quantity
				JOptionPane.showMessageDialog(null, "Invalid Quantity","Error", JOptionPane.ERROR_MESSAGE);				
			}
			else
			{
				int oldQuantity = item.getQuantity();
				int newQuantity = Integer.parseInt(quantity_txt.getText().trim());
				item.setQuantity(oldQuantity+newQuantity);
				
				//writing to file
				try
				{
					fileHandler.writeToFile(list);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Write to ItemDB file error","Update Status", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
				
				//message - updated successfully
				JOptionPane.showMessageDialog(null, "Updated Succesfully","Upadate Status", JOptionPane.INFORMATION_MESSAGE);
				
				//closing the window
				dispose();
				new AddExistingItem();
			}			
		}
		
		//action performed when ADD NEW ITEM button is clicked
		if(e.getSource() == addNewItem_btn)
		{			
			String brandName = brandName_txt.getText().toUpperCase();
			String itemName = itemName_txt.getText().toUpperCase();
			dispose();
			new AddNewItem(brandName,  itemName);
		}
	}	
}
