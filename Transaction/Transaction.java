import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Date;

import java.util.*;




public class Transaction implements Comparable<Transaction> {
	private final String who;
	private final Date   when;
	private final double amount;

	public Transaction(String who, String when, double amount){
		this.who = who;
		this.when = new Date(when);
		if(amount == 0.0)
			this.amount = 0.0;
		else
			this.amount = amount;

	}
	public int compareTo(Transaction that){
			if(this.amount > that.amount)
				return 1;
			else if(this.amount < that.amount)
				return -1;
			else
				return 0;

	}
	public  String who(){
		return this.who;

	}
	public  Date	when(){
		return this.when;


	}
	public double amount(){
		return this.amount;
	
	}
	@Override
	public String toString(){
		return String.format("%-10s %10s %8.1f", this.who, this.when, this.amount);

	}	
	public static void main(String args[]){
		Transaction [] arr = new Transaction[4];
		arr[0] = new Transaction("Clarence", "6/17/1990",  644.08);
        arr[1] = new Transaction("Summer", "3/26/2002", 4121.85);
        arr[2] = new Transaction("Evan", "6/14/1999",  288.34);
        arr[3] = new Transaction("Justin", "8/22/2007", 2678.40);
		StdOut.println("Unsorted");
		for (int i = 0; i < arr.length; i++)
            StdOut.println(arr[i]);
        StdOut.println();
		StdOut.println("Sort by amount");
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++)
            StdOut.println(arr[i]);
		
	}
}


