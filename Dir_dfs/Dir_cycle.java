import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Dir_cycle{

	private boolean[] marked;
	private int [] edgeTo;

	private Stack_by_list<Integer> cycle_ls;
	private boolean [] onStack;

	public Dir_cycle(Dir_Graph G){
		this.marked  = new boolean[G.vertex()];
		this.edgeTo  = new int[G.vertex()];
		this.onStack = new boolean[G.vertex()];
		this.cycle_ls = null;
		for(int i = 0; i < G.vertex(); i++)
			this.marked[i] = false;
		
		for(int v = 0; v < G.vertex(); v++)
			if(!this.marked[v])
				do_dfs(G, v);
	}

	public boolean has_cycle(){

		return (this.cycle_ls != null);
	}

	public Iterable<Integer> cycle_list(){
		return this.cycle_ls;

	}

	private void  do_dfs(Dir_Graph G, int v){
		this.onStack[v] = true;
		this.marked[v] = true;
		for(int w : G.adj(v)){
			if(this.has_cycle())
				return;
			else if(!this.marked[w]){
				this.edgeTo[w] = v;
				do_dfs(G, w);
			}
			else if(this.onStack[w]){
				// be sure have cycle
				this.cycle_ls = new Stack_by_list<Integer>();
				for(int x = v; x != w; x = edgeTo[x])
					this.cycle_ls.push(x);

				this.cycle_ls.push(w);
				this.cycle_ls.push(v);
			}
			// detected over
		}
		
		this.onStack[v] = false;
	}

	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph g = new Dir_Graph(in);
		Dir_cycle task = new Dir_cycle(g);
		if(task.has_cycle()){
			StdOut.println("cycle is ");
			for(Integer w : task.cycle_list())
				StdOut.print(w + "-");
		}
		else
			StdOut.println("has no cycle!");

		StdOut.println(" ");
    }

	
}
		


