package retailBilling;

import java.util.ArrayList;
import java.util.Iterator;

public class LastMemberId 
{
	int lastItemCode;
	ArrayList<Member> list;
	MemberFileHandler fileHandler;
	Member member;	
		
	//public constructor
	public int getLastMemberId()
	{
		//reading data from inventory
		list = new ArrayList<Member>();
		fileHandler = new MemberFileHandler();
			
		try
		{
			list = (ArrayList<Member>)MemberFileHandler.readFromMember();
		}
		catch(Exception e)
		{
			list = new ArrayList<Member>();;
		}
			
		if(list.size() > 0)
		{
			Iterator<Member> itr = list.iterator();			
			while(itr.hasNext())
			{
				member = itr.next();
			}
			return  member.getId();
		}
		else		
			return 0;
	}
}
