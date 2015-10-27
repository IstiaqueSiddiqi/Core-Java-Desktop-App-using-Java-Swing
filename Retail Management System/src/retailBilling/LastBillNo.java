package retailBilling;

import java.util.ArrayList;
import java.util.Iterator;

public class LastBillNo 
{
	private BillHandler billHandler;
	private ArrayList<Bill> billList;
	private Bill bill;	
	
	public int getLastBillNo()
	{
		billHandler = new BillHandler();
		
		//getting billList
		try
		{
			billList = (ArrayList<Bill>)billHandler.readFromFile();
		}
		catch(Exception e)
		{
			billList = new ArrayList<Bill>();
		}
		
		if(billList.size() == 0)
			return 0;
		else
		{
			Iterator<Bill> itr = billList.iterator();
			while(itr.hasNext())
			{
				bill = itr.next();
			}
			return bill.getBillNo();
		}		
	}
}
