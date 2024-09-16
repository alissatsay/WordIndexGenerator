package proj5;

/**
 * Modeling a binary search tree.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Chris Fernandes, Kristina Striegnitz, Alissa Tsay
 * @version Fall 2022
 */

public class BinarySearchTree<T extends Comparable<T>> {

    //Invariant for the Binary Search Tree class:
    // 1. The list has one intance variable - root.
    // 2. If the tree is empty root points to null.
    // 3. If the list is not empty, root points to the root node of the tree.

	private BSTNode<T> root;
	
	/**
	 * Default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}
    
    /**
	 * inserts an new value into this BST    
	 * @param newValue value to insert
	 */
	public void insert(T newValue) {
		root = insert(root,newValue);
	}

	/**
	 * inserts value into tree rooted at subroot
	 * 
	 * @param subroot  subroot of tree to insert into
	 * @param value  the value to insert
	 * @return   root of the subtree I've just finished inserting into
	 */
	private BSTNode<T> insert(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return new BSTNode<T>(value);
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = insert(subroot.rlink,value);
			return subroot;
		}
		else {
			subroot.llink = insert(subroot.llink, value);
			return subroot;
		}
	}
	
	/**
	 * deletes value from tree.  If value not there, do nothing.
	 * @param value  value to delete
	 */
	public void delete(T value) {
		root = delete(root, value);
	}
	
	/**
	 * deletes value from tree rooted at subroot
	 * @param subroot  root of tree to be deleted from
	 * @param value  element to delete
	 * @return pointer to tree rooted at subroot that has value removed from it
	 */
	private BSTNode<T> delete(BSTNode<T> subroot, T value) {
		/**
		 * if subroot is an empty tree
		 *     return null
		 * else if victim is on the left of subroot
		 *     subroot's left link must become what recursion on subroot's left child gives you
		 * else if victim is on the right of subroot
		 *     subroot's right link must become what recursion on subroot's rlink gives you
		 * else
		 *     victim is found!
		 *     case 1) victim is a leaf
		 *         return null
		 *     case 2) victim has exactly one (right) subtree
		 *         return pointer to that right subtree
		 *     (case 2a - take care of just left subtree only)
		 *     case 3) victim has two subtrees
		 *         pick a replacement (largest value in the left subtree)
		 *         move the data from replacement node to victim node
		 *         delete the replacement
		 */
		if (subroot == null){
			return null;
		}
		else if (value.compareTo(subroot.key) > 0){
			subroot.rlink = delete(subroot.rlink, value);
			return subroot;
		}
		else if (value.compareTo(subroot.key) < 0){
			subroot.llink = delete(subroot.llink, value);
			return subroot;
		}
		else{
			if (subroot.isLeaf()){
				return null;
			}
			else if(subroot.hasRightChildOnly()){
				return subroot.rlink;
			}
			else if(subroot.hasLeftChildOnly()){
				return subroot.llink;
			}
			else{
				subroot.key = searchLeftmostNode(subroot.rlink);
				subroot.rlink = delete(subroot.rlink, subroot.key);
				return subroot;
			}
		}
	}

	/**
	 * Find the leftmode node (the smallest value node) on the subroot's rigth side.
	 * 
	 * @param subroot the subroot
	 * @return the leftmost node
	 */
	private T searchLeftmostNode(BSTNode<T> subroot){
		if (subroot.isLeaf()){
			T value = subroot.key;
			return value;
		}
		else if (subroot.hasRightChildOnly()){
			T value = subroot.key;
			return value;
		}
		else {
			return searchLeftmostNode(subroot.llink);
		}
	}

    /**
     * checks whether the target value is in the tree
     * @return true or false to indicate whether the target value is in the tree
     */
    public boolean search(T target) {
        return search(root, target);
    }

	/**
     * checks whether the target value is in the tree rooted at subroot.
	 * 
	 * @param subroot subroot of tree to check
	 * @param value the target of search
     * @return true or false to indicate whether the target value is in the tree
     */
	private boolean search(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return false;
		}
		else if (value.compareTo(subroot.key) == 0){
			return true;
		}
		else if (value.compareTo(subroot.key) > 0){
			return search(subroot.rlink,value);
		}
		else {
			return search(subroot.llink, value);
		}
	}

    /**
     * checks whether the target value is in the tree and return it.
     * @return retrieve the traget value
     */
    public T retrieve(T target) {
        return retrieve(root, target);
    }

	/**
     * checks whether the target value is in the tree rooted at subroot.
	 * If it is, returns that value.
	 * 
	 * @param subroot subroot of tree to check
	 * @param value the target of search
     * @return the traget data or null
     */
	private T retrieve(BSTNode<T> subroot, T value) {
		if (subroot==null){
			return null;
		}
		else if (value.compareTo(subroot.key) == 0){
			return subroot.key;
		}
		else if (value.compareTo(subroot.key) > 0){
			return retrieve(subroot.rlink,value);
		}
		else {
			return retrieve(subroot.llink, value);
		}
	}


	/**
	 * returns tree as printable string
	 * @return tree in string format with form (left subtree) value (right subtree)
	 */
	public String toString(){
		return toString(root);
	}

	/**
	 * recursive helper method for toString()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toString(BSTNode<T> N){
		String ret = "";
		if (N != null){
			ret += "(";
			ret += toString(N.llink);
			ret += "  " + N + "  ";
			ret += toString(N.rlink);
			ret += ")";
		}
		return ret;
	}

	/**
	 * returns a representation of this tree as a list in an ASCII alphbetical order.
	 * @return tree as a list in string format 
	 */
	public String toStringOrdered(){
		return toStringOrdered(root);
	}

	/**
	 * recursive helper method for toStringOrdered()
	 *
	 * @param N root of subtree to make into a string
	 * @return string version of tree rooted at N
	 */
	private String toStringOrdered(BSTNode<T> N){
		String toReturn = "";
		if (N != null){
			toReturn += toStringOrdered(N.llink);
			toReturn += N.toString() + "\n";
			toReturn += toStringOrdered(N.rlink);
		}
		return toReturn;
	}

}