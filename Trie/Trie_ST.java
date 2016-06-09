import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class Trie_ST<Value> 
{
	private static int Collect_sz = 256;
	private Node root;

	
	private static class Node{
		private Object val;
		private Node[] next = new Node[Collect_sz];		
	}
	
	public Trie_ST(){
	}
	
	public void put(String k, Value v){
		this.root = put(root, k, v, 0);		
	}

	private Node put(Node x, String k, Value v, int d){
		if(x == null){
			x = new Node();
			x.val = null;
		}
		if(d == k.length()){
			x.val = v;
			return x;
		}
		char c = k.charAt(d);
		x.next[c] = put(x.next[c], k, v, d+1);	
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
		return get(x.next[c], k, d+1);
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
		if(d == k.length())
			x.val = null;
		else{
			char c = k.charAt(d);
			x.next[c] = delete(x.next[c], k, d+1);
		}
		if(x.val != null)
			return x;
		for(char c = 0; c < Collect_sz; c++)
			if(x.next[c] != null)
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
		for(char c = 0; c < Collect_sz; c++)
			cnt += size(x.next[c]);
		return cnt;
	}

	public String longestPrefixOf(String s){
		int len = search(root, s, 0 ,0);
		return s.substring(0, len);
	}
	
	private int search(Node x, String s, int d, int len){
		if(x == null)
			return len;
		if(x.val != null)
			len = d;
		if(d == s.length())
			return len;
		char c = s.charAt(d);
		return search(x.next[c], s, d+1, len);
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
			q.enqueue(s);
		for(char c = 0; c < Collect_sz; c++)
			collect(x.next[c], s + c, q);
	}
	
	public Iterable<String> key_match(String pat){
		Var_queue<String> q = new Var_queue<String>();
		collect(root, "", pat, q);
		return q;
	}

	private void collect(Node x, String s, String pat, Var_queue<String> q){
		int d = s.length();
		if(x == null)
			return;
		if(d == pat.length() && x.val != null)
			q.enqueue(s);
		if(d == pat.length())
			return;
		char cmp = pat.charAt(d);
		
		for(char c = 0; c < Collect_sz; c++)
			if(cmp =='.' || cmp == c)
				collect(x.next[c], s + c, pat, q);
	}
	public Iterable<String> keys(){
		return keywithprefix("");
	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Trie_ST<Integer> st = new Trie_ST<Integer>();
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

