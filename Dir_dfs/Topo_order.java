import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Topo_order{

	private Iterable<Integer> order;
	
	public Topo_order(Dir_Graph G){
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
		String filename = args[0];
		String sep		= args[1];
		Sym_Dir_Graph sdg = new Sym_Dir_Graph(filename,sep);
		Topo_order top	  = new Topo_order(sdg.graph());
		for(int v : top.order())
			StdOut.println(sdg.vertex_name(v));
		
    }

	
}
		


