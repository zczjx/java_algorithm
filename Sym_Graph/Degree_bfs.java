import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Degree_bfs{
	public static void main(String args[]){
		String filename = args[0];
		String delm		= args[1];
		String src		= args[2];
		Sym_Graph sg = new Sym_Graph(filename, delm);
		Graph_base g = sg.graph();
		if(!sg.contains(src)){
			StdOut.println("no data base!");
			return;
		}

		int start = sg.key_idx(src);
		Path_Bfs bfs = new Path_Bfs(g, start);
		while(!StdIn.isEmpty()){
			String query = StdIn.readLine();
			if(sg.contains(query)){
				int dst = sg.key_idx(query);
				if(bfs.has_PathTo(dst)){
					for(int w : bfs.pathTo(dst))
						StdOut.println(" " + sg.vertex_name(w));
				}
				else
					StdOut.println("Not connected");
			}

			else
				StdOut.println("no data base!");
		}
	}



}







