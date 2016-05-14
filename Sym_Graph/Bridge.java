import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.GraphGenerator;
import edu.princeton.cs.algs4.Graph;

public class Bridge{
	private int bridge_cnt;
	private int v_cnt;

	private int [] pre;
	private int [] set_id;
	

	public Bridge(Graph g){
		this.pre = new int[g.V()];
		this.set_id= new int[g.V()];
		for(int v = 0; v < g.V(); v++){
			this.pre[v] = -1;
			this.set_id[v] = -1;
		}

		for(int v = 0; v < g.V(); v++){
			if(this.pre[v] == -1)
				dfs(g, v, v);
		}
	}

	public int components(){
		return this.bridge_cnt + 1;

	}

	private void dfs(Graph g, int pre_v, int curr_v){
		this.pre[curr_v] = this.v_cnt++;
		this.set_id[curr_v] = this.pre[curr_v];
		for(int w : g.adj(curr_v)){
			if(this.pre[w] == -1){ //uncheck point
				dfs(g, curr_v, w);
				this.set_id[curr_v] = Math.min(this.set_id[w], this.set_id[curr_v]);
				if(this.pre[w] == this.set_id[w]){ //connected entry
					StdOut.println(curr_v + "-" + w + " is a bridge");
					this.bridge_cnt++;
				}
			}
			else if(w !=  pre_v)
				// meet connected cycle
				this.set_id[curr_v] = Math.min(this.set_id[curr_v], this.pre[w]);

		}
	}
	public static void main(String args[]){
		int v = Integer.parseInt(args[0]);
		int e = Integer.parseInt(args[1]);
		Graph g = GraphGenerator.simple(v, e);
		StdOut.println(g);

		Bridge brd = new Bridge(g);
		StdOut.println("Have components = " + brd.components());
	}
}







