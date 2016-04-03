import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class MergeSortX {
	private static final int INSERT_NR = 7;
	private MergeSortX() { }
	
	private static void insertionSort(Comparable[] a, int lo, int hi) {
			for (int i = lo; i <= hi; i++)
				for (int j = i; j > lo && less(a[j], a[j-1]); j--)
					exch(a, j, j-1);
	}
	public static void sort(Comparable[] a) {
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, (a.length - 1));
        assert isSorted(a);
    }

	public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo + INSERT_NR){
			insertionSort(a, lo, hi);
			return;
		}
			
		int mid = lo + (hi -lo)/2;
		sort(a, aux, lo, mid);
		sort(a, aux, mid + 1, hi);
		System.arraycopy(a,lo,aux,lo,hi - lo + 1);
		if(!less(a[mid + 1], a[mid]))
			return;
		merge(a, aux, lo, mid, hi);
    }
	public static void merge(Comparable[] a, Comparable[] aux, 
								int lo, int mid, int hi) {
		assert isSorted(a, lo, mid);	
		assert isSorted(a, mid + 1, hi);
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++){
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if(less(aux[j], aux[i]))
				a[k] = aux[j++];
			else
				a[k] = aux[i++];

		}
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
        MergeSortX.sort(a);
        show(a);
    }
	
}


