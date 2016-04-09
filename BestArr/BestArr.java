import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class BestArr {
	private BestArr() { }
	
	public static Integer [] best(int N) {
		Integer [] a = new Integer [N];
		for(int i =0; i < N; i++)
			a[i] = i;
		best(a, 0, N-1);
		return a;
		//show(a);
		
			
    }

	public static void best(Comparable[] a, int lo, int hi) {
		for(int i = lo; i < hi; i++)
			assert a[i] == i;
		if(hi <= lo)
			return;
		int mid = lo + (hi -lo)/2;
		best(a, lo, mid - 1);
		best(a, mid + 1, hi);
		exch(a, lo, mid);
    }
	 private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	 

	 private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; insertion bests them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String ab = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int N = Integer.parseInt(args[0]);
		Integer [] a = BestArr.best(N);
		for(int i =0; i < N; i++)
			StdOut.print(ab.charAt(a[i]));
        StdOut.println(" ");
    }
	
}


