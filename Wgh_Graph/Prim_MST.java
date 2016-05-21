import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stopwatch;



public class Prim_MST{

	private boolean[] marked;
	
	private Wgh_Edge [] edgeTo; // instead of var_queue for exchange item convenient
	private double [] distTo; // edgeTo[w].weight()
	
	private IndexMinPQ<Double> tmp_edge;
	private double total_wgh;
	
	public Prim_MST(Wgh_Graph G){
		marked = new boolean[G.vertex_cnt()];
		edgeTo = new Wgh_Edge [G.vertex_cnt()];
		distTo = new double[G.vertex_cnt()];
		for(int v = 0; v < G.vertex_cnt(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		
		tmp_edge = new IndexMinPQ<Double>(G.vertex_cnt());
		
		distTo[0] = 0.0;
		tmp_edge.insert(0, 0.0);
		this.total_wgh = 0;
		while(!tmp_edge.isEmpty()){
				explore_v(G, tmp_edge.delMin());
		}
	}

	public Iterable<Wgh_Edge> edges(){
		Var_queue<Wgh_Edge> mst = new Var_queue<Wgh_Edge>();
		for(int v = 0; v < edgeTo.length; v++){
			Wgh_Edge e = edgeTo[v];
			if(e != null)
				mst.enqueue(e);
		}	
		return mst;
	}
	
	private double weight(){
		for(int v = 0; v < edgeTo.length; v++){
			Wgh_Edge e = edgeTo[v];
			if(e != null)
				this.total_wgh += distTo[v];
		}	
		return this.total_wgh;
	}

	private void explore_v(Wgh_Graph G, int v){
		this.marked[v] = true;
		for(Wgh_Edge eg : G.adj(v)){
			int w = eg.other(v);
			if(marked[w])
				continue;
			
			if(eg.weight() < distTo[w]){
				edgeTo[w] = eg;
				distTo[w] = eg.weight();
				if(tmp_edge.contains(w))
					tmp_edge.change(w, distTo[w]);
				else
					tmp_edge.insert(w, distTo[w]);
				
			}
		}
	}
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
        Wgh_Graph G = new Wgh_Graph(in);
		Stopwatch timer = new Stopwatch();
       	Prim_MST mst  = new Prim_MST(G);
		Double use_t = timer.elapsedTime();
		for(Wgh_Edge e : mst.edges())
			StdOut.println(e);

		StdOut.println("total weight : " + mst.weight());
		StdOut.println("time use is : " + use_t);
	
    }
}
		


