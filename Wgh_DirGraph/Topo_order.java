import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Topo_order{

	private Iterable<Integer> order;
	

	public Topo_order(Wgh_DirGraph G){
		Dir_cycle c_check = new Dir_cycle(G);
		if(!c_check.has_cycle()){
			Order_dfs top = new Order_dfs(G);
			this.order = top.reversePost_order();
		}	
	}

	
	public boolean isDAG(){
		return order != null;

	}
	
	
	public Iterable<Integer> order(){

		return this.order;

	}
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Wgh_DirGraph sdg = new Wgh_DirGraph(in);
		Topo_order top	  = new Topo_order(sdg);
		for(int v : top.order())
			StdOut.print(v + "-->");
		StdOut.println(" ");
		
    }

	
}
		


