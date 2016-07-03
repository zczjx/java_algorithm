import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;

public class Flow_EG {
	private final int v;
	private final int w;
	private final double cap;
	private double flow;
	
	public Flow_EG(int v, int w, double cap){
		this.v = v;
		this.w = w;
		this.cap = cap;
		this.flow = 0.0;
		
	}
	
	public int from(){
		return this.v;

	}

	public int to(){
		return this.w;

	}

	public int other(int vex){
		if(vex == this.v)
			return this.w;
		else if(vex == this.w)
			return this.v;
		else
			return -1;
	}

	
	public double capacity(){
		return this.cap;

	}

	public double flow(){
		return this.flow;

	}

	public double spare_capacity(int v){
		if(v == this.v)
			return flow;
		else if(v == this.w)
			return cap - flow;
		else
			throw new RuntimeException("invalid param!");
	}

	public void add_flow(int v, double delta){
		if(v == this.v)
			this.flow -= delta;
		else if(v == this.w)
			this.flow += delta;

	}
	

	public String toString(){
		String s = "";
		s = s + this.v + "-->" + this.w + " " + cap + " : " + flow;
		return s;
	}

	public static void main(String[] args) {
        Flow_EG eg = new Flow_EG(Integer.parseInt(args[0]), Integer.parseInt(args[1]),
								Double.parseDouble(args[2]));
		eg.add_flow(Integer.parseInt(args[0]), 1.0);
		StdOut.println(eg);
    }
	
}
		


