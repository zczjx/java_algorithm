import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

class SymNode<Key extends Comparable<Key>, Val>{
		public Key k;
		public Val v;
		SymNode<Key, Val> next;

		public SymNode(){
			this.k = null;
			this.v = null;
			this.next = null;
		}
		public SymNode(Key k, Val v){
			this.k = k;
			this.v  = v;
			this.next = null;
		}
	}


public class Sym_tlb_seq_ls<Key extends Comparable<Key>, Val> implements Iterable<Key>
{
	private SymNode<Key, Val> head;
	private int nr_cnt;
	
	public Sym_tlb_seq_ls(){
		this.head = null;
		this.nr_cnt = 0;
	}

	private SymNode<Key, Val> nth_node_of_ls(int n){
		SymNode<Key, Val> tmp = this.head;
		for(int j = 0 ; j < n; j++)
			tmp = tmp.next;
		return tmp;
	}
	public void put(Key k, Val v){
		int i = rank(k);
		if((i < this.nr_cnt) && (nth_node_of_ls(i).k.compareTo(k) == 0)){
			SymNode<Key, Val> tmp = nth_node_of_ls(i);
			tmp.v = v;
			return;
		}
		if(i == 0){
			this.head = new SymNode(k, v);
			this.head.next = null;
			this.nr_cnt++;
			return;
		}
		SymNode<Key, Val> tmp = nth_node_of_ls(i - 1);
		SymNode<Key, Val> insert = new SymNode(k, v);
		insert.next = tmp.next;
		tmp.next = insert;
		this.nr_cnt++;
	}
	
	public Val get(Key k){
		if(isEmpty())
			return null;
		int i = rank(k);
		if((i < this.nr_cnt) && (nth_node_of_ls(i).k.compareTo(k) == 0)){
			SymNode<Key, Val> tmp = nth_node_of_ls(i);
			return tmp.v;
		}
		else
			return null;
	}

	public void delete(Key k){
		int i = rank(k);
		if((i < this.nr_cnt) && (nth_node_of_ls(i).k.compareTo(k) == 0)){
			if(i == 0){
				this.head = null;
				this.nr_cnt--;
				return;
			}
			SymNode<Key, Val> tmp = nth_node_of_ls(i - 1);
			SymNode<Key, Val> del = tmp.next;
			tmp.next = del.next;
			del = null;
			this.nr_cnt--;
			return;
		}
		else
			return;

	}
	
	public boolean contains(Key k){
		int i = rank(k);
		if((i < this.nr_cnt) && (nth_node_of_ls(i).k.compareTo(k) == 0))
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
		return nth_node_of_ls(0).k;

	}

	public Key max(){
		return nth_node_of_ls(this.nr_cnt - 1).k;

	}

	public int rank(Key k){
		int lo = 0, hi = this.nr_cnt - 1;
		while(lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = k.compareTo(nth_node_of_ls(mid).k);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public Iterable<Key> keys(Key lo, Key hi){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(int i = rank(lo); i < rank(hi); i++)
			foo.enqueue(nth_node_of_ls(i).k);
		if(contains(hi))
			foo.enqueue(nth_node_of_ls(rank(hi)).k);
		return foo;



	}
	private class Sym_tlb_iterator implements Iterator<Key>{
		private int i = 0;

		public boolean	hasNext(){
			return (i < nr_cnt);
		}

		public Key	next(){
			if(hasNext())
				return nth_node_of_ls(i++).k;
			return null;
		}

		public void remove(){

		}

	}

	public Iterator<Key> iterator(){
		return new Sym_tlb_iterator();
	}

	
	public static void main(String args[]){
		Sym_tlb_seq_ls<String, Double> gpa = new Sym_tlb_seq_ls<String, Double>();
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


