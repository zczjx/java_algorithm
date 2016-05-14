import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Bacon_histogram{

	public static void main(String args[]){
		String filename = args[0];
		String delm		= args[1];
		
		StdOut.println(args[2]);
		String src		= args[2];
		Sym_Graph sg = new Sym_Graph(filename, delm);
		Graph_base g = sg.graph();
		if(!sg.contains(src)){
			StdOut.println("no data base!");
			return;
		}

		int start = sg.key_idx(src);
		Path_Bfs bfs = new Path_Bfs(g, start);

		//stastic data
		int MAX_BACON = 100;
		int [] hist = new int[MAX_BACON + 1];
		for(int v = 0; v < g.vertex(); v++){
			int bacon = Math.min(MAX_BACON, bfs.distTo(v));
			hist[bacon]++;
			
			if((bacon / 2) >= 7 && (bacon < MAX_BACON))
					StdOut.printf("%d %s\n", (bacon / 2), sg.vertex_name(v));
		}
		for(int i = 0; i < MAX_BACON; i += 2){
			if(hist[i] == 0)
				break;
			StdOut.printf("%d %s\n", i / 2, hist[i]);
		}
		 StdOut.printf("Inf %8d\n", hist[MAX_BACON]);

	}



}







