import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Path_Bfs{

	private boolean[] marked;
	private int cnt;

	private final int start;
	private int [] edgeTo;
	private int [] distTo;

	public Path_Bfs(Graph_base G, int start){
		this.marked = new boolean[G.vertex()];
		this.edgeTo = new int[G.vertex()];
		this.distTo = new int[G.vertex()];
		for(int i = 0; i < G.vertex(); i++){
			this.marked[i] = false;
			this.distTo[i] = Integer.MAX_VALUE;
		}
		this.start = start;
		this.cnt = 0;
		do_bfs(G, start);
		
		
	}

	public boolean has_PathTo(int v){
		if(v < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		return this.marked[v];
	}

	public int distTo(int v){
		if(v < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		return this.distTo[v];
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
	private void  do_bfs(Graph_base G, int s){
		Var_queue<Integer> queue = new Var_queue<Integer>();
		this.marked[s] = true;
		this.distTo[s] = 0;
		queue.enqueue(s);
		while(!queue.isEmpty()){
			int v = queue.dequeue();
			this.cnt++;
			for(int w : G.adj(v)){
				if(!this.marked[w]){
					this.edgeTo[w] = v;
					this.distTo[w] = this.distTo[v] + 1;
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
		Path_Bfs task = new Path_Bfs(g, start);
		
		for(int v = 0; v < g.vertex(); v++){
			if(task.has_PathTo(v)){
				StdOut.println(start + " to " + v + " : " + task.distTo(v));
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
		


