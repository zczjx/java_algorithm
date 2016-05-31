import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.IndexMinPQ;

public class BellFord_SP{

	private Wgh_DirEdge [] edgeTo; // instead of var_queue for exchange item convenient
	private double [] distTo; // edgeTo[w].weight()
	private boolean [] on_q_flag;
	private Var_queue<Integer> rlx_queue;
	private int rlx_cost;
	public Iterable<Wgh_DirEdge> n_cycle;
	
	public BellFord_SP(Wgh_DirGraph G, int start){
		this.edgeTo = new Wgh_DirEdge[G.vertex_cnt()];
		this.distTo = new double[G.vertex_cnt()];
		this.on_q_flag = new boolean[G.vertex_cnt()];
		this.rlx_queue = new Var_queue<Integer>();
		
		for(int i = 0; i < G.vertex_cnt(); i++)
			this.distTo[i] = Double.POSITIVE_INFINITY;
		distTo[start] = 0.0;
		rlx_queue.enqueue(start);
		on_q_flag[start] = true;
		while(!rlx_queue.isEmpty() && !has_negative_cycle()){
			int v = rlx_queue.dequeue();
			on_q_flag[v] = false;
			relax(G, v);
		}
	}

	private void relax(Wgh_DirGraph G, int v){
		for(Wgh_DirEdge eg : G.adj(v)){
			int w = eg.to();
			if(distTo[w] > (distTo[v] + eg.weight())){
				distTo[w] = distTo[v] + eg.weight();
				edgeTo[w] = eg;
				if(!on_q_flag[w]){
					rlx_queue.enqueue(w);
					on_q_flag[w] = true;
				}
			}
			if((rlx_cost++ % G.vertex_cnt()) == 0){ // be sure have negative cycle
				do_find_negative_cycle();
				if(has_negative_cycle())
					return;
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
	
	private void do_find_negative_cycle(){
		int v_cnt = edgeTo.length;
		Wgh_DirGraph tmp_g = new Wgh_DirGraph(v_cnt);
		for(int i = 0; i < v_cnt; i++){
			if(edgeTo[i] != null)
				tmp_g.addEdge(edgeTo[i]);
		}
		// have know have negative cycle 
		Dir_cycle task = new Dir_cycle(tmp_g);
		this.n_cycle = task.cycle_list();
	}

	public boolean has_negative_cycle(){
		return this.n_cycle != null;

	}

	public Iterable<Wgh_DirEdge> negative_cycle(){
		return this.n_cycle;


	}
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Wgh_DirGraph g = new Wgh_DirGraph(in);
		int start = Integer.parseInt(args[1]);
		BellFord_SP task = new BellFord_SP(g, start);
		if(task.has_negative_cycle()){
			for(Wgh_DirEdge eg : task.negative_cycle())
				StdOut.print(eg + " ");
			StdOut.println(" ");
		}
		else{	
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
}
		


