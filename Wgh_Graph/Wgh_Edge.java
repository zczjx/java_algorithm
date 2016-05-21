import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class Wgh_Edge implements Comparable<Wgh_Edge>{

	private final int v;
	private final int w;
	private final double wgh;
	public Wgh_Edge(int v, int w, double wgh){
		this.v = v;
		this.w = w;
		this.wgh = wgh;
		
		
	}
	
	public double weight(){

		return this.wgh;

	}
	
	public int either(){
		return this.v;

	}

	public int other(int v){
		if(v == this.v)
			return this.w;

		else if(v == this.w)
			return this.v;

		else
			throw new RuntimeException("no this vertex");
	}

	
	
	@Override
	public int compareTo(Wgh_Edge that){
		if(this.wgh < that.wgh)
			return -1;
		else if(this.wgh > that.wgh)
			return +1;
		else
			return 0;
	}

	public String toString() {
        return String.format("%d-%d %.2f", this.v, this.w, this.wgh);
    }
	

	public static void main(String args[]){
		Wgh_Edge e = new Wgh_Edge(12, 34, 5.67);
        StdOut.println(e);
	
    }
}
		


