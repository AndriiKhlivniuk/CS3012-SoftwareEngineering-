import java.util.ArrayList;
import java.util.List;


public  class DAG<Key extends Comparable<Key>> {
		public Node root; // root of BST


		class Node {
			List<Node> successor;
			Key key;

			public Node(Key key) {
				this.key = key;
			}
		}

		public Node addNode(Key key) { // adds node to the root of the graph
			if (root == null)
				return root = new Node(key);
			if (root.successor == null)
				root.successor = new ArrayList<Node>();

			Node tmp = new Node(key);
			root.successor.add(tmp);
			return tmp;
		}

		public Node connectNew(Key key, Key newNode) { //adds new node and connect it to existing node
			Node find = search(key);
			if (find.successor == null)
				find.successor = new ArrayList<Node>();
			Node tmp = new Node(newNode);
			find.successor.add(tmp);
			return tmp;
		}

		public Node search(Key key) {     // search node with a given key
			if (root.key == key)
				return root;
			return search(root, key,root);
		}

		private Node search(Node find, Key key, Node ans) {
			
			if (find.key == key){
			  ans=find;
			  return ans;
			}
			int i = 0;

			if (find.successor != null) {
				while ((i) < find.successor.size()) {
					
					ans=search(find.successor.get(i), key,ans);
					 i++;
				}
			}
		    return ans;
		}
		
		public boolean connect(Key first, Key second){    // connect two existing nodes according to DAG graph structure
			boolean toCheck=false;
			Node firstN=search(first);
			Node secondN=search(second);
			toCheck=connectCheck(root, firstN, secondN, 0,true);
				
			
			if(toCheck){
				if (firstN.successor == null)
					firstN.successor = new ArrayList<Node>();
				firstN.successor.add(secondN);
				return true;
			}
			return false;
		}
		private boolean connectCheck(Node head, Node first, Node second, int count, boolean ans){ // checks if it is possible to connect two nodes according to DAg graph structure
			int i = -1;
			if(head.key==second.key)
				count++;
			if(count>0&&head.key==first.key){				
				ans= false;
				return ans;
			}
			if (head.successor != null) {
				
				while ((i + 1) < head.successor.size()) {
					i++;
					 ans=connectCheck(head.successor.get(i), first, second,count,ans);
				}	
			}
			
			return ans;
		}
		public Node LCA (Key first, Key second){   // find LCA for two nodes in DAG graph
			Node firstN=search(first);
			Node secondN=search(second);
			if(first==second)
				return firstN;
			return LCA(root,firstN, secondN,root);
		}
		private Node LCA(Node head, Node first, Node second, Node ans){
			int i = -1;
		    if(head.key==first.key){
		    	ans=searchLCA(first,second.key,ans,first);
		    	return ans;
		    }
		    if(head.key==second.key){
		    	ans=searchLCA(second,first.key,ans,second);
		    	return ans;
		    }
			if (head.successor != null) {
				
				while ((i + 1) < head.successor.size()) {
					i++;
					ans=LCA(head.successor.get(i), first, second,ans);
				}	
			}
			
			return ans;
		}
	private Node searchLCA(Node find, Key key, Node ans, Node start) {
			
			if (find.key == key){
			  ans=start;
			  return ans;
			}
			int i = 0;

			if (find.successor != null) {
				while ((i) < find.successor.size()) {
					
					ans=searchLCA(find.successor.get(i), key,ans, start);
					 i++;
				}
			}
		    return ans;
		}
	
	public boolean isConnected(Key first, Key second){ // checks if second node is successor of first
		Node firstN=search(first);
		Node secondN=search(second);
		if(firstN.successor==null)
			return false;
		int i=-1;
		while ((i+1)<firstN.successor.size()){
			i++;
			if(firstN.successor.get(i).key==secondN.key)
				return true;
		}
		return false;
	}

	}
