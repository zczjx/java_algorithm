import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

public class JobMulti<Item>
	implements Comparable<JobMulti<Item>>
{
	private Item    cont;
	private Double time;
	public JobMulti(){


	}
	public JobMulti(Item obj, Double time){
		this.cont = obj;
		this.time = time;
	}
	public int	compareTo(JobMulti o){
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

	public Item get_cont(){
		return this.cont;	
	}
	public static void main(String args[]){
		int M = Integer.parseInt(args[0]);
		int N = Integer.parseInt(args[1]);
		JobMulti<Integer> [] processor = new JobMulti[M];
		
		My_min_pq<JobMulti<Integer>> core_pq1 = new My_min_pq<JobMulti<Integer>>(M);
		My_min_pq<JobMulti<Integer>> core_pq2 = new My_min_pq<JobMulti<Integer>>(M);
		for(int i = 0; i < M; i++){
			core_pq1.insertion (new JobMulti<Integer>(i, 0.0));
			core_pq2.insertion (new JobMulti<Integer>(i, 0.0));
		}
		JobMulti<String> [] tasks = new JobMulti[N];
		for(int i = 0; i < N; i++){
			String name = StdIn.readString();
			double time = StdIn.readDouble();
			tasks[i] = new JobMulti<String>(name, time);
		}
		StdOut.println("per task time use with no sort is:");
		for(int i = 0; i < N; i++){
			JobMulti<Integer> min = core_pq1.delmin();
			min.add_time(tasks[i].get_time());
			core_pq1.insertion (min);
		}
		
		My_max_pq<JobMulti<Integer>> tmp1_pq = new My_max_pq<JobMulti<Integer>>(M);
		for(int i = 0; i < M; i++){
			JobMulti<Integer> min = core_pq1.delmin();
			tmp1_pq.insertion (min);
		}
		double t = tmp1_pq.delmax().get_time();
		StdOut.println(t + "  secs");
		My_max_pq<JobMulti<String>> task_pq = new My_max_pq<JobMulti<String>>(N);
		for(int i = 0; i < N; i++)
			task_pq.insertion (tasks[i]);

		for(int i = 0; i < N; i++){
			JobMulti<Integer> min = core_pq2.delmin();
			min.add_time(task_pq.delmax().get_time());
			core_pq2.insertion (min);
		}
		My_max_pq<JobMulti<Integer>> tmp2_pq = new My_max_pq<JobMulti<Integer>>(M);
		for(int i = 0; i < M; i++){
			JobMulti<Integer> min = core_pq2.delmin();
			tmp2_pq.insertion (min);
		}
		t = tmp2_pq.delmax().get_time();
		StdOut.println("per task time use after sort is:");
		StdOut.println(t + "  secs");
		
	}
		
}


