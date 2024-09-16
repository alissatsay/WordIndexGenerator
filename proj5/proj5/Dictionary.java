package proj5;

/**
 * Model an Dictionary class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay
 * @version Spring 2024
 */

public class Dictionary {

    //Invariant for the Dicitonary class:
    // 1. The dictionary has one intance variable - dict.
    // 2. The elements of the Dicitonary are contained in the insance variable dict.
    // 3. If the Dicitonary is empty, dict = null, if not, then the number of connected Nodes
    //    is equal to the number of elements in the Dicitonary.

    private BinarySearchTree<String> dict;

    /**
     * Contsruct an index.
     */
    public Dictionary(){
        dict = new BinarySearchTree<String>();
    }

    /**
	 * inserts an new word into this dictionary.    
	 * @param word the word to insert
	 */
    public void insert(String word){
        dict.insert(word);
    }

    /**
	 * deletes word from index.  If value not there, do nothing.
	 * @param word word to delete
	 */
    public void delete(String word){
        dict.delete(word);
    }

    /**
     * checks whether the target word is in the index.
     * @return true or false to indicate whether the word is in the index
     */
    public boolean contains(String word){
        return dict.search(word);
    }

    /**
     * Produce a String representation of this dictionary.
     * 
     * @return the string representation
     */
    public String toString(){
        return dict.toStringOrdered();
    }
}
