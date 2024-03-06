import java.util.Comparator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T>
{
	private Comparator<T> comparator;
	
	public SortedDoubleLinkedList(Comparator<T> comp)
	{
		comparator = comp;
	}
	
	//Adds the specified element to the list in the correct position
	public SortedDoubleLinkedList<T> add(T data)
	{
		Node node = new Node(data);

        if (size == 0 || comparator.compare(data, head.data) <= 0) 
        {
            node.next = head;
            if (head != null) 
            {
                head.previous = node;
            } 
            else 
            {
                tail = node;
            }
            head = node;
        } 
        else 
        {
            Node current = head;
            while (current.next != null && comparator.compare(data, current.next.data) > 0) 
            {
                current = current.next;
            }
            node.next = current.next;
            if (current.next != null) 
            {
                current.next.previous = node;
            } 
            else 
            {
                tail = node;
            }
            current.next = node;
            node.previous = current;
        }

        size++;
        return this;
	}
	
	@Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

}
