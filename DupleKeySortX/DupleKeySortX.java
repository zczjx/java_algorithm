import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class DupleKeySortX {
	public  static long ex_cnt = 0;
	private DupleKeySortX() { }
	
	public static void sort(Comparable[] a) {
		sort(a, 0, (a.length - 1));
			
    }


	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int i = lo, j = hi + 1;
		int p = lo, q = hi + 1;
		
		Comparable var = a[lo];
		
		while(true){
			while(less(a[++i], var)){
				if(i == hi)
					break;
			}
			while(less(var, a[--j])){
				if(j == lo)
					break;
			}
			if((i == j) && eq(a[i], var))
				exch(a, ++p, i);
			
			if(i >= j)
				break;
			exch(a, i, j);
			if(eq(a[i], var))
				exch(a, ++p, i);
			if(eq(a[j], var))
				exch(a, --q, j);
		}
		i = j + 1;
		for(int k = lo; k <= p; k++)
			exch(a, k, j--);
		for(int k = hi; k >= q; k--)
			exch(a, k, i++);
		sort(a, lo, j);
		sort(a, i, hi);
    }

	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
	private static boolean eq(Comparable v, Comparable w) {
        return v.compareTo(w) == 0;
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
		Integer [] foo = new Integer[N];
		for(int i = 0; i < N; i++){
				if((StdRandom.uniform() >= 0) && (StdRandom.uniform() < 0.25) )
					foo[i] = arr[i] = 0;
				else if((StdRandom.uniform() >= 0.25) && (StdRandom.uniform() < 0.5) )
					foo[i] = arr[i] = 1;
				else if((StdRandom.uniform() >= 0.5) && (StdRandom.uniform() < 0.75) )
					foo[i] = arr[i] = 2;
				else
					foo[i] = arr[i] = 3;
		}

		Stopwatch timer1 = new Stopwatch();
        DupleKeySortX.sort(arr);
		StdOut.println("Xsort is : " + timer1.elapsedTime());
		StdOut.println("Xsort exch : " + DupleKeySortX.ex_cnt);
		Stopwatch timer2 = new Stopwatch();
		DupleKeySort.sort(foo);
		StdOut.println("sort is : " + timer2.elapsedTime());
		StdOut.println("sort exch : " + DupleKeySort.ex_cnt);
	
    }
	
}


