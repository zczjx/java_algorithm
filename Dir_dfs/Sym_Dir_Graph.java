import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Sym_Dir_Graph{
	private SymTlb_RB_Tree<String, Integer> st;
	private String [] keys;
	private Dir_Graph graph;

	
	public Sym_Dir_Graph(String filename, String delm){
		this.st = new SymTlb_RB_Tree<String, Integer>();
		In in = new In(filename);
		while(!in.isEmpty()){
			String [] tmp = in.readLine().split(delm);
			for(int i = 0; i < tmp.length; i++){
				if(!this.st.contains(tmp[i]))
					st.put(tmp[i], st.size());
			}

		}
		StdOut.println("Reading have done! " + filename);
		this.keys = new String[st.size()];
		for(String name : st.keys()){
			//StdOut.println(name);
			this.keys[st.get(name)] = name;
		}
		this.graph = new Dir_Graph(st.size());
		in = new In(filename);
		while(in.hasNextLine()){
			String [] tmp = in.readLine().split(delm);
			int v = this.st.get(tmp[0]);
			
			for(int i = 1; i < tmp.length; i++){
				int w = this.st.get(tmp[i]);
				this.graph.addEdge(v, w);
			}

		}
		StdOut.println("Second reading have done! " );
		
	}
	
	public boolean contains(String key){
		return st.contains(key) ;
	}

	
	public int key_idx(String key){
		return st.get(key);
	}

	public String vertex_name(int v){
		return this.keys[v] ;

	}
	public Dir_Graph graph(){
		return this.graph;

	}
	
	public static void main(String args[]){
		String filename = args[0];
		String delm		= args[1];
		Sym_Dir_Graph sg = new Sym_Dir_Graph(filename, delm);
		Dir_Graph g = sg.graph();
		while(StdIn.hasNextLine()){
			String src = StdIn.readLine();
			if(sg.contains(src)){
				int s = sg.key_idx(src);
				for(int v : g.adj(s))
					StdOut.println("   " + sg.vertex_name(v));
			}
			else
				StdOut.println("input not contain '" + src + "'");

		}


    }
}
		


