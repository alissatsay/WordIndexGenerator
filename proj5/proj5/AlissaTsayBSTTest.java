package proj5;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * 
 * Model a set of test for a binary tree class.
 * 
 * I affirm that I have carried out the attached academic endeavors with full academic honesty,
 * in accordance with the Union College Honor Code and the course syllabus. [Alissa Tsay]
 * 
 * @author Alissa Tsay 
 * @version June 3, 2024
 *
 */

public class AlissaTsayBSTTest {

    @Rule
    public Timeout timeout = Timeout.millis(100);
	
    private BinarySearchTree<String> tree;
    
    @Before
    public void setUp() throws Exception {
        tree = new BinarySearchTree<String>();
    }

    @After
    public void tearDown() throws Exception {
        tree = null;
    }

    @Test
    public void testConstructor_toString () {
        assertEquals ("", tree.toString());
    }

    @Test
    public void testInsert() {
        // inseting one value when the tree is empty
        tree.insert("lamb");
        assertEquals ("(  lamb  )", tree.toString());

        //inseting one value when it is not empty
        tree.insert("chicken");
        assertEquals ("((  chicken  )  lamb  )", tree.toString());

        //inseting several at once
        tree.insert("beef");
        tree.insert("lobster");
        tree.insert("crab");
        tree.insert("meatloaf");
        assertEquals ("(((  beef  )  chicken  (  crab  ))  lamb  (  lobster  (  meatloaf  )))", tree.toString());
    }

    @Test
    public void testSearch() {
        // searching for a value when the tree is empty
        assertFalse(tree.search("chicken"));

        //searching for a value when it's there
        tree.insert("chicken");
        tree.insert("beef");
        tree.insert("lobster");
        tree.insert("crab");
        tree.insert("meatloaf");

        // searching the root value
        assertTrue(tree.search("chicken"));

        //searching the subroot value
        assertTrue(tree.search("beef"));

        //searching a leaf value
        assertTrue(tree.search("meatloaf"));


        //searching for a value when it's not there
        assertFalse(tree.search("pork"));
    }

    @Test
    public void testDelete() {
        // deleting a value when the tree is empty
        tree.delete("a");
        assertEquals ("", tree.toString());

        // deleting values when it's there - tree non empty
        tree.insert("m");
        tree.insert("f");
        tree.insert("r");
        tree.insert("o");
        tree.insert("t");
        tree.insert("b");
        tree.insert("j");

        assertEquals ("(((  b  )  f  (  j  ))  m  ((  o  )  r  (  t  )))", tree.toString());

        // deleting a leaf
        tree.delete("b");
        assertEquals ("((  f  (  j  ))  m  ((  o  )  r  (  t  )))", tree.toString());

        //deleting a subroot with one child
        tree.delete("f");
        assertEquals ("((  j  )  m  ((  o  )  r  (  t  )))", tree.toString());

        // deleting a subroot with two children
        tree.delete("r");
        assertEquals ("((  j  )  m  ((  o  )  t  ))", tree.toString());

        // deleting a root
        tree.delete("m");
        assertEquals ("((  j  )  o  (  t  ))", tree.toString());

        // deleting a value when it's not there - tree non empty
        tree.delete("6");
        assertEquals ("((  j  )  o  (  t  ))", tree.toString());
    }

@Test
    public void testRetrieve() {
        // deleting a value when the tree is empty
        tree.retrieve("a");
        assertEquals ("", tree.toString());

        // deleting values when it's there - tree non empty
        tree.insert("m");
        tree.insert("f");
        tree.insert("r");
        tree.insert("o");
        tree.insert("t");
        tree.insert("b");
        tree.insert("j");

        assertEquals ("(((  b  )  f  (  j  ))  m  ((  o  )  r  (  t  )))", tree.toString());

        // deleting a leaf
        String word = tree.retrieve("b");
        assertEquals("b", word.toString());
        assertEquals ("(((  b  )  f  (  j  ))  m  ((  o  )  r  (  t  )))", tree.toString());
    }
}
