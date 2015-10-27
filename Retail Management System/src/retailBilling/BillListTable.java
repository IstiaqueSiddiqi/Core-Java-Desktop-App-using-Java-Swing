package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BillListTable extends JFrame
{
	private JTable bill_table;
	private ArrayList<Bill> billList;
	private Bill bill;
	private BillHandler billHandler;
	private Object data [][] ;
	private JScrollPane jsp;
	
	
	//Constructor
	public  BillListTable()
	{
		//setting the title of the window
		this.setTitle("Bill List Table");
		
		billHandler = new BillHandler(); 
		String colHead[] = {"Sl_NO","Bill_NO","Date (dd/mm/yy)","Member Id","Amount"};
		try
		{
			billList = (ArrayList<Bill>)billHandler.readFromFile();
		}
		catch(Exception ex)
		{
			billList = new ArrayList<Bill>();
		}
		
		data = new Object[billList.size()][5]; 
		if(billList.size()==0)
		{
			//Message
			JOptionPane.showMessageDialog(null, "NO BILL RECORD FOUND","Error",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			Iterator<Bill> itr=billList.iterator(); 
			int index = 0;
			while(itr.hasNext())
			{
				bill=itr.next();
				
				data[index][0] = index+1;
				data[index][1] = bill.getBillNo();
				data[index][2] = bill.getDate();
				data[index][3] = bill.getMemberId();
				data[index++][4] = bill.getTotalAmount();
				
			}
			
			bill_table=new JTable(data,colHead);
			bill_table.setEnabled(false);
			jsp=new JScrollPane(bill_table);
			
			//setting the size of the table
			bill_table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			bill_table.getColumnModel().getColumn(0).setPreferredWidth(75);
			bill_table.getColumnModel().getColumn(1).setPreferredWidth(75);
			bill_table.getColumnModel().getColumn(2).setPreferredWidth(150);
			bill_table.getColumnModel().getColumn(3).setPreferredWidth(100);
			bill_table.getColumnModel().getColumn(4).setPreferredWidth(150);
			
			
			add(jsp);
			
			//making the window visible
			setVisible(true);
			
			//setting the size of the window
			setSize(600,550);
			
			// setting window in center of screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
			
			//resizing the window - disabled
			setResizable(false);
			
			//default close operation
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
		}
	}
	
/*public static  void main(String args[])
{
	new BillListTable();
}*/
}
