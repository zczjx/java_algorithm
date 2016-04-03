import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Inv_cnt {

	private Inv_cnt() { }
	public static long conut(Comparable[] a) {
		long inv_cnt = 0;
		Comparable[] aux = new Comparable[a.length];
		inv_cnt = conut(a, aux, 0, (a.length - 1));
		return inv_cnt;

    }

	public static long conut(Comparable[] a, Comparable[] aux, int lo, int hi) {
		if(hi <= lo)
			return 0;
		long inversions = 0;
		int mid = lo + (hi -lo)/2;
		inversions += conut(a, aux, lo, mid);
		inversions += conut(a, aux, mid + 1, hi);
		inversions += merge(a, aux, lo, mid, hi);
		return inversions;
    }
	public static long merge(Comparable[] a, Comparable[] aux, 
								int lo, int mid, int hi) { 
		for(int i = 0; i <= hi; i++) //copy to save space first
			aux[i] = a[i];
		long inversions = 0;
		int i = lo;
		int j = mid + 1;
		for(int k = lo; k <= hi; k++){
			if(i > mid)
				a[k] = aux[j++];
			else if(j > hi)
				a[k] = aux[i++];
			else if(less(aux[j], aux[i])){
				inversions += (mid  - i + 1);
				a[k] = aux[j++];
			}
			else
				a[k] = aux[i++];

		}
		return inversions;
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
     * Reads in a sequence of strings from standard input; insertion conuts them;
     * and prints them to standard output in ascending order.
     */
    public static void main(String[] args) {
    	int N = Integer.parseInt(args[0]);
       	Double [] arr = new Double[N];
		Double [] foo = new Double[N];
		for(int i = 0; i < N; i++){
				arr[i] = StdRandom.uniform();
				foo[i] = arr[i];
			}
        StdOut.println("this time have "+ Inv_cnt.conut(arr)+ " inversions!");
  		StdOut.println("insertion times is "+ Insertion_cnt.sort(foo));
    }
	
}


