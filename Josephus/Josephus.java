import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Josephus{
	public static void main(String args[]){
		Var_queue<Integer> kill_q;
		kill_q = new Var_queue<Integer>();
		int N = Integer.parseInt(args[0]);
		int M = Integer.parseInt(args[1]);
		for(int i = 0; i < N; i++)
			kill_q.enqueue(i + 1);
		
		while(kill_q.size() > 1){
			for(int i = 0; i < M; i++)
				kill_q.enqueue(kill_q.dequeue());
			StdOut.println(kill_q.dequeue() + " be killed!!!");
		}
		StdOut.println("the reviver is : " + kill_q.dequeue());
	}
	

}

