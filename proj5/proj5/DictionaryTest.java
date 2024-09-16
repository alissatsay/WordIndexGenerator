package proj5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Model a set of test for a dictionary class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay 
 * @version Spring 2024
 *
 */
public class DictionaryTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private Dictionary dict;
    
    @Before
    public void setUp() throws Exception {
        dict = new Dictionary();
    }

    @After
    public void tearDown() throws Exception {
        dict = null;
    }

    @Test
    public void testConstructor_toString () {
        assertEquals ("", dict.toString());
    }

    @Test
    public void testInsert() {
        // inseting one value when the dict is empty
        dict.insert("l");
        assertEquals ("l\n", dict.toString());

        //inseting one value when it is not empty
        dict.insert("c");
        assertEquals ("c\nl\n", dict.toString());

        //inseting several at once
        dict.insert("b");
        dict.insert("lo");
        dict.insert("cr");
        dict.insert("m");
        assertEquals ("b\nc\ncr\nl\nlo\nm\n", dict.toString());
    }

    @Test
    public void testContains() {
        // searching for a value when the dict is empty
        assertFalse(dict.contains("c"));

        //searching for a value when it's there
        dict.insert("c");
        dict.insert("b");
        dict.insert("lo");
        dict.insert("cr");
        dict.insert("m");

        // searching the root value
        assertTrue(dict.contains("c"));

        //searching the subroot value
        assertTrue(dict.contains("b"));

        //searching a leaf value
        assertTrue(dict.contains("m"));

        //searching for a value when it's not there
        assertFalse(dict.contains("p"));
    }

    @Test
    public void testDelete() {
        // deleting a value when the dict is empty
        dict.delete("a");
        assertEquals ("", dict.toString());

        // deleting values when it's there - dict non empty
        dict.insert("m");
        dict.insert("f");
        dict.insert("r");
        dict.insert("o");
        dict.insert("t");
        dict.insert("b");
        dict.insert("j");

        assertEquals ("b\nf\nj\nm\no\nr\nt\n", dict.toString());

        // deleting a leaf
        dict.delete("b");
        assertEquals ("f\nj\nm\no\nr\nt\n", dict.toString());

        //deleting a subroot with one child
        dict.delete("f");
        assertEquals ("j\nm\no\nr\nt\n", dict.toString());

        // deleting a subroot with two children
        dict.delete("r");
        assertEquals ("j\nm\no\nt\n", dict.toString());

        // deleting a root
        dict.delete("m");
        assertEquals ("j\no\nt\n", dict.toString());

        // deleting a value when it's not there - dict non empty
        dict.delete("6");
        assertEquals ("j\no\nt\n", dict.toString());
    }
    
}
