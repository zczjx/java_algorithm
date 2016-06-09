import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class Three_ST<Value> 
{
	private Node root;

	private class Node{
		char c;
		Node left, mid, right;
		private Value val;
			
	}
	
	public Three_ST(){
	}
	
	public void put(String k, Value v){
		this.root = put(root, k, v, 0);		
	}

	private Node put(Node x, String k, Value v, int d){
		char c = k.charAt(d);
		if(x == null){
			x = new Node();
			x.c = c;
		}
		if(c < x.c)
			x.left = put(x.left, k, v, d);
		else if (c > x.c)
			x.right = put(x.right, k, v, d);
		else if(d < (k.length() - 1))
			x.mid = put(x.mid, k, v, d+1);
		else
			x.val = v;
		
		return x;
	}
	public Value get(String k){
		Node x = get(this.root, k, 0);
		if(x == null)
			return null;
		return (Value) x.val;
	}

	private Node get(Node x, String k, int d){
		if(x == null)
			return null;	
		if(d == k.length())
			return x;
		char c = k.charAt(d);
		if(c < x.c)
			return get(x.left, k, d);
		else if (c > x.c)
			return get(x.right, k, d);
		else if(d < (k.length() - 1))
			return get(x.mid, k, d+1);
		else
			return x;
	}

	public void delete(String k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(!contains(k))
			return ;
		this.root = delete(root, k, 0);
		
	}

	private Node delete(Node x, String k, int d){
		if(x == null)
			return null;
		if(d == (k.length() - 1))
			x.val = null;
		else{
			char c = k.charAt(d);
			if(c < x.c)
				x.left = delete(x.left, k, d);
			else if (c > x.c)
				x.right = delete(x.right, k, d);
			else if(d < (k.length() - 1))
				x.mid = delete(x.mid, k, d+1);
		}
		if(x.val != null)
			return x;
		if((x.left != null) || (x.mid != null) || (x.right != null))
			return x;
		return null;
	}
	
	public boolean contains(String k){
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

	private int size(Node x){
		if(x == null)
			return 0;
		int cnt = 0;
		if(x.val != null)
			cnt++;
		cnt += size(x.left);
		cnt += size(x.mid);
		cnt += size(x.right);
		return cnt;
	}

	public String longestPrefixOf(String s){
		int len = search(root, s, 0 ,0);
		return s.substring(0, len);
	}
	
	private int search(Node x, String s, int d, int len){
		if(x == null)
			return len;
		char c = s.charAt(d);
		if(c < x.c)
			return search(x.left, s, d, len);
		else if (c > x.c)
			return search(x.right, s, d, len);
		else if(d < (s.length() - 1) && x.val != null)
			return search(x.mid, s, d+1, len);
		else
			len = d;
		return len;
	
	}

	public Iterable<String> keywithprefix(String pre){
		
		Var_queue<String> q = new Var_queue<String>();
		collect(get(root, pre, 0), pre, q);
		return q;
	}

	private void collect(Node x, String s, Var_queue<String> q){
		if(x == null)
			return;
		if(x.val != null)
			q.enqueue(s + x.c);
		
		if(x.left != null)
			collect(x.left, s, q);
		if(x.mid != null)
			collect(x.mid, s + x.c, q);
		if(x.right != null)
			collect(x.right, s, q);
	}
	
	public Iterable<String> key_match(String pat){
		Var_queue<String> q = new Var_queue<String>();
		collect(root, "", pat, q);
		return q;
	}

	private void collect(Node x, String s, String pat, Var_queue<String> q){
		int d = s.length()-1;
		if(x == null)
			return;
		if(d == pat.length() && x.val != null)
			q.enqueue(s);
		if(d == pat.length())
			return;
		
		char cmp = pat.charAt(d);
		
		if(cmp =='.' || cmp < x.c)
			collect(x.left, s + x.left.c, pat, q);
		else if(cmp =='.' || cmp == x.c)
			collect(x.mid, s + x.c, pat, q);
		else if(cmp =='.' || cmp > x.c)
			collect(x.right, s + x.right.c, pat, q);

	}
	public Iterable<String> keys(){
		return keywithprefix("");
	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Three_ST<Integer> st = new Three_ST<Integer>();
		for(int i = 0; !in.isEmpty(); i++){
			String s = in.readString();
			StdOut.println(s + " : " + i);
			st.put(s, i);
		}
		for(String s : st.keys())
			StdOut.println(s);

		StdOut.println("start query......");
	
		while(!StdIn.isEmpty()){
			String k = StdIn.readString();
			StdOut.println(k + " : " + st.get(k));
		}
		
		
	}
}

