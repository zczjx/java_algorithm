import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Insertion_cnt {
	private Insertion_cnt() { }
	public static long sort(Comparable[] a) {
		int N = a.length;
		long ex_cnt = 0;
		for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
                exch(a, j, j-1);
				ex_cnt++;
            }
        }
        assert isSorted(a);
		return ex_cnt;
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
        Insertion_cnt.sort(a);
        show(a);
    }
	
}


