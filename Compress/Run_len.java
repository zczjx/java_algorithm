import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Alphabet;
import java.util.NoSuchElementException;



public class Run_len
{
	
	public Run_len(){
		
	}

	public static void compress(){
		char cnt = 0;
		boolean b, old = false;
		while(!BinaryStdIn.isEmpty()){
			b = BinaryStdIn.readBoolean();
			if(b != old){
				BinaryStdOut.write(cnt);
				cnt = 0;
				old = !old;

			}
			else{
				if(cnt == 255){
					BinaryStdOut.write(cnt);
					cnt = 0;
					// insert 0 byte to seperate
					BinaryStdOut.write(cnt);
				}
				cnt++;
			}
		}
		// last byte
		BinaryStdOut.write(cnt);
		BinaryStdOut.close();
	}

	public static void expand(){
		boolean b = false;
		while(!BinaryStdIn.isEmpty()){
			char cnt = BinaryStdIn.readChar();
			for(int i = 0; i < cnt; i++)
				BinaryStdOut.write(b);
			b = !b;	
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

