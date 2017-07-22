/**
 * A simple binary tree that stores strings.
 * This is an abstract base class for the concrete classes Node and Leaf.
 */
abstract class ExpressionTree {

    val content : String

    // methods for parts (b), (c), and (d)
     def postfix() : String = {
       def proc_node(left:String,right:String,str:String): String={
         left+" "+right+" "+str+" ";
       }
       def proc_leaf(str: String): String={
         str;
       }
       traverse(proc_node,proc_leaf);
     }

     def infix() : String = {
       def proc_node(left:String,right:String,str:String): String={
         left+" "+str+" "+right+" ";
       }
       def proc_leaf(str: String): String={
         str;
       }
       traverse(proc_node,proc_leaf);
     }
     def eval() : Double ={
       def proc_node(left:Double,right:Double,str:String): Double={
        if(str.equals("+"))
          return left+right;
        if(str.equals("-"))
          return left-right;
        if(str.equals("*"))
          return left*right;
        if(str.equals("/"))
          return left/right;
        return 0.0;
       }
       def proc_leaf(str: String): Double={
         str.toDouble;
       }
       traverse(proc_node,proc_leaf);
     }

    /**
     * A higher-order generalization for tree operations.
     *
     * This method implements tree traversal as an
     * abstraction over different tree operations.
     * Tree operations can be implemented by creating function objects
     * proc_node and proc_leaf and passing them to traverse.
     * The abstract method is implemented in Node and Leaf.
     */
    def traverse[A](proc_node: (A,A,String) => A, proc_leaf: String=>A) : A

}

object ExpressionTree {

    // method for part (a)
    def apply(expression : String ) : ExpressionTree ={
      var stack = List[ExpressionTree]();
      for(x <- expression.split(" ")){
        var item = new Leaf(x);
        if(x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")){
          var operand_1 = stack.head;
          stack = stack.tail; //pops off first number
          var node = new Node(x,operand_1,stack.head);
          stack = stack.tail;//pops off second number
          stack = node::stack;
        }
        else{
          stack = item::stack;
        }
      }
      stack.head;
    }


    // You can run ExpressionTree to test your code.
    def main(args : Array[String]) {

        //Uncomment to test part (a)
        val tree : ExpressionTree = ExpressionTree("3 5 6 * + 7 -");

        //Uncomment to test part (b)
        println(tree.postfix())


        // Uncomment to test part (c)
        println(tree.infix())

        // Uncomment to test part(d)
        println(tree.eval())
    }


}


/**
 * A node with exactly two subtrees.
 */
class Node(val content: String, val left: ExpressionTree, val right: ExpressionTree) extends ExpressionTree {

     /**
      * The traverse implementation for Node calls proc_node on the
      * results returned by calling traverse recusively on each
      * subtree and the content of this node.
      */
    def traverse[A](proc_node : (A,A,String) =>A, proc_leaf: String=>A) =
            proc_node(left.traverse(proc_node, proc_leaf),
                     right.traverse(proc_node, proc_leaf),
                     content)
}

/** Companion object for Node -- only used to define an apply method */
object Node {
    def apply(content: String, left : ExpressionTree, right: ExpressionTree) =
        new Node(content, left, right)
}


/**
 * A node that does not have any further subtrees (i.e. a single leaf node).
 */
class Leaf(val content : String) extends ExpressionTree {
    /**
     * The traverse implementation for Leaf calls proc_laf on the content of
     * the node. proc_leaf usually just converts the content into the correct
     * result type.
     */
    def traverse[A](proc_node : (A,A,String)=>A, proc_leaf: String=>A) =
        proc_leaf(content)
}
/**
 * Companion object for the Leaf, only used to define an apply method
 */
object Leaf {
    def apply(content: String) = new Leaf(content)
}