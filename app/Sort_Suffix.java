import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Arrays;
public class Sort_Suffix{
	private final String[] suffix;
	private final int len;

	public Sort_Suffix(String txt){
		this.len = txt.length();
		this.suffix = new String[this.len];
		for(int i = 0; i < this.len; i++)
			suffix[i] = txt.substring(i);
		Quick3_sort.sort(suffix);
		
	}

	public int length(){
		return this.len;
	}

	public String select(int idx){
		return this.suffix[idx];

	}

	public int index_in_string (int i){
		return this.len - this.suffix[i].length();
	}

	public int lcp(int i){
		return lcp(suffix[i], suffix[i-1]);
	}

	private static int lcp(String s1, String s2){
		int nr = Math.min(s1.length(), s2.length());
		for(int i = 0; i < nr; i++)
			if(s1.charAt(i) != s2.charAt(i))
				return i;
		return nr;
	}

	public int rank(String key){
		int lo = 0, hi = this.len - 1;
		while(lo <= hi){
			int mid = lo + (hi - lo)/2;
			int cmp = key.compareTo(this.suffix[mid]);
			if(cmp < 0)
				hi = mid - 1;

			else if(cmp > 0)
				lo = mid + 1;

			else
				return mid;
		}
		return -1;
	}

	
	public static void main(String args[]){
		String s = StdIn.readAll();
		int N = s.length();
		Sort_Suffix sa = new Sort_Suffix(s);
		String lsr = "";
		for(int i = 1; i < N; i++){
			int len = sa.lcp(i);
			if(len > lsr.length())
				lsr = sa.select(i).substring(0, len);
		}
		StdOut.println(lsr);
	}

}