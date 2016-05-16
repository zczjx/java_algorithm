import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Dir_Graph{

	private final int V;
	private int e;
	private Bag<Integer>[] adj;

	public Dir_Graph(int V){
		if(V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.e = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < V; i++)
			this.adj[i] = new Bag<Integer>();
		
	}
	public Dir_Graph(Dir_Graph G){
		this(G.vertex());
		this.e = G.edge();
		for(int i = 0; i < G.vertex(); i++){
			Stack_by_list<Integer> rev = new Stack_by_list<Integer>();
			for(int w : G.adj(i))
				rev.push(w);
			for(int w : rev)
				this.adj[i].add(w);
		}

	}
	public Dir_Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		if(E < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}

	Dir_Graph reverse(){
		Dir_Graph Gr = new Dir_Graph(this.vertex());
		for(int v = 0; v < this.vertex(); v++)
			for(int w : this.adj(v))
				Gr.addEdge(w, v);
		return Gr;

	}
	public int vertex(){
		return this.V;

	}

	
	public int edge(){
		return this.e;

	}
	public void addEdge(int v, int w){
		if((v >= 0) && (v < V)
		&& (w >= 0) && (w < V)){
			adj[v].add(w);
			this.e++;
		}
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
	}

	public int out_degree(int v){
		if((v >= 0) && (v < V))
			return adj[v].size();
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");

	}
	public Iterable<Integer> adj(int v){
		if((v >= 0) && (v < V))
			return adj[v];
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
	}

	
	public String toString(){
		StringBuilder s = new StringBuilder();
        s.append(this.V + " vertices, " + this.e + " edges " + "\n");
        for (int v = 0; v < this.V; v++) {
            s.append(v + ": ");
            for (int w : this.adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();

	}
	

	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph g = new Dir_Graph(in);
		StdOut.println("g is :");
		StdOut.println(g);
		
		Dir_Graph cp = new Dir_Graph(g);
		StdOut.println(" ");
		StdOut.println("cp is : ");
		StdOut.println(cp);

		
		Dir_Graph h = g.reverse();
		StdOut.println(" ");
		StdOut.println("h is : ");
		StdOut.println(h);

    }
}
		


