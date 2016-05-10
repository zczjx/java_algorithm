import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Dfs{

	private boolean[] marked;
	private int cnt;

	public Dfs(Graph_base G, int start){
		this.marked = new boolean[G.vertex()];
		for(int i = 0; i < G.vertex(); i++)
			this.marked[i] = false;
		this.cnt = 0;
		do_dfs(G, start);
		
		
	}

	public boolean is_marked(int v){
		if(v < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		return this.marked[v];
	}

	public int count(){
		return this.cnt;

	}
	private void  do_dfs(Graph_base G, int v){
		this.cnt++;
		this.marked[v] = true;
		for(int w : G.adj(v)){
			if(!this.marked[w])
				do_dfs(G, w);

		}
	}

	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Graph_base g = new Graph_base(in);
		int start = Integer.parseInt(args[1]);
		Dfs task = new Dfs(g, start);
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
		


