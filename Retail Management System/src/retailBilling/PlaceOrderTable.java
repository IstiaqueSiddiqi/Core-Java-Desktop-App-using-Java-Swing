package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PlaceOrderTable extends JFrame
{
	private JTable order_tbl;
	private JScrollPane jsp;
	private ArrayList<Item> itemList;
	private FileHandler fileHandler;
	private Item item;
	private Object[][] data;
	private int counter = 0;
	
	public PlaceOrderTable()
	{
		//setting the title
		super("Place Order Item List");
		
		//defining the column head of the table
		String colHead[]={"Sl_No","Item Code","Brand Name","Item Name","Available Quantity"};
				
		//Reading from ItemDB file
		try
		{   fileHandler=new FileHandler();
			itemList=(ArrayList<Item>)fileHandler.readFromFile();
		}
		catch(Exception ex)
		{
			itemList=new ArrayList<Item>();
		}
		
		//counter to count no of items to be added to the table
		Iterator<Item> itr2 = itemList.iterator();
		while(itr2.hasNext())
		{
			item = itr2.next();
			if(item.getQuantity()<=5)
				counter++;
		}		
		
		//checking if reorder of item is required
		if(counter==0)
		{
			//Message
			JOptionPane.showMessageDialog(null, "No Item to Place order","Place Order Status",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			data=new Object[counter][5];
			int index=0;
			Iterator<Item> itr=itemList.iterator();
			while(itr.hasNext())
			{
				item=itr.next();
				if(item.getQuantity()<=5)
				{
					data[index][0]=index+1;
					data[index][1]=item.getItemCode();
					data[index][2]=item.getProductName();
					data[index][3]=item.getItemName();
					data[index++][4]=item.getQuantity();
				}
			}
			order_tbl=new JTable(data,colHead);
			jsp=new  JScrollPane(order_tbl);
			
			//setting the size of the table
			order_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			order_tbl.getColumnModel().getColumn(0).setPreferredWidth(75);
			order_tbl.getColumnModel().getColumn(1).setPreferredWidth(85);
			order_tbl.getColumnModel().getColumn(2).setPreferredWidth(150);
			order_tbl.getColumnModel().getColumn(3).setPreferredWidth(300);
			order_tbl.getColumnModel().getColumn(4).setPreferredWidth(115);
			
			add(jsp);
			
			//setting the size of the window
			setSize(750,550);
			
			// setting window in center of screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
			
			//resizing the window
			this.setResizable(false);
			
			//making the window visible
			setVisible(true);			
			
			//default close operation
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}		
	}	
}
