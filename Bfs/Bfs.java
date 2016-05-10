import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Bfs{

	private boolean[] marked;
	private int cnt;

	public Bfs(Graph_base G, int start){
		this.marked = new boolean[G.vertex()];
		for(int i = 0; i < G.vertex(); i++)
			this.marked[i] = false;
		this.cnt = 0;
		do_bfs(G, start);
		
		
	}

	public boolean is_marked(int v){
		if(v < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		return this.marked[v];
	}

	public int count(){
		return this.cnt;

	}
	private void  do_bfs(Graph_base G, int s){
		Var_queue<Integer> queue = new Var_queue<Integer>();
		this.marked[s] = true;
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			this.cnt++;
			for(int w : G.adj(v)){
				if(!this.marked[w]){
					this.marked[w] = true;
					queue.enqueue(w);
				}
			}
		}
		
	}

	
	public static void main(String args[]){
		In in = new In(args[0]);
		Graph_base g = new Graph_base(in);
		int start = Integer.parseInt(args[1]);
		Bfs task = new Bfs(g, start);
		for(int v = 0; v < g.vertex(); v++)
			if(task.is_marked(v))
				StdOut.print(v + " ");
		StdOut.println();
		if(task.count() != g.vertex())
			StdOut.println("Graph is NOT connected");
		else
			StdOut.println("Graph is connected");
		
			

    }

	
}
		


