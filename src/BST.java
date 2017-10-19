
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;             // root of BST
    /**
     * Private node class.
     */
    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int N;             // number of nodes in subtree

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    // is the symbol table empty?
    public boolean isEmpty() { return size() == 0; }

    // return number of key-value pairs in BST
    public int size() { return size(root); }

    // return number of key-value pairs in BST rooted at x
    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    /**
     *  Search BST for given key.
     *  Does there exist a key-value pair with given key?
     *
     *  @param key the search key
     *  @return true if key is found and false otherwise
     */
    public boolean contains(Key key) {
        return get(key) != null;
    }

    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { return get(root, key); }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.val;
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void put(Key key, Value val) {
        if (val == null) { delete(key); return; }
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.val   = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }

    public void delete(Key key) {
      //TODO fill in the correct implementation.
    	 if (contains(key))
         root = delete(root, key);
    }
    private Node delete(Node x, Key key){

        int cmp = key.compareTo(x.key);           // find node to delete
        if      (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        
        else{
             if (x.right == null) {
            	 return x.left;  // if right node is null replace node with left node
            	 }
             if (x.left  == null) {
            	 return x.right; // if left node is null replace node with right node
            	 }
             
             Node tmp =x;       // node to delete   
             x=max(tmp.left);   // node to replace
             Node toDelete=x;  
             delete(toDelete.key); // delete node from previous position
             x.left=tmp.left;     // update branches 
             x.right=tmp.right;
             
        }
         x.N = size(x.left) + size(x.right) + 1; // update size
         return x;
    }
    
    private Node max(Node x) {   
        if (x.right == null) return x; // find max node of root x
        
        else return max(x.right); 
    }
    
    public String printKeysInOrder() {
        if (isEmpty()) return "()";
         return "("+printKeysInOrder(root)+")";
      }
      
      private String printKeysInOrder(Node x) {
          if (x==null) return "";
          
          return"("+ printKeysInOrder(x.left)+")"+x.key+"("+printKeysInOrder(x.right)+")";
       }
      

}