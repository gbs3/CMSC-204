
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;
	String password1, password2;

	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<String>();
		String [] password = {"H#LLOW0RLD","Muuufins4U$","x3s$","l5#867$","fl@w3rs","sH0r#",
				"MuffinM@n","Aut0man","NoNumb#r","tooo0M@ny"};
		passwords.addAll(Arrays.asList(password));
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
		      PasswordCheckerUtility.isValidPassword("x3s$");
		      assertTrue("Did not throw lengthException", false);
		    } catch (LengthException e) {
		      assertTrue("Successfully threw a lengthExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides lengthException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
		      PasswordCheckerUtility.isValidPassword("l5#867$");
		      assertTrue("Did not throw NoUpperAlphaException", false);
		    } catch (NoUpperAlphaException e) {
		      assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
		      PasswordCheckerUtility.isValidPassword("H#LLOW0RLD");
		      assertTrue("Did not throw NoLowerAlphaException", false);
		    } catch (NoLowerAlphaException e) {
		      assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides NoLowerAlphaException", false);
		    }
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		 try
		 {
		     PasswordCheckerUtility.isWeakPassword("Moonm3$");
		     assertTrue("Did not throw WeakPasswordException", false);
		 }
		 catch (WeakPasswordException e)
		 {
			 assertTrue("Succesfully threw a WeakPasswordException", true);
		 }
		 catch (Exception e)
		 {
		     System.out.println(e.getMessage());
		     assertTrue("Threw some incorrect exception", false);
		 }
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
		      PasswordCheckerUtility.isValidPassword("Muuufins4U$");
		      assertTrue("Did not throw an InvalidSequenceException", false);
		    } catch (InvalidSequenceException e) {
		      assertTrue("Successfully threw an InvalidSequenceExcepetion", true);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some other exception besides an InvalidSequenceException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
		      PasswordCheckerUtility.isValidPassword("MuffinM@n");
		      assertTrue("Did not throw a NoDigitException", false);
		    } catch (NoDigitException e) {
		      assertTrue("Successfully threw a NoDigitException", true);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some other exception besides a NoDigitException", false);
		    }
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("ZT5rstpxq?"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("%tbXiM7Z"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("M3@NWh1Le!"));
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("Ip@3veryD@y"));
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some incorrect exception", false);
		    }
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwords);
		
		Scanner scan = new Scanner(results.get(0)); 
		assertEquals(scan.next(), "H#LLOW0RLD");
		String nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("lowercase"));
		
		scan = new Scanner(results.get(1));  
		assertEquals(scan.next(), "Muuufins4U$");
		nextResults = scan.nextLine().toLowerCase(); 
		assertTrue(nextResults.contains("sequence"));
		
		 
		scan = new Scanner(results.get(2));  
		assertEquals(scan.next(), "x3s$");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long"));
		
		scan = new Scanner(results.get(3));  
		assertEquals(scan.next(), "l5#867$");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase"));
		
		scan = new Scanner(results.get(4));  
		assertEquals(scan.next(), "fl@w3rs");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("uppercase") );
		
		 
		
		scan = new Scanner(results.get(5));  
		assertEquals(scan.next(), "sH0r#");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("long") );
		
		scan = new Scanner(results.get(6));  
		assertEquals(scan.next(), "MuffinM@n");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(7));  
		assertEquals(scan.next(), "Aut0man");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("special") );
		
		
		scan = new Scanner(results.get(8)); 
		assertEquals(scan.next(), "NoNumb#r");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("digit") );
		
		scan = new Scanner(results.get(9));  
		assertEquals(scan.next(), "tooo0M@ny");
		nextResults = scan.nextLine().toLowerCase();
		assertTrue(nextResults.contains("sequence") );
	}
	
}
