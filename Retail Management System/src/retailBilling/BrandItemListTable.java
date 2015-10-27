package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrandItemListTable extends JFrame
{
	private JTable itemList_tbl;
	private ArrayList<Item> itemList;
	private FileHandler fileHandler;
	private Object data[][];
	private int counter = 0;
	private String brandName;
	private Item item;
	private JScrollPane jsp;
	
	//public constructor
	public BrandItemListTable(String brandName)
	{
		//Setting the title of the window
		this.setTitle("Item List of a Particular Brand");
		
		//defining the brandName
		this.brandName = brandName;
		
		//reading from ItemDB
		try
		{
			fileHandler = new FileHandler();
			itemList = fileHandler.readFromFile();
		}
		catch(Exception ex)
		{
			itemList = new ArrayList<Item>();
		}
		
		//counter to count no of items should be in the table
		Iterator<Item> itr2 = itemList.iterator();
		while(itr2.hasNext())
		{
			item = itr2.next();
			
			if(brandName.equalsIgnoreCase(item.getProductName()))			
				counter++;			
		}
		
		if(counter==0)
		{
			JOptionPane.showMessageDialog(null, "No Item found","Search Status",JOptionPane.INFORMATION_MESSAGE);			
			new SearchItem();
		}
		else
		{		
			//filling the data into the table
			String colHead[] = {"Sl_No","Brand Name","Item Code", "Item Name","Unit Price","Available Quantity"};
			data = new Object[counter][6];
			
			int index = 0;
			Iterator<Item> itr = itemList.iterator();
			while(itr.hasNext())
			{
				item = itr.next();
				if(brandName.equalsIgnoreCase(item.getProductName()))
				{
					data[index][0] = index+1;
					data[index][1] = item.getProductName();
					data[index][2] = item.getItemCode();
					data[index][3] = item.getItemName();
					data[index][4] = item.getPrice();
					data[index++][5] = item.getQuantity();
				}
			}
			
			//creating object of JTable
			itemList_tbl = new JTable(data,colHead);
			itemList_tbl.setEnabled(false);
			jsp = new JScrollPane(itemList_tbl);
			
			//setting the size of the table
			itemList_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			itemList_tbl.getColumnModel().getColumn(0).setPreferredWidth(80);
			itemList_tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
			itemList_tbl.getColumnModel().getColumn(2).setPreferredWidth(80);
			itemList_tbl.getColumnModel().getColumn(3).setPreferredWidth(350);
			itemList_tbl.getColumnModel().getColumn(4).setPreferredWidth(100);
			itemList_tbl.getColumnModel().getColumn(5).setPreferredWidth(150);
			
			//adding table to JFrame
			add(jsp);
			
			//making the window visible
			this.setVisible(true);
			
			//resizing the window
			this.setResizable(false);
			
			//setting the size of the window
			this.setSize(950,550);
			
			// setting window in center of screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
			
			//default close operation
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}
	}
}
