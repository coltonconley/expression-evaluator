import java.util.Scanner;
/******************************************************************************
*
* Name: Colton Conley
* Block: A
* Date: 3/26/15
*
* Expression Evaluator
* Description:
* The objective of this program was to create an RPN expression evaluator, using
* tokens inputed by the user. At the start of the program instructions are printed, 
* and from there the user enters the desired expression to be solved. Any time
* an invalid command is entered, the user will be prompted to try again.  If the 
* user attempts to enter an invalid expression, (something is still on the stack)
* they will be prompted to enter a new expression.
* 
* EXTRAS: This program has additional unary operations: sin, cos, and tan. 
* please note that given the nature of doubles, these operations are not 100
* percent accurate i.e. tan(45) = .9999999.  Additionally, angles are to be entered
* in degrees.
******************************************************************************/
public class ExpressionEvaluator {

	public static Scanner input = new Scanner(System.in);//collects all user input
	public static void main(String[] args) 
	{
		boolean done = false;
		printInstructions();
		while (!done)
		{
			System.out.println("Please enter your expression here:");
			ExpressionTree expressionTree = getExpressionTree();
			
			//print expression tree
			System.out.println(expressionTree.toStringPostOrder());
			
			//evaluate and print result
			System.out.println("Your answer is: " + expressionTree.evaluate());
			
			//check to see if user is finished
			done = userIsDone();
		}

		System.out.println("Thank you for using the Expression Evaluator!");
	}

	/**
	 * Prints instructions for user's convenience 
	 */
	private static void printInstructions()
	{
		System.out.println("Welcome to the Expression Evaluator!"
				+ "\nPlease enter a token, starting with the token type and"
				+ "\nthen the value. When you are finished, enter an e to end."
				+ "\nNOTE: Special unary operations include:"
				+ "\nSine -> sin"
				+ "\nCosine -> cos"
				+ "\nTangent -> tan"
				+ "\n");
	}

	/**
	 * Reads in user input to compile an expression tree.
	 * 
	 * @return		expression tree created by user
	 */
	private static ExpressionTree getExpressionTree()
	{
		Stack stack = new Stack();
		boolean loop = true;
		while(loop)
		{
			loop = addToken(stack);
		}
		ExpressionTree tree = (ExpressionTree) stack.pop();
		
		//check to see if user entered a valid expression
		if(stack.isEmpty())
		{
			return tree;
		}
		else
		{
			System.out.println("Uh oh! Your expression wasn't complete. "
					+ "\nPlease try again");
			return getExpressionTree();
		}
	}

	/**
	 * Adds another token to the expression tree, depending on user input
	 * 
	 * @param stack	Stack object which holds parts of the expression tree
	 * @return
	 */
	private static boolean addToken(Stack stack)
	{
		Token newToken = getToken();
		if(newToken.getType() == Token.NUMBER)
		{
			stack.push(new ExpressionTree(newToken, null, null));
		}
		else if(newToken.getType() == Token.UNARY)
		{
			addUnaryToken(stack, newToken);
		}
		else if(newToken.getType() == Token.BINARY)
		{
			addBinaryToken(stack, newToken);

		}
		else
		{
			//user entered an e, expression is finished
			return false;
		}
		return true;
	}

	/**
	 * Gets a token from the user, asking them to enter the type then
	 * value
	 * 
	 * @return		Returns valid token object
	 */
	private static Token getToken()
	{
		int type = getTypeInput();
		if(type == Token.NUMBER)
		{
			return new Token(type, getNumberInput(), Token.EMPTY);
		}
		else if(type == Token.BINARY)
		{
			return new Token(type, Token.EMPTY, getBinaryOperator());
		}
		else if(type == Token.UNARY)
		{
			return new Token(type, Token.EMPTY, getUnaryOperator());
		}
		else
		{
			return new Token(type, Token.EMPTY, Token.EMPTY);
		}
	}
	
