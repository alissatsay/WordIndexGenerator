package proj5;

/**
 * A node in a BinarySearchTree.
 * 
 * @author Chris Fernandes, Kristina Striegnitz
 * @version Fall 2022
 */
public class BSTNode<T>
{
    //Invariant for the BSt Node class:
    // 1. The binary search tree node has three intance variables - key, rlink, llink.
    // 2. key is the value of some type contained in this node.
    // 3. rlink points to the right child of the node, if there is no right child - rlink points to null.
    // 4. llink points to the left child of the node, if there is no left child - llink points to null.

    public T key;
    public BSTNode<T> llink;
    public BSTNode<T> rlink;

    /**
     * Construct a node of a binary search tree.
     * @param data the value the node should contain.
     */
    public BSTNode(T data){
    	key=data;
    	llink=null;
    	rlink=null;
    }

    /**
     * produce a string representation of this node.
     * 
     * @return string representation
     */
    public String toString() {
    	return "" + key.toString();
    }

    /**
     * Check if this node is a leaf.
     * 
     * @return true if the node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
    	return this.llink == null && this.rlink == null;
    }

    /**
     * Check if this node has only the right child.
     * 
     * @return true if it only has the right child, false otherwise.
     */
    public boolean hasRightChildOnly() {
    	return this.llink == null && this.rlink != null;
    }
    
    /**
     * Check if this node has only the left child.
     * 
     * @return true if it only has the left child, false otherwise.
     */
    public boolean hasLeftChildOnly() {
    	return this.llink != null && this.rlink == null;
    }
                              
}
