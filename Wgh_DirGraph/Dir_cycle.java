import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Dir_cycle{

	private boolean[] marked;
	private Wgh_DirEdge [] edgeTo;

	private Stack_by_list<Wgh_DirEdge> cycle_ls;
	private boolean [] onStack;

	public Dir_cycle(Wgh_DirGraph G){
		this.marked  = new boolean[G.vertex_cnt()];
		this.edgeTo  = new Wgh_DirEdge[G.vertex_cnt()];
		this.onStack = new boolean[G.vertex_cnt()];
		this.cycle_ls = null;
		for(int i = 0; i < G.vertex_cnt(); i++)
			this.marked[i] = false;
		
		for(int v = 0; v < G.vertex_cnt(); v++)
			if(!this.marked[v])
				do_dfs(G, v);
	}

	public boolean has_cycle(){

		return (this.cycle_ls != null);
	}

	public Iterable<Wgh_DirEdge> cycle_list(){
		return this.cycle_ls;

	}

	private void  do_dfs(Wgh_DirGraph G, int v){
		this.onStack[v] = true;
		this.marked[v] = true;
		for(Wgh_DirEdge eg : G.adj(v)){
			int w = eg.to();
			if(this.has_cycle())
				return;
			else if(!this.marked[w]){
				this.edgeTo[w] = eg;
				do_dfs(G, w);
			}
			else if(this.onStack[w]){
				// be sure have cycle
				this.cycle_ls = new Stack_by_list<Wgh_DirEdge>();
				while(eg.from() != w){
					this.cycle_ls.push(eg);
					eg = edgeTo[eg.from()];
				}
				this.cycle_ls.push(eg);
				return;
			}
			// detected over
		}
		
		this.onStack[v] = false;
	}

	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Wgh_DirGraph g = new Wgh_DirGraph(in);
		Dir_cycle task = new Dir_cycle(g);
		if(task.has_cycle()){
			StdOut.println("cycle is ");
			for(Wgh_DirEdge w : task.cycle_list())
				StdOut.print(w);
		}
		else
			StdOut.println("has no cycle!");

		StdOut.println(" ");
    }

	
}
		


