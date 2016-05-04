import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;
import java.util.NoSuchElementException;



class Hash_arr<Key extends Comparable<Key>, >{

	private static final int INIT_CAP = 16;
	private int nr;
	private int cap;
	private Key[] keys; 
	private Val[] vals; 

	public Hash_arr(){
		this(INIT_CAP);
	}

	public Hash_arr(int init_cap){
		this.cap = init_cap;
		this.nr  = 0;
		this.keys = (Key[]) new Comparable[init_cap];
		this.vals = (Val[]) new Object[init_cap];
	}

	private void resize(int new_sz){
		Hash_arr<Key, Val> tmp = new Hash_arr<Key, Val>(new_sz);
		for(int i = 0; i < this.cap; i++){
			if(this.keys[i] != null)
				tmp.put(keys[i], this.get(keys[i]));
		}
		
		this.cap = tmp.cap;
		this.nr  = tmp.nr;
		this.keys = tmp.keys;
		this.vals = tmp.vals;
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
		if(!contains(k))
			return;
		int i = this.hash(k);
		while(!(keys[i].compareTo(k) == 0 )){
			i = (i + 1) % this.cap;
		}
		keys[i] = null;
		vals[i] = null;
		i = (i + 1) % this.cap;
		// mv for continous
		while(keys[i] != null){
			Key k_to_mv = keys[i];
			Val v_to_mv = vals[i];
			keys[i] = null;
			vals[i] = null;
			this.nr--;
			put(k_to_mv, v_to_mv);
			i = (i + 1) % this.cap;
		}

		if((this.cap >= INIT_CAP) 
			&& (this.nr > 0) 
			&& (this.nr <= this.cap/8))
			resize(this.cap/2);
	}

	public boolean contains(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return get(k) != null;

	}
	public Val get(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		for(int i = this.hash(k); keys[i] != null; i = (i + 1) % this.cap){
			if(keys[i].compareTo(k) == 0)
				return vals[i];

		}
		return null;
	}

	public void put(Key k, Val v){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		if(v == null){
			delete(k);
			return ;
		}

		if(this.nr >= (this.cap / 2))
			this.resize(this.cap * 2);
			int i;
		for(i = this.hash(k); keys[i] != null; i = (i + 1) % this.cap){
			// find a null pos for new key
			if(keys[i].compareTo(k) == 0){
				this.vals[i] = v;
				return;
			}
		}
		this.keys[i] = k;
		this.vals[i] = v;
		this.nr++;
	}
	public boolean isEmpty(){
		return this.size() == 0;

	}
	public Iterable<Key> keys(){
		Var_queue<Key> foo = new Var_queue<Key>();
		for(int i = 0; i < this.cap; i++){
			if(keys[i] != null)
				foo.enqueue(keys[i]);
		}
		return foo;
	}


	public static void main(String args[]){
		Hash_arr<String, Integer> bar = new Hash_arr<String, Integer>();


		for (int i = 0; !StdIn.isEmpty(); i++) {
            String k = StdIn.readString();
            bar.put(k, i);
        }

        // print keys
        for (String s : bar.keys()) 
            StdOut.println(s + " " + bar.get(s)); 

    }
}
		

public class S_Vector{

	private Hash_arr<Integer, Double> st;
	private int dm;

	public S_Vector(int d){


	}

	public void put(int i, double v){


	}

	public double get(int i){


	}

	public int nonzero_nr(){


	}

	public int size(){


	}

	public int dimentsion(){


	}

	public S_Vector plus(S_Vector that){


	}

	public S_Vector multiply (double a){


	}
	public double dot(double[] that){


	}

	public double dot(S_Vector that){


	}

	public String toString(){


	}

	public static void main(String args[]){
		


	}
}

