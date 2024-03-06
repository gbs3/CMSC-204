import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

public class BasicDoubleLinkedList<T> implements Iterable<T> 
{
	protected int size;
	protected Node head;
	protected Node tail;
	
	//Constructor that initializes variables
	public BasicDoubleLinkedList()
	{
		size = 0;
		head = null;
		tail = null;
	}
	
	//Returns the size of the list
	public int getSize()
	{
		return size;
	}
	
	//Adds an element to the end of the list
	public void addToEnd(T data)
	{
		Node node = new Node(data);
        if (tail != null) 
        {
            node.previous = tail;
            tail.next = node;
        } 
        else 
        {
            head = node;
        }
        tail = node;
        size++;
	}
	
	//Adds an element to the front of the list
	public void addToFront(T data)
	{
		Node node = new Node(data);
		if(head != null)
		{
			node.next = head;
			head.previous = node;
		}
		else
		{
			tail = node;
		}
		head = node;
		size++;
	}
	
	//Returns the first item in the list or null if its empty
	public T getFirst()
	{
		if(head == null)
		{
			return null;
		}
		else
		{
			return head.data;
		}
	}
	
	//Returns the last item in the list or null if its empty
	public T getLast()
	{
		return tail.data;
	}
	
	//Returns a DoubleLinkeListIterator object
	@Override
	public ListIterator<T> iterator()
	{
		return new DoubleLinkedListIterator();
	}
	
	//Removes the first instance of data specified on input of the method from the list
	public Node remove(T targetData, Comparator<T> comparator)
	{
		Node node = head;
		
		while(node != null)
		{
			if(comparator.compare(node.data, targetData) == 0)
			{
				if(node.previous == null)
				{
					head = node.next;
				}
				else if(node.next == null)
				{
					tail = node.previous;
				}
				else
				{
					node.previous.next = node.next;
					node.next.previous = node.previous;
				}
				size--;
				return node;
			}
			node = node.next;
		}
		return null;
	}
	
	//Removes and returns the first element in the list
	public T retrieveFirstElement()
	{
		if(head == null)
		{
			return null;
		}
		else
		{
			Node node = head;
			head = head.next;
			head.previous = null;
			size--;
			return node.data;
		}
	}
	
	//Removes and returns the last element in the list
	public T retrieveLastElement()
	{
		if(tail == null)
		{
			return null;
		}
		else
		{
			Node node = tail;
			tail = tail.previous;
			tail.previous = null;
			size--;
			return node.data;
		}
	}
	
	//Puts the elements of the list into an ArrayList and returns the ArrayList
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> list = new ArrayList<>();
		Node node = head;
		for(int i = 0; i < size; i++)
		{
			list.add(node.data);
			node = node.next;
		}
		return list;
	}
	
	//A inner class to specify the Node object
	protected class Node
	{
		protected T data;
		protected Node next;
		protected Node previous;
		
		public Node(T data)
		{
			this.data = data;
			this.next = null;
			this.previous = null;
		}
	}
	
	//A inner class to specify the DoubleLinkeListIterator Object
	protected class DoubleLinkedListIterator implements ListIterator<T>
	{
		protected Node node = head;
		protected Node temp ;
		

		@Override
		public boolean hasNext() 
		{
			if(node == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}

		@Override
		public T next() 
		{
			if(hasNext())
			{
				temp = node;
				node = node.next;
				return temp.data;
			}
			throw new NoSuchElementException();
		}

		@Override
		public boolean hasPrevious() 
		{
			if(temp == null)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		
		//Check to make sure it might be node.previous = node.previous
		@Override
		public T previous() 
		{
			if(hasPrevious())
			{
				node = temp;
				temp = temp.previous;
				return node.data;
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() 
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() 
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() 
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) 
		{
			throw new UnsupportedOperationException();
		}

		@Override
		public void add(T e) 
		{
			throw new UnsupportedOperationException();
		}
	}

}
