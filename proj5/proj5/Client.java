package proj5;


/**
 * Driver for the index maker project
 * 
 * @author Alissa Tsay 
 * @version June 9, 2024
 */
public class Client
{
    public static void main(String[] args)
    {
    	makeIndex("uscons.txt");
    }
    
    /**
     * Makes an index out of fileName. Gradescope needs this function.
     * 
     * @param fileName path to text file that you want to index
     */
    public static void makeIndex(String fileName) {
        IndexMaker index = new IndexMaker(fileName);
        index.run();
    }
}
