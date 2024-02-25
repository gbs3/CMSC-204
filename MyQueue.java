import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T>
{
	private ArrayList<T> queue;
	private int max;
	
	public MyQueue()
	{
		max = 50;
		queue = new ArrayList<T>(max);
	}
	
	public MyQueue(int size)
	{
		max = size;
		queue = new ArrayList<T>(max);
	}
	
	@Override
	public boolean isEmpty()
	{
		return queue.isEmpty();
	}
	
	@Override
	public boolean isFull()
	{
		return queue.size() == max; 
	}
	
	@Override
	public T dequeue() throws QueueUnderflowException
	{
		if(isEmpty())
		{
			throw new QueueUnderflowException();
		}
		else
		{
			T dequeueVar = queue.get(0);
			queue.remove(0);
			return dequeueVar;
		}
	}
	
	@Override
	public int size()
	{
		return queue.size();
	}
	
	@Override
	public boolean enqueue(T e) throws QueueOverflowException
	{
		if(isFull())
		{
			throw new QueueOverflowException();
		}
		else
		{
			return queue.add(e);
		}
	}
	
	@Override
	public String toString()
	{
		String str = "";
		for(int i = 0; i < queue.size(); i++)
		{
			str += queue.get(i);
		}
		return str;
	}
	
	@Override
	public String toString(String delimiter)
	{
		String str = "";
		for(int i = 0; i < queue.size(); i++)
		{
			str += queue.get(i);
			if(i != queue.size() - 1)
			{
				str += delimiter;
			}
		}
		return str;
	}
	
	@Override
	public void fill(ArrayList<T> list) throws QueueOverflowException
	{
		ArrayList<T> listCopy = new ArrayList<T>(list);
		for(int i = 0; i < listCopy.size(); i++)
		{
			if(isFull())
			{
				throw new QueueOverflowException();
			}
			enqueue(listCopy.get(i));
		}
	}
}
