import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Arrays;
public class Sort_binTree{


	public static void perfect(SymTlb_binTree<String, Integer> bst, String[] a){
		Arrays.sort(a);
		perfect(bst, a, 0, a.length - 1);
		StdOut.println();
		

	}


	private static void perfect(SymTlb_binTree<String, Integer> bst, String[] a, int lo, int hi){
		if(hi < lo)
			return;
		int mid = lo + (hi -lo)/2;
		bst.put(a[mid], mid);
		StdOut.print(a[mid] + " ");
		perfect(bst, a, lo, mid - 1);
		perfect(bst, a, mid + 1, hi);

	}

	
	public static void main(String args[]){
		String [] words = StdIn.readAllStrings();
		SymTlb_binTree<String, Integer> bst = new SymTlb_binTree<String, Integer>();
		perfect(bst, words);
		StdOut.println("bst height is: " + bst.height());
	}

}