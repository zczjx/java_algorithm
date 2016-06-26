import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.DirectedDFS;
import edu.princeton.cs.algs4.Digraph;

import java.util.NoSuchElementException;



public class Reg_NFA
{
	private char[] reg_buf;
	private Digraph G_NFA;
	private int M;
	
	public Reg_NFA(String regular){
		Stack_by_list<Integer> ops = new Stack_by_list<Integer>();
		reg_buf = regular.toCharArray();
		M = reg_buf.length;
		G_NFA = new Digraph(M + 1);
		for(int i =0; i < M; i++){
			int lp = i;
			if(reg_buf[i] == '(' || reg_buf[i] == '|')
				ops.push(i);
			else if(reg_buf[i] == ')'){
				int or = ops.pop();
				if(reg_buf[or] == '|'){
					lp = ops.pop();
					G_NFA.addEdge(lp, or+1);
					G_NFA.addEdge(or, i);
				}
				else
					lp = or;
			}

			if(i < M-1 && reg_buf[i+1] == '*'){
				// have close package
				G_NFA.addEdge(lp, i+1);
				G_NFA.addEdge(i+1, lp);
			}

			if(reg_buf[i] == '(' || reg_buf[i] == '*' || reg_buf[i] ==')')
				G_NFA.addEdge(i, i+1);
		}
		StdOut.println(G_NFA);
	}

	public boolean recognizes(String txt){
		DirectedDFS dfs = new DirectedDFS(G_NFA, 0);
		Bag<Integer> pc = new Bag<Integer>();
		for(int v = 0; v < G_NFA.V(); v++)
			if(dfs.marked(v)){
				StdOut.print(v + "--> ");
				pc.add(v);
			}
			StdOut.println(" ");

		for(int i = 0; i < txt.length(); i++){
			Bag<Integer> match = new Bag<Integer>();
			for(int v : pc){
				if(v == M)
					continue;
				if(reg_buf[v] == txt.charAt(i) || reg_buf[v] == '.')
					match.add(v+1);
			}
			dfs = new DirectedDFS(G_NFA, match);
			pc = new Bag<Integer>();
			for(int v = 0; v < G_NFA.V(); v++)
				if(dfs.marked(v))
					pc.add(v);

		}
		for(int v : pc)
			if(v == M)
				return true;
		return false;
	}
	
	
	public static void main(String args[]){
	 String regexp = "(" + args[0] + ")";
	 String txt = args[1];
	 Reg_NFA nfa = new Reg_NFA(regexp);
	 StdOut.println(nfa.recognizes(txt));
	 StdOut.println(txt.matches(regexp));
		
	}
}

