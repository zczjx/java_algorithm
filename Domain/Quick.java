import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Quick {
	private Quick() { }
	public static void sort(Comparable[] a) {
		sort(a, 0, (a.length - 1));
		//show(a);
        assert isSorted(a);
		
			
    }

	public static void sort(Comparable[] a, int lo, int hi) {
		if(hi <= lo)
			return;
		int node = partition(a, lo, hi);
		sort(a, lo, node - 1);
		sort(a, node + 1, hi);
    }

	private static int partition(Comparable[] a, int lo, int hi){
		int i = lo, j = hi + 1;
		
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
			if(i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;

	}
	private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
	 private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	 
	private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    } 
	private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

	 private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        show(a);
    }
	
}


