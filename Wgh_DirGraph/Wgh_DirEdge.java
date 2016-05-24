import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class Wgh_DirEdge implements Comparable<Wgh_DirEdge>{

	private final int from;
	private final int to;
	private final double wgh;
	public Wgh_DirEdge(int from, int to, double wgh){
		this.from = from;
		this.to = to;
		this.wgh = wgh;
		
		
	}
	
	public double weight(){

		return this.wgh;

	}
	
	public int from(){
		return this.from;

	}

	public int to(){
		return this.to;
	}

	
	
	@Override
	public int compareTo(Wgh_DirEdge that){
		if(this.wgh < that.wgh)
			return -1;
		else if(this.wgh > that.wgh)
			return +1;
		else
			return 0;
	}

	public String toString() {
        return String.format("%d-->%d : %.2f", this.from, this.to, this.wgh);
    }
	

	public static void main(String args[]){
		Wgh_DirEdge e = new Wgh_DirEdge(12, 34, 5.67);
        StdOut.println(e);
	
    }
}
		


