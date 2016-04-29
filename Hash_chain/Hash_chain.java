import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;


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
		public SymNode(Key k, Val v, SymNode<Key, Val> next){
			this.k = k;
			this.v  = v;
			this.next = next;
		}
	}


class Sym_tlb_seq_ls<Key extends Comparable<Key>, Val> implements Iterable<Key>
{
	private SymNode<Key, Val> head;
	private int nr_cnt;
	
	public Sym_tlb_seq_ls(){
		this.head = null;
		this.nr_cnt = 0;
	}

	private SymNode<Key, Val> select(int n){
		SymNode<Key, Val> tmp = this.head;
		for(int j = 0 ; j < n; j++)
			tmp = tmp.next;
		return tmp;
	}
	public void put(Key k, Val v){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(v == null){
			delete(k);
			return ;
		}
		if(this.head == null){
			this.head = new SymNode(k, v);
			this.nr_cnt++;
			return;
		}
			
		SymNode<Key, Val> pre = this.head;
		for(SymNode<Key, Val> node = head; node != null; node = node.next){
				if(node.k.compareTo(k) == 0){
					node.v = v;
					return ;
				}
				pre = node;
		}
		SymNode<Key, Val> insert = new SymNode(k, v);
		insert.next = null;
		pre.next = insert;
		this.nr_cnt++;
	}
	
	public Val get(Key k){
		if(isEmpty())
			return null;
		for(SymNode<Key, Val> node = this.head; node != null; node = node.next){
				if(node.k.compareTo(k) == 0)
					return node.v;
		}
		return null;
	}

	public void delete(Key k){
		if(isEmpty())
			return ;
		SymNode<Key, Val> pre = this.head ;
		for(SymNode<Key, Val> node = head; node != null; node = node.next){
				if(node.k.compareTo(k) == 0){
					pre.next = node.next;
					node = null;
					this.nr_cnt--;
					return ;
				}
				pre = node;
		}
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
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return get(k) != null;

	}
	public boolean isEmpty(){
		return this.nr_cnt == 0;

	}
	public int size(){
		return this.nr_cnt;

	}
	public Key min(){
		return select(0).k;

	}

	public Key max(){
		return select(this.nr_cnt - 1).k;

	}

	public int rank(Key k){
		int lo = 0, hi = this.nr_cnt - 1;
		while(lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = k.compareTo(select(mid).k);
			if(cmp < 0)
				hi = mid - 1;
			else if(cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	public Iterable<Key> keys(){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(SymNode<Key, Val> node = head; node != null; node = node.next){
				foo.enqueue(node.k);
		}
		return foo;


	}
	private class Sym_tlb_iterator implements Iterator<Key>{
		private int i = 0;

		public boolean	hasNext(){
			return (i < nr_cnt);
		}

		public Key	next(){
			if(hasNext())
				return select(i++).k;
			return null;
		}

		public void remove(){

		}

	}

	public Iterator<Key> iterator(){
		return new Sym_tlb_iterator();
	}

}


public class Hash_chain<Key extends Comparable<Key>, Val>{

	private static final int INIT_CAP = 4;
	private int nr;
	private int cap;
	private Sym_tlb_seq_ls<Key, Val>[] hash_tlb; 

	public Hash_chain(){
		this(INIT_CAP);
	}

	public Hash_chain(int init_cap){
		this.cap = init_cap;
		hash_tlb = (Sym_tlb_seq_ls<Key, Val>[]) new Sym_tlb_seq_ls[init_cap];
		for(int i = 0; i < init_cap; i++)
			this.hash_tlb[i] = new Sym_tlb_seq_ls<Key, Val>();
	}

	private void resize(int new_sz){
		Hash_chain<Key, Val> tmp = new Hash_chain<Key, Val>(new_sz);
		for(int i = 0; i < this.cap; i++){
			for(Key k : this.hash_tlb[i].keys()){
				tmp.put(k, this.hash_tlb[i].get(k));
			}
		}
		this.cap = tmp.cap;
		this.nr  = tmp.nr;
		this.hash_tlb = tmp.hash_tlb;
	}

	private int hash(Key k){
		return (k.hashCode() & 0x7fffffff) % this.cap;
	}
	
	public int size(){
		return this.nr;

	}

	public void delete(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		int i = this.hash(k);
		if(this.hash_tlb[i].contains(k)){
			this.nr--;
			this.hash_tlb[i].delete(k);
		}
		if((this.nr <= 2 * this.cap) && this.cap > INIT_CAP)
			this.resize(this.cap / 2);
	}

	public boolean contains(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return get(k) != null;

	}
	public Val get(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		int i = this.hash(k);
		return this.hash_tlb[i].get(k);
	}

	public void put(Key k, Val v){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(v == null){
			delete(k);
			return ;
		}

		if(this.nr >= (this.cap * 2))
			this.resize(this.cap * 2);
		int i = this.hash(k);
		if(!this.hash_tlb[i].contains(k))
			this.nr++;
		this.hash_tlb[i].put(k, v);
	}
	public boolean isEmpty(){
		return this.size() == 0;

	}
	public Iterable<Key> keys(){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(int i = 0; i < this.cap; i++){
			for(Key k : this.hash_tlb[i].keys()){
				foo.enqueue(k);
			}
		}
		return foo;
	}


	public static void main(String args[]){
		Hash_chain<String, Integer> bar = new Hash_chain<String, Integer>();


		for (int i = 0; !StdIn.isEmpty(); i++) {
            String k = StdIn.readString();
            bar.put(k, i);
        }

        // print keys
        for (String s : bar.keys()) 
            StdOut.println(s + " " + bar.get(s)); 

    }
}
		


