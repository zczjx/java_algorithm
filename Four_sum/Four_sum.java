import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;
import java.util.Arrays;

public class Four_sum{

	public static int nor_count(int[] a) {
        int N = a.length;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
					for (int m = k+1; m < N; m++)
                   		if ((a[i] + a[j] + a[k] + a[m]) == 0) 
                        	cnt++;
                }
            }
        }
        return cnt;
    } 
	private static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++)
            if (a[i] == a[i-1]) return true;
        return false;
    }
	public static int bin_count(int[] a) {
        int N = a.length;
		Arrays.sort(a);
		if (containsDuplicates(a)) 
			throw new IllegalArgumentException("array contains duplicate integers");
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
					int m = Arrays.binarySearch(a, -(a[i] + a[j] + a[k]));
                   		if (m > k) 
                        	cnt++;
                }
            }
        }
        return cnt;
    }
	public static void main(String[] args){
		String mth = args[0];
		int cnt = 0;
		In in = new In(args[1]);
		int arr[] = in.readAllInts();
		Stopwatch t = new Stopwatch();
		if(mth.equals("bin")){
			cnt = bin_count(arr);

		}
		else if(mth.equals("nor")){
			cnt = nor_count(arr);
		}
		else
			StdOut.println("error param " + mth);

	
		StdOut.println("time use  = " + t.elapsedTime());
		StdOut.println("find " + cnt + " groups");

	}


}

