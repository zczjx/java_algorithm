import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/*
  p1 --> p2 --> p3 --> p4 -->q
*/


public class Qk_union_fast {
	private int parent_map[];
	private int cnt;
	public Qk_union_fast (int nr_point){
		if(nr_point < 0)
			throw new NegativeArraySizeException("invalidate nr_point value");
		this.cnt = nr_point;
		parent_map = new int[this.cnt];
		for(int i = 0; i < this.cnt; i++)
			parent_map[i] = i;
	}

	public int count(){
		return this.cnt;

	}

	public int find(int p){
		if((p < 0) || (p >= parent_map.length))
			throw new IndexOutOfBoundsException("invalidate P value");
		while(p != this.parent_map[p])
			p = this.parent_map[p];
		return p;

	}
	public boolean is_connected(int p, int q){

		if((p < 0) || (p >= parent_map.length))
			throw new IndexOutOfBoundsException("invalidate P value");
		if((q < 0) || (q >= parent_map.length))
			throw new IndexOutOfBoundsException("invalidate q value");
		
		return (this.find(p) == this.find(q));
	}

	public void mk_union(int p, int q){
		if((p < 0) || (p >= parent_map.length))
			throw new IndexOutOfBoundsException("invalidate P value");
		if((q < 0) || (q >= parent_map.length))
			throw new IndexOutOfBoundsException("invalidate q value");
		int p_id = this.find(p);
		int q_id = this.find(q);
		if(p_id == q_id)
			return;
		parent_map[p_id] = q_id;
		this.cnt-- ;
	}
	public static void main(String args[]){
		int N = Integer.parseInt(args[0]);
		Qk_union_fast  test = new Qk_union_fast (N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if(test.is_connected(p, q))
				continue;
			test.mk_union(p, q);
			StdOut.println("( " + p + ", " + q + " )");
		}

		StdOut.println(test.count() + " connected region!");
		
	}
}


