import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A utility class for converting Morse code to English text from a string or file using a MorseCodeTree
 * @author Griffin Shay
 */

public class MorseCodeConverter 
{
	private static MorseCodeTree tree = new MorseCodeTree();
	
	public MorseCodeConverter()
	{
		
	}
	
	//Prints the MorseCodeTree as a string
	public static String printTree()
	{
		String str = "";
		ArrayList<String> list = tree.toArrayList();
        for (int i = 0; i < list.size(); i++) 
        {
            str += list.get(i) + " ";
        }
        return str.trim();
	}
	
	//Converts Morse code to English text
	public static String convertToEnglish(String code)
	{
		String str = "";
		String[] words = code.split(" / ");
		
		for (String word : words) 
		{
			String[] letters = word.split(" ");
			for (String letter : letters) 
			{
				str += MorseCodeConverter.tree.fetch(letter);
			}
			str += " ";
		}
		
		return str.trim();
	}
	
	//Converts Morse code from a file to English text
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(codeFile);
		String str = "";
		while (scanner.hasNextLine()) 
		{
			str += scanner.nextLine();
		}
		scanner.close();
		
		return convertToEnglish(str);
	}

}
