package retailBilling;

class NumberToWords
{
	StringBuffer  abc = new StringBuffer();
    public String Words1(int number)
    {
        String s="";
        switch(number)
        {
            case 1: s="One "; break;
            case 2: s="Two "; break;
            case 3: s="Three "; break;
            case 4: s="Four "; break;
            case 5: s="Five "; break;
            case 6: s="Six "; break;
            case 7: s="Seven "; break;
            case 8: s="Eight "; break;
            case 9: s="Nine "; break;
            default :s="";
        }
        return s;
    }
    public String Words2(int num)
    {
        String s="";
        switch(num)
        {
            case 10: s="Ten "; break;
            case 20: s="Twenty "; break;
            case 30: s="Thirty "; break;
            case 40: s="Forty "; break;
            case 50: s="Fifty "; break;
            case 60: s="Sixty "; break;
            case 70: s="Seventy "; break;
            case 80: s="Eighty "; break;
            case 90: s="Ninety "; break;
            default: s="";
        }   
        return s;
    }
    
    
    public String Words3(int num)
    {
        String s="";
        switch(num)
        {
            case 11: s="Eleven "; break;
            case 12: s="Twelve "; break;
            case 13: s="Thirteen "; break;
            case 14: s="Fourteen "; break;
            case 15: s="Fifteen "; break;
            case 16: s="Sixteen "; break;
            case 17: s="Seventeen "; break;
            case 18: s="Eighteen "; break;
            case 19: s="Nineteen "; break;
            default : s="";
        }
        return s;
    }
    
    public String Words4(int num)
    {
        String s="";
        if(num > 20 && num <30 )
        {
            s="Twenty " + Words1(num%10);
        }
        else if(num > 30 && num <40)
        {
            s="Thirty " + Words1(num%10);
        }
        else if(num > 40 && num < 50)
        {
            s="Forty " + Words1(num%10);
        }
        else if(num > 50 && num <60)
        {
            s="Fifty " + Words1(num%10);
        }
        else if(num > 60 && num < 70)
        {
            s="Sixty "  + Words1(num%10);
        }
        else if(num > 70 && num <80)
        {
            s="Seventy " + Words1(num%10);
        }
        else if(num > 80 && num < 90)
        {
            s="Eighty " + Words1(num%10);
        }
        else if(num > 90 && num < 100)
        {
            s="Ninety " + Words1(num%10);
        }
        else
            s="";
        
        return s;
    }
    
   public void analysis(int num)
    {
       if(num % 10 == 0)           
    	   abc.append(Words2(num));
       else if(num > 10 && num < 20)          
    	   abc.append(Words3(num));
       else if(num > 20 && num < 100)           
    	   abc.append(Words4(num));
       else           
    	   abc.append(Words1(num));
    }
   
   int countDigits(int num)
   {
   	int count = 0;
   	while(num>0)
   	{
   		num/=10;
   		count++;
   	}
   	return count;
   }
    
   public void input(int number)
    {
        
        //checking no of digits contained in the number
        int temp = number;
        int c=countDigits(temp);
        
        
        //processing the number
        temp = number;
        while(temp > 0)
        {
            if(c>9)
            {
                System.out.println("Number is so big");
                return;
            }
            else if(c>7 && c<=9) 
            {                
                analysis(temp/10000000); 
                temp = temp - ((temp/10000000) *10000000);                
                abc.append("Crore ");
                c=countDigits(temp);
            }            
            else if(c>5 && c<=7) 
            {                
                analysis(temp/100000); 
                temp = temp - ((temp/100000) *100000);                
                abc.append("Lakh ");
                c=countDigits(temp);
            }
            else if(c>3 && c<=5)
            {
                analysis(temp/1000);
                temp = temp - ((temp/1000) *1000);                
                abc.append("Thousand ");
                c=countDigits(temp);
            }
            else if(c>2 && c<=3)
            {
                analysis(temp/100);
                temp = temp - ((temp/100) *100);                
                abc.append("Hundred ");
                c=countDigits(temp);
            }
            else
            {
                analysis(temp);
                temp = 0;
                c=countDigits(temp);
            }            
        }        
    }    
}



