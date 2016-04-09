import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class My_max_idx_pq<T_item>{
	private int[] pq;
	private int[] qp;
	private T_item[] keys;
	private int nr_cnt;
	
	public My_max_idx_pq(){
		
	}
	public My_max_idx_pq(int max){
		keys = (T_item[]) new Comparable[max + 1];
		pq	 = new int[max + 1];
		qp 	 = new int[max + 1];
		for(int i = 0; i <= max; i++)
			qp[i] = -1;
		nr_cnt = 0;
	}

	public boolean contains(int k){
		return (qp[k] != -1);
	}
	public void insertion(int k, T_item it){
		++nr_cnt;
		pq[nr_cnt] 	 = k;
		keys[k]		 = it;
		qp[k]	 	 = nr_cnt;
		swim(nr_cnt);

	}
	public T_item min(){
		return (pq[nr_cnt - 1] < pq[nr_cnt])? keys[pq[nr_cnt - 1]] : keys[pq[nr_cnt]] ;

	}

	public T_item delmax(){
		int idxofmax = pq[1];
		T_item tmp = keys[idxofmax];
		exch(1, nr_cnt--);
		sink(1);
		qp[pq[nr_cnt + 1]]	 = -1;
		keys[pq[nr_cnt + 1]] = null;
		return tmp ;
	}

	public T_item max(){
		return keys[pq[1]];
		
	}
	public T_item key(int k){
		return keys[k];
		
	}
	public int size(){
		return nr_cnt;
	}
	public boolean isEmpty(){
		return (nr_cnt == 0);
	}

	public boolean isFull(){
		return (nr_cnt == pq.length);
	}

	public boolean isMaxHeap(){
		return isMaxHeap(1);	
	}

	public boolean isMaxHeap(int k){

		if(k > nr_cnt)
			return true;
		int left = k * 2, right = 2*k + 1;
		if((left <= nr_cnt) && less(k, left))
			return false;
		if((right <= nr_cnt) && less(k, right))
			return false;
		return isMaxHeap(left) && isMaxHeap(right);
		
	}
	private boolean less(int i, int j){
		return pq[i] < pq[j];


	}
	private void exch(int i, int j) {
		int swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
		
    }

	private void swim(int k) {
		while((k > 1) && (less(k/2, k))){

			exch(k/2, k);
			k = k/2;
		}
		
    }

	private void sink(int k) {

		while((2 * k) <= nr_cnt){
			int j = 2 *k;
			if(j < nr_cnt && less(j, j+1))
				j++;
			if(!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
		
    }
	
	public static void main(String args[]){
		My_max_idx_pq<Character> foo;
		int i = 1;
		foo = new My_max_idx_pq<Character>(256);
		while(!StdIn.isEmpty()){
			Character tmp = StdIn.readChar();
			if(!tmp.equals('*') && !tmp.equals(' ') ){
				foo.insertion(i, tmp);
				i++;
			}
		}
		StdOut.println("key is:");
		StdOut.println(" ");
		for(int k = 1; k <= foo.nr_cnt; k++ ){
			Character t = foo.key(k);
			StdOut.print(t + " ");
		}
		StdOut.println(" ");
		StdOut.println("pq is :");
		StdOut.println(" ");
		for(int k = 1; k <= foo.nr_cnt; k++ ){
			StdOut.print(foo.pq[k] + " ");
		}
		StdOut.println(" ");
		StdOut.println("qp is :");
		StdOut.println(" ");

		for(int k = 1; k <= foo.nr_cnt; k++ ){
			StdOut.print(foo.qp[k] + " ");
		}
		StdOut.println(" ");
		StdOut.println("del max queue");
		StdOut.println(" ");
		while(!foo.isEmpty())
			StdOut.print((Character) foo.delmax() + " ");
		StdOut.println(" ");
				
	}	
}


