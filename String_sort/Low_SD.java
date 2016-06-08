import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class Low_SD {
	private Low_SD() { }
	public static void sort(String[] a, int w) {
		int n = a.length;
		int list_sz = 256;
		String[] aux = new String[n];
		for(int d = w - 1; d >= 0; d--){
			//stat from low char
			int [] count = new int[list_sz + 1];
			for(int i = 0; i < n; i++)
				// count freq
				count[a[i].charAt(d) + 1]++ ;
			for(int r = 0; r < list_sz; r++)
				//change count[] to index list
				count[r+1] += count[r];
			
            for (int i = 0; i < n; i++)
                aux[count[a[i].charAt(d)]++] = a[i];

			for(int j = 0; j < n; j++)
				a[j] = aux[j];
		}


		
	
    }
	
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
		int N = a.length;
		int w = a[0].length();
		Low_SD.sort(a, w);
		for(int i = 0; i < N; i++)
			StdOut.println(a[i]);

    }
	
}


