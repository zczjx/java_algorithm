import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;


public class SortCompare {
	public static Double time(String sort_name, Double []a){
		Stopwatch timer = new Stopwatch();
		if(sort_name.equals("insertion"))
			Insertion.sort(a);
		else if(sort_name.equals("insertionx"))
			InsertionX.sort(a);
		else if(sort_name.equals("merge"))
			MergeSort.sort(a);
		else if(sort_name.equals("mergex"))
			MergeSortX.sort(a);
		else if(sort_name.equals("fast_merge"))
			FastMergeSort.sort(a);
		else if(sort_name.equals("quick"))
			Quick.sort(a);
		else
			timer.elapsedTime();

		return timer.elapsedTime();


	}

	public static Double timeRandomInput(String sort_name, int N, int T){
		Double total = 0.0;
		Double [] arr = new Double[N];
		for(int t = 0; t < T; t++){
			for(int i = 0; i < N; i++)
				arr[i] = StdRandom.uniform();
			total += time(sort_name, arr);
		}
		return total;
	}
	
	public static void main(String args[]){
		if(args.length != 4)
			throw new IllegalArgumentException("pls input 4 para: sort1,sort2,scale ,times");
		String sort1 = args[0];
		String sort2 = args[1];
		int N = Integer.parseInt(args[2]);
		int T = Integer.parseInt(args[3]);
		Double t1 = timeRandomInput(sort1, N, T);
		Double t2 = timeRandomInput(sort2, N, T);
		StdOut.printf("For %d random Doubles\n    %s is %.3f \n", N, sort1, t1); 
		StdOut.printf("For %d random Doubles\n    %s is %.3f \n", N, sort2, t2);
	}
		
}


