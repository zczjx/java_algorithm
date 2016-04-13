import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;
import java.io.File;

public class FileSort{
	public static void main(String args[]){
		File dir = new File(args[0]);
		
		if(!dir.exists()){
			StdOut.println(args[0] + " does not exist");
			return;
		}
		if(!dir.isDirectory()){
			StdOut.println(args[0] + " is not dir");
			return;
		}
		File [] f_ls = dir.listFiles();
		Arrays.sort(f_ls);
		for(int i = 0; i < f_ls.length; i++)
			StdOut.println(f_ls[i].getName());
		
	}
		
}


