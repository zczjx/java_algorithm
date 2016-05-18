import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Closure{

	private Dir_dfs[] all;
	private int cnt;

	public Closure(Dir_Graph G){
		this.all = new Dir_dfs[G.vertex()];
		for(int i = 0; i < G.vertex(); i++)
			this.all[i] = new Dir_dfs(G, i);
	}

	public boolean is_reachable(int v, int w){
		return this.all[v].is_marked(w);
	}

	
	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph g = new Dir_Graph(in);
		Closure task = new Closure(g);
		while(!StdIn.isEmpty()){
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			if(task.is_reachable(v, w))
				StdOut.println(v + " - " + w + " can reach!");
			else
				StdOut.println(v + " - " + w  + " can NOT reach!");

		}
		

    }

	
}
		


