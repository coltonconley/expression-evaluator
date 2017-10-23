/*************************************************************
 * Token Class
 * 
 * Description: Each token holds a type (int), number (double),
 * and an operation (int).  The class provides all the numbers
 * necessary to interpret the type and operation contained in 
 * a token, and a toString() method is provided which displays 
 * the type and either the number or operator, as appropriate.
 **************************************************************/
public class Token {

	public static final int EMPTY = 0;
	// Types
	public static final int NUMBER = 1; 
	public static final int UNARY = 2; 
	public static final int BINARY = 3; 
	public static final int END = 4;

	// Unary Ops
	public static final int PLUS = 5; 
	public static final int MINUS = 6;
	public static final int SIN = 11;
	public static final int COS = 12;
	public static final int TAN = 13;

	// Binary Ops
	public static final int ADD = 7;
	public static final int SUBTRACT = 8; 
	public static final int MULTIPLY = 9; 
	public static final int DIVIDE = 10;

	private int type;//must be 1-4

	private double num;//any double

	private int op;//must be 5-13

	Token(int newType, double number, int operation)
	{
		type = newType;
		num = number;
		op = operation;
	}

	/**
	 * setters and getters
	 */
	public int getType()
	{
		return type;
	}

	public void setType(int newType)
	{
		type = newType;
	}

	public double getNum()
	{
		return num;
	}

	public void setNum(double newNum)
	{
		num = newNum;
	}

	public int getOp()
	{
		return op;
	}

	public void setOp(int newOp)
	{
		op = newOp;
	}

	/**
	 * Returns a string containing the token type
	 * and token value
	 */
	public String toString()
	{
		if(type == NUMBER)
			return "#, " + num;
		else if(type == UNARY)
			if(op == PLUS)
				return "u, +";
			else if(op == MINUS)
				return "u, -";
			else if(op == SIN)
				return "u, sin";
			else if(op == COS)
				return "u, cos";
			else
				return "u, tan";
		else if(type == BINARY)
			if(op == ADD)
				return "b, +";
			else if(op == SUBTRACT)
				return "b, -";
			else if(op == MULTIPLY)
				return "b, *";
			else
				return "b, /";
		else
			return "end";
	}
}
