package proj5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Model a set of test for an index node class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay 
 * @version Spring 2024
 *
 */

public class IndexNodeTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private IndexNode indexWord;
    
    @Before
    public void setUp() throws Exception {
        indexWord = new IndexNode("head");
    }

    @After
    public void tearDown() throws Exception {
        indexWord = null;
    }

    @Test
    public void testConstructor_toString () {
        assertEquals ("head {}", indexWord.toString());
    }

    @Test
    public void testGetWord () {
        assertEquals ("head", indexWord.getWord());
    }

    @Test
    public void testAddNewPage() {
        // adding a new page when the pagelist of the index word is empty
        indexWord.addNewPage(1);
        assertEquals ("head {1}", indexWord.toString());

        // adding a new page when the pagelist of the index word is not empty
        indexWord.addNewPage(2);
        indexWord.addNewPage(3);
        assertEquals ("head {1, 2, 3}", indexWord.toString());

        //adding a page when the pagelist of the index word is full - should do nothing
        indexWord.addNewPage(4);
        indexWord.addNewPage(5);
        assertEquals ("head {1, 2, 3, 4}", indexWord.toString());
    }

    @Test
    public void testHasThisPage() {
        // checking if the pagelist of the index word contains the page when it's empty
        assertEquals ("head {}", indexWord.toString());
        assertFalse(indexWord.hasThisPage(1));

        // checking if the pagelist of the index word contains the page when it's not empty
        indexWord.addNewPage(1);
        indexWord.addNewPage(2);
        assertTrue(indexWord.hasThisPage(1));
        assertEquals ("head {1, 2}", indexWord.toString());

        // checking if the pagelist of the index word contains 
        // the page when it's not empty but the page is not there
        assertFalse(indexWord.hasThisPage(3));
    }

    @Test
    public void testGetPageCount() {
        // checking the number of pages when the pagelist of the index word is empty
        assertEquals(0, indexWord.getPageCount());
        assertEquals ("head {}", indexWord.toString());

        // checking the number of pages when the pagelist of the index word is not empty
        indexWord.addNewPage(1);
        assertEquals(1, indexWord.getPageCount());
        indexWord.addNewPage(2);
        indexWord.addNewPage(3);
        assertEquals(3, indexWord.getPageCount());
    }

    @Test
    public void testIsFull() {
        // checking if the pagelist of the index word is full when it's empty
        assertEquals(0, indexWord.getPageCount());
        assertFalse(indexWord.isFull());

        // checking if the pagelist of the index word is full when it's not empty but not full yet
        indexWord.addNewPage(1);
        indexWord.addNewPage(2);
        indexWord.addNewPage(3);
        assertEquals(3, indexWord.getPageCount());
        assertFalse(indexWord.isFull());

        // checking if the pagelist of the index word is full when it is
        indexWord.addNewPage(4);
        assertEquals(4, indexWord.getPageCount());
        assertTrue(indexWord.isFull());
    }

    @Test
    public void testCompareTo() {
        // comparing to the word that precedes it in alphabet - lowercase
        IndexNode wordBefore = new IndexNode("arm");
        assertEquals(1, indexWord.compareTo(wordBefore));

        // comparing to the word that goes after it in alphabet - lowercase
        IndexNode wordAfter = new IndexNode("leg");
        assertEquals(-1, indexWord.compareTo(wordAfter));

        // comparing to the word that has the place in alphabet - lowercase
        IndexNode equalWord = new IndexNode("head");
        assertEquals(0, indexWord.compareTo(equalWord));

        // comparing to the word that precedes it in alphabet - uppercase
        IndexNode wordBeforeU = new IndexNode("Arm");
        assertEquals(1, indexWord.compareTo(wordBeforeU));

        // comparing to the word that goes after it in alphabet - uppercase
        IndexNode wordAfterU = new IndexNode("Leg");
        assertEquals(1, indexWord.compareTo(wordAfterU));

        // comparing to an equal word but in uppercase
        IndexNode equalWordU = new IndexNode("Head");
        assertEquals(1, indexWord.compareTo(equalWordU));
    }
}
