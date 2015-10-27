package retailBilling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler 
{
	FileInputStream fin = null;
	FileOutputStream fout = null;
	ObjectInputStream obReader = null;
	ObjectOutputStream obWriter = null;
	
	//method to write to file
	public void writeToFile(ArrayList<Item> list) throws Exception
	{
		try
		{
			fout = new FileOutputStream("ItemDB");
			obWriter = new ObjectOutputStream(fout);
			obWriter.writeObject(list);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		//closing the file
		try
		{
			if(fout!=null)
				fout.close();
			if(obWriter!=null)
				obWriter.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	//method to read from a file
	public ArrayList<Item> readFromFile() throws Exception
	{
		ArrayList<Item> list = null;
		
		try
		{
			fin = new FileInputStream("ItemDB");
			obReader = new ObjectInputStream(fin);
			list = (ArrayList<Item>)obReader.readObject();			
		}
		catch(Exception e)
		{
			throw e;
		}
		
		//closing the file
		try
		{
			if(fin!=null)
				fin.close();
			if(obReader!=null)
				obReader.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		//returning list
		return list;
	}
}
