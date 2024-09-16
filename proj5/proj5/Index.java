package proj5;

/**
 * Modeling an Index class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay
 * @version Spring 2024
 */

public class Index {

    //Invariant for the Index class:
    // 1. The Indx has one intance variable - index.
    // 2. The elements of the Index are contained in the insance variable index.
    // 3. If the Index is empty, index = null, if not, then the number of connected Nodes
    //    is equal to the number of elements in the Index.

    private BinarySearchTree<IndexNode> index;

    /**
     * Contsruct an index.
     */
    public Index(){
        index = new BinarySearchTree<IndexNode>();
    }

    /**
	 * inserts an new word into this index.    
	 * @param word the word to insert
	 */
    public void insert(String word){
        IndexNode indexWord = new IndexNode(word);
        index.insert(indexWord);
    }

    /**
	 * deletes word from index.  If value not there, do nothing.
	 * @param word word to delete
	 */
    public void delete(String word){
        IndexNode indexWord = new IndexNode(word);
        index.delete(indexWord);
    }

    /**
     * checks whether the target word is in the index.
     * @return true or false to indicate whether the word is in the index
     */
    public boolean contains(String word){
        IndexNode indexWord = new IndexNode(word);
        return index.search(indexWord);
    }

    /**
     * Add a new page to the pagelist of the word in the index.
     */
    public void addNewPage(String word, int pageNumber){
        IndexNode indexWord = new IndexNode(word);
        indexWord = index.retrieve(indexWord);
        indexWord.addNewPage(pageNumber);
    }

    /**
     * Check if the pagelist for a given word is full.
     * 
     * @return true is the pagelist is full, false otherwise.
     */
    public boolean wordPagelistIsFull(String word){
        IndexNode indexWord = new IndexNode(word);
        indexWord = index.retrieve(indexWord);
        return indexWord.isFull();
    }

    /**
     * Check if the pagelist for a given word already contains a page.
     * 
     * @return true is the pagelist contains the page, false otherwise.
     */
    public boolean wordPagelistHasPage(String word, int pageNumber){
        IndexNode indexWord = new IndexNode(word);
        indexWord = index.retrieve(indexWord);
        return indexWord.hasThisPage(pageNumber);
    }

    /**
     * Produce a string representation of one word in the index.
     */
    public String toString(String word){
        IndexNode indexWord = new IndexNode(word);
        indexWord = index.retrieve(indexWord);
        return indexWord.toString();
    }

    /**
     * Produce a string representation of this index.
     */
    public String toString(){
        return index.toStringOrdered();
    }
}
