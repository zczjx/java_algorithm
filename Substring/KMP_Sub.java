import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class KMP_Sub
{
	private String pat;
	private int[][] dfa;
	public KMP_Sub(String pat){
		this.pat = pat;
		int p_len = pat.length();
		int R = 256;
		this.dfa = new int[R][p_len];
		dfa[pat.charAt(0)][0] = 1;  //from 0 --> 1
		for(int X = 0, j = 1; j < p_len; j++){
			for(int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][X];
			dfa[pat.charAt(j)][j] = j + 1;
			X = dfa[pat.charAt(j)][X];
		}
		
	}

	public int search(String txt){
		int i ,j;
		int N = txt.length();
		int p_len = pat.length();
		for(i = 0, j = 0; i < N && j < p_len; i++)
			j = dfa[txt.charAt(i)][j];
		if(j == p_len)
			return i - p_len;
		else
			return N;
	}
	
	
	public static void main(String args[]){

		String pat = args[0];
		String txt = args[1];
		KMP_Sub kmp = new KMP_Sub(pat);
		int offset = kmp.search(txt);
		StdOut.println(txt);
		for(int i = 0; i < offset; i++)
			StdOut.print(" ");
		StdOut.println(pat);
		
	}
}

