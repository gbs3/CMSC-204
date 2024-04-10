import java.util.ArrayList;

/**
 * A class that provides methods to construct a MorseCodeTree using strings of Morse code and the corresponding letters.
 * It allows the user to fetch the letter based on Morse code and convert the tree to an ArrayList of strings.
 * @author Griffin Shay
 */

public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
	private TreeNode<String> root = new TreeNode<>("");
	
	//Constructor that builds a MorseCodeTree
	public MorseCodeTree()
	{
		buildTree();
	}
	
	//Retrieves the root node of the MorseCodeTree
	public TreeNode<String> getRoot()
	{
		return root;
	}
	
	//Sets the root node of the MorseCodeTree
	public void setRoot(TreeNode<String> newNode)
	{
		root = newNode;
	}
	
	//Inserts a Morse code string and it's corresponding letter into the tree
	public void insert(String code, String letter)
	{
		addNode(root, code, letter);
	}
	
	//Recursively adds a node to the MorseCodeTree based on the Morse code
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		if(code.length() == 1)
		{
			if(code.charAt(0) == '.')
			{
				root.leftChild = new TreeNode<String>(letter);
			}
			else if(code.charAt(0) == '-')
			{
				root.rightChild = new TreeNode<String>(letter);
			}
		}
		else
		{
			if(!(code.isEmpty()) && code.charAt(0) == '.')
			{
				addNode(root.leftChild, code.substring(1), letter);
			}
			else if(!(code.isEmpty()) && code.charAt(0) == '-')
			{
				addNode(root.rightChild, code.substring(1), letter);
			}
		}
	}
	
	//Fetches the letter corresponding to a given Morse code
	public String fetch(String code)
	{
		return fetchNode(root, code);
	}
	
	//Recursively fetches the letter corresponding to a given Morse code
	public String fetchNode(TreeNode<String> root, String code)
	{
		if(code.length() == 1)
		{
			if(code.charAt(0) == '.')
			{
				return root.leftChild.data;
			}
			else if(code.charAt(0) == '-')
			{
				return root.rightChild.data;
			}
		}
		else
		{
			if(!(code.isEmpty()) && code.charAt(0) == '.')
			{
				return fetchNode(root.leftChild, code.substring(1));
			}
			else if(!(code.isEmpty()) && code.charAt(0) == '-')
			{
				return fetchNode(root.rightChild, code.substring(1));
			}
		}
		return null;
	}
	
	
	public MorseCodeTree delete(String data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
	
	public MorseCodeTree update() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}
	
	//Builds a MorseCodeTree by inserting Morse codes and the corresponding letters
	public void buildTree()
	{	
		insert("", "");
		
		insert(".", "e");
		insert("-", "t");
		
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
		
	}
	
	//Converts the MorseCodeTree to an ArrayList of strings
	public ArrayList<String> toArrayList()
	{
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(root, list);
		return list;
	}
	
	//Performs an inorder traversal of the tree and populates the ArrayList
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list)
	{
		if(root != null)
		{
			if(root.leftChild != null)
			{
				LNRoutputTraversal(root.leftChild, list);
			}
			list.add(root.data);
			if(root.rightChild != null)
			{
				LNRoutputTraversal(root.rightChild, list);
			}
		}
	}

}
