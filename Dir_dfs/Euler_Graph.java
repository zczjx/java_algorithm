import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Euler_Graph{
	
	private boolean is_connected;
	private Stack_by_list<Integer> cycle = null;


	public Euler_Graph(Dir_Graph G){
		Dir_dfs dfs = new Dir_dfs(G, 0);
		if(dfs.count() == G.vertex())
			this.is_connected = true;
		else
			this.is_connected = false;
		if(this.has_Euler_cycle(G)){
			Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.vertex()];
			for(int w = 0; w < G.vertex(); w++)
				adj[w] = G.adj(w).iterator();

			
			Stack_by_list<Integer> tmp = new Stack_by_list<Integer>();
			this.cycle = new Stack_by_list<Integer>();
			tmp.push(0);
			while(!tmp.isEmpty()){
				int v = tmp.pop();  //test vertex
				while(adj[v].hasNext()){
					tmp.push(v);
					v = adj[v].next();

				}
				this.cycle.push(v);
			}
			if(this.cycle.size() != (G.vertex() + 1))
				this.cycle = null;
		}
		
		
	}

    public Iterable<Integer> Euler_cycle() {
        return cycle;
    }

	public boolean has_Euler_cycle(){
		return this.cycle != null;
	}

	private boolean has_Euler_cycle(Dir_Graph G){
		if(this.is_connected == false)
			return false;
		if(G.edge() == 0)
			return false;

		for(int v = 0; v < G.vertex(); v++){
			if(G.in_degree(v)!= G.out_degree(v))
				return false;
		}
		return true;

	}

	
	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph g = new Dir_Graph(in);
		Euler_Graph task = new Euler_Graph(g);
		if(task.has_Euler_cycle()){
			StdOut.println("Euler cycle is:");
			for(int w : task.Euler_cycle())
				StdOut.print(w + "-");
		}
		else
			StdOut.println("has no Euler cycle");

    }

	
}
		


