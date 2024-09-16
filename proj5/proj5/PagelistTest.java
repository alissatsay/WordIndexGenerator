package proj5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Model a set of test for a pagelist class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay 
 * @version Spring 2024
 *
 */


public class PagelistTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Pagelist pages;
    
    @Before
    public void setUp() throws Exception {
        pages = new Pagelist();
    }

    @After
    public void tearDown() throws Exception {
        pages = null;
    }

    @Test
    public void testConstructor_toString () {
        assertEquals ("", pages.toString());
    }

    @Test
    public void testAddNewPage() {
        // adding a new page when the pagelist is empty
        pages.addNewPage(1);
        assertEquals ("1", pages.toString());

        // adding a new page when the pagelist is not empty
        pages.addNewPage(2);
        //adding the same page
        pages.addNewPage(2);
        pages.addNewPage(3);
        assertEquals ("1, 2, 3", pages.toString());

        //adding a page when the pagelist is full - should do nothing
        pages.addNewPage(4);
        pages.addNewPage(5);
        assertEquals ("1, 2, 3, 4", pages.toString());
    }

    @Test
    public void testContains() {
        // checking if the pagelist contains the page when it's empty
        assertEquals ("", pages.toString());
        assertFalse(pages.contains(1));

        // checking if the pagelist contains the page when it's not empty
        pages.addNewPage(1);
        pages.addNewPage(2);
        assertTrue(pages.contains(1));
        assertEquals ("1, 2", pages.toString());

        // checking if the pagelist contains the page when it's not empty but the page is not there
        assertFalse(pages.contains(3));
    }

    @Test
    public void testGetPageCount() {
        // checking the number of pages when the pagelist is empty
        assertEquals(0, pages.getSize());
        assertEquals ("", pages.toString());

        // checking the number of pages when the pagelist is not empty
        pages.addNewPage(1);
        assertEquals(1, pages.getSize());
        pages.addNewPage(2);
        pages.addNewPage(3);
        assertEquals(3, pages.getSize());
    }

    @Test
    public void testIsFull() {
        // checking if the pagelist is full when it's empty
        assertEquals(0, pages.getSize());
        assertFalse(pages.isFull());

        // checking if the pagelist is full when it's not empty but not full yet
        pages.addNewPage(1);
        pages.addNewPage(2);
        pages.addNewPage(3);
        assertEquals(3, pages.getSize());
        assertFalse(pages.isFull());

        // checking if the pagelist is full when it is
        pages.addNewPage(4);
        assertEquals(4, pages.getSize());
        assertTrue(pages.isFull());
    }

    
}
