import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.*;

public class My_set<Key extends Comparable<Key>>
implements Iterable<Key>
{
	private TreeSet<Key> set;


	public My_set(){
		this.set = new TreeSet<Key>();
	}
	public My_set(My_set<Key> x){
		this.set = new TreeSet<Key>(x.set);
	}

	public void add(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		this.set.add(k);
	}
	
	public boolean contains(Key k) {
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		return this.set.contains(k);
    }

	public void delete(Key k){
		if(k == null)
			throw new NullPointerException("argument to contains() is null");
		this.set.remove(k);
	}

	public int size(){
		return this.set.size();
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public Iterator<Key> iterator() {
        return this.set.iterator();
    }

	public Key max(){
		if(isEmpty())
			throw new NoSuchElementException("no this elem");
		return this.set.last();

	}

	public Key min(){
		if(isEmpty())
			throw new NoSuchElementException("no this elem");
		return this.set.first();
	}

	@Override
	public boolean equals(Object that){
		if(that == this)
			return true;
		if(that == null)
			return false;
		if(that.getClass() != this.getClass())
			return false;
		My_set other = (My_set) that;
		return this.set.equals(other.set);
	}

	public String	toString(){
		StringBuilder s = new StringBuilder();
		for(Key k : this)
			s.append(k + " ");

		return s.toString();
	}
	
	public static void main(String args[]){
		My_set<String> set = new My_set<String>();

        // insert some keys
        set.add("www.cs.princeton.edu");
        set.add("www.cs.princeton.edu");    // overwrite old value
        set.add("www.princeton.edu");
        set.add("www.math.princeton.edu");
        set.add("www.yale.edu");
        set.add("www.amazon.com");
        set.add("www.simpsons.com");
        set.add("www.stanford.edu");
        set.add("www.google.com");
        set.add("www.ibm.com");
        set.add("www.apple.com");
        set.add("www.slashdot.com");
        set.add("www.whitehouse.gov");
        set.add("www.espn.com");
        set.add("www.snopes.com");
        set.add("www.movies.com");
        set.add("www.cnn.com");
        set.add("www.iitb.ac.in");


        StdOut.println(set.contains("www.cs.princeton.edu"));
        StdOut.println(!set.contains("www.harvardsucks.com"));
        StdOut.println(set.contains("www.simpsons.com"));
        StdOut.println();

    
        StdOut.println();


        // print out all keys in this set in lexicographic order
        for (String s : set) {
            StdOut.println(s);
        }

        StdOut.println();
        My_set<String> set2 = new My_set<String>(set);
        StdOut.println(set.equals(set2));
		
	}
		
}


