import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Path_Dfs{

	private boolean[] marked;
	private int cnt;

	private final int start;
	private int [] edgeTo;

	public Path_Dfs(Graph_base G, int start){
		this.marked = new boolean[G.vertex()];
		this.edgeTo = new int[G.vertex()];
		for(int i = 0; i < G.vertex(); i++)
			this.marked[i] = false;
		this.start = start;
		this.cnt = 0;
		do_dfs(G, start);
		
		
	}

	public boolean has_PathTo(int v){
		if(v < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		return this.marked[v];
	}

	public int count(){
		return this.cnt;

	}

	public Iterable<Integer> pathTo(int v){
		if(!has_PathTo(v))
			return null;
		Stack_by_list<Integer> path = new Stack_by_list<Integer>();
		for(int w = v; w != start; w = this.edgeTo[w])
			path.push(w);
		path.push(start);
		return path;
	}
	private void  do_dfs(Graph_base G, int v){
		this.cnt++;
		this.marked[v] = true;
		for(int w : G.adj(v)){
			if(!this.marked[w]){
				this.edgeTo[w] = v;
				do_dfs(G, w);
			}
		}
	}

	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Graph_base g = new Graph_base(in);
		int start = Integer.parseInt(args[1]);
		Path_Dfs task = new Path_Dfs(g, start);
		
		for(int v = 0; v < g.vertex(); v++){
			if(task.has_PathTo(v)){
				StdOut.println(start + " to " + v + " : ");
				StdOut.print(start);
				for(int x : task.pathTo(v)){
					if(x != start)
						StdOut.print("-" + x);
				}
				StdOut.println();
			}
			else
				StdOut.println(start + " to " + v + ": is NOT connected");
		}
			

    }

	
}
		


