package retailBilling;

import java.io.Serializable;

public class Admin implements Serializable
{
	private String password;
	
	public Admin(String password)
	{
		this.password = password;
	}

	public String getPassword() 
	{
		return password;
	}	
}
