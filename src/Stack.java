/**********************************************************************************************
Class:		Stack
Created by:	Richard Steinberg
Created on: 11/21/08

Description:
	A class to implement a stack using a linked list of ListNodes.
	IMPORTANT NOTES: 
	-- The Stack class protects itself.  The is no way to call it that causes an invalid Stack.
	-- To keep this class useful in multiple applications the Stack class PRINTS NO ERROR MESSAGES.  
		After all, it doesn't know what the class that calls it wants to do or say in case of errors 
		(popping when empty.)  Only the calling class knows.  So this class is 	written so that the 
		calling class can check for error cases and handle them however it wants.
	-- Why have the size parameter in the constructor and the isFull() method?  These are here only
		so that this class is interchangeable with the array implementation of the Stack class that
		did need them.

*********************************************************************************************/
public class Stack {
	private ListNode stackTop;

	/* Constructor*/
	public Stack()
	{
		stackTop = null;
	}

	/* isEmpty - return true if empty, false otherwise. */
	public boolean isEmpty()
	{
		return stackTop == null;
	}

	/* isFull - never true for a linked list! */
	public boolean isFull()
	{
		return false;
	}

	/* push - Push s onto the stack.  Should never fail. */
	public void push(Object s)
	{
		stackTop = new ListNode(s, stackTop);
	}

	/* pop - If there is anything on the stack, pop the top and return it. */
	public Object pop()
	{
		if (!isEmpty())
		{
			Object temp = stackTop.getValue();
			stackTop = stackTop.getNext();
			return temp;
		}
		else
		{
			return null;
		}
	}

	/* toString - Convert the stack to a string and return it. */
	public String toString()
	{
		String stackAsString = "";
		ListNode current = stackTop;
		int index = 0;
		while(current != null)
		{
			stackAsString = stackAsString + index + " : " + current.getValue() + '\n';
			current = current.getNext();
			index++;
		}
		return stackAsString;
	}
}
