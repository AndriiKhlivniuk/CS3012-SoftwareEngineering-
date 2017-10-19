import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *  Test class for Doubly Linked List
 *
 *  @version 3.1 09/11/15 11:32:15
 *
 *  @author  TODO
 */

@RunWith(JUnit4.class)
public class BSTTest
{
  

    @Test 
    	public void testPut(){
    	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
    	 bst.put(7, 7); 
    	 assertEquals("Put root in the tree", "(()7())", bst.printKeysInOrder());
         bst.put(8, 8);   
         bst.put(8,null);
    	 assertEquals("Put the same value, but null key. should delete node with key",
    			 "(()7())", bst.printKeysInOrder());
    	 bst.put(8, 8);
    	 assertEquals("Put node on the right side of tree", "(()7(()8()))", bst.printKeysInOrder());
    	 bst.put(7,0);
    	 assertEquals("Put node with the same Key, but different value",
    			 "(()7(()8()))", bst.printKeysInOrder());
    }
    
    @Test
    public void testDelete() {
    BST<Integer, Integer> bst = new BST<Integer, Integer>();
    bst.delete(1);
    assertEquals("Deleting from empty tree", "()", bst.printKeysInOrder());
    
    bst.put(7, 7);   //        _7_
    bst.put(8, 8);   //      /     \
    bst.put(3, 3);   //    _3_      8
    bst.put(1, 1);   //  /     \
    bst.put(2, 2);   // 1       6
    bst.put(6, 6);   //  \     /
    bst.put(4, 4);   //   2   4
    bst.put(5, 5);   //        \
                     //         5
    
    assertEquals("Checking order of constructed tree",
            "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());
    
    bst.delete(9);
    assertEquals("Deleting non-existent key",
            "(((()1(()2()))3((()4(()5()))6()))7(()8()))", bst.printKeysInOrder());

    bst.delete(8);
    assertEquals("Deleting leaf", "(((()1(()2()))3((()4(()5()))6()))7())", bst.printKeysInOrder());

    bst.delete(6);
    assertEquals("Deleting node with single child",
            "(((()1(()2()))3(()4(()5())))7())", bst.printKeysInOrder());

    bst.delete(3);
    assertEquals("Deleting node with two children",
            "(((()1())2(()4(()5())))7())", bst.printKeysInOrder());
    bst.delete(4);
    assertEquals("Deleting node with right child",
            "(((()1())2(()5()))7())", bst.printKeysInOrder());
    
}
    @Test 
 	public void testHeight(){
	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
     
     bst.put(7, 7);   //        _7_
     bst.put(8, 8);   //      /     \
     bst.put(3, 3);   //    _3_      8
     bst.put(1, 1);   //  /     \
     bst.put(2, 2);   // 1       6
     bst.put(6, 6);   //  \     /
     bst.put(4, 4);   //   2   4
     bst.put(5, 5);   //        \
                      //         5
     assertEquals("Checking height of the tree", 4, bst.height());
 }
    @Test
    
 	public void testMedian(){
	 BST<String, Integer> bst = new BST<String, Integer>();
	 
	 assertEquals("Checking median of emty tree",null, bst.median());
	 
     bst.put("7", 7);   //        _7_
     bst.put("8", 8);   //      /     \
     bst.put("3", 3);   //    _3_      8
     bst.put("1", 1);   //  /     \
     bst.put("2", 2);   // 1       6
     bst.put("6", 6);   //  \     /
     bst.put("4", 4);   //   2   4
     bst.put("5", 5);   //        \
                        //         5
     assertEquals("Checking median of non-empty tree","4", bst.median());
 }
 	public void testFindSize(){
	 BST<Integer, Integer> bst = new BST<Integer, Integer>();
     
     bst.put(7, 7);   //        _7_
     bst.put(8, 8);   //      /     \
     bst.put(3, 3);   //    _3_      8
     bst.put(1, 1);   //  /     \
     bst.put(2, 2);   // 1       6
     bst.put(6, 6);   //  \     /
     bst.put(4, 4);   //   2   4
     bst.put(5, 5);   //        \
                      //         5
     assertEquals("Checking size of the tree", 8, bst.findSize());
 }
 	@Test
 	 public void testPrettyPrint() {
 	     BST<Integer, Integer> bst = new BST<Integer, Integer>();
 	     assertEquals("Checking pretty printing of empty tree",
 	             "-null\n", bst.prettyPrintKeys());
 	      
 	                          //  -7
 	                          //   |-3
 	                          //   | |-1
 	                          //   | | |-null
 	     bst.put(7, 7);       //   | |  -2
 	     bst.put(8, 8);       //   | |   |-null
 	     bst.put(3, 3);       //   | |    -null
 	     bst.put(1, 1);       //   |  -6
 	     bst.put(2, 2);       //   |   |-4
 	     bst.put(6, 6);       //   |   | |-null
 	     bst.put(4, 4);       //   |   |  -5
 	     bst.put(5, 5);       //   |   |   |-null
 	                          //   |   |    -null
 	                          //   |    -null
 	                          //    -8
 	                          //     |-null
 	                          //      -null
 	     
 	     String result = 
 	      "-7\n" +
 	      " |-3\n" + 
 	      " | |-1\n" +
 	      " | | |-null\n" + 
 	      " | |  -2\n" +
 	      " | |   |-null\n" +
 	      " | |    -null\n" +
 	      " |  -6\n" +
 	      " |   |-4\n" +
 	      " |   | |-null\n" +
 	      " |   |  -5\n" +
 	      " |   |   |-null\n" +
 	      " |   |    -null\n" +
 	      " |    -null\n" +
 	      "  -8\n" +
 	      "   |-null\n" +
 	      "    -null\n";
 	     assertEquals("Checking pretty printing of non-empty tree", result, bst.prettyPrintKeys());
 	     }

}