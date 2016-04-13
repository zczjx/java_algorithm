import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.util.Arrays;


public class Distinct{

	public static int distinct(Comparable[] a){
		if(a.length == 0)
			return 0;
		Arrays.sort(a);
		int distinct = 1;
		for(int i = 1; i < a.length; i++)
			if(a[i].compareTo(a[i-1]) != 0)
				distinct++;
		return distinct;
	}
	public static void main(String args[]){
		int M = Integer.parseInt(args[0]);
		int N = Integer.parseInt(args[1]);
		int T = Integer.parseInt(args[2]);
		int [] result = new int[T];
		for(int t = 0; t < T; t++){
			Integer [] arr = new Integer[N];
			for(int i = 0; i < N; i++)
				arr[i] = StdRandom.uniform(M);
			result[t] = distinct(arr);
		}
		double pra_val = StdStats.mean(result);
		double alpha = N / M;
		double theore_val = M * (1 - Math.exp(-alpha));
		StdOut.printf("Theoretical = %.3f\n", theore_val);
		StdOut.printf("Practical   = %.3f\n", pra_val);
	}
		
}


