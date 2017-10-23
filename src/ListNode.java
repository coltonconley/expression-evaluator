/*************************************************************
 * Class:		 TreeNode
 * Created by:	Richard Steinberg
 * 
 * Description: A single node in a linked list structure, 
 * containing a value and a pointer to the next node
 **************************************************************/
public class ListNode {
	private Object value;
	private ListNode next;

	// Constructor
	public ListNode(Object initValue, ListNode initNext)
	{
		value = initValue;
		next = initNext;
	}

	// Accessors
	public Object getValue()
	{
		return value;
	}
	public ListNode getNext()
	{
		return next;
	}

	// Mutators
	public void setValue(Object theNewVal)
	{
		value = theNewVal;
	}
	public void setNext(ListNode theNewNext)
	{
		next = theNewNext;
	}
}
