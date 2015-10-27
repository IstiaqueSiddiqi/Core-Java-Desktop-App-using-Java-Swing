package retailBilling;

import java.util.Scanner;


public class Validation 
{	
	public static boolean isValidNumber(String input)
	{
		if (input.matches("[0-9]+") && input.length() > 0)
		{
			return true;
		}
		else
			return false;		
	}
	
	public static boolean isValidPrice(String input)
	{		
		if(input.length() == 1 && input.indexOf('.')==0)
			return false;
		
		if((input.indexOf('.')) == input.lastIndexOf('.'))
		{
			if(input.matches("[0-9.]+") && input.length() > 0)
				return true;
			else
				return false;
		}
		else
			return false;
			
	}
	
	public static boolean isValidName(String input)
	{
		if(!input.matches("[0-9]+") && input.length() > 0)
			return true;
		else
			return false;
	}
	
}
