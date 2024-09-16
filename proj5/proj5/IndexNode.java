package proj5;
import java.lang.Comparable;

/**
 * Modeling a node of an index.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay
 * @version May 29, 2024
 */

public class IndexNode implements Comparable<IndexNode>{

    //Invariant for the Index Node class:
    // 1. The index node has two intance variables - word, and pages.
    // 2. word is the String value contained in this node.
    // 3. pages is a pagelist that contains the full pagelist for this word.
    
    private String word;
    private Pagelist pages;

    /**
     * Construct a node of an IndexNode class.
     * @param value the word the index node should contain.
     */
    IndexNode(String value){
        word = value;
        pages = new Pagelist();
    }

    /**
     * Construct a node of an IndexNode class with a value in pagelist.
     * @param value the word the index node should contain.
     * @param pageNumber the first page where the word occured.
     */
    IndexNode(String value, int pageNumber){
        word = value;
        pages = new Pagelist();
        pages.addNewPage(pageNumber);
    }

    /**
     * Compare this index Node to other index node.
     * Returns -1 if the this word precedes the other word in the alphabet;
     * 1 if the other word precedes this one, and 0 if their positions equal.
     * 
     * @param other
     * @return -1, 1, or 0
     */
    public int compareTo(IndexNode other){
        if (getWord().compareTo(other.getWord()) < 0){
            return -1;
        }
        else if (getWord().compareTo(other.getWord()) > 0){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
     * Get the String stored in this index node.
     * @return
     */
    public String getWord(){
        return word;
    }

    /**
     * Add a new page to the pagelist of a given word.
     * 
     * @param pageNumber the page number to add.
     */
    public void addNewPage(int pageNumber){
        pages.addNewPage(pageNumber);
    }

    /**
     * Check if the pagelist of this word is full.
     * 
     * @return true if the pagelist is full, false otherwise.
     */
    public boolean isFull(){
        return pages.isFull();
    }

    /**
     * Get the number of pages in the pagelist of this word.
     * 
     * @return the number of pages
     */
    public int getPageCount(){
        return pages.getSize();
    }

    /**
     * Check if the pagelist if this word contains a given page.
     * 
     * @param pageNumber the page to check
     * @return true if the page is already in the pagelist, false otherwise.
     */
    public boolean hasThisPage(int pageNumber){
        return pages.contains(pageNumber);
    }

    /**
     * Produce a string representation of the indexNode.
     * 
     * @return a string representation.
     */
    public String toString(){
        return word + " {" + pages.toString() + "}";
    }
}
