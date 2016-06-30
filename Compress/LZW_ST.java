import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Alphabet;
import java.util.NoSuchElementException;



public class LZW_ST
{
	private static final int R = 256;
	private static final int Tot = 4096;
	private static final int Wid = 12;
	
	public LZW_ST(){
		
	}

	public static void compress(){
		String i_str = BinaryStdIn.readString();
		Three_ST<Integer> st = new Three_ST<Integer>();
		for(int i = 0; i < R; i++)
			st.put("" + (char) i, i);
		int code = R + 1;  // R is EOF
		while(i_str.length() > 0){
			// s is  have in t
			String s = st.longestPrefixOf(i_str);
			BinaryStdOut.write(st.get(s), Wid);
			int t = s.length();
			if(t < i_str.length() && code < Tot)
				// new codec string be added
				st.put(i_str.substring(0, t+1), code++);
			i_str = i_str.substring(t);
		}
		BinaryStdOut.write(R, Wid);
		BinaryStdOut.close();
	}

	public static void expand(){
		String[] st = new String[Tot];
		int i = 0;
		for(i = 0; i < R; i++)
			st[i] = "" + (char) i;
		st[i++] = " "; //EOF
		int codew = BinaryStdIn.readInt(Wid);
		String val = st[codew];
		while(true){
			BinaryStdOut.write(val);
			codew = BinaryStdIn.readInt(Wid);
			if(codew == R)
				break;
			String s = st[codew];
			if(i == codew)
				s = val + val.charAt(0);
			if(i < Tot)
				st[i++] = val + s.charAt(0);
			val = s;
		}
		BinaryStdOut.close();
	}
	
	public static void main(String args[]){
		if(args[0].equals("-")) 
			compress();
        else if (args[0].equals("+")) 
			expand();
       
		
	}
}

