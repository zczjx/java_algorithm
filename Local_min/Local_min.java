import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;
import java.util.Arrays;

public class Local_min{

	public static boolean nor_local_min(int[] a) {
        int N = a.length;
        for (int i = 1; i < (N-1); i++) {
			if((a[i] < a[i-1]) && (a[i] < a[i+1])){
				StdOut.println("a[i-1]: " + a[i-1]);
				StdOut.println("a[i]: " + a[i]);
				StdOut.println("a[i+1]: " + a[i+1]);
				return true;
			}
        }
        return false;
    } 
	public static boolean bin_local_min(int[] a) {
		int N = a.length;
		int [] a_left;
		int [] a_right;
		if(N < 3)
			return false;
		if((a[N/2] < a[(N/2)-1]) && (a[N/2] < a[(N/2)+1])){
				StdOut.println("a[i-1]: " + a[(N/2)-1]);
				StdOut.println("a[i]: " + a[(N/2)]);
				StdOut.println("a[i+1]: " + a[(N/2)+1]);
				return true;
			}
		a_left  =  Arrays.copyOfRange(a, 0, N/2);
		a_right =  Arrays.copyOfRange(a, N/2, N-1);
		if(bin_local_min(a_left))
			return true;
		else if(bin_local_min(a_right))
			return true;
		else
        	return false;
    }
	public static void main(String[] args){
		String mth = args[0];
		boolean ret = false;
		In in = new In(args[1]);
		int arr[] = in.readAllInts();
		Stopwatch t = new Stopwatch();
		if(mth.equals("bin")){
			ret = bin_local_min(arr);

		}
		else if(mth.equals("nor")){
			ret = nor_local_min(arr);
		}
		else
			StdOut.println("error param " + mth);

	
		StdOut.println("time use  = " + t.elapsedTime());
		if(ret == true)
			StdOut.println("find the min point! " );
		else
			StdOut.println("Not find the min point! " );

	}


}

