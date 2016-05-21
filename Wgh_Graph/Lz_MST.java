import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stopwatch;


public class Lz_MST{

	private boolean[] marked;
	private Var_queue<Wgh_Edge> mst;
	private MinPQ<Wgh_Edge> tmp_edge;
	private double total_wgh;
	
	public Lz_MST(Wgh_Graph G){
		tmp_edge = new MinPQ<Wgh_Edge>();
		marked = new boolean[G.vertex_cnt()];
		mst = new Var_queue<Wgh_Edge>();
		this.total_wgh = 0;
		explore_v(G, 0);
		while(!tmp_edge.isEmpty()){
			Wgh_Edge e = tmp_edge.delMin();
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w])
				continue;
			mst.enqueue(e);
			this.total_wgh += e.weight();
			if(!marked[v])
				explore_v(G, v);
			if(!marked[w])
				explore_v(G, w);
		}
	}

	public Iterable<Wgh_Edge> edges(){
		return this.mst;

	}
	private double weight(){
		return this.total_wgh;
	}

	private void explore_v(Wgh_Graph G, int v){
		this.marked[v] = true;
		for(Wgh_Edge eg : G.adj(v))
			if(!marked[eg.other(v)])
				tmp_edge.insert(eg);
	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
        Wgh_Graph G = new Wgh_Graph(in);
		Stopwatch timer = new Stopwatch();
       	Lz_MST mst  = new Lz_MST(G);
		Double use_t = timer.elapsedTime();
		for(Wgh_Edge e : mst.edges())
			StdOut.println(e);

		StdOut.println("total weight : " + mst.weight());
		StdOut.println("time use is : " + use_t);
	
    }
}
		


