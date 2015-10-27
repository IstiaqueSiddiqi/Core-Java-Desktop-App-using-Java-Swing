package retailBilling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BoughtListFileHandler 
{
	private FileInputStream fin = null;
	private FileOutputStream fout = null;
	private ObjectInputStream obReader = null;
	private ObjectOutputStream obWriter = null;
	private ArrayList<Item> boughtList;
	
	public void writeToFile(ArrayList<Item> boughtList) throws Exception
	{
		//opening the file to write
		try
		{
			fout = new FileOutputStream("BoughtListDB");
			obWriter = new ObjectOutputStream(fout);
			obWriter.writeObject(boughtList);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		//closing the file
		try
		{
			if(fout != null)
				fout.close();
			if(obWriter != null)
				obWriter.close();
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public ArrayList<Item> readFromFile() throws Exception
	{
		//opening the file
		try
		{
			fin = new FileInputStream("BoughtListDB");
			obReader = new ObjectInputStream(fin);
			boughtList = (ArrayList<Item>)obReader.readObject();
		}
		catch(Exception e)
		{
			throw e;
		}
		
		//closing the file
		try
		{
			if(fin != null)
				fin.close();
			if(obReader != null)
				obReader.close();
		}
		catch(Exception e)
		{
			throw e;
		}
		return boughtList;
	}
}
