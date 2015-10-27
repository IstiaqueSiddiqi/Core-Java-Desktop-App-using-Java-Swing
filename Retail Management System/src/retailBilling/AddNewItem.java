package retailBilling;

import java.awt.BorderLayout;
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


public class AddNewItem extends JFrame implements ActionListener
{
	//declaring the reference variables of different window components
	private JLabel itemCode_lbl, itemName_lbl, itemPrice_lbl, itemQuantity_lbl,itemProduct_lbl;
	private JTextField itemCode_txt, itemName_txt, itemPrice_txt, itemQuantity_txt,itemProduct_txt;
	private JButton itemAdd_btn, itemSaveAndExit_btn, itemReset_btn;
	private JPanel itemCodePanel, itemNamePanel, itemPricePanel, itemQuantityPanel, itemProductPanel, itemPanel, itemButtonPanel;
	private FileHandler fileHandler;
	private ArrayList<Item> list = null;
	private int lastItemCode;
	private LastItemCode lCode_obj;
	private int itemCounter = 0;
	private String code="";	
	private int newAddedItem=0;
	
	//public constructor
	public AddNewItem(String brandName, String itemName)
	{
		//set title to the window
		this.setTitle("Add New Item");
		
		//creating object of JLabel
		itemCode_lbl = new JLabel("Item Code");
		itemName_lbl = new JLabel("Item Name");
		itemPrice_lbl = new JLabel("Price");
		itemQuantity_lbl = new JLabel("Quantity");
		itemProduct_lbl = new JLabel("Product");
		
		//creating object of JTextField
		itemCode_txt = new JTextField("",15);
		itemName_txt = new JTextField("",25);
		itemPrice_txt = new JTextField("",15);
		itemQuantity_txt = new JTextField("",15);
		itemProduct_txt = new JTextField("",15);
		
		itemCode_txt.setEditable(false);
		
		//setting the text field of item_Product and item_Name
		itemProduct_txt.setText(brandName);
		itemName_txt.setText(itemName);
		
		//creating object of JButton
		itemAdd_btn = new JButton("ADD");
		itemSaveAndExit_btn = new JButton("SAVE & EXIT");
		itemReset_btn = new JButton("RESET");
		
		//creating object of JPanel
		itemCodePanel = new JPanel();
		itemNamePanel = new JPanel();
		itemPricePanel = new JPanel();
		itemQuantityPanel = new JPanel();
		itemProductPanel = new JPanel();
		itemButtonPanel = new JPanel();
		itemPanel = new JPanel();
		
		//adding component to panels
		itemCodePanel.add(itemCode_lbl);
		itemCodePanel.add(itemCode_txt);
		itemNamePanel.add(itemName_lbl);
		itemNamePanel.add(itemName_txt);
		itemPricePanel.add(itemPrice_lbl);
		itemPricePanel.add(itemPrice_txt);
		itemQuantityPanel.add(itemQuantity_lbl);
		itemQuantityPanel.add(itemQuantity_txt);
		itemProductPanel.add(itemProduct_lbl);
		itemProductPanel.add(itemProduct_txt);
		
		itemButtonPanel.add(itemAdd_btn);
		itemButtonPanel.add(itemReset_btn);
		itemButtonPanel.add(itemSaveAndExit_btn);		
		
		itemPanel.add(itemCodePanel);
		itemPanel.add(itemProductPanel);
		itemPanel.add(itemNamePanel);
		itemPanel.add(itemPricePanel);
		itemPanel.add(itemQuantityPanel);
		
		//adding panels to JFrame
		add(itemPanel, BorderLayout.CENTER);
		add(itemButtonPanel, BorderLayout.SOUTH);		
		
		//resizing the window
		this.setResizable(false);
		
		//making window visible
		this.setVisible(true);
		
		
		//setting size of the window
		this.setSize(400, 320);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//when added to main window, it must be HIDE_ON_CLOSE
		
		//creating object of FileHandler
		fileHandler = new FileHandler();
		
		//reading data from file
		try
		{
			list = fileHandler.readFromFile();
		}
		catch(Exception e)
		{
			list = new ArrayList<Item>();
		}
		
		//getting the last item code
		lCode_obj = new LastItemCode();
		lastItemCode = lCode_obj.getLastItemCode();
		
		//displaying the last code in the itemCode_txt
		code = "" + (lastItemCode+1);
		itemCode_txt.setText(code);
		
		//registration of window components
		itemAdd_btn.addActionListener(this);
		itemSaveAndExit_btn.addActionListener(this);
		itemReset_btn.addActionListener(this);		
	}
	
