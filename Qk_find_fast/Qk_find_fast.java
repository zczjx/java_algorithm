import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;




public class Qk_find_fast{
	private int id_map[];
	private int cnt;
	public Qk_find_fast(int nr_point){
		if(nr_point < 0)
			throw new NegativeArraySizeException("invalidate nr_point value");
		this.cnt = nr_point;
		id_map = new int[this.cnt];
		for(int i = 0; i < this.cnt; i++)
			id_map[i] = i;
	}

	public int count(){
		return this.cnt;

	}

	public int find(int p){
		if((p < 0) || (p >= cnt))
			throw new IndexOutOfBoundsException("invalidate P value");
		return this.id_map[p];

	}
	public boolean is_connected(int p, int q){

		if((p < 0) || (p >= cnt))
			throw new IndexOutOfBoundsException("invalidate P value");
		if((q < 0) || (q >= cnt))
			throw new IndexOutOfBoundsException("invalidate q value");
		
		return (this.find(p) == this.find(q));
	}

	public void mk_union(int p, int q){
		if((p < 0) || (p >= cnt))
			throw new IndexOutOfBoundsException("invalidate P value");
		if((q < 0) || (q >= cnt))
			throw new IndexOutOfBoundsException("invalidate q value");
		int p_id = this.find(p);
		int q_id = this.find(q);
		if(p_id == q_id)
			return;

		for(int i = 0; i < id_map.length; i++)
			if(id_map[i] == p_id)
				id_map[i] = q_id;
		this.cnt-- ;
	}
	public static void main(String args[]){
		int N = Integer.parseInt(args[0]);
		Qk_find_fast test = new Qk_find_fast(N);
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


