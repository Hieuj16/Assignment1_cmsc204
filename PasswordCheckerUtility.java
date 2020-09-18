import java.util.ArrayList;
import java.util.regex.*;
/**
 * @author Hieu Tran
 */
public class PasswordCheckerUtility {
	 public PasswordCheckerUtility() {
	    }
	 
	 public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {

			if((password.equals(passwordConfirm)))
					return true;
			else
					return false;
				
		}
	 
	 public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException,
	 NoLowerAlphaException, NoDigitException, InvalidSequenceException, NoSpecialCharacterException
		{
		 if (password.length() < 6) 
		 {
	            throw new LengthException();           // Length exception
		}
		 
		 for (int i = 0; i < password.length(); i++)
		 {
	            if (Character.isUpperCase(password.charAt(i)))
	            {
	                break;
	            }
	            if(i == password.length()-1 ) {
	            	throw new  NoUpperAlphaException();         //No Upper case ALpha exception
	            }
	        }
		 
		 for (int i = 0; i < password.length(); i++) 
		 {
	            if (Character.isLowerCase(password.charAt(i)))
	            {
	                break;
	            } 
	            if (i == password.length() - 1)
                {
                    throw new NoLowerAlphaException();			// No Lower Alpha Exception
                }
	        }
		 
		 for (int i = 0; i < password.length(); i++)
		 {
	            if (Character.isDigit(password.charAt(i))) 
	            {
	               break;
	            } 
	            if (i == password.length() - 1) 
                {
                    throw new NoDigitException();			//No Digit Exception
                }
	        }
		 
		 int valid = password.length();
	        for (int i = 0; i < valid; i++)
	        {
	            if ((i + 1 < valid) && (i + 2 < valid)) 
	            {
	                if ((password.charAt(i) == password.charAt(i + 1) && password.charAt(i + 1) == password.charAt(i + 2)))
	                		{
	                    throw new InvalidSequenceException();		// Invalid Sequence Exception
	                }
	            }
	        }
	        
	        Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
			Matcher matcher = pattern.matcher(password);
			if(matcher.matches())
				throw new NoSpecialCharacterException(); // Special character
	       
			
			return true;
	    }
	
	 public static boolean isWeakPassword(String password)
		{
			boolean valid = true;
			if ( password.length() > 5 && password.length() < 10)
			{
				valid = false;
			}
			if (valid)
			{
				return false;
			}
			else
				return true;
		}
	
	 public static ArrayList<String> invalidPasswords(ArrayList<String> passwords)  
		{
			ArrayList<String> invalidPasswords = new ArrayList<String>();
			for (String pw : passwords)
			{
				try {
					isValidPassword(pw);
				}
				catch (LengthException | NoUpperAlphaException | NoLowerAlphaException | NoDigitException 
						| InvalidSequenceException| NoSpecialCharacterException e) {
					invalidPasswords.add(pw + " " + e.getMessage()+"\n");
				}
			}
			return invalidPasswords;
		}
	 
	 public static ArrayList<String> getInvalidPasswords (ArrayList<String> passwords) {
			ArrayList<String> invalid = new ArrayList<>();
			
			for (String pw: passwords) 
			{
				try{
					isValidPassword(pw);
					}
				catch(Exception e) {
					invalid.add(pw +" -> " +e.getMessage());
				}
					
			}
			
			return invalid;
		}
	
}
 