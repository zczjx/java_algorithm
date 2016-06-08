import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Mag_SD {
	private static int Collect_sz = 256;
	private static String [] aux;

	private Mag_SD() { }

	private static int charAt(String s, int d){
		if(d < s.length())
			return s.charAt(d);
		else
			return -1;

	}
	public static void sort(String[] a) {
		int n = a.length;
		aux = new String[n];
		sort(a, 0, n-1, 0);
	}
		
	private static void sort(String[] a, int lo, int hi, int d){
		if(hi <= lo)
			return;
		int [] count = new int[Collect_sz + 2];
		for(int i = lo; i <= hi; i++)
			//stat from magnificant char
			count[charAt(a[i], d) + 2]++ ;

		for(int r = 0; r < (Collect_sz+1); r++){
				//change count[] to index list
				count[r+1] += count[r];
				//StdOut.println(count[r]);
		}

		 for (int i = lo; i <= hi; i++)
                aux[count[charAt(a[i], d) + 1]++] = a[i];
		
		for(int j = lo; j <= hi; j++)
				a[j] = aux[j - lo];

		for(int r = 0; r < Collect_sz; r++)
			sort(a, lo + count[r], lo + count[r+1] - 1, d+1);
		
	}
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
		int N = a.length;
		Mag_SD.sort(a);
		for(int i = 0; i < N; i++)
			StdOut.println(a[i]);

    }
	
}


