import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Sym_tlb_arr<Key extends Comparable<Key>, Val> implements Iterable<Key>
{
	private Key[] k_arr;
	private Val[] v_arr;
	private int nr_cnt;

	public Sym_tlb_arr(){
		
	}
	public Sym_tlb_arr(int max){
		this.k_arr = (Key[])  new Comparable[max];
		this.v_arr = (Val[])  new Object[max];
		this.nr_cnt = 0;
	}


	public void put(Key k, Val v){
		if (k == null) throw new NullPointerException("first argument to put() is null"); 

        if (v == null) {
            delete(k);
            return;
        }
		
		int i = rank(k);
		if((i < this.nr_cnt) && (k_arr[i].compareTo(k) == 0)){
			v_arr[i] = v;
			return;
		}
		for(int j = this.nr_cnt; j > i; j--){
				k_arr[j] = k_arr[j - 1];
				v_arr[j] = v_arr[j - 1];
		}
		k_arr[i] = k;
		v_arr[i] = v;
		this.nr_cnt++;
	}
	
	public Val get(Key k){
		if(isEmpty())
			return null;
		int i = rank(k);
		if((i < this.nr_cnt) && (k_arr[i].compareTo(k) == 0))
			return v_arr[i];
		else
			return null;


	}

	public void delete(Key k){
		if (k == null) throw new NullPointerException("argument to delete() is null"); 
        if (isEmpty()) return;
		int i = rank(k);
		if((i < this.nr_cnt) && (k_arr[i].compareTo(k) == 0)){
			if(i == 0){
				k_arr[this.nr_cnt] = null;
				v_arr[this.nr_cnt] = null;
				this.nr_cnt--;
				return;
			}
			for(int j = i; j < this.nr_cnt; j++){
				k_arr[j] = k_arr[j + 1];
				v_arr[j] = v_arr[j + 1];
			}
			k_arr[this.nr_cnt - 1] = null;
			v_arr[this.nr_cnt - 1] = null;
			this.nr_cnt--;
		}

		else
			return;

	}
	public void delmin(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		delete(min());
	}
	public void delmax(){
		if(isEmpty())
			throw new NoSuchElementException("under flow");
		delete(max());
	}
	public boolean contains(Key k){
		int i = rank(k);
		if((i < this.nr_cnt) && (k_arr[i].compareTo(k) == 0))
			return true;
		else
			return false;

	}
	public boolean isEmpty(){
		return this.nr_cnt == 0;

	}
	public int size(){
		return this.nr_cnt;

	}
	public Key min(){
		return k_arr[0];

	}

	public Key max(){
		return k_arr[this.nr_cnt - 1];

	}

	public int rank(Key k){
		int lo = 0, hi = this.nr_cnt - 1;
		while(lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = k.compareTo(k_arr[mid]);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public Key select(int k){
		if((k < 0) || (k >= this.nr_cnt))
			return null;
		return this.k_arr[k];
	}

	public Iterable<Key> keys(Key lo, Key hi){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(int i = rank(lo); i < rank(hi); i++)
			foo.enqueue(k_arr[i]);
		if(contains(hi))
			foo.enqueue(k_arr[rank(hi)]);
		return foo;



	}
	private class Sym_tlb_iterator implements Iterator<Key>{
		private int i = 0;

		public boolean	hasNext(){
			return (i < nr_cnt);
		}

		public Key	next(){
			if(hasNext())
				return k_arr[i++];
			return null;
		}

		public void remove(){

		}

	}

	public Iterator<Key> iterator(){
		return new Sym_tlb_iterator();
	}

	
	public static void main(String args[]){
		Sym_tlb_arr<String, Double> gpa = new Sym_tlb_arr<String, Double>(256);
		gpa.put("A",  4.00);
        gpa.put("B",  3.00);
        gpa.put("C",  2.00);
        gpa.put("D",  1.00);
        gpa.put("F",  0.00);
        gpa.put("A+", 4.33);
        gpa.put("B+", 3.33);
        gpa.put("C+", 2.33);
        gpa.put("A-", 3.67);
        gpa.put("B-", 2.67);
		StdOut.println("before del size is: " + gpa.size());
		while(!StdIn.isEmpty()){
			String g = StdIn.readString();
			StdOut.println(g + " : " + gpa.get(g));
		}
		
		for(Object s: gpa.keys("A", "C"))
			StdOut.println(s);
		
		gpa.delete("B+");
		gpa.delete("A+");
		gpa.delete("C+");


		while(!StdIn.isEmpty()){
			String g = StdIn.readString();
			StdOut.println(g + " : " + gpa.get(g));
		}
		
		for(Object s: gpa.keys("A", "C"))
			StdOut.println(s);

		StdOut.println("after del size is: " + gpa.size());
	
	}
		
}


