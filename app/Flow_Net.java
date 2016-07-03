import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Flow_Net{

	private final int v;
	private int e;
	private Bag<Flow_EG>[] adj;  // vertex v releated adj wgh_edge
	public Flow_Net(int v){
		this.v = v;
		this.e = 0;
		this.adj = (Bag<Flow_EG>[]) new Bag[this.v];
		for(int i = 0; i < v; i++)
			adj[i] = new Bag<Flow_EG>();
	}

	public Flow_Net(In in){
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
			double cap = in.readDouble();
			Flow_EG tmp = new Flow_EG(v, w, cap);
			addEdge(tmp);
		}
	}
	public Iterable<Flow_EG> adj(int v){
		if((v >= 0) && (v < this.v))
			return adj[v];
		else
			throw new IllegalArgumentException("Number of vertices must be nonnegative");


	}
	public Iterable<Flow_EG> edges(){
			Bag<Flow_EG> ret_bag = new Bag<Flow_EG>();
			for(int i = 0; i < this.v; i++){
				for(Flow_EG eg : this.adj(i))
					if(eg.to() != i)
						ret_bag.add(eg);	
				}
			return ret_bag;
	}

	public void addEdge(Flow_EG eg){
		if(eg == null)
			throw new NullPointerException("edge is null");
		int v = eg.from();
		int w = eg.to();
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
            for (Flow_EG eg : this.adj(v)) {
				if(eg.to() != v)
                	s.append(eg + " ");
            }
            s.append("\n");
        }
        return s.toString();

	}

	
	

	public static void main(String args[]){
        In in = new In(args[0]);
        Flow_Net G = new Flow_Net(in);
        StdOut.println(G);
    }
	
}
		


