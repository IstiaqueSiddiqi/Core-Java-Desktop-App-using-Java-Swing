package retailBilling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

public class AdminFileHandler 
{
	private FileInputStream fin = null;
	private ObjectInputStream obReader = null;
	
	private FileOutputStream fout = null;
	private ObjectOutputStream obWriter = null;
	
	public void writeToFile(Admin admin) throws Exception
	{
		try
		{
			fout = new FileOutputStream("AdminDB");
			obWriter = new ObjectOutputStream(fout);
			obWriter.writeObject(admin);
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
	
	//reading
	public Admin readFromFile() throws Exception
	{
		Admin admin;
		
		try
		{
			fin = new FileInputStream("AdminDB");
			obReader = new ObjectInputStream(fin);
			admin = (Admin)obReader.readObject();
		}
		catch(Exception e)
		{
			admin = new Admin("pass");
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
		return admin;
	}
}
