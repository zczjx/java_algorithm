import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

public class Order_dfs{

	private boolean[] marked;
	private Var_queue<Integer> pre;
	private Var_queue<Integer> post;
	private Stack_by_list<Integer> rev_post;
	
	public Order_dfs(Dir_Graph G){
		this.marked = new boolean[G.vertex()];
		pre = new Var_queue<Integer>();
		post = new Var_queue<Integer>();
		rev_post = new Stack_by_list<Integer>();
		
		for(int i = 0; i < G.vertex(); i++)
			this.marked[i] = false;

		
		for(int v = 0; v < G.vertex(); v++)
			if(!this.marked[v])
				do_dfs(G, v);
	}

	
	private void  do_dfs(Dir_Graph G, int v){
		pre.enqueue(v);
		this.marked[v] = true;
		for(int w : G.adj(v)){
			if(!this.marked[w])
				do_dfs(G, w);
		}
		post.enqueue(v);
		rev_post.push(v);
	}

	
	public Iterable<Integer> pre_order(){

		return this.pre;


	}

	public Iterable<Integer> post_order(){

		return this.post;

	}

	
	
	public Iterable<Integer> reversePost_order(){

		return this.rev_post;

	}
	
	public static void main(String args[]){
		In in = new In(args[0]);
		Dir_Graph g = new Dir_Graph(in);
		Order_dfs task = new Order_dfs(g);
		StdOut.println("pre is :");
		for(int w: task.pre_order())
			StdOut.print(w + " - ");
		
		StdOut.println(" ");

		StdOut.println("post is :");
		for(int w: task.post_order())
			StdOut.print(w + " - ");
		
		StdOut.println(" ");

		StdOut.println("reverse post is :");
		for(int w: task.reversePost_order())
			StdOut.print(w + " - ");
		
		StdOut.println(" ");
			
			
			

    }

	
}
		


