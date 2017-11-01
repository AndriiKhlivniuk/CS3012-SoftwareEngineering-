

public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right; // left and right subtrees
		private int N; // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return number of key-value pairs in BST
	public int size() {
		return size(root);
	}

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	/**
	 * Search BST for given key. Does there exist a key-value pair with given
	 * key?
	 *
	 * @param key
	 *            the search key
	 * @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * Search BST for given key. What is the value associated with given key?
	 *
	 * @param key
	 *            the search key
	 * @return value associated with the given key if found, or null if no such
	 *         key exists.
	 */
	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}

	/**
	 * Insert key-value pair into BST. If key already exists, update with new
	 * value.
	 *
	 * @param key
	 *            the key to insert
	 * @param val
	 *            the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = put(x.left, key, val);
		else if (cmp > 0)
			x.right = put(x.right, key, val);
		else
			x.val = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	public void delete(Key key) {
		// TODO fill in the correct implementation.
		if (contains(key))
			root = delete(root, key);
	}

	private Node delete(Node x, Key key) {

		int cmp = key.compareTo(x.key); // find node to delete
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);

		else {
			if (x.right == null) {
				return x.left; // if right node is null replace node with left
								// node
			}
			if (x.left == null) {
				return x.right; // if left node is null replace node with right
								// node
			}

			Node tmp = x; // node to delete
			x = max(tmp.left); // node to replace
			Node toDelete = x;
			delete(toDelete.key); // delete node from previous position
			x.left = tmp.left; // update branches
			x.right = tmp.right;

		}
		x.N = size(x.left) + size(x.right) + 1; // update size
		return x;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x; // find max node of root x

		else
			return max(x.right);
	}

	public String printKeysInOrder() {
		if (isEmpty())
			return "()";
		return "(" + printKeysInOrder(root, 0) + ")";
	}

	private String printKeysInOrder(Node x, int i) {
		if (x == null)
			return "";

		return "(" + printKeysInOrder(x.left, i) + ")" + x.key + "(" + printKeysInOrder(x.right, i) + ")";
	}

	public int height() {

		return height(root); // start counting height from the root
	}

	private int height(Node node) {
		// TODO fill in the correct implementation.
		if (node == null)
			return -1;
		else {
			return 1 + Math.max(height(node.left), height(node.right));
		} // recursively return node from left and right branches,
			// stops when node equals to null. return max value
	}

	public Key median() {
		if (isEmpty())
			return null;
		// TODO fill in the correct implementation. The running time should be
		// Theta(h), where h is the height of the tree.

		int n = (size() - 1) / 2; // position of median node
		return median(root, n); // start finding median from root
	}

	private Key median(Node x, int n) {

		int t = size(x.left); // size of the left branch
		if (t > n)
			return median(x.left, n); // if size of left branch greater then
										// median position go left
		else if (t < n)
			return median(x.right, n - t - 1); // if it smaller go right
		else
			return x.key; // if equal return median
	}

	public int findSize() {
		return findSize(root); // start from the root
	}

	private int findSize(Node node) {
		if (node == null)
			return 0;

		return 1 + findSize(node.left) + findSize(node.right); // add each left
																// and right
																// nodes
																// together

	}

	public String prettyPrintKeys() {
		// TODO fill in the correct implementation.
		if (isEmpty())
			return "-null\n";
		String prefix = "";

		return prettyPrintKeys(root, prefix);
	}

	private String prettyPrintKeys(Node x, String prefix) {
		if (x == null)
			return prefix + "-null\n";

		return "" + prefix + "-" + x.key + "\n" + prettyPrintKeys(x.left, prefix + " " + "|")
				+ prettyPrintKeys(x.right, prefix + "  ");
		// passing to function different prefix, depends on branch
	}

	// code to find Lowest Common ancestor using recursion
	public Key LowestCommonAncestor(Key x, Key y) {
		if (x == y)
			return x;
		int cmp = x.compareTo(y);
		if (cmp < 0)
			return LowestCommonAncestor(root, x, y);
		else
			return LowestCommonAncestor(root, y, x);
	}

	private Key LowestCommonAncestor(Node root, Key x, Key y) {
		int cmpX = x.compareTo(root.key);
		int cmpY = y.compareTo(root.key);
		if (cmpX > 0 && cmpY > 0)
			return LowestCommonAncestor(root.right, x, y);
		if (cmpX < 0 && cmpY < 0)
			return LowestCommonAncestor(root.left, x, y);
		else
			return root.key;
	}


}