	/**
	 * Gets a number from the user
	 * 
	 * @return		double entered by user
	 */
	private static double getNumberInput()
	{
		double temp = input.nextDouble();
		input.nextLine();
		System.out.println(temp);
		return temp;
	}
	
	/**
	 * Gets the token type from the user
	 * 
	 * @return		Returns and int corresponding to user input
	 */
	private static int getTypeInput()
	{
		String type = input.nextLine();
		System.out.println(type);
		if(type.equals("#"))
		{
			return Token.NUMBER;
		}
		else if(type.equals("b"))
		{
			return Token.BINARY;
		}
		else if(type.equals("u"))
		{
			return Token.UNARY;
		}
		else if(type.equals("e"))
		{
			return Token.END;
		}
		
		//invalid type
		else
		{
			System.out.println("Invalid imput. Please try again:");
			return getTypeInput();
		}
	}

	/**
	 * Gets a valid binary operator from the user
	 * 
	 * @return returns an int corresponding to user input
	 */
	private static int getBinaryOperator()
	{
		String op = input.nextLine();
		System.out.println(op);
		if(op.equals("+"))
		{
			return Token.ADD;
		}
		else if(op.equals("-"))
		{
			return Token.SUBTRACT;
		}
		else if(op.equals("*"))
		{
			return Token.MULTIPLY;
		}
		else if(op.equals("/"))
		{
			return Token.DIVIDE;
		}
		else
		{
			System.out.println("Invalid imput. Please try again:");
			return getBinaryOperator();
		}
	}

	
	/**
	 * Gets a valid unary operator from the user
	 * 
	 * @return		returns an int corresponding to the unary operator
	 * 				entered
	 */
	private static int getUnaryOperator()
	{
		String op = input.nextLine();
		System.out.println(op);
		if(op.equals("+"))
		{
			return Token.PLUS;
		}
		else if(op.equals("-"))
		{
			return Token.MINUS;
		}
		else if(op.equals("sin"))
		{
			return Token.SIN;
		}
		else if(op.equals("cos"))
		{
			return Token.COS;
		}
		else if(op.equals("tan"))
		{
			return Token.TAN;
		}
		else
		{
			System.out.println("Invalid imput. Please try again:");
			return getUnaryOperator();
		}
	}
	
	/**
	 * Adds a unary token to an expression tree stored in a stack
	 * 
	 * @param stack			stack holding expression trees
	 * @param newToken		unary token to be added to the expression
	 * 						tree
	 */
	private static void addUnaryToken(Stack stack, Token newToken)
	{
		ExpressionTree oldTree = (ExpressionTree)stack.pop();
		if(oldTree == null)
		{
			System.out.println("Pop failed. Please enter a valid expression.");
		}
		else
		{
			stack.push(new ExpressionTree(newToken, oldTree, null));
		}
	}

	
	/**
	 * Adds a binary token to an expression tree stored in a stack
	 * 
	 * @param stack			stack holding expression trees
	 * @param newToken		binary token to be added to the expression
	 * 						tree
	 */
	private static void addBinaryToken(Stack stack, Token newToken)
	{
		ExpressionTree oldTree = (ExpressionTree)stack.pop();
		ExpressionTree olderTree = (ExpressionTree)stack.pop();
		if(oldTree == null || olderTree == null)
		{
			System.out.println("Pop failed. Please ensure you enter a valid token.");
		}
		else
		{
			stack.push(new ExpressionTree(newToken, oldTree, olderTree));
		}
	}

	
	/**
	 * Checks to see if user is done
	 * 
	 * @return		Returns true if user is done, false if not
	 */
	private static boolean userIsDone()
	{
		System.out.println("Would you like to enter another expression?");
		System.out.println("Enter 1 for yes or 2 for no.");
		if(input.nextInt() == 1)
		{
			input.nextLine();
			return false;
		}
		else
		{
			input.nextLine();
			return true;
		}
	}

}
