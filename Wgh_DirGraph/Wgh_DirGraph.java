import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Wgh_DirGraph{

	private final int v;
	private int e;
	private Bag<Wgh_DirEdge>[] adj;  // vertex v releated adj wgh_edge
	public Wgh_DirGraph(int v){
		this.v = v;
		this.e = 0;
		this.adj = (Bag<Wgh_DirEdge>[]) new Bag[this.v];
		for(int i = 0; i < v; i++)
			adj[i] = new Bag<Wgh_DirEdge>();
	}

	public Wgh_DirGraph(In in){
		this(in.readInt());
		int E = in.readInt();
		if(E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			if (v < 0 || v >= this.v) 
				throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (this.v-1));
            if (w < 0 || w >= this.v) 
				throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (this.v-1));
			double weight = in.readDouble();
			Wgh_DirEdge tmp = new Wgh_DirEdge(v, w, weight);
			addEdge(tmp);
		}
	}
	public Iterable<Wgh_DirEdge> adj(int v){
		if((v >= 0) && (v < this.v))
			return adj[v];
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");


	}
	public Iterable<Wgh_DirEdge> edges(){
			Bag<Wgh_DirEdge> ret_bag = new Bag<Wgh_DirEdge>();
			for(int i = 0; i < this.v; i++){
				for(Wgh_DirEdge eg : this.adj[i])
						ret_bag.add(eg);	
				}
			return ret_bag;
	}

	public void addEdge(Wgh_DirEdge eg){
		if(eg == null)
			throw new NullPointerException("edge is null");
		int v = eg.from();
		this.adj[v].add(eg);
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
            for (Wgh_DirEdge w : this.adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();

	}

	
	

	public static void main(String args[]){
        In in = new In(args[0]);
        Wgh_DirGraph G = new Wgh_DirGraph(in);
        StdOut.println(G);
    }
	
}
		


