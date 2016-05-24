import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stopwatch;



public class Kruskal_MST{

	private Var_queue<Wgh_Edge> mst;
	private double total_wgh;
	
	public Kruskal_MST(Wgh_Graph G){
		mst = new Var_queue<Wgh_Edge>();
		MinPQ<Wgh_Edge> tmp_edge_pq = new MinPQ<Wgh_Edge>(G.vertex_cnt());
		this.total_wgh = 0;
		for(Wgh_Edge e : G.edges())
			tmp_edge_pq.insert(e);

		Qk_union_fast uf = new Qk_union_fast (G.vertex_cnt());
		
		while(!tmp_edge_pq.isEmpty() 
			&& mst.size() < (G.vertex_cnt() - 1)){

			Wgh_Edge eg = tmp_edge_pq.delMin();
			int v = eg.either();
			int w = eg.other(v);
			if(uf.is_connected(v, w))
				continue;
			uf.mk_union(v, w);
			mst.enqueue(eg);
			this.total_wgh += eg.weight();
		}
		
	}

	public Iterable<Wgh_Edge> edges(){
		
		return this.mst;
	}
	
	private double weight(){
		return this.total_wgh;
	}

	
	
	public static void main(String args[]){
		In in = new In(args[0]);
        Wgh_Graph G = new Wgh_Graph(in);
		Stopwatch timer = new Stopwatch();
       	Kruskal_MST mst  = new Kruskal_MST(G);
		Double use_t = timer.elapsedTime();
		for(Wgh_Edge e : mst.edges())
			StdOut.println(e);

		StdOut.println("total weight : " + mst.weight());
		StdOut.println("time use is : " + use_t);
	
    }
}
		


