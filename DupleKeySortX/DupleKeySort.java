import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class DupleKeySort {
	public  static long ex_cnt = 0;
	private DupleKeySort() { }
	
	public static void sort(Comparable[] a) {
		sort(a, 0, (a.length - 1));
			
    }


	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int i = lo + 1, lt = lo, gt = hi;
		Comparable node = a[lo];
		// do partion here and generate lt & gt
		while(i <= gt){
			int cmp = a[i].compareTo(node);
			if(cmp < 0)
				exch(a, lt++, i++);
			else if(cmp > 0)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
    }

	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
	 private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
		ex_cnt++;
    }
	 
	
    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
    	int N = Integer.parseInt(args[0]);
		Integer [] arr = new Integer[N];
		for(int i = 0; i < N; i++){
				if((StdRandom.uniform() >= 0) && (StdRandom.uniform() < 0.25) )
					arr[i] = 0;
				else if((StdRandom.uniform() >= 0.25) && (StdRandom.uniform() < 0.5) )
					arr[i] = 1;
				else if((StdRandom.uniform() >= 0.5) && (StdRandom.uniform() < 0.75) )
					arr[i] = 2;
				else
					arr[i] = 3;
		}

		StdOut.println("before sort:");
		for(int i = 0; i < N; i++){
				StdOut.print(arr[i] + "  ");
		}
		StdOut.println("  ");
        DupleKeySort.sort(arr);
		StdOut.println("after sort:");
		for(int i = 0; i < N; i++){
				StdOut.print(arr[i] + "  ");
		}
   		StdOut.println("  ");
    }
	
}


