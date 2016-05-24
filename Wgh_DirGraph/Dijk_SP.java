import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class Dijk_SP{

	private Wgh_DirEdge [] edgeTo; // instead of var_queue for exchange item convenient
	private double [] distTo; // edgeTo[w].weight()
	private IndexMinPQ<Double> pq_tmp_edge;
	
	public Dijk_SP(Wgh_DirGraph G, int start){
		this.edgeTo = new Wgh_DirEdge[G.vertex_cnt()];
		this.distTo = new double[G.vertex_cnt()];
		this.pq_tmp_edge = new IndexMinPQ<Double>(G.vertex_cnt());
		
		for(int i = 0; i < G.vertex_cnt(); i++)
			this.distTo[i] = Double.POSITIVE_INFINITY;
		distTo[start] = 0.0;
		pq_tmp_edge.insert(start, 0.0);
		while(!pq_tmp_edge.isEmpty())
			relax(G, pq_tmp_edge.delMin());
		
	}

	private void relax(Wgh_DirGraph G, int v){
		for(Wgh_DirEdge eg : G.adj(v)){
			int w = eg.to();
			if(distTo[w] > (distTo[v] + eg.weight())){
				distTo[w] = distTo[v] + eg.weight();
				edgeTo[w] = eg;
				if(pq_tmp_edge.contains(w))
					pq_tmp_edge.change(w, distTo[w]);
				else
					pq_tmp_edge.insert(w, distTo[w]);
			}
		}
		
	}
	
	public double distTo(int v){
		return this.distTo[v];


	}
	public boolean has_PathTo(int v){
		return this.distTo[v] < Double.POSITIVE_INFINITY;
	}

	public Iterable<Wgh_DirEdge> pathTo(int v){
		if(!has_PathTo(v))
			return null ;
		Stack_by_list<Wgh_DirEdge> path = new Stack_by_list<Wgh_DirEdge>();
		for(Wgh_DirEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()])
			path.push(e);
		return path;
	}
	
	
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Wgh_DirGraph g = new Wgh_DirGraph(in);
		int start = Integer.parseInt(args[1]);
		Dijk_SP task = new Dijk_SP(g, start);
		for(int v = 0; v < g.vertex_cnt(); v++){
			StdOut.print(start + " to " + v);
			StdOut.print(" : " + task.distTo(v));
			StdOut.println(" ");
			if(task.has_PathTo(v)){
				for(Wgh_DirEdge eg: task.pathTo(v))
					StdOut.print(" " + eg);
			}
			StdOut.println(" ");
		}
    }
}
		


