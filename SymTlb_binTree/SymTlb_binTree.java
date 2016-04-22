import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;


class BinNode<Key extends Comparable<Key>, Val>{
		public Key k;
		public Val v;
		public int node_nr;
		BinNode<Key, Val> left;
		BinNode<Key, Val> right;

		public BinNode(){
			this.k = null;
			this.v = null;
			this.left  = null;
			this.right = null;

		}
		public BinNode(Key k, Val v, int node_nr){
			this.k     = k;
			this.v     = v;
			this.node_nr = node_nr; 
			this.left  = null;
			this.right = null;
		}
		public BinNode(Key k, Val v, 
						BinNode<Key, Val> left, BinNode<Key, Val> right, 
						int node_nr){
			this.k = k;
			this.v  = v;
			this.left  = left;
			this.right = right;
			this.node_nr = node_nr; 
		}
	}


public class SymTlb_binTree<Key extends Comparable<Key>, Val> 
{
	private BinNode<Key, Val> root;

	
	public SymTlb_binTree(){
		this.root = null;

	}
	public Key select(int k){
		if((k < 0) || k >= size())
			throw new IllegalArgumentException();
		BinNode<Key, Val> nd = select(root, k);
		return nd.k;
	}
	private BinNode<Key, Val> select(BinNode<Key, Val> node, int n){
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
	private BinNode<Key, Val> put(BinNode<Key, Val> node, Key k, Val v){
		if(node == null)
			return new BinNode<Key, Val> (k, v, 1);
		int cmp = k.compareTo(node.k);
		if(cmp < 0)
			node.left = put(node.left, k, v);

		else if(cmp > 0)
			node.right = put(node.right, k, v);
		else
			node.v = v;
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
		
	}
	public Val get(Key k){
		return get(this.root, k);
	}
	private Val get(BinNode<Key, Val> node, Key k){
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
		this.root = delete(root, k); //update root
	}
	private BinNode<Key, Val> delete(BinNode<Key, Val> node, Key k){
		if(k == null)
			return null;

		int cmp = k.compareTo(node.k);
		if(cmp < 0)
			node.left = delete(node.left, k);

		else if(cmp > 0)
			node.right = delete(node.right, k);
		else{
			if(node.right == null)
				return node.left;
			if(node.left == null)
				return node.right;
			BinNode<Key, Val> tmp = node;
			node = min(tmp.right);
			node.right = delmin(tmp.right);
			node.left  = tmp.left;
		}
		node.node_nr = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public void delmin(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		this.root = delmin(root);
	}

	private BinNode<Key, Val> delmin(BinNode<Key, Val> node){
		if(node.left == null)
			return node.right;
		node.left = delmin(node.left);
		node.node_nr = 1 + size(node.left) + size(node.right);
		return node;
	}
	
	public void delmax(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		this.root = delmax(root);
	}

	private BinNode<Key, Val> delmax(BinNode<Key, Val> node){
		if(node.right == null)
			return node.left;
		node.right = delmin(node.right);
		node.node_nr = 1 + size(node.left) + size(node.right);
		return node;
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
	private int size (BinNode<Key, Val> node){
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
	private BinNode<Key, Val> min(BinNode<Key, Val> node){
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

	private BinNode<Key, Val> max(BinNode<Key, Val> node){
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

	private int height(BinNode<Key, Val> node){
		if(node == null)
			return -1;
		return 1 + Math.max(height(node.left), height(node.right));
	}

	private int rank(BinNode<Key, Val> node, Key k){
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
	public boolean isBST(){
		return isBST(root, null, null);
	}

	
	private boolean isBST(BinNode<Key, Val> node, Key min, Key max){
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
	
	public static void main(String args[]){
		SymTlb_binTree<String, Double> gpa = new SymTlb_binTree<String, Double>();
		gpa.put("C",  2.00);
		gpa.put("C+", 2.33);
		gpa.put("D",  1.00);
		gpa.put("B",  3.00);
		gpa.put("B+", 3.33);       
        gpa.put("B-", 2.67);
		gpa.put("A",  4.00);
		gpa.put("A-", 3.67);
		gpa.put("A+", 4.33);
        gpa.put("F",  0.00);
		StdOut.println(gpa.isBST());

		StdOut.println("before del size is: " + gpa.size());
		while(!StdIn.isEmpty()){
			String g = StdIn.readString();
			StdOut.println(g + " : " + gpa.get(g));
		}
		Var_queue<String> que = (Var_queue<String>)gpa.keys("A", "C");
		StdOut.println("height is :" + gpa.height());
		StdOut.println("key sort is :");
		while(!que.isEmpty())
		StdOut.println(que.dequeue());
		gpa.delete("B+");
		gpa.delete("A+");
		gpa.delete("C+");


		while(!StdIn.isEmpty()){
			String g = StdIn.readString();
			StdOut.println(g + " : " + gpa.get(g));
		}
		StdOut.println("after del size is: " + gpa.size());

		StdOut.println("height is :" + gpa.height());
	}
		
}


