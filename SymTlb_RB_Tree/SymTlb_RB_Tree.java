import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
enum Color {
	RED,
	BLACK
}


class RBNode<Key extends Comparable<Key>, Val>{
		public Key k;
		public Val v;
		public int node_nr;
		public Color color;
		RBNode<Key, Val> left;
		RBNode<Key, Val> right;

		public RBNode(){
			this.k = null;
			this.v = null;
			this.left  = null;
			this.right = null;
			this.color = Color.BLACK;

		}
		public RBNode(Key k, Val v, Color color, int node_nr){
			this.k     = k;
			this.v     = v;
			this.node_nr = node_nr; 
			this.color = color;
			this.left  = null;
			this.right = null;
		}
		public RBNode(Key k, Val v, 
						RBNode<Key, Val> left, RBNode<Key, Val> right, 
						Color color, int node_nr){
			this.k = k;
			this.v  = v;
			this.left  = left;
			this.right = right;
			this.node_nr = node_nr; 
			this.color = color;
		}

	}


public class SymTlb_RB_Tree<Key extends Comparable<Key>, Val> 
{
	private RBNode<Key, Val> root;

	
	public SymTlb_RB_Tree(){
		this.root = null;

	}
	
	public boolean isRed(RBNode<Key, Val> node){
			if(node == null)
				return false;
			return node.color == Color.RED;
		}
	public Key select(int k){
		if((k < 0) || k >= size())
			throw new IllegalArgumentException();
		RBNode<Key, Val> nd = select(root, k);
		return nd.k;
	}
	private RBNode<Key, Val> select(RBNode<Key, Val> node, int n){
		if(node == null)
			return null;
		int cnt = size(node.left);
		if(cnt > n)
			return select(node.left, n);
		else if(cnt < n)
			return select(node.right, n - cnt - 1);
		else
			return node;
	}
	private RBNode<Key, Val> rotateLeft(RBNode<Key, Val> node){
		RBNode<Key, Val> tmp = node.right;
		node.right = tmp.left;
		tmp.left = node;
		tmp.color = tmp.left.color;
		tmp.left.color = Color.RED;
		tmp.node_nr = node.node_nr;
		node.node_nr = 1 + size(node.left) + size(node.right);
		return tmp;
	}

	private RBNode<Key, Val> rotateRight(RBNode<Key, Val> node){
		RBNode<Key, Val> tmp = node.left;
		node.left = tmp.right;
		tmp.right = node;
		tmp.color = tmp.right.color;
		tmp.right.color = Color.RED;
		tmp.node_nr = node.node_nr;
		node.node_nr = 1 + size(node.left) + size(node.right);
		return tmp;

	}

