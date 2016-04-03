import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class IdxSort {
	private IdxSort() { }
	
	public static int [] index_sort(Comparable[] a) {
		int [] index = new int[a.length];
		for(int i =0; i < index.length; i++)
			index[i] = i;
		sort(a, index);
		return index;
    }
	public static void sort(Comparable[] a, int[] idx) {
		int[] aux = new int [idx.length];
		sort(a, idx, aux, 0, (a.length - 1));
       // assert isSorted(a);
    }

	public static void sort(Comparable[] a, int[] idx, int[] aux, int lo, int hi) {
		if(hi <= lo)
			return;
		int mid = lo + (hi -lo)/2;
		sort(a, idx, aux, lo, mid);
		sort(a, idx, aux, mid + 1, hi);
		merge(a, idx, aux, lo, mid, hi);
    }
	public static void merge(Comparable[] a, int[] idx, int[] aux, 
								int lo, int mid, int hi) {
		//assert isSorted(a, lo, mid);	
		//assert isSorted(a, mid + 1, hi);
		for(int i = 0; i <= hi; i++) //copy to save space first
			aux[i] = idx[i];

		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++){
			if(i > mid)
				idx[k] = aux[j++];
			else if(j > hi)
				idx[k] = aux[i++];
			else if(less(a[aux[j]], a[aux[i]]))
				idx[k] = aux[j++];
			else
				idx[k] = aux[i++];

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
	 
	
    /**
     * Reads in a sequence of strings from standard input; insertion sorts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
    	int N = Integer.parseInt(args[0]);
		Double [] arr = new Double[N];
		int perm[];
		for(int i = 0; i < N; i++){
				arr[i] = StdRandom.uniform();
			}
        perm = IdxSort.index_sort(arr);
		for(int i = 0; i < N; i++){
				StdOut.println(perm[i]+ "     : " + arr[i]);
			}
   
    }
	
}


