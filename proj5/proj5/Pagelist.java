package proj5;

/**
 * Modeling a pagelist of word in the index.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay
 * @version Spring 2024
 */

public class Pagelist {

    //Invariant for the Pagelist class:
    // 1. The pagelist has three intance variables - contents, size and MEX_SIZE.
    // 2. contents is an integer array that contains the contents of this pagelist.
    // 3. MAX_SIZE is the maximum length of the pagelist.
    // 4. When the pagelist is created, the length of contents is equal to MAX_SIZE
    //    and that length never changes.
    // 5. The number of elements in the pagelist is in the instance variable size
    // 6. size <= pagerPerNode
    // 7. size >= 0
    // 8. size always equals to the number of elements in the Pagelist
    // 9. For an empty Pagelist, we do not care what is stored in any of data;
    //    for a non-empty Pagelist, the elements in the Pagelist are stored in data[0]
    //    through data[size-1], and we don't care what's in the rest of data.

    private int[] contents;
    private int size;
    public static final int MAX_SIZE = 4;

    /**
     * Construct a new pagelist.
     */
    public Pagelist(){
        contents = new int[MAX_SIZE];
        size = 0;
    }

    /**
     * Add a new page number into the pagelist.
     * 
     * @param pageNumber the number of the page to add.
     */
    public void addNewPage(int pageNumber){
        if (!isFull() && !contains(pageNumber)){
            contents[getSize()] = pageNumber;
            size++;
        }
    }

    /**
     * Check if the pagelist contains this page.
     * 
     * @param pageNumber the page to check
     * @return true if the page is already in the pagelist, false otherwise.
     */
    public boolean contains(int pageNumber){
        for (int i = 0; i < getSize(); i++){
            if (contents[i] == pageNumber){
                return true;
            }
        }
        return false;
    }

    /**
     * Get the number of contents in this pagelist.
     * 
     * @return the number of contents
     */
    public int getSize(){
        return size;
    }

    /**
     * Check if the pagelist is full.
     * 
     * @return true if the pagelist is full, flase otherwise.
     */
    public boolean isFull(){
        return getSize() == MAX_SIZE;
    }

    /**
     * Produce a string representation of this pagelist.
     * The string will return all contents separated by commas.
     * 
     * @return string representation
     */
    public String toString(){
        String toReturn = "";
        if (getSize() != 0){
            for (int i = 0; i < getSize()-1; i++){
                toReturn = toReturn + contents[i] + ", ";
            }
            toReturn = toReturn + contents[getSize()-1];
        }
        return toReturn;
    }
}