	private void flipcolors(RBNode<Key, Val> node){
		if(isRed(node))
			node.color = Color.BLACK;
		else
			node.color = Color.RED;
		
		if(isRed(node.left))
			node.left.color = Color.BLACK;
		else
			node.left.color = Color.RED;

		if(isRed(node.right))
			node.right.color = Color.BLACK;
		else
			node.right.color = Color.RED;
	}
	private RBNode<Key, Val> put(RBNode<Key, Val> node, Key k, Val v){
		if(node == null)
			return new RBNode<Key, Val> (k, v, Color.RED, 1);
		int cmp = k.compareTo(node.k);
		if(cmp < 0)
			node.left = put(node.left, k, v);

		else if(cmp > 0)
			node.right = put(node.right, k, v);
		else
			node.v = v;
		
		if(isRed(node.right) && !isRed(node.left)) 
			node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left)) 
			node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right)) 
			flipcolors(node);
		node.node_nr = 1 + size(node.left) + size(node.right);
		return node;
		
	}
	public void put(Key k, Val v){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(v == null){
			delete(k);
			return ;
		}
		this.root = put(root, k, v); //update root
		this.root.color = Color.BLACK;
		
	}
	public Val get(Key k){
		return get(this.root, k);
	}
	private Val get(RBNode<Key, Val> node, Key k){
		if(node == null)
			return null;
		int cmp = k.compareTo(node.k);
		if(cmp < 0)
			return  get(node.left, k);
		else if(cmp > 0)
			return  get(node.right, k);
		else
			return node.v;
	}

	public void delete(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(!contains(k))
			return ;
		if(!isRed(root.left) && !isRed(root.right))
			root.color = Color.RED;
		
		this.root = delete(root, k); //update root
		if(!isEmpty())
			root.color = Color.BLACK;
		
	}
	private RBNode<Key, Val> delete(RBNode<Key, Val> node, Key k){

		if(k.compareTo(node.k) < 0){
			// delete node in left tree
			if(!isRed(node.left) && !isRed(node.left.left))
				// left is 2- node
				node = moveRedleft(node);
			node.left = delete(node.left, k);
		}
		else{
			// delete node in right tree
			if(isRed(node.left)) //node have red left,when del big oneshould rotate before del and then be balanced
				node = rotateRight(node);
			if((k.compareTo(node.k) == 0) && (node.right == null))
				//node is leaf node, del directly
				return null;
			if(!isRed(node.right) && !isRed(node.right.left))
				// node.right is  2- node
				node = moveRedright(node);
			if(k.compareTo(node.k) == 0){
				// del node is 3- or 4- node in right
				RBNode<Key, Val> tmp = min(node.right);
				node.k = tmp.k;
				node.v = tmp.v;
				node.right = delmin(node.right);
			}
			else
				node.right = delete(node.right, k);
		}
		return balance(node);
		
	}

	private RBNode<Key, Val> moveRedleft(RBNode<Key, Val> node){
		flipcolors(node);
		if(isRed(node.right.left)){
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipcolors(node);
		}
		return node;
	}

	private RBNode<Key, Val> moveRedright(RBNode<Key, Val> node){
		flipcolors(node);
		if(isRed(node.left.left)){
			node = rotateRight(node);
			flipcolors(node);
		}
		return node;
	}

	private RBNode<Key, Val> balance(RBNode<Key, Val> node){
		if(isRed(node.right)) 
			node = rotateLeft(node);
		if(isRed(node.left) && isRed(node.left.left)) 
			node = rotateRight(node);
		if(isRed(node.left) && isRed(node.right)) 
			flipcolors(node);
		node.node_nr = 1 + size(node.left) + size(node.right);
		return node;
	}
	public void delmin(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		if(!isRed(root.left) && !isRed(root.right))
			root.color = Color.RED;
		this.root = delmin(root);
		if(!isEmpty())
			root.color = Color.BLACK;
	}

	private RBNode<Key, Val> delmin(RBNode<Key, Val> node){
		if(node.left == null)
			return null;
		if(!isRed(node.left) && !isRed(node.left.left))
				// left is 2- node
				node = moveRedleft(node);
		node.left = delmin(node.left);
		return balance(node);
	}
	
	public void delmax(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		if(!isRed(root.left) && !isRed(root.right))
			root.color = Color.RED;
		this.root = delmax(root);
		if(!isEmpty())
			root.color = Color.BLACK;
	}

	private RBNode<Key, Val> delmax(RBNode<Key, Val> node){
		if(isRed(node.left)) //node have red left,when del big oneshould rotate before del and then be balanced
			node = rotateRight(node);
		if(node.right == null)
			return null;
		if(!isRed(node.right) && !isRed(node.right.left))
				// node.right is  2- node
			node = moveRedright(node);
		
		node.right = delmax(node.right);
		return balance(node);
	}
	public boolean contains(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return get(k) != null;

	}
	public boolean isEmpty(){
		return this.size() == 0;

	}
	public int size(){
		return size(this.root);

	}
	private int size (RBNode<Key, Val> node){
		if(node == null)
			return 0;
		else
			return node.node_nr;

	}
	public Key min(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		return min(root).k;

	}
	private RBNode<Key, Val> min(RBNode<Key, Val> node){
		if(node.left == null)
			return node;
		else
			return min(node.left);

	}

	public Key max(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		return max(root).k;

	}

	private RBNode<Key, Val> max(RBNode<Key, Val> node){
		if(node.right == null)
			return node;
		else
			return max(node.right);

	}
	public int rank(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return rank(root, k);
	}
	public int height(){
		return height(this.root);
	}

	private int height(RBNode<Key, Val> node){
		if(node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	private int rank(RBNode<Key, Val> node, Key k){
		if(k == null)
			return 0;
		int cmp = k.compareTo(node.k);
		if(cmp < 0)
			return rank(node.left, k);

		else if(cmp > 0)
			return 1 + size(node.left) + rank(node.right, k);

		else
			return size(node.left);
			
	}
	public boolean isRank_comparable(RBNode<Key, Val> node){
		for(int i =0; i < size(); i++){
			if(i != rank(select(i)))
				return false;
		}
		StdOut.println("rank select");
		Var_queue<Key> foo = (Var_queue<Key>) keys(max(), min());
		while(!foo.isEmpty()){
			Key k = foo.dequeue();
			if(k.compareTo(select(rank(k))) != 0)
				return false;
		}
		StdOut.println("select rank");
		return true;
				
	}
	
	public boolean isBST(){
		return isBST(root, null, null);
	}

	
	private boolean isBST(RBNode<Key, Val> node, Key min, Key max){
		if(node == null)
			return true;

		if((min != null) && node.k.compareTo(min) <= 0)
			return false;
		if((max != null) && node.k.compareTo(max) >= 0)
			return false;

		return isBST(node.left, min, node.k) && isBST(node.right, node.k, max);

	}

	public Iterable<Key> keys(Key lo, Key hi){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(int i = rank(lo); i < rank(hi); i++)
			foo.enqueue(select(i));
		if(contains(hi))
			foo.enqueue(select(rank(hi)));
		return foo;



	}
	private boolean is23(){
		return is23(this.root);

	}

	private boolean is23(RBNode<Key, Val> node){
		if(node == null)
			return true;
		if(isRed(node.right))
			return false;
		if(node != this.root && isRed(node) && isRed(node.left))
			return false;
		return is23(node.left) && is23(node.right);
	}

	private boolean isbalanced(){
		int black = 0;
		RBNode<Key, Val> tmp = this.root;
		while(tmp != null){
			if(!isRed(tmp))
				black++;  // total black node in left
			tmp = tmp.left;
		}
		return isbalanced(this.root, black);

	}

	private boolean isbalanced(RBNode<Key, Val> node, int black_cnt){
		if(node == null)
			return black_cnt == 0;
		if(!isRed(node))
			black_cnt--;
		return isbalanced(node.left, black_cnt) && isbalanced(node.right, black_cnt);

	}
	
	public static void main(String args[]){
		String [] words = StdIn.readAllStrings();
		SymTlb_RB_Tree<String, Integer> bst = new SymTlb_RB_Tree<String, Integer>();
		for(int i = 0; i < words.length; i++)
			bst.put(words[i], i);
		StdOut.println("bst height is: " + bst.height());	
		StdOut.println("is23 ?  : " + bst.is23());
		StdOut.println("isbalanced ?  : " + bst.isbalanced());
		
	}
}

