package proj5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Modeling a class for compiling an index dictionary from a text.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay
 * @version Spring 2024
 */

public class IndexMaker {

    private Scanner myReader;
    private Dictionary dictionary;
    private Index index;
    private int currentPage;
    public static final int FIRST_PAGE = 1;
    public static final String PAGE_DELIMITER = "#";

    /**
     * Initializes the demo with the given file.
     * @param filename
     */
    public IndexMaker (String filename) {
        try {
            myReader = new Scanner(new File(filename));
            myReader.useDelimiter("[^a-zA-Z#]+");
        } catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
        dictionary = new Dictionary();
        index = new Index();
        currentPage = FIRST_PAGE;
    }

    /**
     * Reads and print out an index for a given text.
     */
    public void run () {
        while (myReader.hasNext()) {
            String nextWord = myReader.next();
            if (isDelimiter(nextWord)){
                currentPage ++;
            }
            else if (!dictionary.contains(nextWord) && !hasTwoSymbolsOrLess(nextWord)){
                process(nextWord);
            }
        }
        System.out.println(index.toString());
        System.out.println(dictionary.toString());
    }

    /**
     * Check if the next symbol symbolises the end of the page.
     * 
     * @param nextExpression hext symbol
     * @return true if symbolises the end of the page, false otherwise.
     */
    private boolean isDelimiter(String nextExpression){
        return nextExpression.equals(PAGE_DELIMITER);
    }

    /**
     * Check if the word consists of two symbols or less.
     * 
     * @param word the word to check
     * @return true if its length is <= 2, false otherwise.
     */
    private boolean hasTwoSymbolsOrLess(String word){
        return word.length() <= 2;
    }

    /**
     * Process a given word in the text.
     * If the word is not in the index, add this word to index.
     * If the pagelist for this word is full, delete the word from the index and add to the dictionary.
     * If the pagelist of the word already contains a given page, do nothing.
     * If the pagelist of the word does not contain a given page, add this page to the word index.
     * 
     * @param word the word to check
     * @param pageNumber current page
     */
    public void process(String word){
        if (!index.contains(word)){
            index.insert(word);
            index.addNewPage(word, currentPage);
        }
        else{
            if (index.wordPagelistIsFull(word)){
                System.out.println("Deleting '" + index.toString(word) + "' from index.");
                index.delete(word);
                dictionary.insert(word);
            }
            else if (!index.wordPagelistHasPage(word, currentPage)){
                index.addNewPage(word, currentPage);
            }
        }
    }
}
