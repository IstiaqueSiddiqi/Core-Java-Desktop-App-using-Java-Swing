package retailBilling;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DailyReportGenrator extends JFrame
{
	private JTable dailyReport_tbl;
	private ArrayList<Bill> billList;
	private JScrollPane jsp;
	private Bill bill;
	private Object[][] data;
	private BillHandler billHandler;	
	private Date date;
	private DateFormat dt;
	int counter = 0;
	
	//Constructor
	public DailyReportGenrator()
	{
		super("Daily Report Table");
		
		//getting date
		date = new Date();
				
		// SHORT Format
		dt=DateFormat.getDateInstance (DateFormat.SHORT);
		String sDate=dt.format(date);
		
		String colHead[] = {"Sl_NO","Date (dd/mm/yy)","Bill_NO","Member Id","Amount"};
		
		//Reading From BillDB file
		try
		{
			billHandler=new BillHandler();
			billList=(ArrayList<Bill>)billHandler.readFromFile();
		}
		catch(Exception ex)
		{
			billList=new ArrayList<Bill>();
		}
		
		//counter to count no of valid dates matched
		Iterator<Bill> itr2 = billList.iterator();
		while(itr2.hasNext())
		{			
			bill = itr2.next();
			if(sDate.equals(bill.getDate()))
				counter++;
		}		
	
		if(counter==0)
		{
			//Message
			JOptionPane.showMessageDialog(null, "No record found","Report Status",JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			int index=0;
			data=new Object[counter][5];
			Iterator<Bill> itr = billList.iterator();
			while(itr.hasNext())
			{
				bill=itr.next();
				if(sDate.equals(bill.getDate()))
				{
					data[index][0]=index+1;
					data[index][1]=bill.getDate();
					data[index][2]=bill.getBillNo();
					data[index][3]=bill.getMemberId();
					data[index++][4]=bill.getTotalAmount();
				}
			}
			dailyReport_tbl = new JTable(data,colHead);
			dailyReport_tbl.setEnabled(false);
			jsp = new JScrollPane(dailyReport_tbl);
			
			//setting the size of the table
			dailyReport_tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			dailyReport_tbl.getColumnModel().getColumn(0).setPreferredWidth(75);
			dailyReport_tbl.getColumnModel().getColumn(1).setPreferredWidth(150);
			dailyReport_tbl.getColumnModel().getColumn(2).setPreferredWidth(75);
			dailyReport_tbl.getColumnModel().getColumn(3).setPreferredWidth(100);
			dailyReport_tbl.getColumnModel().getColumn(4).setPreferredWidth(150);
			add(jsp);
			
			//setting the size of the window
			setSize(600,550);
			
			// setting window in center of screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2);
			
			//making the window visible
			setVisible(true);
			
			//resizing the window
			this.setResizable(false);
			
			//default close operation
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}		
	}
}
