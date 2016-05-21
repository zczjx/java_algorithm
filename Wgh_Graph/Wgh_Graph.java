import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Wgh_Graph{

	private final int v;
	private int e;
	private Bag<Wgh_Edge>[] adj;  // vertex v releated adj wgh_edge
	public Wgh_Graph(int v){
		this.v = v;
		this.e = 0;
		this.adj = (Bag<Wgh_Edge>[]) new Bag[this.v];
		for(int i = 0; i < v; i++)
			adj[i] = new Bag<Wgh_Edge>();
	}

	public Wgh_Graph(In in){
		this(in.readInt());
		int E = in.readInt();
		if(E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			double weight = in.readDouble();
			Wgh_Edge tmp = new Wgh_Edge(v, w, weight);
			addEdge(tmp);
		}
	}
	public Iterable<Wgh_Edge> adj(int v){
		if((v >= 0) && (v < this.v))
			return adj[v];
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");


	}
	public Iterable<Wgh_Edge> edges(){
			Bag<Wgh_Edge> ret_bag = new Bag<Wgh_Edge>();
			for(int i = 0; i < this.v; i++){
				int selfloop = 0;
				for(Wgh_Edge eg : this.adj[i]){
					if(eg.other(i) > i)
						ret_bag.add(eg);
					else if(eg.other(i) == i){
						if((selfloop % 2) == 0)
							ret_bag.add(eg);	
					}
				}
			}
			
			return ret_bag;
	}

	public void addEdge(Wgh_Edge eg){
		if(eg == null)
			throw new NullPointerException("edge is null");
		int v = eg.either();
		int w = eg.other(v);
		this.adj[v].add(eg);
		this.adj[w].add(eg);
		this.e++;
	}
	
	public int vertex_cnt(){
		return this.v;

	}

	public int edge_cnt(){
		return this.e;

	}
	public String toString(){
		StringBuilder s = new StringBuilder();
        s.append(this.v + " vertices, " + this.e + " edges " + "\n");
        for (int v = 0; v < this.v; v++) {
            s.append(v + ": ");
            for (Wgh_Edge w : this.adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();

	}

	
	

	public static void main(String args[]){
        In in = new In(args[0]);
        Wgh_Graph G = new Wgh_Graph(in);
        StdOut.println(G);
    }
	
}
		


