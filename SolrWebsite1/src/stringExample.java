
public class stringExample {

	public static int distance (String a) {
		int foo = 0;
		try{
		// TODO Auto-generated method stub
		String Value = "null";
		
		if(a.matches(".*\\d+.*")){
			//System.out.println("true");
			if(a.contains("miles"))
				 Value = "miles";
				 else if (a.contains("kilometers"))
					 Value = "kilometers";
				 else if (a.contains("km"))
					 Value = "km";
				 else if (a.contains("meters"))
					 Value = "meters";
				 else if (a.contains("kms"))
					 Value = "kms";
				 else if (a.contains("m"))
					 Value = "m";
			if(Value.equals("null")){
				//System.out.println("Not distance");
				foo = 0;
			}
			
			int newIndex = a.indexOf(Value);
			String newString = a.substring(0, newIndex-1);
			int endIndex = newString.lastIndexOf(" ");
			String numberString = newString.substring(endIndex+1);
			foo = Integer.parseInt(numberString);
			System.out.println(foo);
		//	foo = 0;
			}
		
		else
			//System.out.println("false");
			{;}

	}catch(Exception e){
		//System.out.println("Wrong");
	}
		return foo;
	} 
	
}
