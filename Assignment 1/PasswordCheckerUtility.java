import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/*
	This class checks passwords to see if they are the the appropriate length, have a number, have an uppercase letter,
	have a lowercase letter, have a special character, and that it doesn't have three of the same characters in a row.
	It also can compare two passwords to see if they match and check a list of passwords and return a list of invalid ones.
*/
public class PasswordCheckerUtility 
{
	//Checks if passwords match and throws UnmatchedException if passwords don't match
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
	{
		password.compareTo(passwordConfirm);
	}
	
	//Checks if passwords match and throws UnmatchedException if passwords don't match
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) throws UnmatchedException
	{
		if(password.compareTo(passwordConfirm) == 0)
		{
			return true;
		}
		else
		{
			throw new UnmatchedException();
		}
	}
	
	//Checks if password is to short and throws Length exception if it less than 6 characters
	public static boolean isValidLength(String password) throws LengthException
	{
		if(password.length() < 6)
		{
			throw new LengthException();
		}
		else
		{
		return true;
		}
	}
	
	//Checks if password contains uppercase letter and throws NoUpperAlphaException if it doesn't
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
	{
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isUpperCase(password.charAt(i)))
			{
				return true;
			}
		}
		throw new NoUpperAlphaException();
	}
	
	//Checks if password contains a lowercase letter and throws NoLowerAlphaException if it doesn't
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
	{
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isLowerCase(password.charAt(i)))
			{
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}
	
	//Checks if password contains a number and throws NoDigitException if it doesn't
	public static boolean hasDigit(String password) throws NoDigitException
	{
		for(int i = 0; i < password.length(); i++)
		{
			if(Character.isDigit(password.charAt(i)))
			{
				return true;
			}
		}
		throw new NoDigitException();
	}
	
	//Checks if password contains a special character and throws NoSpecialCharacterException if it doesn't
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
	{
		String reg="[a-zA-Z0-9]*";
		Pattern pat=Pattern.compile(reg);
		Matcher mat=pat.matcher(password);
		
		if(mat.matches())
		{
			throw new NoSpecialCharacterException();
		}
		else
		{
			return true;
		}
	}
	
	//Checks if password has three of the same character in a row and throws InvalidSequenceException if it does
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException
	{
		Pattern pat=Pattern.compile("^.*(.)\\1\\1.*$");
		Matcher mat=pat.matcher(password);
		if(mat.find())
		{
			throw new InvalidSequenceException();
		}
		else
		{
			return true;
		}
	}
	
	//Checks if the password is the appropriate length, has a number, has an uppercase letter, has a lowercase letter,
	//has a special character, and that it doesn't have three of the same characters in a row
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, 
		NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		if(isValidLength(password) && hasDigit(password) && hasUpperAlpha(password) && hasLowerAlpha(password)
				&& hasSpecialChar(password) && NoSameCharInSequence(password))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//Checks if password is between 6 and 9 characters and throws WeakPasswordException if it is
	public static boolean isWeakPassword(String password) throws WeakPasswordException
	{
		if(password.length() > 5 && password.length() < 10)
		{
			throw new WeakPasswordException();
		}
		else
		{
			return false;
		}
	}
	
	//Checks if passwords in an ArrayList are valid and returns a list of invalid passwords
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
	{
		ArrayList<String> invalidPasswords = new ArrayList<String>();
		String passwordCheck = null;
		for (int i = 0;i < passwords.size();i++) 
		{	
			passwordCheck = passwords.get(i);
			try 
			{
				isValidPassword(passwordCheck);
			}
			catch(Exception e) 
			{
				invalidPasswords.add(passwordCheck + " " + e.getMessage());
			}
		}
		return invalidPasswords;
	}	
}
