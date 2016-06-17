import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class BM_Sub
{
	private String pat;
	private int[] right;
	public BM_Sub(String pat){
		this.pat = pat;
		int p_len = pat.length();
		int R = 256;
		right = new int[R];
		for(int c = 0; c < R; c++)
			right[c] = -1;
		for(int j = 0; j < p_len; j++)
			right[pat.charAt(j)] = j;
		
	}

	public int search(String txt){
		int N = txt.length();
		int p_len = pat.length();
		int skip;
		for(int i = 0; i <= N - p_len; i += skip){
			skip = 0;
			StdOut.println("i is " + i);
			for(int j = p_len - 1; j >= 0; j--){
				if(pat.charAt(j) != txt.charAt(i+j)){
					skip = j - right[txt.charAt(i+j)];
					if(skip < 1)
						skip = 1;
					break;
				}
			}
			if(skip == 0)
				//this round cmp over
				return i;
			StdOut.println("skip is " + skip);
			
		}
		return N;
		
	}
	
	
	public static void main(String args[]){

		String pat = args[0];
		String txt = args[1];
		BM_Sub kmp = new BM_Sub(pat);
		int offset = kmp.search(txt);
		StdOut.println(txt);
		for(int i = 0; i < offset; i++)
			StdOut.print(" ");
		StdOut.println(pat);
		
	}
}

