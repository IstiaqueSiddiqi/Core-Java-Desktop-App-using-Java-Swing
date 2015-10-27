package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StockListTable extends JFrame
{
	JTable stockListTable;
	JScrollPane jsp;
	String colHeads[];
	Object data[][];
	FileHandler fileHandler;
	ArrayList<Item> list = null;
	
	//public constructor
	public StockListTable()
	{
		//setting the title of the window
		this.setTitle("Stock List");
		
		//defining columns of table
		String colHeads [] = {"Item Code","Product","Name","Price","Available Items"};
				
		//creating object of FileHandler
		fileHandler = new FileHandler();
		
		try
		{
			list = fileHandler.readFromFile();
		}
		catch(Exception e)
		{
			//creating object of ArrayList when no record found
			list = new ArrayList<Item>();
		}
		
		//defining the size of data[][]
		data = new Object[list.size()][5];
		
		//setting index
		int i=0;
		
		//defining data[][]
		for(Item ref : list)
		{
			data[i][0] = ref.getItemCode();
			data[i][1] = ref.getProductName();
			data[i][2] = ref.getItemName();
			data[i][3] = ref.getPrice();
			data[i++][4] = ref.getQuantity();
		}
		
		//creating object of JTable
		stockListTable = new JTable(data, colHeads);
		stockListTable.setEnabled(false);
		//creating object of JScrollPane
		jsp =new JScrollPane(stockListTable);
		
		//setting the size of the table
		stockListTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		stockListTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		stockListTable.getColumnModel().getColumn(1).setPreferredWidth(150);
		stockListTable.getColumnModel().getColumn(2).setPreferredWidth(300);
		stockListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		stockListTable.getColumnModel().getColumn(4).setPreferredWidth(100);
		
		//adding jsp to JFrame
		add(jsp);
		
		//making the window visible
		this.setVisible(true);
		
		//setting the size of the window
		this.setSize(775,550);
		
		// setting window in center of screen
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
		
		//resizing the window - disabled
		this.setResizable(false);
		
		//default close operation
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);				
	}	
}
