import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class My_max_pq<Key extends Comparable<Key>>{
	private Key[] pq;
	private int nr_cnt;
	
	public My_max_pq(){
		
	}
	public My_max_pq(int max){
		pq = (Key[])new Comparable[max + 1];
	}

	public void insertion(Key it){
		pq[++nr_cnt] = it;
		swim(nr_cnt);

	}
	public Key min(){
		return ((pq[nr_cnt - 1].compareTo(pq[nr_cnt]) < 0)? pq[nr_cnt - 1] : pq[nr_cnt] );

	}

	public Key delmax(){
		Key tmp = pq[1];
		exch(1, nr_cnt--);
		pq[nr_cnt + 1] = null;
		sink(1);
		return tmp ;
	}

	public Key max(){
		return pq[1];
		
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
		return pq[i].compareTo(pq[j]) < 0;


	}
	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;		
		
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
		My_max_pq<Character> foo;
		foo = new My_max_pq<Character>(256);
		while(!StdIn.isEmpty()){
			Character tmp = StdIn.readChar();
			if(!tmp.equals('*') && !tmp.equals(' ') )
				foo.insertion(tmp);
		}
		StdOut.println("min is " + (Character)foo.min());
		while(!foo.isEmpty())
			StdOut.println((Character) foo.delmax());
				
	}	
}


