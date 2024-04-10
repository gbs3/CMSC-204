
/**
 * A class that provides methods to construct a TreeNode, store data of a generic type, and reference the left and right
 * child nodes.
 * @author Griffin Shay
 */

public class TreeNode<T> 
{ 
	protected T data;
	protected TreeNode<T> leftChild;
	protected TreeNode<T> rightChild;
	
	//Constructs a TreeNode with the specified data
	public TreeNode(T dataNode)
	{
		data = dataNode;
		leftChild = null;
		rightChild = null;
	}
	
	//Constructs a copy of the given TreeNode
	public TreeNode(TreeNode<T> node)
	{
		this.data = node.data;
		this.leftChild = node.leftChild;
		this.rightChild = node.rightChild;
	}
	
	//Retrieves the data stored in the TreeNode
	public T getData()
	{
		return this.data;
	}

}
