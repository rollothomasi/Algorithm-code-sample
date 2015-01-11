import java.util.*;


public class stringToLong {

	public static long stringToLong(String s) throws NumberFormatException
	{
		long res=0x0000000000000000;
		int j=0;			
		if(s.charAt(0)=='-')
			//a positive or negative number
			j=1;
		for (int i=j;i<s.length();i++)
		{
			if(s.length()==0) 
				
				throw new NumberFormatException();
			if(5*res>0x3FFFFFFFFFFFFFFFl) 
				//in case that the the string is too long
				throw new NumberFormatException();
			if(s.charAt(i)<='9' && s.charAt(i)>='0')
				//in case the string is not a number	
				res=res*10+s.charAt(i)-'0';
			else 
					throw new NumberFormatException();
		}
		res=res*(1-2*j);
		return res;
	}

	public static void main(String[] args) {
        String str;
        for(;;)
        {
        	Scanner scanner=new Scanner(System.in);
        	str=scanner.nextLine();
        	try
        	{
        		if(str.length()==0)
        		{
        			System.out.println("please input a number:");
        			continue;
        			}
        	//in case input nothing
		
			long i = stringToLong(str);
			System.out.println("the number tranfered is:"+i);
			}
        	catch(NumberFormatException e)
        	{
        		System.out.println("Not a correct input number!");
        		}
        	}
        }
	}
