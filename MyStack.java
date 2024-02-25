import java.util.ArrayList;

public class MyStack<T> implements StackInterface<T> 
{
	private ArrayList<T> stack;
	private int max;
	
	public MyStack()
	{
		max = 50;
		stack = new ArrayList<T>(max);
	}
	
	public MyStack(int size)
	{
		max = size;
		stack = new ArrayList<T>(max);
	}
	
	@Override
	public boolean isEmpty()
	{
		return stack.isEmpty();
	}
	
	@Override
	public boolean isFull()
	{
		return stack.size() == max;
	}
	
	@Override
	public T pop() throws StackUnderflowException
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		else
		{
			T poppedVar = stack.get(size() - 1);
			stack.remove(size() - 1);
			return poppedVar;
		}
	}
	
	@Override
	public T top() throws StackUnderflowException
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		else
		{
			return stack.get(size() - 1);
		}
	}
	
	@Override
	public int size()
	{
		return stack.size();
	}
	
	@Override
	public boolean push(T e) throws StackOverflowException
	{
		if(isFull())
		{
			throw new StackOverflowException();
		}
		else
		{
			return stack.add(e);
		}
	}
	
	@Override
	public String toString()
	{
		String str = "";
		for(int i = 0; i < stack.size(); i++)
		{
			str += stack.get(i);
		}
		return str;
	}
	
	@Override
	public String toString(String delimiter)
	{
		String str = "";
		for(int i = 0; i < stack.size(); i++)
		{
			str += stack.get(i);
			if(i != stack.size() - 1)
			{
				str += delimiter;
			}
		}
		return str;
	}
	
	@Override
	public void fill(ArrayList<T> list) throws StackOverflowException
	{
		ArrayList<T> listCopy = new ArrayList<T>(list);
		for(int i = 0; i < listCopy.size(); i++)
		{
			if(isFull())
			{
				throw new StackOverflowException();
			}
			push(listCopy.get(i));
		}
	}
}
