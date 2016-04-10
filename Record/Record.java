import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

public class Record<Key>
	implements Comparable<Record>
{
	private Key cont;
	private int cnt;
	public Record(){


	}
	public Record(Key obj){
		this.cont = obj;
		this.cnt = 1;
	}
	public int	compareTo(Record o){
		if(this.cnt < o.cnt)
			return -1;
		if(this.cnt > o.cnt)
			return 1;
		return 0;

	}
	public String	toString(){

		return (String) this.cont;
	}
	public void inc_cnt(){
		this.cnt++;
		
	}

	public int get_cnt(){
		return this.cnt;	
	}

	public Key get_cont(){
		return this.cont;	
	}
	public static void main(String args[]){
		String [] arr = StdIn.readAllStrings();
		int N = arr.length;
		Arrays.sort(arr);
		Record<String>[] stat = new Record[N];
		String wd = arr[0];
		int j = 0;
		stat[j] = new Record<String>(wd);
		for(int i = 1; i < N; i++){
			if(arr[i].equals(wd))
				stat[j].inc_cnt();
			else{
				j++;
				wd = arr[i];
				stat[j] = new Record<String>(wd);
			}
				
		}
		Arrays.sort(stat,0, j);
		for(int i = 0; i < j; i++)
			StdOut.println(stat[i] + " : " + stat[i].get_cnt());

	}
		
}


