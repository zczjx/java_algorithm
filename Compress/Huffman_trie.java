import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Alphabet;
import java.util.NoSuchElementException;




public class Huffman_trie
{
	private static final int R = 256;

	private static class Node implements Comparable<Node>{
		private char ch;
		private Node left, right;
		private int freq;

		public Node(char ch, int freq, Node left, Node right){
			this.ch = ch;
			this.freq = freq;
			this.left = left;
			this.right = right;
		}
		public boolean isLeaf(){
			return (this.left == null && this.right == null);
		}
		public int compareTo(Node that){
			return this.freq - that.freq;
		}	
	}
	
	public Huffman_trie(){
		
	}

	public static void compress(){
		String s = BinaryStdIn.readString();
		char [] ch_arr = s.toCharArray();
		int [] freq_stat = new int[R];
		for(int i =0; i < ch_arr.length; i++){
			freq_stat[ch_arr[i]]++;
		}
		// construct leaf node in MinPQ first
		// then construct the no-leaf node in it too
		Node root = build_huffman_trie(freq_stat);

		String [] code_tlb = new String[R];
		// give each char a new code use freq
		build_code_tlb(code_tlb, root, "");
		wr_huffman_trie(root);
		BinaryStdOut.write(ch_arr.length);

		// do compress
		for(int i =0; i < ch_arr.length; i++){
			String code = code_tlb[ch_arr[i]];
			for(int j = 0; j < code.length(); j++){
				if(code.charAt(j) == '1')
					BinaryStdOut.write(true);
				else
					BinaryStdOut.write(false);
			}
		}
		BinaryStdOut.close();
	}

	public static void expand(){
		Node root = rd_huffman_trie();
		int N = BinaryStdIn.readInt();
		for(int i = 0; i < N; i++){
			Node x = root;
			while(!x.isLeaf()){
				if(BinaryStdIn.readBoolean())
					x = x.right;
				else
					x = x.left;
				
				BinaryStdOut.write(x.ch);
				}
		}
		BinaryStdOut.close();
	}

	private static Node build_huffman_trie(int[] freq){
		MinPQ<Node> pq = new MinPQ<Node>();
		for(char c = 0; c < R; c++){
			if(freq[c] > 0)
				pq.insert(new Node(c, freq[c], null, null));
		}
		while(pq.size() > 1){
			Node x = pq.delMin();
			Node y = pq.delMin();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.insert(parent);
		}
		return pq.delMin();
	}

	private static void build_code_tlb(String[] code_tlb, Node x, String s){
		if(x.isLeaf()){
			code_tlb[x.ch] = s;
			return;
		}
		build_code_tlb(code_tlb, x.left, s + '0');
		build_code_tlb(code_tlb, x.right, s + '1');
	}
	
	private static Node rd_huffman_trie(){
		if(BinaryStdIn.readBoolean())
			// not need freq in expand now
			return new Node(BinaryStdIn.readChar(), 0, null, null);
		return new Node('\0', 0, rd_huffman_trie(), rd_huffman_trie());
	}

	private static void wr_huffman_trie(Node x){
		if(x.isLeaf()){
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch);
			return;
		}
		BinaryStdOut.write(false);
		wr_huffman_trie(x.left);
		wr_huffman_trie(x.right);
	}
	

	
	public static void main(String args[]){
		if(args[0].equals("-")) 
			compress();
        else if (args[0].equals("+")) 
			expand();
        else 
			throw new IllegalArgumentException("Illegal command line argument");

	}
}

