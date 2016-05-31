import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class Acy_LP{

	private Wgh_DirEdge [] edgeTo; // instead of var_queue for exchange item convenient
	private double [] distTo; // edgeTo[w].weight()
	
	public Acy_LP(Wgh_DirGraph G, int start){
		this.edgeTo = new Wgh_DirEdge[G.vertex_cnt()];
		this.distTo = new double[G.vertex_cnt()];
		for(int i = 0; i < G.vertex_cnt(); i++)
			this.distTo[i] = Double.NEGATIVE_INFINITY;
		distTo[start] = 0.0;
		Topo_order top	  = new Topo_order(G);
		if(top.isDAG()){
			for(int v : top.order()){
				for(Wgh_DirEdge eg : G.adj(v))
					relax_eg(eg);
			}
		}
		else
			StdOut.println("has cycle in the graph");
			
		
	}

	private void relax_eg(Wgh_DirEdge eg){
		int v = eg.from();
		int w = eg.to();
		if(distTo[w] < (distTo[v] + eg.weight())){
				distTo[w] = distTo[v] + eg.weight();
				edgeTo[w] = eg;
		}


	}
	private void relax(Wgh_DirGraph G, int v){
		if(distTo[v] == Double.POSITIVE_INFINITY)
			return;
		for(Wgh_DirEdge eg : G.adj(v)){
			int w = eg.to();
			if(distTo[w] > (distTo[v] + eg.weight())){
				distTo[w] = distTo[v] + eg.weight();
				edgeTo[w] = eg;
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
		Acy_LP task = new Acy_LP(g, start);
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
		


