import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.NoSuchElementException;
import java.util.Arrays;
public class Sort_Suffix_V2{
	private final char[] txt_arr;
	private final int[] idx; //idx[i] point to txt.substring(i, len)
	private final int len;

	public Sort_Suffix_V2(String txt){
		this.len = txt.length();
		txt = txt + '\0';
		this.txt_arr = txt.toCharArray();
		this.idx = new int[this.len]; 
		for(int i = 0; i < this.len; i++)
			idx[i] = i;
		sort(0, len-1, 0);
	}

	private void sort(int lo, int hi, int d){
		if(hi <= lo)
			return;
		int lt = lo, gt = hi;
		char val = txt_arr[idx[lo] + d];
		int i = lo + 1;
		while(i <= gt){
			char t = txt_arr[idx[i] + d];
			if(t < val)
				exch(lt++, i++);
			else if(t > val)
				exch(i, gt--);
			else
				i++;
		}
		sort(lo, lt-1, d);
		if(val > 0)
			sort(lt, gt, d+1);
		sort(gt+1, hi, d);
	}

	private boolean less(int i, int j, int d){
		if(i == j)
			return false;
		i = i + d;
		j = j + d;

		while((i < len) && (j < len)){
			if(txt_arr[i] < txt_arr[j])
				return true;
			if(txt_arr[i] > txt_arr[j])
				return false;
			i++;
			j++;
		}
		return i > j;
	}
	private void exch(int i, int j){
		int swp = idx[i];
		idx[i] = idx[j];
		idx[j] = swp;
	}

	private int compare(String q, int i){
		int m = q.length();
		int j = 0;
		while(i < len && j < len){
			if(q.charAt(j) != txt_arr[i])
				return q.charAt(j) - txt_arr[i];
			i++;
			j++;
		}
		if(i < len)
			return -1;
		if(j < m)
			return +1;
		return 0;

	}
	
	public int length(){
		return this.len;
	}

	public String select(int i){
		if(i < 0 || i >= len)
			return null;
		return new String(txt_arr, idx[i], len -idx[i]);
	}

	public int index_in_string (int i){
		if(i < 0 || i >= len)
			return -1;
		return idx[i];
	}

	public int lcp(int i){
		if(i < 1 || i >= len)
			return -1;
		return lcp(idx[i], idx[i-1]);
	}

	private int lcp(int i, int j){
		int tmp_len = 0;
		while(i < len && j < len){
			if(txt_arr[i] != txt_arr[j])
				return tmp_len;
			i++;
			j++;
			tmp_len++;
		}
		
		return tmp_len;
	}

	public int rank(String key){
		int lo = 0, hi = this.len - 1;
		while(lo <= hi){
			int mid = lo + (hi - lo)/2;
			int cmp = compare(key, idx[mid]);
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
		Sort_Suffix_V2 sa = new Sort_Suffix_V2(s);
		String lsr = "";
		for(int i = 1; i < N; i++){
			int len = sa.lcp(i);
			if(len > lsr.length())
				lsr = sa.select(i).substring(0, len);
		}
		StdOut.println(lsr);
	}

}