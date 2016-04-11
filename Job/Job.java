import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

public class Job<Key>
	implements Comparable<Job>
{
	private Key    cont;
	private Double time;
	public Job(){


	}
	public Job(Key obj, Double time){
		this.cont = obj;
		this.time = time;
	}
	public int	compareTo(Job o){
		if(this.time < o.time)
			return -1;
		if(this.time > o.time)
			return 1;
		return 0;

	}
	public String	toString(){

		return (String) this.cont;
	}
	public void add_time(Double time){
		this.time += time;
		
	}

	public double get_time(){
		return this.time;	
	}

	public Key get_cont(){
		return this.cont;	
	}
	public static void main(String args[]){
		int N = Integer.parseInt(args[0]);
		Job<String> [] jobs = new Job[N];
		for(int i = 0; i < N; i++){
			String name = StdIn.readString();
			double time = StdIn.readDouble();
			jobs[i] = new Job<String>(name, time);
		}
		StdOut.println("per task time use with no sort is:");
		double t = 0;
		for(int i = 0; i < N; i++)
			t += jobs[i].get_time() * (N - i);
		t /= N;
		StdOut.println(t + "  secs");
		Quick.sort(jobs);
		StdOut.println("per task time use after sort is:");
		for(int i = 0; i < N; i++)
			t += jobs[i].get_time() * (N - i);
		t /= N;
		StdOut.println(t + "  secs");

		StdOut.println("Job schedule list: ");
		for(int i = 0; i < N; i++)
			StdOut.println(jobs[i].get_cont() + " : " + jobs[i].get_time());
		
	}
		
}


