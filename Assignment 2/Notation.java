
public class Notation 
{
	public Notation()
	{
		
	}
	
	//This method reads a string postfix expression and returns it as a double.
	public static double evaluatePostfixExpression​(String postfixExpr) throws InvalidNotationFormatException
	{

		MyStack<Double> stack = new MyStack<Double>(postfixExpr.length());
		
		//In the for loop below each character in string is checked.
		for(int i = 0; i < postfixExpr.length(); i++)
		{
			String str = "" + postfixExpr.charAt(i);
			//If the character is a space it is skipped.
			if(postfixExpr.charAt(i) == ' ')
			{
				continue;
			}
			//If the character is a digit it is added to the stack as a double.
			else if(Character.isDigit(postfixExpr.charAt(i)))
			{
				try 
				{
					stack.push(Double.parseDouble(str));
				}
				catch(StackOverflowException e)
				{
					throw new InvalidNotationFormatException();
				}
			}
			//If the character in the string is an operator the top two double in the stack are popped
			//and the result of the two double and operator are added to the stack.
			else if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"))
			{
				try
				{
					double one = stack.pop();
					double two = stack.pop();
					double combine = 0.0;
					switch(str)
					{
						case "+":
						{
							combine = two + one;
							break;
						}
						case "-":
						{
							combine = two - one;
							break;
						}
						case "*":
						{
							combine = two * one;
							break;
						}
						case "/":
						{
							combine = two / one;
							break;
						}
					}
					try 
					{
						stack.push(combine);
					} 
					catch (StackOverflowException e) 
					{
						throw new InvalidNotationFormatException();
					}
				}
				catch(StackUnderflowException e)
				{
					throw new InvalidNotationFormatException();
				}
			}
		}
		//Returns the end result.
		try 
		{
			return stack.top();
		}
		catch (StackUnderflowException e) 
		{
			throw new InvalidNotationFormatException();
		}
	}
	
	//This method takes a string in postfix and rewrites it as infix.
	public static String convertPostfixToInfix​(String postfix) throws InvalidNotationFormatException
	{
		
		MyStack<String> stack = new MyStack<String>(postfix.length());
		
		//The for loop goes through each character of the postfix string.
		for(int i = 0; i < postfix.length(); i++)
		{
			String str = "" + postfix.charAt(i);
			//If the character is a space it is ignored.
			if(postfix.charAt(i) == ' ')
			{
				continue;
			}
			//If the character is a digit it is added to the stack.
			else if(Character.isDigit(postfix.charAt(i)))
			{
				try 
				{
					stack.push(str);
				}
				catch(StackOverflowException e)
				{
					throw new InvalidNotationFormatException();
				}
			}
			//if the character is an operator the top two strings are popped and a new string is made
			//with the second popped string the operator and the first popped string enclosed with parenthesis.
			else if(str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/"))
			{
				try
				{
					String one = stack.pop();
					String two = stack.pop();
					String combine = "(" + two + str + one + ")";
					try 
					{
						stack.push(combine);
					} 
					catch (StackOverflowException e)
					{
						throw new InvalidNotationFormatException();
					}
				}
				catch(StackUnderflowException e)
				{
					throw new InvalidNotationFormatException();
				}
			}
		}
		//If the stack's size is larger than 1 an error is thrown otherwise the infix string is returned.
		if(stack.size() > 1)
		{
			throw new InvalidNotationFormatException();
		}
		
		return stack.toString();
	}
	
	//This method takes a string in infix and rewrites it to postfix.
	public static String convertInfixToPostfix​(String infix) throws InvalidNotationFormatException
	{
		MyStack<String> stack = new MyStack<String>(infix.length());
		MyQueue<String> postfixSolutionQueue = new MyQueue<String>(infix.length());
		
		//The for loop goes through each character of the infix string.
		for(int i = 0; i < infix.length(); i++)
		{
			String str = "" + infix.charAt(i);
			String op = " ";
			if(!stack.isEmpty())
			{
				try
				{
					op = stack.top();
				}
				catch (StackUnderflowException e)
				{
					throw new InvalidNotationFormatException();
				}
			}
			//If the character is a space ignore it.
			if(infix.charAt(i) == ' ')
			{
				continue;
			}
			//If the character is a digit add it to the queue.
			else if(Character.isDigit(infix.charAt(i)))
			{
				try
				{
					postfixSolutionQueue.enqueue(str);
				}
				catch(QueueOverflowException e) 
				{
					throw new InvalidNotationFormatException();
				}
			}
			//If the character is a left parenthesis add it to the stack.
			else if(infix.charAt(i) == '(')
			{
				try
				{
					stack.push(str);
				} 
				catch (StackOverflowException e) 
				{
					throw new InvalidNotationFormatException();
				}
			}
			//Checks if the character is an operator.
			else if(infix.charAt(i) == '+' || infix.charAt(i) == '-' || infix.charAt(i) == '*' || infix.charAt(i) == '/')
			{
				//If the character is a plus or minus operator pop operators from the stack and add them to the queue.
				//Then push the character onto the stack.
				if(infix.charAt(i) == '+' || infix.charAt(i) == '-')
				{
					while(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
					{
						try 
						{
							postfixSolutionQueue.enqueue(stack.pop());
						}
						catch(StackUnderflowException | QueueOverflowException e)
						{
							throw new InvalidNotationFormatException();
						}
					}
					try 
					{
						stack.push(str);
					} 
					catch(StackOverflowException e) 
					{
						throw new InvalidNotationFormatException();
					}
				}
				//If the character is a multiplication or division operator pop multiplication and division operators
				//from the top of the stack and add them to the queue. Then push the character onto the stack.
				else if(infix.charAt(i) == '*' || infix.charAt(i) == '/')
				{
					while(op.equals("*") || op.equals("/"))
					{
						try
						{
							postfixSolutionQueue.enqueue(stack.pop());
						} 
						catch(StackUnderflowException | QueueOverflowException e) 
						{
							throw new InvalidNotationFormatException();
						}
					}
					try 
					{
						stack.push(str);
					}
					catch(StackOverflowException e)
					{
						throw new InvalidNotationFormatException();
					}
				}
			}
			//If the character is a right parenthesis pop strings from the stack and add them to the queue until
			//a left parenthesis is on top of the stack and then pop it.
			else if(infix.charAt(i) == ')')
			{
				try 
				{
					while(!stack.isEmpty() && !stack.top().equals("("))
					{
						try 
						{
							postfixSolutionQueue.enqueue(stack.pop());
						}
						catch (QueueOverflowException e) 
						{
							throw new InvalidNotationFormatException();
						}
					}
					stack.pop();
				}
				catch (StackUnderflowException e) 
				{
					throw new InvalidNotationFormatException();
				}
			}
		}
		//While the stack is not empty pop strings from the top of the stack and add them to the queue.
		while(!(stack.isEmpty()))
		{
			try 
			{
				postfixSolutionQueue.enqueue(stack.pop());
			} 
			catch(QueueOverflowException | StackUnderflowException e) 
			{
				throw new InvalidNotationFormatException();
			}
		}
		
		//Return the queue as a string in the postfix format.
		return postfixSolutionQueue.toString();
	}
}
