import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Arrays;
public class Sort_RB_Tree{


	public static void perfect(SymTlb_RB_Tree<String, Integer> bst, String[] a){
		Arrays.sort(a);
		perfect(bst, a, 0, a.length - 1);
		StdOut.println();
		

	}


	private static void perfect(SymTlb_RB_Tree<String, Integer> bst, String[] a, int lo, int hi){
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
		SymTlb_RB_Tree<String, Integer> bst = new SymTlb_RB_Tree<String, Integer>();
		for(int i = 0; i < words.length; i++)
			bst.put(words[i], i);
		StdOut.println("bst height is: " + bst.height());
	}

}