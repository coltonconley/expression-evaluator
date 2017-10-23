/*************************************************************
 * ExpressionTree Class
 * 
 * Description: Each Expression Tree holds a single tree node, 
 * which acts as the root of the Expression Tree.  The tree nodes
 * all contain tokens in their value fields and children as 
 * appropriate.  The class provides methods to set and get the
 * root node, as well as convert the tree to a string and
 * evaluate it.
 **************************************************************/
public class ExpressionTree {

	private TreeNode root; //root node of the expression tree

	/**
	 * Constructor
	 * 
	 * Correctly assigns values to root depending on which parameters 
	 * are null
	 */
	public ExpressionTree(Token rootToken, ExpressionTree leftExpr,
			ExpressionTree rightExpr)
	{
		if(leftExpr == null)
		{
			if(rightExpr == null)
			{
				root = new TreeNode(rootToken, null, null);
			}
			else
			{
				root = new TreeNode(rootToken, null, rightExpr.root);
			}
		}
		else
		{
			if(rightExpr == null)
			{
				root = new TreeNode(rootToken, leftExpr.root, null);
			}
			else
			{
				root = new TreeNode(rootToken, leftExpr.root, rightExpr.root);
			}
		}
	}
	
	/**
	 * Setter and Getter for the root node
	 */
	public void setRoot(TreeNode r)
	{
		root = r;
	}

	public TreeNode getRoot()
	{
		return root;
	}

	/**
	 * Compiles a string of the entire expression tree in 
	 * post order (wrapper method)
	 * 
	 * @return 		returns the string of the expression 
	 * 				tree's contents
	 */
	public String toStringPostOrder()
	{
		return auxToStringPostOrder(root);
	}

	/**
	 * Recursively compiles a string of the entire expression tree in 
	 * post order
	 * 
	 * @param node 		tree node being added to the string
	 * @return			returns the a string containing all the contents 
	 * 					of a given tree node 
	 */
	private String auxToStringPostOrder(TreeNode node)
	{
		if (node == null)
		{
			return "";
		}
		else
		{
			return 	auxToStringPostOrder(node.getLeft()) +
					auxToStringPostOrder(node.getRight()) +
					node.getValue().toString() + '\n'; 

		}
	}

	/**
	 * Wrapper method which returns the value of the expression tree
	 * 
	 * @return		final value of the expression tree
	 */
	public double evaluate()
	{
		return auxEvaluate(root);
	}

	
	/**
	 * Recursively evaluates the value of a tree node and all its children.
	 * 
	 * @param root		Tree node who's value will be evaluated
	 * @return			returns double representing the value of of a
	 * 					tree node and all its children
	 */
	private double auxEvaluate(TreeNode root)
	{
		Token rootToken = (Token)root.getValue();
		if(rootToken.getType() == Token.NUMBER)
		{
			return rootToken.getNum();
		}
		else if(rootToken.getType() == Token.UNARY)
		{
			 return evalUnary(rootToken, root.getLeft());
		}
		else 
		{
			return evalBinary(rootToken, root);
		}
	}

	
	/**
	 * Evaluates a tree node with a unary token in its value
	 * field. 
	 * 
	 * @param unaryToken	Unary token
	 * @param root			Tree node to be evaluated
	 * @return				Returns the value of the tree node
	 * 						entered and all its children
	 */
	private double evalUnary(Token unaryToken, TreeNode root)
	{
		if(unaryToken.getOp() == Token.MINUS)
		{
			return -1 * auxEvaluate(root);
		}
		else if(unaryToken.getOp() == Token.PLUS)
		{
			return auxEvaluate(root);
		}
		else if(unaryToken.getOp() == Token.SIN)
		{
			return Math.sin(Math.toRadians(auxEvaluate(root)));
		}
		else if(unaryToken.getOp() == Token.COS)
		{
			return Math.cos(Math.toRadians(auxEvaluate(root)));
		}
		else
		{
			return Math.tan(Math.toRadians(auxEvaluate(root)));
		}		
	}
	
	
	/**
	 * Evaluates a tree node with a binary token in its value field.  
	 * 
	 * @param rootToken		Binary token
	 * @param root			Tree node to be evaluated
	 * @return				Returns value of the tree node after
	 * 						evaluating all its children
	 */
	private double evalBinary(Token rootToken, TreeNode root)
	{
		if(rootToken.getOp() == Token.ADD)
		{
			return auxEvaluate(root.getLeft()) + auxEvaluate(root.getRight());
		}
		else if(rootToken.getOp() == Token.SUBTRACT)
		{
			return auxEvaluate(root.getLeft()) - auxEvaluate(root.getRight());
		}
		else if(rootToken.getOp() == Token.MULTIPLY)
		{
			return auxEvaluate(root.getLeft()) * auxEvaluate(root.getRight());
		}
		else
		{
			return auxEvaluate(root.getLeft()) / auxEvaluate(root.getRight());
		}
	}
}
