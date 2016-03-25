import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/*
  p1 --> p2 --> p3 --> p4 -->q
*/


public class Erdos {
	
	public static int count(int N){
		int nr = 0;
		Qk_union_cpre  test = new Qk_union_cpre (N);
		while(test.count() > 1){
		 int p = StdRandom.uniform(N);
		 int q = StdRandom.uniform(N);
		 test.mk_union(p, q);
		 nr++;
		}
		return nr;
	}
	public static void main(String args[]){
		int N = Integer.parseInt(args[0]);
		StdOut.println(count(N));	
	}
}