	//implementing the actionPerformed method of ActionListener
	public void actionPerformed(ActionEvent e)
	{
		//action performed when add_btn is clicked
		if(e.getSource() == itemAdd_btn)
		{
			//validation of input
			if(itemProduct_txt.getText().isEmpty() || itemName_txt.getText().isEmpty() || itemQuantity_txt.getText().isEmpty() || itemPrice_txt.getText().isEmpty())
			{
				//message - No fields can be left empty
				JOptionPane.showMessageDialog(null, "No field can be left blank..!!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				if(!Validation.isValidName(itemProduct_txt.getText()))
				{
					//message - Product not valid
					JOptionPane.showMessageDialog(null, "Invalid Product", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!Validation.isValidPrice(itemPrice_txt.getText()))
				{
					//message - Quantity is not valid
					JOptionPane.showMessageDialog(null, "Invalid Price", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if(!Validation.isValidNumber(itemQuantity_txt.getText()))
				{
					//message - Price is not valid
					JOptionPane.showMessageDialog(null, "Invalid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					int itemCode = Integer.parseInt(itemCode_txt.getText());
					String itemProduct = itemProduct_txt.getText().toUpperCase();
					String itemName = itemName_txt.getText().toUpperCase();
					int itemQuantity = Integer.parseInt(itemQuantity_txt.getText());
					Double itemPrice = Double.parseDouble(itemPrice_txt.getText());
					
					//creating object of Item class
					list.add(new Item(itemCode, itemProduct, itemName, itemPrice, itemQuantity));
					
					//successful add message to the user
					JOptionPane.showMessageDialog(null, "New Item Successfully Added","Add Status", JOptionPane.INFORMATION_MESSAGE);
					
					//new added item counter
					++newAddedItem;
					
					
					//auto increment of item code
					++itemCounter;
					lastItemCode = lCode_obj.getLastItemCode();
					code = "" + (lastItemCode + itemCounter + 1);
					
					//reseting the text fields
					itemCode_txt.setText(code);
					itemProduct_txt.setText("");
					itemName_txt.setText("");
					itemQuantity_txt.setText("");
					itemPrice_txt.setText("");
				}
			}			
		}	
		
		//action performed when cancel_btn is clicked
		if(e.getSource() == itemSaveAndExit_btn)
		{
			//checking if new item is added or not
			if(newAddedItem == 0)
			{
				JOptionPane.showMessageDialog(null, "No new Item is added","Save Status", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				//writing to file
				try
				{
					fileHandler = new FileHandler();
					fileHandler.writeToFile(list);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Write to ItemDB file error","Save Status", JOptionPane.ERROR_MESSAGE);
					dispose();
				}
			
				//successful save message to user
				JOptionPane.showMessageDialog(null, "Save Successful","Save Status",JOptionPane.INFORMATION_MESSAGE);
			}			
			//closing the window
			dispose();
		}		
		
		//action performed when reset_btn is clicked
		if(e.getSource() == itemReset_btn)
		{
			//itemCode_txt.setText("");
			itemName_txt.setText("");
			itemPrice_txt.setText("");
			itemQuantity_txt.setText("");
			itemProduct_txt.setText("");
		}
	}	
}
