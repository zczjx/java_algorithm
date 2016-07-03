import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class F_Fukson{

	private boolean[] marked;
	private Flow_EG[] edgeTo;
	private double tot_flow;  // vertex v releated adj wgh_edge
	public F_Fukson(Flow_Net net, int st, int tm){
		while(has_augment_path(net, st, tm)){
			double min_delta = Double.POSITIVE_INFINITY;
			
			for(int v = tm; v != st; v = edgeTo[v].other(v)){
				min_delta = Math.min(min_delta, edgeTo[v].spare_capacity(v));
			}
			for(int v = tm; v != st; v = edgeTo[v].other(v))
				edgeTo[v].add_flow(v,min_delta);
			this.tot_flow += min_delta;
		}
		StdOut.println("over init");
	}

	private boolean has_augment_path(Flow_Net net, int st, int tm){
		marked = new boolean[net.vertex_cnt()];
		edgeTo = new Flow_EG[net.vertex_cnt()];
		Var_queue<Integer> q = new Var_queue<Integer>();
		q.enqueue(st);
		while(!q.isEmpty()){
			int v = q.dequeue();
	
			for(Flow_EG eg : net.adj(v)){
				int w = eg.other(v);
				if(eg.spare_capacity(w) > 0 && !marked[w]){
					edgeTo[w] = eg;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
		return marked[tm];
	}

	public double  tot_flow(){
		return tot_flow;

	}
	public boolean inCut(int v){

		return marked[v];
	}

	

	public static void main(String args[]){
        Flow_Net G = new Flow_Net(new In(args[0]));
	
		int s = 0;
		int t = G.vertex_cnt() - 1;
		StdOut.println("terminal is : " + t);
		F_Fukson max_flow = new F_Fukson(G, s, t);
		StdOut.println("flow : " + s + "-->" + t);
		for (int v = 0; v < G.vertex_cnt(); v++) {
            for (Flow_EG e : G.adj(v)) {
                if ((v == e.from()) && e.flow() > 0)
                    StdOut.println("   " + e);
            }
        }
		
		StdOut.println("Tot flow : " + max_flow.tot_flow());
		
    }
	
}
		


