package retailBilling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MemberFileHandler 
{
	public static void writeonmember(ArrayList<Member>list) throws Exception
	{
		FileOutputStream fout=null;
		ObjectOutputStream wrtob=null;
		
		try
		{
			fout=new FileOutputStream("MemberDB");
			wrtob=new ObjectOutputStream(fout);
			wrtob.writeObject(list);
		}
		
		catch(Exception e)
		{
			throw e;
		}
		try
		{
			if(fout!=null)
				fout.close();
			if(wrtob!=null)
				wrtob.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	//Reading from a file 
	
	public static ArrayList<Member> readFromMember() throws Exception
	{
		FileInputStream fin=null;
		ObjectInputStream rdob=null;
		ArrayList<Member> list=null;
	  
		try
		{
			fin=new FileInputStream("MemberDB");
			rdob=new ObjectInputStream(fin);
			list=(ArrayList<Member>)rdob.readObject();
		}
		catch(Exception e)
		{
		  throw e;
		}
		try
		{
			if(fin!=null)
				fin.close();
			if(rdob!=null)
				rdob.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		return list;
	}
}




