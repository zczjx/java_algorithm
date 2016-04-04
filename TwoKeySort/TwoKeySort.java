import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class TwoKeySort {
	private TwoKeySort() { }
	
	public static void sort(Comparable[] a) {
		sort(a, 0, (a.length - 1));
			
    }


	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int node = partition(a, lo, hi);
    }

	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, lt = lo, gt = hi;
		while(i <= gt){
			int cmp = a[i].compareTo(a[lt]);
			if(cmp < 0)
				exch(a, lt++, i++);
			else if(cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}
		
		return lt;

	}
	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
	 private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	 
	
    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
    	int N = Integer.parseInt(args[0]);
		Integer [] arr = new Integer[N];
		for(int i = 0; i < N; i++){
				if(StdRandom.uniform() >= 0.5)
					arr[i] = 1;
				else
					arr[i] = 0;
		}

		StdOut.println("before sort:");
		for(int i = 0; i < N; i++){
				StdOut.println(arr[i]);
		}
		
        TwoKeySort.sort(arr);
		StdOut.println("after sort:");
		for(int i = 0; i < N; i++){
				StdOut.println(arr[i]);
		}
   
    }
	
}


