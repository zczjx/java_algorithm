import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class CubeSum implements Comparable<CubeSum>{
	private final int sum;
	private final int i , j;
	public CubeSum(int i, int j){
		this.sum = i*i*i + j*j*j;
		this.i = i;
		this.j = j;
	}
	public int	compareTo(CubeSum o){
		if(this.sum < o.sum)
			return -1;
		if(this.sum > o.sum)
			return 1;
		return 0;

	}
	@Override
	public String	toString(){
		return (this.sum + " = " + this.i + "^3" + " + " + this.j + "^3");

	}	
	public static void main(String args[]){
		int N = Integer.parseInt(args[0]);
		My_max_pq<CubeSum>pq = new My_max_pq<CubeSum>(N * 4);
		for(int i = 0; i < N; i++)
			pq.insertion(new CubeSum(i, i));
		while(!pq.isEmpty()){
			CubeSum tmp = pq.delmax();
			StdOut.println(tmp);
			if(tmp.j < N)
				pq.insertion(new CubeSum(tmp.i, tmp.j + 1));
		}

	}
		
}


