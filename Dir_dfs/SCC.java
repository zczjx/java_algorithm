import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class SCC{
	private boolean[] marked;
	private int scc_cnt;
	private int [] scc_id;
	
	public SCC(Dir_Graph G){
		this.marked = new boolean[G.vertex()];
		this.scc_id = new int[G.vertex()];
		this.scc_cnt = 0;
		Order_dfs task = new Order_dfs(G.reverse());
		for(int v : task.reversePost_order())
			if(!this.marked[v]){
				do_dfs(G, v);
				this.scc_cnt++;
			
			}
				
	}

	
	public boolean isSCC(int v, int w){
		return this.scc_id[v] == this.scc_id[w];

	}

	private void  do_dfs(Dir_Graph G, int v){
		this.marked[v] = true;
		this.scc_id[v] = this.scc_cnt;
		for(int w : G.adj(v)){
			if(!this.marked[w])
				do_dfs(G, w);
		}
	}
	public int scc_cnt(){

		return this.scc_cnt;
	
	}

	public int scc_id(int v){
		return this.scc_id[v];

	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph G = new Dir_Graph(in);
		SCC scc = new SCC(G);
		
		// number of connected components
		int M = scc.scc_cnt();
		StdOut.println(M + " components");
		
				// compute list of vertices in each strong component
		Var_queue<Integer>[] components = (Var_queue<Integer>[]) new Var_queue[M];
		for (int i = 0; i < M; i++) 
			components[i] = new Var_queue<Integer>();
			
		for (int v = 0; v < G.vertex(); v++) 
			components[scc.scc_id(v)].enqueue(v);
	
		
		for (int i = 0; i < M; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}

    }

	
}
		


