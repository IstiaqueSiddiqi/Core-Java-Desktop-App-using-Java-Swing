package retailBilling;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements ActionListener
{
	//declaring reference variables of window components
	JMenu inventory_menu, member_menu, admin_menu, buy_menu, logout_menu,bill_menu,placeOrder_menu,report_menu, search_menu;
	JMenuBar bar;
	JMenuItem addItem_menuItem, displayStockTable_menuItem, addMember_menuItem, updateMember_menuItem, deleteMember_menuItem, changePassword_menuItem, buyItem_menuItem, logout_menuItem,billList_menuItem,memberList_menuItem,placeOrder_menuItem,dailyReport_menuItem,dateReport_menuItem, searchByBrand_menuItem;
		
	//public constructor
	public MainWindow()
	{
		//setting title of the window
		this.setTitle("Main Retail Window");
		
		//creating objects JMenu
		inventory_menu = new JMenu("Inventory");
		member_menu = new JMenu("Member");
		admin_menu = new JMenu("Admin");
		buy_menu = new JMenu("Buy");
		logout_menu = new JMenu("Logout");
		bill_menu= new JMenu("Bill");
		placeOrder_menu=new JMenu("Place Order");
		report_menu = new JMenu("Report");
		search_menu = new JMenu("Search");
		
		//creating objects of JMenuItem
		addItem_menuItem = new JMenuItem("Add Item");
		displayStockTable_menuItem = new JMenuItem("Stock List");
		addMember_menuItem = new JMenuItem("Add Member");
		deleteMember_menuItem = new JMenuItem("Delete Member");
		updateMember_menuItem = new JMenuItem("Update Member");
		changePassword_menuItem = new JMenuItem("Change Password");
		buyItem_menuItem = new JMenuItem("Buy Item");
		logout_menuItem = new JMenuItem("LOGOUT");
		billList_menuItem = new JMenuItem("Bill List");
		memberList_menuItem = new JMenuItem("Member List");
		placeOrder_menuItem = new JMenuItem("Place Order List");
		dailyReport_menuItem = new JMenuItem("Daily Report");
		dateReport_menuItem = new JMenuItem("Date Wise Report");
		searchByBrand_menuItem = new JMenuItem("Item By Brand");
		
		
		//creating object of JMenuBar
		bar = new JMenuBar();
		
		//adding submenu to Inventory
		inventory_menu.add(addItem_menuItem);
		inventory_menu.add(displayStockTable_menuItem);
		
		//adding sub menu to Member
		member_menu.add(addMember_menuItem);
		member_menu.add(updateMember_menuItem);
		member_menu.add(deleteMember_menuItem);
		member_menu.add(memberList_menuItem);	
		
		//adding sub menu to Admin
		admin_menu.add(changePassword_menuItem);
		
		//adding menu to buy menu
		buy_menu.add(buyItem_menuItem);
		
		//adding place submenu to place menu
		placeOrder_menu.add(placeOrder_menuItem);
		
		//adding report submenu to report menu
		report_menu.add(dailyReport_menuItem);
		report_menu.add(dateReport_menuItem);
				
		logout_menu.add(logout_menuItem);
		
		bill_menu.add(billList_menuItem);
		
		search_menu.add(searchByBrand_menuItem);
		
		//adding menus to menu bar
		bar.add(inventory_menu);
		bar.add(member_menu);
		bar.add(admin_menu);
		bar.add(buy_menu);
		bar.add(bill_menu);
		bar.add(placeOrder_menu);
		bar.add(report_menu);
		bar.add(search_menu);
		bar.add(logout_menu);		
		
		//adding bar to JFrame
		add(bar, BorderLayout.NORTH);
		
		//keyboard shortcut for menus
		inventory_menu.setMnemonic(KeyEvent.VK_I);
		member_menu.setMnemonic(KeyEvent.VK_M);
		admin_menu.setMnemonic(KeyEvent.VK_A);
		buy_menu.setMnemonic(KeyEvent.VK_U);
		logout_menu.setMnemonic(KeyEvent.VK_L);
		bill_menu.setMnemonic(KeyEvent.VK_B);
		placeOrder_menu.setMnemonic(KeyEvent.VK_O);
		report_menu.setMnemonic(KeyEvent.VK_R);
		search_menu.setMnemonic(KeyEvent.VK_S);
		
		addItem_menuItem.setMnemonic(KeyEvent.VK_A);
		displayStockTable_menuItem.setMnemonic(KeyEvent.VK_S);
		addMember_menuItem.setMnemonic(KeyEvent.VK_A);
		deleteMember_menuItem.setMnemonic(KeyEvent.VK_D);
		updateMember_menuItem.setMnemonic(KeyEvent.VK_U);
		changePassword_menuItem.setMnemonic(KeyEvent.VK_P);
		buyItem_menuItem.setMnemonic(KeyEvent.VK_I);
		logout_menuItem.setMnemonic(KeyEvent.VK_O);
		billList_menuItem.setMnemonic(KeyEvent.VK_B);
		memberList_menuItem.setMnemonic(KeyEvent.VK_L);
		placeOrder_menuItem.setMnemonic(KeyEvent.VK_O);
		dailyReport_menuItem.setMnemonic(KeyEvent.VK_D);
		dateReport_menuItem.setMnemonic(KeyEvent.VK_W);
		searchByBrand_menuItem.setMnemonic(KeyEvent.VK_B);
		
		//making window visible
		this.setVisible(true);
		
		//setting size of the window
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width,screenSize.height-40);
		
		JPanel panel = new JPanel(){  
		      public void paintComponent(Graphics g) {  
		        Image image = new ImageIcon("pic.jpg").getImage();
		        g.drawImage(image, 0, 0, null);}};
		    getContentPane().add(panel);
			
		//resizing the window - disabled
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//component registration
		displayStockTable_menuItem.addActionListener(this);
		addItem_menuItem.addActionListener(this);
		
		addMember_menuItem.addActionListener(this);
		updateMember_menuItem.addActionListener(this);
		deleteMember_menuItem.addActionListener(this);
		
		changePassword_menuItem.addActionListener(this);
		buyItem_menuItem.addActionListener(this);
		logout_menuItem.addActionListener(this);
		billList_menuItem.addActionListener(this);
		memberList_menuItem.addActionListener(this);
		placeOrder_menuItem.addActionListener(this);
		dailyReport_menuItem.addActionListener(this);
		dateReport_menuItem.addActionListener(this);
		searchByBrand_menuItem.addActionListener(this);
	}
	
	//implementing actionPerformed method
	public void actionPerformed(ActionEvent e)
	{
		//action performed when Stock List MenuItem is clicked
		if(e.getSource() == displayStockTable_menuItem)
		{
			new StockListTable();
		}
		
		//action performed when Add Item is clicked
		if(e.getSource() == addItem_menuItem)
		{
			new AddExistingItem();
		}
		if(e.getSource()==dailyReport_menuItem)
		{
			new DailyReportGenrator();
		}		
		
		//action performed when Change Password is clicked
		if(e.getSource() == changePassword_menuItem)
		{
			new ChangePassword();			
		}
		
		//action performed when addMember_menuItem is selected
		if(e.getSource() == addMember_menuItem)
		{
			new AddMember();
		}
		
		//action performed when updateMember_menuItem is selected
		if(e.getSource() == updateMember_menuItem)
		{
			new UpdateMember();
		}
		
		//action performed when delete member is selected
		if(e.getSource() == deleteMember_menuItem)
		{
			new DeleteMember();
		}
		
		//action performed when clicked on buy item
		if(e.getSource() == buyItem_menuItem)
		{
			new BuyWindow();
		}
		
		//action performed when Clicked on logout 
		if(e.getSource() == logout_menuItem)
		{
			dispose();
			JOptionPane.showMessageDialog(null, "Sucessfully Logout","Logout Status",JOptionPane.INFORMATION_MESSAGE);
			
		}
		
		//action performed when clicked on billList_menuItem
		if(e.getSource() == billList_menuItem)
		{
			new BillListTable();
		}
		
		//action performed when clicked on memberList menu item
		if(e.getSource()==memberList_menuItem)
		{
			new MemberListTable();
		}
		
		//action performed when clicked on placeOrder menu item
		if(e.getSource()==placeOrder_menuItem)
		{
			new PlaceOrderTable();
		}
		
		//action performed when clicked on date wise report
		if(e.getSource() == dateReport_menuItem)
		{
			new DateReportInput();
		}
		
		//action performed when clicked on searchByBrand
		if(e.getSource() == searchByBrand_menuItem)
		{
			new SearchItem();
		}
	}
}
