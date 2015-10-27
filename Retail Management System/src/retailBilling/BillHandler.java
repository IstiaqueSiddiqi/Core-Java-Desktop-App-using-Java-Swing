package retailBilling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class BillHandler 
{
	private FileInputStream fin = null;
	private FileOutputStream fout = null;
	
	private ObjectInputStream obReader = null;
	private ObjectOutputStream obWriter = null;
	
	private ArrayList<Bill> billList;
	
	public void writeToFile(ArrayList<Bill> billList) throws Exception
	{
		try
		{
			fout = new FileOutputStream("BillDB");
			obWriter = new ObjectOutputStream(fout);
			obWriter.writeObject(billList);
		}
		catch(Exception e)
		{
			throw e;
		}
		
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
	
	public ArrayList<Bill> readFromFile() throws Exception
	{
		try
		{
			fin = new FileInputStream("BillDB");
			obReader = new ObjectInputStream(fin);
			this.billList = (ArrayList<Bill>)obReader.readObject();
		}
		catch(Exception e)
		{
			throw e;
		}
		
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
		
		return billList;
	}
}
