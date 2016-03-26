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
	public static Transaction[]  readTransactions(){
		Var_queue<Transaction> tq = new Var_queue<Transaction>();
		while(StdIn.hasNextLine()){
			String who, when;
			double amount;
			String [] line = StdIn.readLine().split("\\s+");
			who    = line[0];
			when   = line[1];
			amount = Double.valueOf(line[2]);
			Transaction t = new Transaction(who, when, amount);
			tq.enqueue(t);
		}
		int N = tq.size();
		Transaction [] ret = new Transaction[N];
		for(int i = 0; i < N; i++)
			ret[i] = tq.dequeue();
		return ret;
	}
	public static void main(String args[]){
		Transaction [] arr = readTransactions();
		StdOut.println("Sort by amount");
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++)
            StdOut.println(arr[i]);
		
	}
}


