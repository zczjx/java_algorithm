import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

public class Domain
	implements Comparable<Domain>
{
	private String[] fields;
	private int nr;
	public Domain(){


	}
	public Domain(String name){
		this.fields = name.split("\\.");
		this.nr = this.fields.length;
	}
	public int	compareTo(Domain o){

		for(int i = 0; i < Math.min(this.nr, o.nr); i++){
			String s = this.fields[this.nr - i - 1];
			String t = o.fields[o.nr - i - 1];
			int ret = s.compareTo(t);
			if(ret < 0)
				return -1;
			else if (ret > 0)
				return 1;

		}
		return this.nr - o.nr;

	}
	@Override
	public String	toString(){
		if(this.nr == 0)
			return " ";
		String s = this.fields[0];
		for (int i = 1; i < this.nr; i++)
			s = this.fields[i] + "." + s;
		return s;
	}
	public static void main(String args[]){
		String [] arr = StdIn.readAllStrings();
		Domain[] dms = new Domain[arr.length];
		for(int i = 0; i < dms.length; i++)
				dms[i] = new Domain(arr[i]);
		Quick.sort(dms);
		for(int i = 0; i < dms.length; i++)
			StdOut.println(dms[i]);
	}	
}


