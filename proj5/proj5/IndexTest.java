package proj5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Model a set of test for an index class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay 
 * @version Spring 2024
 *
 */

public class IndexTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Index index;
    
    @Before
    public void setUp() throws Exception {
        index = new Index();
    }

    @After
    public void tearDown() throws Exception {
        index = null;
    }

    @Test
    public void testConstructor_toString () {
        assertEquals ("", index.toString());
    }

    @Test
    public void testInsert() {
        // inseting one value when the index is empty
        index.insert("l");
        assertEquals ("l {}\n", index.toString());

        //inseting one value when it is not empty
        index.insert("c");
        assertEquals ("c {}\nl {}\n", index.toString());

        //inseting several at once
        index.insert("b");
        index.insert("lo");
        index.insert("cr");
        index.insert("m");
        assertEquals ("b {}\nc {}\ncr {}\nl {}\nlo {}\nm {}\n", index.toString());
    }

    @Test
    public void testContains() {
        // searching for a value when the index is empty
        assertFalse(index.contains("c"));

        //searching for a value when it's there
        index.insert("c");
        index.insert("b");
        index.insert("lo");
        index.insert("cr");
        index.insert("m");

        // searching the root value
        assertTrue(index.contains("c"));

        //searching the subroot value
        assertTrue(index.contains("b"));

        //searching a leaf value
        assertTrue(index.contains("m"));

        //searching for a value when it's not there
        assertFalse(index.contains("p"));
    }

    @Test
    public void testDelete() {
        // deleting a value when the index is empty
        index.delete("a");
        assertEquals ("", index.toString());

        // deleting values when it's there - index non empty
        index.insert("m");
        index.insert("f");
        index.insert("r");
        index.insert("o");
        index.insert("t");
        index.insert("b");
        index.insert("j");

        assertEquals ("b {}\nf {}\nj {}\nm {}\no {}\nr {}\nt {}\n", index.toString());

        // deleting a leaf
        index.delete("b");
        assertEquals ("f {}\nj {}\nm {}\no {}\nr {}\nt {}\n", index.toString());

        //deleting a subroot with one child
        index.delete("f");
        assertEquals ("j {}\nm {}\no {}\nr {}\nt {}\n", index.toString());

        // deleting a subroot with two children
        index.delete("r");
        assertEquals ("j {}\nm {}\no {}\nt {}\n", index.toString());

        // deleting a root
        index.delete("m");
        assertEquals ("j {}\no {}\nt {}\n", index.toString());

        // deleting a value when it's not there - index non empty
        index.delete("6");
        assertEquals ("j {}\no {}\nt {}\n", index.toString());
    }

    @Test 
    public void testToString_oneWord(){
        // printing out one word when there is only one word
        index.insert("m");
        assertEquals("m {}", index.toString("m"));

        // printing out one word when there are several words
        index.insert("f");
        index.insert("r");
        assertEquals("m {}", index.toString("m"));
    }

    @Test
    public void testAddNewPage() {
        //adding a new page to the index with one word
        index.insert("m");
        index.addNewPage("m", 1);
        assertEquals("m {1}", index.toString("m"));

        // adding a new page to the index with several words
        index.insert("f");
        index.insert("r");
        index.insert("o");
        index.insert("t");
        index.insert("b");
        index.insert("j");

        index.addNewPage("m", 2);
        //adding the same page
        index.addNewPage("m", 2);
        index.addNewPage("m", 3);
        assertEquals("m {1, 2, 3}", index.toString("m"));

        assertEquals ("b {}\nf {}\nj {}\nm {1, 2, 3}\no {}\nr {}\nt {}\n", index.toString());
    }

    @Test
    public void testWordPagelistIsFull() {
        //checking if the pagelist for a word is full when it's empty
        index.insert("m");
        assertFalse(index.wordPagelistIsFull("m"));

        //checking if the pagelist for a word is full when it's not empty but not full
        index.addNewPage("m", 1);
        assertEquals("m {1}", index.toString("m"));
        assertFalse(index.wordPagelistIsFull("m"));

        //checking if the pagelist for a word is full when it's  full
        index.addNewPage("m", 2);
        index.addNewPage("m", 3);
        index.addNewPage("m", 4);
        assertEquals("m {1, 2, 3, 4}", index.toString("m"));
        assertTrue(index.wordPagelistIsFull("m"));
    }

    @Test
    public void testWordPagelistHasPage() {
        //checking if the pagelist for a word has a page when it's empty
        index.insert("m");
        assertFalse(index.wordPagelistHasPage("m", 1));

        //checking if the pagelist for a word has a page 
        //when it's not empty but does not have the page
        index.addNewPage("m", 1);
        assertEquals("m {1}", index.toString("m"));
        assertFalse(index.wordPagelistHasPage("m", 2));

        //checking if the pagelist for a word has page 
        //when it has it
        index.addNewPage("m", 2);
        index.addNewPage("m", 3);
        index.addNewPage("m", 4);
        assertEquals("m {1, 2, 3, 4}", index.toString("m"));
        assertTrue(index.wordPagelistHasPage("m", 2));
    }
    
}
