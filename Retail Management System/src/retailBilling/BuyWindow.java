package retailBilling;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class BuyWindow extends JFrame implements ActionListener
{
	//declaring the reference variables of window components
	
	private JButton buyAndAddToCart_btn, done_btn, ok_btn, set_btn;
	private JLabel title_lbl, itemCode_lbl, itemName_lbl, brandName_lbl, unitPrice_lbl, itemsAvailable_lbl, itemQuantity_lbl, memberId_lbl;
	private JPanel panel1, panel2, panel3,panel4,panel5,panel6,panel7,panel8,panel9, panelRadio, panelMember;
	private JRadioButton member_rbtn, nonMember_rbtn;
	private JTextField itemCode_txt, itemName_txt, brandName_txt, unitPrice_txt, itemsAvailable_txt, itemQuantity_txt, memberId_txt;
	private FileHandler fileHandler;
	private BoughtListFileHandler boughtListFileHandler;
	private ArrayList<Item> list, boughtList;
	private Item item, boughtItem;
	private boolean itemAvailable = false;
	private double amount = 0.0;
	private ButtonGroup group;
	private int memberId;
	private int mID = 0;
	private ArrayList<Member> memberList;
	private MemberFileHandler memberFileHandler;
	private Member member;
	private Bill bill;
	private BillHandler billHandler;
	private ArrayList<Bill> billList;
	boolean memberIdFound = false;
	private LastBillNo lastBillNo;	
	
	public BuyWindow()
	{
		//reading data from BillDB		
		try
		{
			billHandler = new BillHandler();
			billList = (ArrayList<Bill>)billHandler.readFromFile();
		}
		catch(Exception ex)
		{
			billList = new ArrayList<Bill>();
		}			
		
		//reading data from file
		try
		{
			fileHandler = new FileHandler();
			list = fileHandler.readFromFile();
		}
		catch(Exception e)
		{
			list = new ArrayList<Item>();
		}
		
		//product list
		
		//creating object of buyList 
		boughtList = new ArrayList<Item>();
		
		//creating the object of JButton
		buyAndAddToCart_btn = new JButton("BUY AND ADD TO CART");
		buyAndAddToCart_btn.setEnabled(false);
		done_btn = new JButton("DONE");
		ok_btn  = new JButton("OK");
		set_btn = new JButton("SET");
		
		//creating the object of JLabel
		title_lbl = new JLabel("BUY WINDOW");
		itemCode_lbl = new JLabel("Enter Item Code");
		itemName_lbl = new JLabel("Item Name");
		brandName_lbl  = new JLabel("Brand Name");
		unitPrice_lbl = new JLabel("Unit Price");
		itemsAvailable_lbl  = new JLabel("Items Available");
		itemQuantity_lbl = new JLabel("Enter quantity");
		memberId_lbl = new JLabel("Enter Member ID");
		
		
		//creating the object of JTextField
		itemCode_txt = new JTextField("",5);
		itemName_txt = new JTextField("",25);
		brandName_txt = new JTextField("",15);
		unitPrice_txt = new JTextField("",15);
		itemsAvailable_txt = new JTextField("",15);
		itemQuantity_txt = new JTextField("",15);
		memberId_txt = new JTextField("",10);
		
		itemName_txt.setEditable(false);
		brandName_txt.setEditable(false);
		unitPrice_txt.setEditable(false);
		itemsAvailable_txt.setEditable(false);
		itemQuantity_txt.setEditable(false);
		memberId_txt.setEditable(false);
		
		//creating object of JRadioButton
		member_rbtn = new JRadioButton("Member");
		nonMember_rbtn = new JRadioButton("Non-Member");
		nonMember_rbtn.setSelected(true);
		
		//group the radio buttons
		group = new ButtonGroup();
		group.add(member_rbtn);
		group.add(nonMember_rbtn);
		
		//checking which radio button is selected
		
		
		//creating object of JPanel
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		panel6 = new JPanel();
		panel7 = new JPanel();
		panel8 = new JPanel();
		panel9 = new JPanel();
		panelRadio = new JPanel();
		panelMember = new JPanel();
		
		//adding the components to panels
		panel1.add(title_lbl);
		
		panel2.add(itemCode_lbl);
		panel2.add(itemCode_txt);
		panel2.add(ok_btn);
		panel4.add(itemName_lbl);
		panel4.add(itemName_txt);
		panel5.add(brandName_lbl);
		panel5.add(brandName_txt);
		panel6.add(unitPrice_lbl);
		panel6.add(unitPrice_txt);
		panel7.add(itemsAvailable_lbl);
		panel7.add(itemsAvailable_txt);
		panel8.add(itemQuantity_lbl);
		panel8.add(itemQuantity_txt);
		panelRadio.add(nonMember_rbtn);
		panelRadio.add(member_rbtn);
		panelMember.add(memberId_lbl);
		panelMember.add(memberId_txt);
		panelMember.add(set_btn);
		
		//disabling the set_btn
		set_btn.setEnabled(false);
		//member_rbtn.setEnabled(false);
		//nonMember_rbtn.setEnabled(false);
		
		panel3.add(buyAndAddToCart_btn);
		panel3.add(done_btn);
		
		panel9.add(panel2);
		panel9.add(panel3);
		panel9.add(panel5);
		panel9.add(panel4);
		panel9.add(panel6);
		panel9.add(panel7);
		panel9.add(panel8);
		panel9.add(panelRadio);
		panel9.add(panelMember);
		
		//adding components to JFrame
		add(panel1, BorderLayout.NORTH);
		add(panel9, BorderLayout.CENTER);
		add(panel3, BorderLayout.SOUTH);
		
		//setting the title of the window
		this.setTitle("Buy Window");
		
		//making the window visible
		this.setVisible(true);
		
		//setting the size of the window
		this.setSize(400, 400); 
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//resizing the window
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//registering the window component
		ok_btn.addActionListener(this);
		buyAndAddToCart_btn.addActionListener(this);
		done_btn.addActionListener(this);
		member_rbtn.addActionListener(this);
		nonMember_rbtn.addActionListener(this);
		set_btn.addActionListener(this);
		
		//grabbing focus to itemCode_txt field
		itemCode_txt.grabFocus();
	}
	
	//implementing actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == ok_btn)
		{
			//erasing the content of text fields
			brandName_txt.setText("");
			itemName_txt.setText("");
			unitPrice_txt.setText("");
			itemQuantity_txt.setText("");
			itemsAvailable_txt.setText("");			
			
			//checking if the item code is valid or not
			if(Validation.isValidNumber(itemCode_txt.getText().trim()))
			{
				Iterator<Item> itr = list.iterator();
				while(itr.hasNext())
				{
					item = itr.next();
					
					//matching code
					if(item.getItemCode() == Integer.parseInt(itemCode_txt.getText().trim()))
					{
						//displaying the details of the item
						brandName_txt.setText(item.getProductName());
						itemName_txt.setText(item.getItemName());
						itemsAvailable_txt.setText(""+item.getQuantity());
						unitPrice_txt.setText(""+item.getPrice());
												
						buyAndAddToCart_btn.setEnabled(true);
						itemQuantity_txt.setEditable(true);
						itemAvailable = true;

						//setting cursor focus to item quantity text field
						itemQuantity_txt.grabFocus();
						break;
					}					
				}
				
				//if item is available
				if(itemAvailable == false)
				{
					JOptionPane.showMessageDialog(null, "Item not found in the stock","Error", JOptionPane.ERROR_MESSAGE);
					brandName_txt.setText("");
					itemName_txt.setText("");
					unitPrice_txt.setText("");
					itemsAvailable_txt.setText("");
					itemQuantity_txt.setEditable(false);
				}
			}
			else
			{
				//message - invalid item code
				JOptionPane.showMessageDialog(null, "Invalid code","Error",JOptionPane.ERROR_MESSAGE);
				itemCode_txt.grabFocus();
			}		
			
			//resetting match to false
			itemAvailable = false;
			
		}
		
		//action performed when buyAndAddToCart button is clicked
		if(e.getSource() == buyAndAddToCart_btn)
		{
			//disabling the set_btn 
			set_btn.setEnabled(false);
			
			//disabling the radio button panel
			panelRadio.setEnabled(false);
			member_rbtn.setEnabled(false);
			nonMember_rbtn.setEnabled(false);
			
			if(Validation.isValidNumber(itemQuantity_txt.getText().trim()))
			{
				int noOfItemsAvailable = item.getQuantity();
				int buyQuantity = Integer.parseInt(itemQuantity_txt.getText().trim());
				
				//checking
				if(noOfItemsAvailable >= buyQuantity)
				{
					//buying item possible
					noOfItemsAvailable -= buyQuantity;
					item.setQuantity(noOfItemsAvailable);
					
					//writing to the file
					try
					{
						fileHandler = new FileHandler();
						fileHandler.writeToFile(list);
					}
					catch(Exception ex)
					{
						JOptionPane.showMessageDialog(null, "Write to ItemDB file error","Item Update Status",JOptionPane.ERROR_MESSAGE);
					}					
					
					//calculating amount and buy list
					boughtItem = new Item();
					boughtItem.setItemCode(item.getItemCode());
					boughtItem.setProductName(item.getProductName());
					boughtItem.setItemName(item.getItemName());
					boughtItem.setPrice(item.getPrice());
					boughtItem.setQuantity(buyQuantity);
					
					//adding boughtItem to boughtList
					boughtList.add(boughtItem);					
					
					//message to place an order if no of particular items get low
					if(item.getQuantity() <= 5)
					{
						JOptionPane.showMessageDialog(null, "Please place an order of this item","Place order", JOptionPane.INFORMATION_MESSAGE);
					}					
					
					//message - successfully bought and added to cart
					JOptionPane.showMessageDialog(null, "Item successfully bought and added to cart","Buy Status", JOptionPane.INFORMATION_MESSAGE);
					itemQuantity_txt.setEditable(false);
					buyAndAddToCart_btn.setEnabled(false);
					
					//restricting to buy only 10 items at a time
					if(boughtList.size() == 10)
					{
						JOptionPane.showMessageDialog(null, "Maximum buy item level reached. Click on DONE","Buy Status", JOptionPane.INFORMATION_MESSAGE);
						ok_btn.setEnabled(false);
						itemCode_txt.setEditable(false);
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "That much no of Item not Available","Error", JOptionPane.ERROR_MESSAGE);
					buyAndAddToCart_btn.setEnabled(false);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Entered Quantity is invalid","Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//action performed when clicked on DONE button
		if(e.getSource() == done_btn)
		{			
			//checking whether any item is bought or not
			if(boughtList.size() > 0)
			{
				//writing the boughtList to the file
				try
				{
					boughtListFileHandler = new BoughtListFileHandler();
					boughtListFileHandler.writeToFile(boughtList);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Write to BoughtListDB file error..!","Error", JOptionPane.ERROR_MESSAGE);
				}				
				
				//disabling the buyAndAddToCart button
				buyAndAddToCart_btn.setEnabled(false);
				
				//adding new Bill object to BillDB
				bill = new Bill();
				lastBillNo = new LastBillNo();
				int lastBill = lastBillNo.getLastBillNo();
				bill.setMemberId(memberId);
				bill.setBillNo(++lastBill);
				
				billList.add(bill);
				
				try
				{
					billHandler.writeToFile(billList);
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, "Write to BillDB file error..!","Error", JOptionPane.ERROR_MESSAGE);
				}
				
				//setting the member id for discount
				if(memberId_txt.getText().trim().length() > 0)
					mID = Integer.parseInt(memberId_txt.getText());
				
				//displaying the bill and writing to file BillDB (BillDisplay writes the file)
				new BillDisplay(mID);				
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No Item is Added to the cart","Buy Status",JOptionPane.ERROR_MESSAGE);
			}
			dispose();			
		}
	
		//action performed when member radio button is selected
		if(e.getSource() == member_rbtn)
		{
			memberId_txt.setEditable(true);		
			set_btn.setEnabled(true);
			
			//grabbing cursor focus on memberId_txt field
			memberId_txt.grabFocus();			
			
			//message to input the member id and click on set
			JOptionPane.showMessageDialog(null, "Enter Member ID and click on SET","Set Member ID",JOptionPane.INFORMATION_MESSAGE);
		}		
		
		//action performed when non member radio button is selected
		if(e.getSource() == nonMember_rbtn)
		{
			memberId_txt.setEditable(false);
			set_btn.setEnabled(false);
			//buyAndAddToCart_btn.setEnabled(true);
		}	
	
		//action performed when set button is clicked
		if(e.getSource() == set_btn)
		{
			//validation for member id
			if(Validation.isValidNumber(memberId_txt.getText().trim()) && !memberId_txt.getText().isEmpty())
			{
				//checking whether the memberId is right or not
				try
				{
					memberList = (ArrayList<Member>)memberFileHandler.readFromMember();
				}
				catch(Exception ex)
				{
					memberList = new ArrayList<Member>();
				}				
				
				Iterator<Member> itr = memberList.iterator();
				memberId = Integer.parseInt(memberId_txt.getText());
				while(itr.hasNext())
				{
					member = itr.next();
					if(memberId == member.getId())
					{
						//bill.setMemberId(mID);
						memberIdFound = true;
						memberId_txt.setEditable(false);
						set_btn.setEnabled(false);
						member_rbtn.setEnabled(false);
						nonMember_rbtn.setEnabled(false);
						//buyAndAddToCart_btn.setEnabled(true);
						break;
					}
				}
				if(!memberIdFound)
				{
					JOptionPane.showMessageDialog(null,"Member ID not found", "Error", JOptionPane.ERROR_MESSAGE);
					memberId_txt.setText("");
					memberId_txt.grabFocus();
				}				
			}
		}
	}
}
