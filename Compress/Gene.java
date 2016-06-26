import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Alphabet;
import java.util.NoSuchElementException;



public class Gene
{
	private static final Alphabet DNA = new Alphabet("ACGT");
	
	public Gene(){
		
	}

	public static void compress(){
		String s = BinaryStdIn.readString();
		int N = s.length();
		BinaryStdOut.write(N);
		for(int i = 0; i < N; i++){
			int d = DNA.toIndex(s.charAt(i));
			BinaryStdOut.write(d, 2);
		}
		BinaryStdOut.close();
	}

	public static void expand(){
		int N = BinaryStdIn.readInt();
		for(int i=0; i < N; i++){
			char c = BinaryStdIn.readChar(2);
			BinaryStdOut.write(DNA.toChar(c), 8);

		}
		BinaryStdOut.close();
	}
	
	public static void main(String args[]){
		if(args[0].equals("-")){
			compress();
			StdOut.println(" ");
		}
			
		else if(args[0].equals("+")){
			expand();
			StdOut.println(" ");
		}
		
	}
}

