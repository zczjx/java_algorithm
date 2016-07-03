import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Quick3_sort {

	private Quick3_sort() { }

	private static int charAt(String s, int d){
		if(d < s.length())
			return s.charAt(d);
		else
			return -1;

	}
	public static void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
		
	private static void sort(String[] a, int lo, int hi, int d){
		if(hi <= lo)
			return;
		int lt = lo, gt = hi;
		int v  = charAt(a[lo], d);
		int i = lo + 1;
		while(i <= gt){
			int tmp = charAt(a[i], d);
			if(tmp < v)
				exch(a, lt++, i++);
			else if(tmp > v)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1, d);
		if(v >= 0)
			sort(a, lt, gt, d+1);
		sort(a, gt + 1, hi, d);
		
	}
	private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
	 
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
		int N = a.length;
		Quick3_sort.sort(a);
		for(int i = 0; i < N; i++)
			StdOut.println(a[i]);

    }
	
}


