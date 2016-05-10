import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Graph_base{

	private final int V;
	private int e;
	private Bag<Integer>[] adj;

	public Graph_base(int V){
		if(V < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		this.V = V;
		this.e = 0;
		this.adj = (Bag<Integer>[]) new Bag[V];
		for(int i = 0; i < V; i++)
			this.adj[i] = new Bag<Integer>();
		
	}
	public Graph_base(In in){
		this(in.readInt());
		int E = in.readInt();
		if(E < 0)
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
		StdOut.println("v is :" + this.V);
		StdOut.println("e is :" + this.e);
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
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
			this.e++;
			adj[v].add(w);
			adj[w].add(v);
		}
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");
	}

	public int degree(int v){
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
		Graph_base g = new Graph_base(in);
		StdOut.println(g);

    }
}
		


