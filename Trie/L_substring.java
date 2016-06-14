import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

import java.util.NoSuchElementException;



public class L_substring
{
	
	public L_substring(){
	}
	
	public static void main(String args[]){
		In in = new In(args[0]);
		int cnt = Integer.parseInt(args[1]);
		Three_ST<Integer> st = new Three_ST<Integer>();
		String raw_str = in.readString();
		StdOut.println("s len is " + raw_str.length());
		for(int i = 0; i < raw_str.length() - cnt; i++){
			String tmp = raw_str.substring(i, i + cnt);
			if(!st.contains(tmp))
				st.put(tmp, i);
		}
		StdOut.println("unique String : ");
		for(String s : st.keys())
			StdOut.println(s);
	}
}

