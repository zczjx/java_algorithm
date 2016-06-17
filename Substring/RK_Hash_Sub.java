import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.math.BigInteger;
import java.util.Random;


import java.util.NoSuchElementException;



public class RK_Hash_Sub
{
	private String pat;
	private long pat_hash;
	private int p_len;
	private long big_prim;
	private int R ;
	private long R_p_len;  //R^(p_len-1) %  big_prim
	
	public RK_Hash_Sub(String pat){
		this.pat = pat;
		this.p_len = pat.length();
		R = 256;
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		big_prim  = prime.longValue(); 
		
		R_p_len = 1;
		
		for(int i = 1; i <= p_len - 1; i++)
			R_p_len = (R * R_p_len) % big_prim;
		
		pat_hash = hash(pat, p_len);
		
	}
	private long hash(String k, int pre_len){
		long h = 0;
		for(int j = 0; j < pre_len; j++)
			h = (R * h + k.charAt(j)) % big_prim;
		return h;
	}

	private boolean check(String s, String p, int len){

			return true;
	}
	public int search(String txt){
		int N = txt.length();
		if(N < p_len)
			return N;
		long txt_hash = hash(txt, p_len);
		if(pat_hash == txt_hash)
			if(check(txt, pat, p_len))
				return 0;
		for(int i = p_len; i < N; i++){
			//recursive function of fast hash array
            txt_hash = (txt_hash + big_prim - R_p_len * txt.charAt(i-p_len) % big_prim) % big_prim; 
            txt_hash = (txt_hash*R + txt.charAt(i)) % big_prim;


			if(pat_hash == txt_hash)
				if(check(txt, pat, p_len))
					return i - p_len + 1;
		}
		return N;
	}
	
	
	public static void main(String args[]){

		String pat = args[0];
		String txt = args[1];
		RK_Hash_Sub kmp = new RK_Hash_Sub(pat);
		int offset = kmp.search(txt);
		StdOut.println(txt);
		for(int i = 0; i < offset; i++)
			StdOut.print(" ");
		StdOut.println(pat);
		
	}
}

