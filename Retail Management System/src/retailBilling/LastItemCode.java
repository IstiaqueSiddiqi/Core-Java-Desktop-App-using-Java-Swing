package retailBilling;

import java.util.ArrayList;
import java.util.Iterator;

public class LastItemCode 
{
	int lastItemCode;
	ArrayList<Item> list;
	FileHandler fileHandler;
	Item item;	
	
	public int getLastItemCode()
	{
		//reading data from inventory		
		try
		{
			fileHandler = new FileHandler();
			list = fileHandler.readFromFile();
		}
		catch(Exception e)
		{
			list = new ArrayList<Item>();
		}
		
		if(list.size() > 0)
		{
			Iterator<Item> itr = list.iterator();
			
			while(itr.hasNext())
			{
				item = itr.next();
			}
			return item.getItemCode();
		}
		else		
			return 0;
	}	
}
